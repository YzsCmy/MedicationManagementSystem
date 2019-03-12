package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

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

import dao.AgencyDao;
import model.Agency;
import model.User;
import utils.Dbutils;
import utils.StringUtils;

import java.awt.Font;

public class AgencyAddInterFrm extends JInternalFrame {
	private User currentUser;
	
	private JTextField agencynoTxt;
	private JTextField agencynameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField agencyphoneTxt;
	private AgencyDao agencyDao=new AgencyDao();
	
	private JTextArea agencyremarkTxt;
	private JRadioButton manJrb=null;
	private JRadioButton femaleJrb=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgencyAddInterFrm frame = new AgencyAddInterFrm(null);
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
	public AgencyAddInterFrm(User u) {
		currentUser = u;
		setClosable(true);
		setIconifiable(true);
		setTitle("经办人添加");
		setBounds(100, 100, 720, 630);
		
		JLabel lblNewLabel = new JLabel("经办人编号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1 = new JLabel("经办人姓名：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		agencynoTxt = new JTextField();
		agencynoTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		agencynoTxt.setColumns(10);
		
		agencynameTxt = new JTextField();
		agencynameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		agencynameTxt.setColumns(10);
		
		JLabel label = new JLabel("性别：");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		
		manJrb = new JRadioButton("男");
		manJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		manJrb.setSelected(true);
		buttonGroup.add(manJrb);
		
		femaleJrb = new JRadioButton("女");
		femaleJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		buttonGroup.add(femaleJrb);
		
		JLabel label_1 = new JLabel("经办人手机号：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		agencyphoneTxt = new JTextField();
		agencyphoneTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		agencyphoneTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("备注：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 17));
		
		agencyremarkTxt = new JTextArea();
		agencyremarkTxt.setFont(new Font("Monospaced", Font.PLAIN, 17));
		
		JButton button = new JButton("添加");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agencyAddActioPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(AgencyAddInterFrm.class.getResource("/images/add.png")));
		
		JButton button_1 = new JButton("重置");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(AgencyAddInterFrm.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(87)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_3)
									.addGap(2)
									.addComponent(agencyremarkTxt))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(agencynoTxt, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(manJrb)
											.addGap(18)
											.addComponent(femaleJrb)))
									.addGap(34)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_1)
										.addComponent(label_1))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(agencynameTxt, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
										.addComponent(agencyphoneTxt, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(80)
							.addComponent(button)
							.addGap(81)
							.addComponent(button_1)))
					.addContainerGap(110, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(83)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(agencynoTxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(agencynameTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(manJrb)
						.addComponent(femaleJrb)
						.addComponent(agencyphoneTxt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGap(84)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(agencyremarkTxt, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(85))
		);
		
		getContentPane().setLayout(groupLayout);
		
		// 设置文本域边框
		agencyremarkTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		
	}
	private void resetActionPerformed(ActionEvent e) {
		resetVlaue();
	}

	private void agencyAddActioPerformed(ActionEvent evt) {
		if(!currentUser.getUpdate().equals("1")){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		String ano=agencynoTxt.getText();
		String agencyName=agencynameTxt.getText();
		String aphone=agencyphoneTxt.getText().replace(" ", "");
		String aremark=agencyremarkTxt.getText();
		String sex=null;
		if(StringUtils.isEmpty(ano)){
			JOptionPane.showMessageDialog(null, "经办人编号不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(agencyName)){
			JOptionPane.showMessageDialog(null, "经办人姓名不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(aphone)){
			JOptionPane.showMessageDialog(null, "经办人电话不能为空！");
			return ;
		}
		if(aphone.length()>12){
			JOptionPane.showMessageDialog(null, "经办人电话长度超出限制！");
			return ;
		}
		String reg = "\\d{6,12}";
		if(!aphone.matches(reg)){
			JOptionPane.showMessageDialog(null, "经办人电话输入有误！");
			return ;
		}
		if(manJrb.isSelected()){
			sex="男";
		}else if(femaleJrb.isSelected()){
			sex="女";
		}
		Connection con=null;
		Agency temp=null;
		try {
			con = Dbutils.getCon();
			temp = agencyDao.findById(con, ano);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "添加失败！");
			throw new RuntimeException(e);
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(temp!=null){
			JOptionPane.showMessageDialog(null, "经办人编号已存在,请重新输入！");
			return ;
		}
		Agency agency = new Agency(ano,agencyName,sex,aphone,aremark,null);
		
		try {
			con=Dbutils.getCon();
			agencyDao.save(con, agency);
			JOptionPane.showMessageDialog(null, "添加成功！");
			resetVlaue();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "添加失败！");
			throw new RuntimeException(e);
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void resetVlaue() {
		agencynoTxt.setText("");
		agencynameTxt.setText("");
		agencyphoneTxt.setText("");
		agencyremarkTxt.setText("");
		manJrb.setSelected(true);
	}

	
}
