package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.table.DefaultTableModel;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;

import utils.Dbutils;
import utils.StringUtils;

import dao.UserDao;
import model.User;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.jws.soap.SOAPBinding.Use;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Font;

public class UserManageInterFrm extends JInternalFrame {
	private User currentUser;
	
	private JTable userTable;
	private JTextField userNameTxt;

	private UserDao userDao=new UserDao();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField editUserNameTxt;
	private JPasswordField editPasswordTxt;
	
	private JCheckBox queryCheckBox;
	private JCheckBox browserCheckBox;
	private JCheckBox updateCheckBox;
	private JCheckBox reportCheckBox;
	private JTextField idTxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManageInterFrm frame = new UserManageInterFrm(null);
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
	public UserManageInterFrm(User u) {
		currentUser = u;
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 17));
		setClosable(true);
		setIconifiable(true);
		setTitle("用户管理");
		setBounds(100, 100, 730, 714);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 685, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton button_1 = new JButton("修改");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userUpdateActionPerform(e);
			}
		});
		button_1.setIcon(new ImageIcon(UserManageInterFrm.class.getResource("/images/modify.png")));
		
		JButton button_2 = new JButton("删除");
		button_2.setFont(new Font("宋体", Font.PLAIN, 17));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDelActioPerformed(e);
			}
		});
		button_2.setIcon(new ImageIcon(UserManageInterFrm.class.getResource("/images/delete.png")));
		
		JLabel label = new JLabel("用户名：");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editUserNameTxt = new JTextField();
		editUserNameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		editUserNameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("密  码：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editPasswordTxt = new JPasswordField();
		editPasswordTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel label_2 = new JLabel("设置权限：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		
		queryCheckBox = new JCheckBox("查询");
		queryCheckBox.setFont(new Font("宋体", Font.PLAIN, 17));
		
		browserCheckBox = new JCheckBox("浏览");
		browserCheckBox.setFont(new Font("宋体", Font.PLAIN, 17));
		
		updateCheckBox = new JCheckBox("更新");
		updateCheckBox.setFont(new Font("宋体", Font.PLAIN, 17));
		
		reportCheckBox = new JCheckBox("报表");
		reportCheckBox.setFont(new Font("宋体", Font.PLAIN, 17));
		
		idTxt = new JTextField();
		idTxt.setVisible(false);
		idTxt.setColumns(10);
		idTxt.setText(currentUser.getId().toString());
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
									.addComponent(editPasswordTxt, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
									.addGap(40)
									.addComponent(editUserNameTxt, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
							.addGap(35))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(queryCheckBox, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
									.addComponent(browserCheckBox, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(updateCheckBox, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addGap(4))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(button_1)
									.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
									.addComponent(button_2)
									.addGap(103)))))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(reportCheckBox, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(236, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(editUserNameTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(label)))
					.addGap(37)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(editPasswordTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
									.addComponent(reportCheckBox)
									.addComponent(updateCheckBox)
									.addComponent(browserCheckBox))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(4)
									.addComponent(label_2)))
							.addGap(47)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1)
								.addComponent(button_2)))
						.addComponent(queryCheckBox))
					.addGap(34))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("用户名称：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		userNameTxt.setColumns(10);
		
		JButton button = new JButton("查询");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userSearchActioPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(UserManageInterFrm.class.getResource("/images/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(13)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userNameTxt, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(button)
					.addGap(6))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		userTable = new JTable();
		userTable.setFont(new Font("宋体", Font.PLAIN, 17));
		userTable.setRowHeight(25);
		userTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				userTableMouseEvent(e);
			}
		});
		userTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237\u7F16\u53F7", "\u7528\u6237\u540D\u79F0", "\u7528\u6237\u5BC6\u7801", "\u67E5\u8BE2\u6743\u9650", "\u66F4\u65B0\u6743\u9650", "\u6D4F\u89C8\u6743\u9650", "\u62A5\u8868\u6743\u9650"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(userTable);
		getContentPane().setLayout(groupLayout);
		if("1".equals(currentUser.getBrowser())){
			fillTable("");
		}

	}
	private void userDelActioPerformed(ActionEvent e) {
		if(!"1".equals(currentUser.getUpdate())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		int[] nums=userTable.getSelectedRows();//得到选中的数据行
		if(nums.length==0){
			JOptionPane.showMessageDialog(null, "请选择要删除的数据！");
			return ;
		}
		int confNum=JOptionPane.showConfirmDialog(null, "您确定要删除吗？");
		if(confNum==0){
			Connection con=null;
			con=Dbutils.getCon();//获取数据库连接
			try {
				con.setAutoCommit(false);//开启事务
				for(int num:nums){//遍历删除所选行
					String id=(String) userTable.getValueAt(num, 0);
					userDao.delete(con, id);//操作数据库，执行相应sql，完成删除 
					
				}
				con.commit();//事务提交
			} catch (Exception e1) {
				try {
					con.rollback();//若删除失败，回滚事务
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "删除失败！");
				throw new RuntimeException(e1);
			}finally {
				try {
					Dbutils.closeCon(con);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			JOptionPane.showMessageDialog(null, "删除成功！");
			resetVlaue();
			//重新填充表格
			if(!"1".equals(currentUser.getBrowser())){
				userTable.removeAll();
			}else{
				fillTable("");
			}
		}
	}

	private void userUpdateActionPerform(ActionEvent evt) {
		if(!"1".equals(currentUser.getUpdate())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		if(StringUtils.isEmpty(idTxt.getText())){
			JOptionPane.showMessageDialog(null, "请选择要修改的用户信息！");
			return ;
		}
		//获取数据
		String username=editUserNameTxt.getText();
		String pwd=new String(editPasswordTxt.getPassword());
		String query = queryCheckBox.isSelected()?"1":"0";
		String update = updateCheckBox.isSelected()?"1":"0";
		String browser = browserCheckBox.isSelected()?"1":"0";
		String report = reportCheckBox.isSelected()?"1":"0";
		//数据校验
		if(StringUtils.isEmpty(username)){
			JOptionPane.showMessageDialog(null, "用户名称不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(pwd)){
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return ;
		}
		pwd = DigestUtils.md5Hex(pwd);//密码加密
		//封装对象
		User editUser = new User(username, pwd, query, update, browser, report);
		editUser.setId(Integer.parseInt(idTxt.getText()));
		Connection con=null;
		try {
			con=Dbutils.getCon();//获取数据库连接
			userDao.modify(con, editUser);//操作数据库，执行修改
			JOptionPane.showMessageDialog(null, "修改成功！");
			//如果被修改用户为当前登录用户，则更新当前用户数据
			if(editUser.getId().equals(currentUser.getId())){
				currentUser.setUsername(editUser.getUsername());
				currentUser.setPassword(editUser.getPassword());
				currentUser.setQuery(editUser.getQuery());
				currentUser.setUpdate(editUser.getUpdate());
				currentUser.setBrowser(editUser.getBrowser());
				currentUser.setReport(editUser.getReport());
			}
			resetVlaue();//重置表单
			if(!"1".equals(currentUser.getBrowser())){
				userTable.removeAll();
			}else{
				fillTable("");
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "修改失败！");
			throw new RuntimeException(e);
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void resetVlaue() {
		editUserNameTxt.setText("");
		editPasswordTxt.setText("");
		queryCheckBox.setSelected(false);
		browserCheckBox.setSelected(false);
		updateCheckBox.setSelected(false);
		reportCheckBox.setSelected(false);
	}

	private void userTableMouseEvent(MouseEvent evt) {
		int row=userTable.getSelectedRow();
		idTxt.setText((String)userTable.getValueAt(row, 0));
		editUserNameTxt.setText((String)userTable.getValueAt(row, 1));
		editPasswordTxt.setText((String)userTable.getValueAt(row, 2));
		String query = (String)userTable.getValueAt(row, 3);
		String update = (String)userTable.getValueAt(row, 4);
		String browser = (String)userTable.getValueAt(row, 5);
		String report = (String)userTable.getValueAt(row, 6);
		if(query.equals("有")){
			queryCheckBox.setSelected(true);
		}else {
			queryCheckBox.setSelected(false);
		}
		if(update.equals("有")){
			updateCheckBox.setSelected(true);
		}else {
			updateCheckBox.setSelected(false);
		}
		if(browser.equals("有")){
			browserCheckBox.setSelected(true);
		}else {
			browserCheckBox.setSelected(false);
		}
		if(report.equals("有")){
			reportCheckBox.setSelected(true);
		}else {
			reportCheckBox.setSelected(false);
		}
		
	}

	private void userSearchActioPerformed(ActionEvent evt) {
		String userName=userNameTxt.getText();
		if(StringUtils.isEmpty(userName)){
			if("1".equals(currentUser.getBrowser())){
				fillTable("");
				return ;
			}else {
				JOptionPane.showMessageDialog(null, "暂无权限！");
				return ;
			}
		}else{
			if("1".equals(currentUser.getBrowser())||"1".equals(currentUser.getQuery())){
				this.fillTable(userName);
			}
		}
		
	}

	private void fillTable(String username){
		DefaultTableModel dtm=(DefaultTableModel) userTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		con=Dbutils.getCon();//得到数据库连接
		List<User> rst;
		try {
			rst = userDao.findByName(con, username);//按用户名进行查询，得到数据库返回结果
		} finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		//填充表格，显示数据
		for(User u:rst) {
			Vector<String> v=new Vector<String>();
			v.add(u.getId().toString());
			v.add(u.getUsername());
			v.add(u.getPassword());
			
			if(u.getQuery().equals("1")){
				v.add("有");
			}else {
				v.add("无");
			}
			if(u.getUpdate().equals("1")){
				v.add("有");
			}else {
				v.add("无");
			}
			if(u.getBrowser().equals("1")){
				v.add("有");
			}else {
				v.add("无");
			}
			if(u.getReport().equals("1")){
				v.add("有");
			}else {
				v.add("无");
			}
			dtm.addRow(v);
		}
			
	}
}
