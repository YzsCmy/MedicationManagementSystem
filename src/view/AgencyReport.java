package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.util.List;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.AgencyDao;
import model.Agency;
import model.User;
import utils.Dbutils;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;

public class AgencyReport extends JInternalFrame {
	private User currentUser;
	
	private JTable agencyTable;

	private AgencyDao agencyDao=new AgencyDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgencyReport frame = new AgencyReport(null);
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
	public AgencyReport(User u) {
		currentUser = u;
		setClosable(true);
		setIconifiable(true);
		setTitle("经办人报表");
		setBounds(100, 100, 815, 764);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button = new JButton("打印");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printReport(e);
			}
		});
		button.setIcon(new ImageIcon(AgencyReport.class.getResource("/images/printer.png")));
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(333))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(55)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addGap(165)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		
		agencyTable = new JTable();
		agencyTable.setFont(new Font("宋体", Font.PLAIN, 19));
		agencyTable.setRowHeight(25);
		
		agencyTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u7ECF\u529E\u4EBA\u59D3\u540D", "\u7ECF\u529E\u6570"
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
		agencyTable.setDefaultRenderer(Object.class, cr);
		agencyTable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 19));
		
		agencyTable.getColumnModel().getColumn(1).setPreferredWidth(115);
		scrollPane.setViewportView(agencyTable);
		getContentPane().setLayout(groupLayout);
		
		if("1".equals(currentUser.getReport())){
			fillTable();
		}

	}
	
	private void printReport(ActionEvent e) {
		//判断当前用户权限
		if(!"1".equals(currentUser.getReport())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		try {
			agencyTable.print();//调用系统打印功能
		} catch (PrinterException e1) {
			e1.printStackTrace();
		}
		
	}
	private void fillTable(){
		//获取表格对象
		DefaultTableModel dtm=(DefaultTableModel) agencyTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=Dbutils.getCon();//获取数据库连接
			List<Agency> rst=agencyDao.findAll(con);//查询所有经办人信息
			int total = agencyDao.getTotal(con);//得到总代理数
			int totalAgency = agencyDao.getTotalAgency(con);//得到总人数
			for(Agency a:rst) {
				//按经办人编号查询该经办人的经办总数
				int agentNum = agencyDao.getNumByAgency(con, a.getAno());
				Vector<String> v=new Vector<String>();
				v.add(a.getAno());
				v.add(a.getAname());
				v.add(agentNum+"");
				dtm.addRow(v);//表格填充数据
			}
			dtm.addRow(new String[]{"总人数： "+totalAgency,"","经办总数："+total});
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
