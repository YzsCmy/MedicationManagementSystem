package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.AgencyDao;
import dao.ClientDao;
import dao.MedicineDao;
import model.Agency;
import model.Client;
import model.Medicine;
import model.User;
import sun.misc.Cleaner;
import utils.Dbutils;
import utils.StringUtils;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.Font;
import com.eltima.components.ui.DatePicker;
import java.awt.Dimension;
import java.util.Locale;

public class ClientReport extends JInternalFrame {
	private User currentUser;
	
	private JTable clientTable;

	private ClientDao clientDao=new ClientDao();
	private AgencyDao agencyDao=new AgencyDao();
	private MedicineDao medicineDao=new MedicineDao();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientReport frame = new ClientReport(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientReport(User u) {
		currentUser = u;
		setClosable(true);
		setIconifiable(true);
		setTitle("顾客报表");
		setBounds(100, 100, 815, 764);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button = new JButton("打印");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printReport(e);
			}
		});
		button.setIcon(new ImageIcon(ClientReport.class.getResource("/images/printer.png")));
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(355)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
					.addGap(202)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(87, Short.MAX_VALUE))
		);
		
		clientTable = new JTable();
		clientTable.setFont(new Font("宋体", Font.PLAIN, 19));
		clientTable.setRowHeight(25);
		
		clientTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u8BA2\u5355\u6570"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		//设置内容居中
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		clientTable.setDefaultRenderer(Object.class, cr);
		clientTable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 19));
		
		scrollPane.setViewportView(clientTable);
		getContentPane().setLayout(groupLayout);
		if("1".equals(currentUser.getReport())){
			fillTable();
		}

	}

	private void printReport(ActionEvent e) {
		if(!"1".equals(currentUser.getReport())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		try {
			clientTable.print();
		} catch (PrinterException e1) {
			e1.printStackTrace();
		}
		
	}
	private void fillTable(){
		DefaultTableModel dtm=(DefaultTableModel) clientTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=Dbutils.getCon();
			List<Client> rst=clientDao.findAllClient(con);
			String clients = clientDao.countClient(con);//总人数
			String orders = clientDao.countOrders(con);//总订单数
			for(Client a:rst) {
				String res = clientDao.getNumByClient(con, a.getCno());//每个客户订单数
				Vector<String> v=new Vector<String>();
				v.add(a.getCno());
				v.add(a.getCname());
				v.add(res);
				dtm.addRow(v);
			}
			dtm.addRow(new String[]{"客户总数： "+clients,"","订单总数："+orders});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
