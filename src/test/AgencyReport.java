package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.TableUI;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.AgencyDao;
import model.Agency;
import model.User;
import utils.Dbutils;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

public class AgencyReport extends JFrame {
	private AgencyDao agencyDao = new AgencyDao();
	private JTable agencyTable;
	private JButton button;
	private JScrollPane scrollPane;

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

	public AgencyReport(User u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setClosable(true);
//		setIconifiable(true);
		setTitle("经办人报表");
		setBounds(100, 100, 815, 764);
		
		scrollPane = new JScrollPane();
		
		button = new JButton("打印");
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
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(343))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(55)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(50, Short.MAX_VALUE))
		);
		
		agencyTable = new JTable();
		agencyTable.setFont(new Font("宋体", Font.PLAIN, 17));
		agencyTable.setRowHeight(HEIGHT*10);
		
		agencyTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u7ECF\u529E\u6570"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(agencyTable);
		getContentPane().setLayout(groupLayout);
		fillTable();
	}

	private void printReport(ActionEvent e) {
		
		
//		scrollPane.printAll(getGraphics());
		try {
			agencyTable.print();
		} catch (PrinterException e1) {
			e1.printStackTrace();
		}
		
	}
	private void fillTable(){
		DefaultTableModel dtm=(DefaultTableModel) agencyTable.getModel();
		agencyTable.setLayout(new BorderLayout());
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=Dbutils.getCon();
			List<Agency> rst=agencyDao.findAll(con);
			int total = agencyDao.getTotal(con);//总代理数
			int totalAgency = agencyDao.getTotalAgency(con);//总人数
			for(Agency a:rst) {
				int agentNum = agencyDao.getNumByAgency(con, a.getAno());
				Vector<String> v=new Vector<String>();
				v.add(a.getAno());
				v.add(a.getAname());
				v.add(agentNum+"");
				dtm.addRow(v);
			}
//			System.out.println(dtm.getRowCount());
//			dtm.setColumnIdentifiers(new String[]{"总人数：" +totalAgency+",经办总数："+total});
			dtm.addRow(new String[]{"       总人数： "+totalAgency,"","        经办总数："+total});
//			System.out.println(dtm.getRowCount());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
