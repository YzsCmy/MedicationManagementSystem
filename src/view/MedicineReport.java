package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
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
import dao.MedicineDao;
import model.Agency;
import model.Medicine;
import model.User;
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

public class MedicineReport extends JInternalFrame {
	private User currentUser;
	
	private JTable medicineTable;

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
					MedicineReport frame = new MedicineReport(null);
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
	public MedicineReport(User u) {
		currentUser = u;
		setClosable(true);
		setIconifiable(true);
		setTitle("药品管理");
		setBounds(100, 100, 815, 764);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button = new JButton("打印");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printReport(e);
			}
		});
		button.setIcon(new ImageIcon(MedicineReport.class.getResource("/images/printer.png")));
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
							.addGap(360)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(115)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(297)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		
		medicineTable = new JTable();
		medicineTable.setFont(new Font("宋体", Font.PLAIN, 19));
		medicineTable.setRowHeight(25);
		medicineTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u540D\u79F0", "\u9500\u91CF"
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
		medicineTable.setDefaultRenderer(Object.class, cr);
		medicineTable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 19));
		
		scrollPane.setViewportView(medicineTable);
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
			medicineTable.print();
		} catch (PrinterException e1) {
			e1.printStackTrace();
		}
		
	}
	
	private void fillTable(){
		DefaultTableModel dtm=(DefaultTableModel) medicineTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=Dbutils.getCon();
			List<Medicine> rst=medicineDao.findAll(con);
			String medicines = medicineDao.countMedicine(con);//药品种数
			String orders = medicineDao.countOrders(con);//总销量
			for(Medicine a:rst) {
				String res = medicineDao.getNumByMno(con, a.getMno());
				Vector<String> v=new Vector<String>();
				v.add(a.getMno());
				v.add(a.getMname());
				v.add(res);
				dtm.addRow(v);
			}
			dtm.addRow(new String[]{"药品种数： "+medicines,"","总销量："+orders});
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
