package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import dao.ClientDao;
import dao.MedicineDao;
import model.Agency;
import model.Client;
import model.Medicine;
import model.User;
import utils.Dbutils;
import utils.StringUtils;

import java.awt.Font;
import com.eltima.components.ui.DatePicker;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ClientAddInterFrm extends JInternalFrame {
	private User currentUser;
	
	private DatePicker datePicker;
	private JTextField cnoTxt;
	private JTextField cnameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField cageTxt;
	private ClientDao clientDao=new ClientDao();
	private AgencyDao agencyDao=new AgencyDao();
	private MedicineDao medicineDao=new MedicineDao();
	
	private JTextArea clientremarkTxt;
	private JRadioButton manJrb=null;
	private JRadioButton femaleJrb=null;
	private JTextField cphoneTxt;
	private JTextField csymtomTxt;
	private JTextField caddressTxt;
	
	private JComboBox medicineJcb;
	private JComboBox agencyJcb;
	private int modcount;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientAddInterFrm frame = new ClientAddInterFrm(null);
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
	public ClientAddInterFrm(User u) {
		currentUser = u;
		setClosable(true);
		setIconifiable(true);
		setTitle("顾客信息添加");
		setBounds(100, 100, 720, 800);
		
		JLabel lblNewLabel = new JLabel("编号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1 = new JLabel("姓名：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		cnoTxt = new JTextField();
		cnoTxt.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if(modcount==0){
					fillForm();
				}
			}
		});
		cnoTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		cnoTxt.setColumns(10);
		
		cnameTxt = new JTextField();
		cnameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		cnameTxt.setColumns(10);
		
		JLabel label = new JLabel("性别：");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		
		manJrb = new JRadioButton("男");
		manJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		manJrb.setSelected(true);
		buttonGroup.add(manJrb);
		
		femaleJrb = new JRadioButton("女");
		femaleJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		buttonGroup.add(femaleJrb);
		
		JLabel label_1 = new JLabel("年龄：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		cageTxt = new JTextField();
		cageTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		cageTxt.setColumns(10);
		
		
		
		JLabel label_3 = new JLabel("备注：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 17));
		
		clientremarkTxt = new JTextArea();
		clientremarkTxt.setFont(new Font("Monospaced", Font.PLAIN, 17));
		
		JButton button = new JButton("添加");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientAddActioPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(ClientAddInterFrm.class.getResource("/images/add.png")));
		
		JButton button_1 = new JButton("重置");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(ClientAddInterFrm.class.getResource("/images/reset.png")));
		
		JLabel label_2 = new JLabel("手机：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		
		cphoneTxt = new JTextField();
		cphoneTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		cphoneTxt.setColumns(10);
		
		JLabel label_4 = new JLabel("症状：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 17));
		
		csymtomTxt = new JTextField();
		csymtomTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		csymtomTxt.setColumns(10);
		
		JLabel label_5 = new JLabel("地址：");
		label_5.setFont(new Font("宋体", Font.PLAIN, 17));
		
		caddressTxt = new JTextField();
		caddressTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		caddressTxt.setColumns(10);
		
		JLabel label_6 = new JLabel("选择日期：");
		label_6.setFont(new Font("宋体", Font.PLAIN, 17));
		
		datePicker = new DatePicker();
		datePicker.dimension = new Dimension(400, 400);
		datePicker.font = new Font("Dialog", Font.PLAIN, 17);
		datePicker.setFont(new Font("Dialog", Font.PLAIN, 17));
		datePicker.fd.setEditable(false);
		datePicker.getInnerTextField().setEditable(false);
		datePicker.btn.setFont(new Font("宋体", Font.PLAIN, 15));
		datePicker.getInnerButton().setFont(new Font("宋体", Font.PLAIN, 15));
		datePicker.getInnerTextField().setFont(new Font("宋体", Font.PLAIN, 20));
		datePicker.setLocale(Locale.US);
		datePicker.getInnerTextField().setText("");
		datePicker.setTimePanleVisible(true);
		
		JLabel label_7 = new JLabel("药品：");
		label_7.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel label_8 = new JLabel("经办人：");
		label_8.setFont(new Font("宋体", Font.PLAIN, 17));
		
		medicineJcb = new JComboBox();
		medicineJcb.setFont(new Font("宋体", Font.PLAIN, 17));
		
		agencyJcb = new JComboBox();
		agencyJcb.setFont(new Font("宋体", Font.PLAIN, 17));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(133)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(clientremarkTxt, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(label_6)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(medicineJcb, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
										.addComponent(label_8)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(agencyJcb, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(cnoTxt, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(label)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(manJrb)
												.addGap(18)
												.addComponent(femaleJrb))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(cphoneTxt, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(lblNewLabel_1)
													.addComponent(label_1))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(cnameTxt, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
													.addComponent(cageTxt, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
											.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(csymtomTxt, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(caddressTxt, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)))
								.addGap(127)))))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(188, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(137)
					.addComponent(button_1)
					.addGap(205))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(83)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(cnoTxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(manJrb)
								.addComponent(femaleJrb)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(cnameTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cageTxt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
							.addGap(39)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(cphoneTxt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(csymtomTxt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(caddressTxt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(55)
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(45)
							.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(medicineJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(agencyJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(clientremarkTxt, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(36))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(35))))
		);
		
		getContentPane().setLayout(groupLayout);
		
		// 设置文本域边框
		clientremarkTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		fillJcb();
		modcount = 0;
		
	}
	private void fillForm() {
		
		String cnoStr = cnoTxt.getText();
		if(cnoStr==null||cnoStr.equals("")){
			return;
		}
		Connection con=null;
		Client temp;
		try {
			con = Dbutils.getCon();
			temp = clientDao.findById(con, cnoStr);
		} finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(temp==null){
//			String name = cnameTxt.getText();
//			List list = clientDao.findByName(Dbutils.getCon(), name);
//			if(list!=null&&list.size()>0){
//				JOptionPane.showMessageDialog(null, "客户  "+name+" 已存在,请重新输入！");
//				cnameTxt.setText("");
//				return ;
//			}
//			resetVlaue();
//			cnoTxt.setText(cnoStr);
		}else{
			cnameTxt.setText(temp.getCname());
			cageTxt.setText(temp.getCage()+"");
			cphoneTxt.setText(temp.getCphone());
			caddressTxt.setText(temp.getCaddress());
			csymtomTxt.setText(temp.getCsymptom());
			String csex = temp.getCsex();
			if("男".equals(csex)){
				manJrb.isSelected();
			}else if("女".equals(csex)){
				femaleJrb.isSelected();
			}
			modcount++;
		}
		
		
	}

	private void resetActionPerformed(ActionEvent e) {
		resetVlaue();
	}

	private void clientAddActioPerformed(ActionEvent evt) {
		if(!currentUser.getUpdate().equals("1")){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		String cno=cnoTxt.getText();
		String cName=cnameTxt.getText();
		String cage=cageTxt.getText().replace(" ", "");
		String cphone = cphoneTxt.getText().replace(" ", "");
		String caddress = caddressTxt.getText();
		Date cdate = (Date) datePicker.getValue();
		String cremark=clientremarkTxt.getText();
		String csymtom = csymtomTxt.getText();
		Medicine m = (Medicine) medicineJcb.getSelectedItem();
		Agency a = (Agency) agencyJcb.getSelectedItem();
		String med = m.getMno();
		String agency = a.getAno();
		String sex=null;
		if(StringUtils.isEmpty(cno)){
			JOptionPane.showMessageDialog(null, "顾客编号不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(cName)){
			JOptionPane.showMessageDialog(null, "顾客姓名不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(cage)){
			JOptionPane.showMessageDialog(null, "顾客年龄不能为空！");
			return ;
		}
		int age;
		try{
			age = Integer.parseInt(cage);
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "顾客年龄输入有误！");
			return ;
		}
		if(age<0||age>150){
			JOptionPane.showMessageDialog(null, "顾客年龄有误！");
			return ;
		}
		if(StringUtils.isEmpty(cphone)){
			JOptionPane.showMessageDialog(null, "顾客电话不能为空！");
			return ;
		}
		if(cphone.length()>12){
			JOptionPane.showMessageDialog(null, "顾客电话长度超出限制！");
			return ;
		}
		String reg = "\\d{6,12}";
		if(!cphone.matches(reg)){
			JOptionPane.showMessageDialog(null, "顾客电话输入有误！");
			return ;
		}
		if(StringUtils.isEmpty(csymtom)){
			JOptionPane.showMessageDialog(null, "顾客症状不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(caddress)){
			JOptionPane.showMessageDialog(null, "顾客地址不能为空！");
			return ;
		}
		if("-1".equals(med)){
			JOptionPane.showMessageDialog(null, "请选择药品！");
			return ;
		}
		if("-1".equals(agency)){
			JOptionPane.showMessageDialog(null, "请选择经办人！");
			return ;
		}
		
		if(manJrb.isSelected()){
			sex="男";
		}else if(femaleJrb.isSelected()){
			sex="女";
		}
		Connection con=null;
		if(cdate==null){
			JOptionPane.showMessageDialog(null, "请选择日期！");
			return ;
		}
		Client client = new Client(cno, m, a, cName, sex, age, caddress, cphone, csymtom, new Timestamp(cdate.getTime()), cremark);
		try {
			con=Dbutils.getCon();
			clientDao.save(con, client);
			JOptionPane.showMessageDialog(null, "添加成功！");
			resetVlaue();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "添加失败！");
			throw new RuntimeException(e);
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void resetVlaue() {
		cnoTxt.setText("");
		cnameTxt.setText("");
		cageTxt.setText("");
		cphoneTxt.setText("");
		csymtomTxt.setText("");
		caddressTxt.setText("");
		clientremarkTxt.setText("");
//		datePicker.getInnerTextField().setText("");
		manJrb.setSelected(true);
		if(medicineJcb.getItemCount()>0){
			medicineJcb.setSelectedIndex(0);
		}
		if(agencyJcb.getItemCount()>0){
			agencyJcb.setSelectedIndex(0);
		}
	}
	private void fillJcb(){
		
		Medicine medicine=new Medicine();
		medicine.setMname("请选择...");
		medicine.setMno("-1");
		Agency agency = new Agency();
		agency.setAname("请选择...");
		agency.setAno("-1");
		medicineJcb.addItem(medicine);
		agencyJcb.addItem(agency);
		Connection con = Dbutils.getCon();
		List<Medicine> rst = null;
		List<Agency> rst1 = null;
		try {
			rst=medicineDao.findAll(con);
			rst1=agencyDao.findAll(con);
		} catch (Exception e) {
			Dbutils.closeCon(con);
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(Medicine m:rst){
			medicineJcb.addItem(m);
		}
		for(Agency a:rst1){
			agencyJcb.addItem(a);
		}
		
	}
}
