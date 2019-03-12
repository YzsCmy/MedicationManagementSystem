package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import org.apache.commons.codec.digest.DigestUtils;

import dao.UserDao;
import model.User;
import utils.Dbutils;
import utils.StringUtils;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.Font;

public class UserAddInterFrm extends JInternalFrame {
	private User currentUser;
	
	private JTextField usernameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private UserDao userDao=new UserDao();
	
	private JCheckBox queryCheckBox;
	private JCheckBox browserCheckBox;
	private JCheckBox updateCheckBox;
	private JCheckBox reportCheckBox;
	
	private JPasswordField passwordTxt;
	private JPasswordField repasswordTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAddInterFrm frame = new UserAddInterFrm(null);
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
	public UserAddInterFrm(User u) {
		currentUser = u;
		setClosable(true);
		setIconifiable(true);
		setTitle("用户添加");
		setBounds(100, 100, 613, 630);
		
		JLabel lblNewLabel = new JLabel("用户名：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1 = new JLabel("密  码：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		usernameTxt = new JTextField();
		usernameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		usernameTxt.setColumns(10);
		
		JButton button = new JButton("添加");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAddActioPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(UserAddInterFrm.class.getResource("/images/add.png")));
		
		JButton button_1 = new JButton("重置");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(UserAddInterFrm.class.getResource("/images/reset.png")));
		
		JLabel label = new JLabel("确认密码：");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		
		queryCheckBox = new JCheckBox("查询");
		queryCheckBox.setFont(new Font("宋体", Font.PLAIN, 17));
		
		browserCheckBox = new JCheckBox("浏览");
		browserCheckBox.setFont(new Font("宋体", Font.PLAIN, 17));
		
		updateCheckBox = new JCheckBox("更新");
		updateCheckBox.setFont(new Font("宋体", Font.PLAIN, 17));
		
		reportCheckBox = new JCheckBox("报表");
		reportCheckBox.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel label_1 = new JLabel("设置权限：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		
		repasswordTxt = new JPasswordField();
		repasswordTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(184)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_1)
								.addGap(18))))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addComponent(usernameTxt, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
							.addGap(151))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(repasswordTxt, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(95, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addGap(18)
							.addComponent(queryCheckBox)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(browserCheckBox)
							.addGap(18))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(button)
							.addGap(80)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(164))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(updateCheckBox)
							.addGap(6)
							.addComponent(reportCheckBox)
							.addGap(117))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(140)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(usernameTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(repasswordTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(72)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(updateCheckBox)
							.addComponent(queryCheckBox)
							.addComponent(browserCheckBox)
							.addComponent(label_1))
						.addComponent(reportCheckBox))
					.addGap(55)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(136))
		);
		
		getContentPane().setLayout(groupLayout);
		
	}
	private void resetActionPerformed(ActionEvent e) {
		resetVlaue();
	}

	private void UserAddActioPerformed(ActionEvent evt) {
		//判断当前用户是否拥有修改权限
		if(!currentUser.getUpdate().equals("1")){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		
		String username = usernameTxt.getText();//获取用户名
		String pwd = new String(passwordTxt.getPassword());//获取密码
		String repwd = new String(repasswordTxt.getPassword());
		String query = "0";
		String browser = "0";
		String update = "0";
		String report = "0";
		
		if(StringUtils.isEmpty(username)){
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(pwd)){
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(repwd)){
			JOptionPane.showMessageDialog(null, "确认密码不能为空！");
			return ;
		}
		if(!pwd.equals(repwd)){
			JOptionPane.showMessageDialog(null, "两次密码不相同！");
			return ;
		}
		
		if(queryCheckBox.isSelected()){
			query = "1";
		}
		if(updateCheckBox.isSelected()){
			update = "1";
		}
		if(browserCheckBox.isSelected()){
			browser = "1";
		}
		if(reportCheckBox.isSelected()){
			report = "1";
		}
		pwd = DigestUtils.md5Hex(pwd);//加密密码
		User user = new User(username, pwd, query, update, browser, report);
		List list;
		Connection con = null;
		try {
			con = Dbutils.getCon();
			list = userDao.findByName(con, username);//查询要录入的用户是否存在
		} finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(list.size()>0){//已存在
			JOptionPane.showMessageDialog(null, "用户已存在！");
		}
		//不存在
		try {
			con = Dbutils.getCon();
			userDao.save(con, user);//向数据库插入新增用户数据
			JOptionPane.showMessageDialog(null, "添加成功！");
			resetVlaue();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败！");
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void resetVlaue() {
		usernameTxt.setText("");
		passwordTxt.setText("");
		repasswordTxt.setText("");
		queryCheckBox.setSelected(false);
		updateCheckBox.setSelected(false);
		browserCheckBox.setSelected(false);
		reportCheckBox.setSelected(false);
	}

}
