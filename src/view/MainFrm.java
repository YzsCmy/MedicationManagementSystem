package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainFrm extends JFrame {

	private User currentUser;
	private JPanel contentPane;
	private JDesktopPane table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrm(User user) {
		setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		currentUser = user;
//		System.out.println(user);
		setTitle("医药信息管理主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 441);
		
		JMenuBar menuBar = new JMenuBar();
//		menuBar.setSize(500, 100);
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("基本数据维护");
		menu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/base.png")));
		menuBar.add(menu);
		
		JMenu menu_2 = new JMenu("顾客信息管理");
		menu_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menu_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/customers.png")));
		menu.add(menu_2);
		
		JMenuItem menuItem_1 = new JMenuItem("顾客信息添加");
		menuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientAddInterFrm caif=new ClientAddInterFrm(currentUser);
				caif.setVisible(true);
				table.add(caif);
			}
		});
		menuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		menu_2.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("顾客信息维护");
		menuItem_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientManageInterFrm cmif=new ClientManageInterFrm(currentUser);
				cmif.setVisible(true);
				table.add(cmif);
			}
		});
		menuItem_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		menu_2.add(menuItem_2);
		
		JMenu mnNewMenu = new JMenu("经办人信息管理");
		mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mnNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/agency.png")));
		menu.add(mnNewMenu);
		
		JMenuItem menuItem_3 = new JMenuItem("经办人信息添加");
		menuItem_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgencyAddInterFrm aaf=new AgencyAddInterFrm(currentUser);
				aaf.setVisible(true);
				table.add(aaf);
			}
		});
		menuItem_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		mnNewMenu.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("经办人信息维护");
		menuItem_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgencyManageInterFrm amf=new AgencyManageInterFrm(currentUser);
				amf.setVisible(true);
				table.add(amf);
			}
		});
		menuItem_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		mnNewMenu.add(menuItem_4);
		
		JMenuItem menuItem = new JMenuItem("安全退出");
		menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int exit=JOptionPane.showConfirmDialog(null, "是否退出系统");
				if(exit==0){
					dispose();
				}
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("药品信息管理");
		mnNewMenu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mnNewMenu_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/drugs.png")));
		menu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("药品信息添加");
		mntmNewMenuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedicineAddInterFrm maf=new MedicineAddInterFrm(currentUser);
				maf.setVisible(true);
				table.add(maf);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("药品信息维护");
		mntmNewMenuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedicineManageInterFrm mmf=new MedicineManageInterFrm(currentUser);
				mmf.setVisible(true);
				table.add(mmf);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_2 = new JMenu("用户信息管理");
		mnNewMenu_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mnNewMenu_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/administrat.png")));
		menu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("用户信息添加");
		mntmNewMenuItem_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAddInterFrm uaf=new UserAddInterFrm(currentUser);
				uaf.setVisible(true);
				table.add(uaf);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("用户信息维护");
		mntmNewMenuItem_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManageInterFrm umf=new UserManageInterFrm(currentUser);
				umf.setVisible(true);
				table.add(umf);
			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenuItem menuItem_7 = new JMenuItem("修改密码");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditUserPasswdFrm passwdFrm = new EditUserPasswdFrm(currentUser);
				passwdFrm.setVisible(true);
				table.add(passwdFrm);
				
			}
		});
		menuItem_7.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		menuItem_7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menu.add(menuItem_7);
		menuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/exit.png")));
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("数据报表");
		menu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menu_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/report.png")));
		menuBar.add(menu_1);
		
		JMenuItem mntmyzs = new JMenuItem("顾客信息报表");
		mntmyzs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!"1".equals(currentUser.getReport())){
					JOptionPane.showMessageDialog(null, "暂无权限！");
					return ;
				}
				ClientReport clientReport = new ClientReport(currentUser);
				clientReport.setVisible(true);
				table.add(clientReport);
			}
		});
		mntmyzs.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mntmyzs.setIcon(new ImageIcon(MainFrm.class.getResource("/images/customers.png")));
		menu_1.add(mntmyzs);
		
		JMenuItem menuItem_5 = new JMenuItem("经办人信息报表");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!"1".equals(currentUser.getReport())){
					JOptionPane.showMessageDialog(null, "暂无权限！");
					return ;
				}
				AgencyReport agencyReport = new AgencyReport(currentUser);
				agencyReport.setVisible(true);
				table.add(agencyReport);
			}
		});
		menuItem_5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/agency.png")));
		menu_1.add(menuItem_5);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("药品信息报表");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!"1".equals(currentUser.getReport())){
					JOptionPane.showMessageDialog(null, "暂无权限！");
					return ;
				}
				MedicineReport medicineReport = new MedicineReport(currentUser);
				medicineReport.setVisible(true);
				table.add(medicineReport);
			}
		});
		mntmNewMenuItem_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mntmNewMenuItem_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/drugs.png")));
		menu_1.add(mntmNewMenuItem_4);
		
		JMenu menu_3 = new JMenu(" 帮助");
		menu_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menu_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/help.png")));
		menuBar.add(menu_3);
		
		JMenuItem menuItem_6 = new JMenuItem("使用说明");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserGuideInterFrm guideInterFrm = new UserGuideInterFrm(currentUser);
				guideInterFrm.setVisible(true);
				table.add(guideInterFrm);
			}
		});
		menuItem_6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_6.setIcon(new ImageIcon(MainFrm.class.getResource("/images/emblem.png")));
		menu_3.add(menuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		table = new JDesktopPane();
		table.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_table = new GroupLayout(table);
		gl_table.setHorizontalGroup(
			gl_table.createParallelGroup(Alignment.LEADING)
				.addGap(0, 609, Short.MAX_VALUE)
		);
		gl_table.setVerticalGroup(
			gl_table.createParallelGroup(Alignment.LEADING)
				.addGap(0, 368, Short.MAX_VALUE)
		);
		table.setLayout(gl_table);
		contentPane.add(table, "name_552341586750910");
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		if(!currentUser.getUsername().equals("root")){
			mnNewMenu_2.setEnabled(false);
		}
	}
}
