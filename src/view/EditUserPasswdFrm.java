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

public class EditUserPasswdFrm extends JInternalFrame {
	private User currentUser;
	private UserDao userDao=new UserDao();
	
	private JPasswordField passwordTxt;
	private JPasswordField repasswordTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditUserPasswdFrm frame = new EditUserPasswdFrm(null);
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
	public EditUserPasswdFrm(User u) {
		currentUser = u;
		setClosable(true);
		setIconifiable(true);
		setTitle("修改密码");
		setBounds(100, 100, 613, 630);
		
		JLabel lblNewLabel_1 = new JLabel("新密码：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JButton button = new JButton("修改");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPasswdActioPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(EditUserPasswdFrm.class.getResource("/images/modify.png")));
		
		JButton button_1 = new JButton("重置");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(EditUserPasswdFrm.class.getResource("/images/reset.png")));
		
		JLabel label = new JLabel("确认密码：");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		
		repasswordTxt = new JPasswordField();
		repasswordTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(184)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
							.addGap(151))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(repasswordTxt, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(170)
					.addComponent(button)
					.addGap(100)
					.addComponent(button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(153))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(206)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(repasswordTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(107)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(184))
		);
		
		getContentPane().setLayout(groupLayout);
		
	}
	private void resetActionPerformed(ActionEvent e) {
		resetVlaue();
	}

	private void editPasswdActioPerformed(ActionEvent evt) {
		
		String pwd = new String(passwordTxt.getPassword());
		String repwd = new String(repasswordTxt.getPassword());
		
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
		
		pwd = DigestUtils.md5Hex(pwd);
		User user = new User(currentUser.getUsername(), pwd, currentUser.getQuery(), 
				currentUser.getUpdate(), currentUser.getBrowser(), 
				currentUser.getReport());
		user.setId(currentUser.getId());
		Connection con = null;
		try {
			con = Dbutils.getCon();
			userDao.modify(con, user);
			JOptionPane.showMessageDialog(null, "修改成功！");
			resetVlaue();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败！");
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void resetVlaue() {
		passwordTxt.setText("");
		repasswordTxt.setText("");
	}

}
