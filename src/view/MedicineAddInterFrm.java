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
import dao.MedicineDao;
import model.Agency;
import model.Medicine;
import model.User;
import utils.Dbutils;
import utils.StringUtils;

import java.awt.Font;

public class MedicineAddInterFrm extends JInternalFrame {
	private User currentUser;
	
	private JTextField mnoTxt;
	private JTextField mnameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private MedicineDao medicineDao=new MedicineDao();
	
	private JTextArea effectTxt;
	private JRadioButton manJrb=null;
	private JRadioButton inJrb;
	private JRadioButton femaleJrb=null;
	private JRadioButton outJrb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicineAddInterFrm frame = new MedicineAddInterFrm(null);
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
	public MedicineAddInterFrm(User u) {
		currentUser = u;
		setClosable(true);
		setIconifiable(true);
		setTitle("药品添加");
		setBounds(100, 100, 720, 630);
		
		JLabel lblNewLabel = new JLabel("药品编号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1 = new JLabel("药品名称：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		mnoTxt = new JTextField();
		mnoTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		mnoTxt.setColumns(10);
		
		mnameTxt = new JTextField();
		mnameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		mnameTxt.setColumns(10);
		
		JLabel label = new JLabel("服用方法：");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		
		inJrb = new JRadioButton("内服");
		inJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		inJrb.setSelected(true);
		buttonGroup.add(inJrb);
		
		outJrb = new JRadioButton("外用");
		outJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		buttonGroup.add(outJrb);
		
		JLabel label_3 = new JLabel("功效：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 17));
		
		effectTxt = new JTextArea();
		effectTxt.setFont(new Font("Monospaced", Font.PLAIN, 17));
		
		JButton button = new JButton("添加");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicineAddActioPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(MedicineAddInterFrm.class.getResource("/images/add.png")));
		
		JButton button_1 = new JButton("重置");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(MedicineAddInterFrm.class.getResource("/images/reset.png")));
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
									.addComponent(effectTxt, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(mnoTxt, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
									.addGap(68)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(mnameTxt, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inJrb)
									.addGap(18)
									.addComponent(outJrb))))
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
						.addComponent(mnoTxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(mnameTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(67)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(inJrb)
						.addComponent(outJrb))
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3)
						.addComponent(effectTxt, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(85))
		);
		
		getContentPane().setLayout(groupLayout);
		
		// 设置文本域边框
		effectTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		
	}
	private void resetActionPerformed(ActionEvent e) {
		resetVlaue();
	}

	private void medicineAddActioPerformed(ActionEvent evt) {
		if(!currentUser.getUpdate().equals("1")){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		String mno=mnoTxt.getText();
		String mname=mnameTxt.getText();
		String meffect=effectTxt.getText();
		String mode=null;
		if(StringUtils.isEmpty(mno)){
			JOptionPane.showMessageDialog(null, "药品编号不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(mname)){
			JOptionPane.showMessageDialog(null, "药品名称不能为空！");
			return ;
		}
		
		if(inJrb.isSelected()){
			mode="内服";
		}else if(outJrb.isSelected()){
			mode="外用";
		}
		Connection con=null;
		Medicine temp;
		try {
			con = Dbutils.getCon();
			temp = medicineDao.findById(con, mno);
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(temp!=null){
			JOptionPane.showMessageDialog(null, "药品编号已存在,请重新输入！");
			return ;
		}
		Medicine medicine = new Medicine(mno, mname, mode, meffect, null);
		try {
			con=Dbutils.getCon();
			medicineDao.save(con, medicine);
			JOptionPane.showMessageDialog(null, "添加成功！");
			resetVlaue();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "添加失败！");
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
		mnoTxt.setText("");
		mnameTxt.setText("");
		effectTxt.setText("");
		inJrb.setSelected(true);
	}

	
}
