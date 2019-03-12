package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.digest.DigestUtils;

import dao.UserDao;
import model.User;
import utils.Dbutils;
import utils.StringUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class LogOnFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	private UserDao userDao=new UserDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrm frame = new LogOnFrm();
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
	public LogOnFrm() {
		//修改默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		setResizable(false);
		setTitle("管理员登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 615);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel(" 医药管理信息系统");
		label.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/medicine.png")));
		label.setFont(new Font("宋体", Font.BOLD, 26));
		
		JLabel label_1 = new JLabel("用户名:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		label_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/userName.png")));
		
		JLabel label_2 = new JLabel("密  码:");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		label_2.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/password.png")));
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerform(e);
			}
		});
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerform(e);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(249, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(24))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(19)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(userNameTxt, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
								.addComponent(passwordTxt)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(124)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(34)))
					.addGap(256))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(115)
					.addComponent(label)
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(137, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
	}

	private void loginActionPerform(ActionEvent e) {
		String name=userNameTxt.getText();//获取用户名
		String password=new String(passwordTxt.getPassword());//获取密码
		if(StringUtils.isEmpty(name)){
			JOptionPane.showMessageDialog(null, "用户名不能为空!");
			return;
		}
		if(StringUtils.isEmpty(password)){
			JOptionPane.showMessageDialog(null, "密码不能为空!");
			return;
		}	
		password = DigestUtils.md5Hex(password);//MD5加密用户密码
		User user=new User();//创建用户对象
		user.setUsername(name);
		user.setPassword(password);
		Connection con=null;
		try {
			con=Dbutils.getCon();//获取数据库连接
			User currentUser=userDao.login(con, user);//发送查询sql语句，得到返回结果
			if(currentUser!=null){
				dispose();
				new MainFrm(currentUser).setVisible(true);//登录成功，进入主界面
			}else{
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");//失败
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				Dbutils.closeCon(con);//关闭连接
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	private void resetActionPerform(ActionEvent evt) {
		this.userNameTxt.setText("");
		this.passwordTxt.setText("");
	}
}
