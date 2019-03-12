package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.hamcrest.CoreMatchers;

import dao.AgencyDao;
import dao.ClientDao;
import dao.MedicineDao;
import model.Agency;
import model.Client;
import model.Medicine;
import model.User;
import sun.misc.Cleaner;
import utils.Dbutils;
import utils.StringUtils;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import com.eltima.components.ui.DatePicker;
import java.awt.Dimension;
import java.util.Locale;

public class ClientManageInterFrm extends JInternalFrame {
	private User currentUser;
	
	private JRadioButton manrb;
	private JRadioButton femalerb;
	
	private JTable clientTable;
	private JTextField cnoTxt;
	private JTextField cnameTxt;

	private ClientDao clientDao=new ClientDao();
	private AgencyDao agencyDao=new AgencyDao();
	private MedicineDao medicineDao=new MedicineDao();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JTextArea editremarkTxt;
	private JTextField hiddenCnoTxt;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField editcnoTxt;
	private JTextField editcnameTxt;
	private JTextField editcageTxt;
	private JTextField editPhoneTxt;
	private JTextField editsymptomTxt;
	private JTextField editaddressTxt;
	
	private JComboBox medicineJcb;
	private JComboBox agencyJcb;
	
	private JRadioButton editfemaleJrb;
	private JRadioButton editmanJrb;
	
	private DatePicker datePicker;
	private JTextField hiddenDateTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientManageInterFrm frame = new ClientManageInterFrm(null);
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
	public ClientManageInterFrm(User u) {
		currentUser = u;
		setClosable(true);
		setIconifiable(true);
		setTitle("顾客管理");
		setBounds(100, 100, 1164, 988);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 1120, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel label_7 = new JLabel("备注：");
		label_7.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editremarkTxt = new JTextArea();
		editremarkTxt.setLineWrap(true);
		editremarkTxt.setFont(new Font("Monospaced", Font.PLAIN, 17));
		
		JButton button_1 = new JButton("修改");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clientUpdateActionPerform(e);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setIcon(new ImageIcon(ClientManageInterFrm.class.getResource("/images/modify.png")));
		
		JButton button_2 = new JButton("删除");
		button_2.setFont(new Font("宋体", Font.PLAIN, 17));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clientDelActioPerformed(e);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setIcon(new ImageIcon(ClientManageInterFrm.class.getResource("/images/delete.png")));
		
		hiddenCnoTxt = new JTextField();
		hiddenCnoTxt.setVisible(false);
		hiddenCnoTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("编号：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editcnoTxt = new JTextField();
		editcnoTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		editcnoTxt.setColumns(10);
		
		JLabel label_5 = new JLabel("姓名：");
		label_5.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editcnameTxt = new JTextField();
		editcnameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		editcnameTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("性别：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editmanJrb = new JRadioButton("男");
		buttonGroup.add(editmanJrb);
		editmanJrb.setSelected(true);
		editmanJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editfemaleJrb = new JRadioButton("女");
		buttonGroup.add(editfemaleJrb);
		editfemaleJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel label_4 = new JLabel("年龄：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editcageTxt = new JTextField();
		editcageTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		editcageTxt.setColumns(10);
		
		editPhoneTxt = new JTextField();
		editPhoneTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		editPhoneTxt.setColumns(10);
		
		JLabel label_6 = new JLabel("手机：");
		label_6.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel label_8 = new JLabel("症状：");
		label_8.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editsymptomTxt = new JTextField();
		editsymptomTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		editsymptomTxt.setColumns(10);
		
		JLabel label_9 = new JLabel("地址：");
		label_9.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editaddressTxt = new JTextField();
		editaddressTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		editaddressTxt.setColumns(10);
		
		JLabel label_10 = new JLabel("选择日期：");
		label_10.setFont(new Font("宋体", Font.PLAIN, 17));
		
		datePicker = new DatePicker();
		datePicker.btn.setFont(new Font("宋体", Font.PLAIN, 15));
		datePicker.fd.setEditable(false);
		datePicker.getInnerTextField().setText("");
		datePicker.getInnerTextField().setFont(new Font("宋体", Font.PLAIN, 20));
		datePicker.getInnerTextField().setEditable(false);
		datePicker.getInnerButton().setFont(new Font("宋体", Font.PLAIN, 15));
		datePicker.setTimePanleVisible(true);
		datePicker.setLocale(Locale.US);
		datePicker.font = new Font("Dialog", Font.PLAIN, 17);
		datePicker.setFont(new Font("Dialog", Font.PLAIN, 17));
		datePicker.dimension = new Dimension(400, 400);
		
		JLabel label_12 = new JLabel("药品：");
		label_12.setFont(new Font("宋体", Font.PLAIN, 17));
		
		medicineJcb = new JComboBox();
		medicineJcb.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel label_13 = new JLabel("经办人：");
		label_13.setFont(new Font("宋体", Font.PLAIN, 17));
		
		agencyJcb = new JComboBox();
		agencyJcb.setFont(new Font("宋体", Font.PLAIN, 17));
		
		hiddenDateTextField = new JTextField();
		hiddenDateTextField.setVisible(false);
		hiddenDateTextField.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
											.addGap(6)
											.addComponent(editcnoTxt, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
											.addGap(2)
											.addComponent(editmanJrb, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(editfemaleJrb, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
									.addGap(90)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
											.addGap(6)
											.addComponent(editcnameTxt, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
											.addGap(6)
											.addComponent(editPhoneTxt, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
									.addGap(90)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
											.addGap(6)
											.addComponent(editcageTxt, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
											.addGap(6)
											.addComponent(editsymptomTxt, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
									.addGap(88)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(hiddenDateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(hiddenCnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_7)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editremarkTxt, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
									.addGap(90)
									.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(medicineJcb, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
									.addGap(84)
									.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(agencyJcb, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(272)
							.addComponent(button_1)
							.addGap(286)
							.addComponent(button_2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(editaddressTxt, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(173, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(editcnoTxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(8)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(editcageTxt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(hiddenCnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(editcnameTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(4)
											.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
										.addComponent(editmanJrb, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
										.addComponent(editfemaleJrb, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(8)
											.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
										.addComponent(editPhoneTxt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(26)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(8)
											.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
											.addComponent(editsymptomTxt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
											.addComponent(hiddenDateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
							.addGap(60)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(10)
									.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(medicineJcb, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
									.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
							.addGap(78)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(8)
									.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addComponent(editaddressTxt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
							.addGap(57)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_7)
								.addComponent(editremarkTxt, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(135)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(agencyJcb, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("顾客编号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		cnoTxt = new JTextField();
		cnoTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		cnoTxt.setColumns(10);
		
		JLabel label = new JLabel("姓名：");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		
		cnameTxt = new JTextField();
		cnameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		cnameTxt.setColumns(10);
		
		JButton button = new JButton("查询");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientSearchActioPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(ClientManageInterFrm.class.getResource("/images/search.png")));
		
		JLabel label_1 = new JLabel("性别：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		manrb = new JRadioButton("男");
		buttonGroup_1.add(manrb);
		manrb.setFont(new Font("宋体", Font.PLAIN, 17));
		manrb.setSelected(true);
		
		femalerb = new JRadioButton("女");
		buttonGroup_1.add(femalerb);
		femalerb.setFont(new Font("宋体", Font.PLAIN, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(71)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cnoTxt, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cnameTxt, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(79)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(manrb, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(femalerb, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addGap(92)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addGap(45))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(manrb)
						.addComponent(femalerb)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(cnameTxt, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(cnoTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		
		clientTable = new JTable();
		clientTable.setFont(new Font("宋体", Font.PLAIN, 17));
		clientTable.setRowHeight(25);
		clientTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				clientTableMouseEvent(e);
			}
		});
		clientTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u5E74\u9F84", "\u5730\u5740", "\u7535\u8BDD", "\u75C7\u72B6", "\u836F\u54C1", "\u7ECF\u529E\u4EBA", "\u65E5\u671F", "\u5907\u6CE8"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(clientTable);
		getContentPane().setLayout(groupLayout);
		// 设置文本域边框
		editremarkTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		fillJcb();
		if("1".equals(currentUser.getBrowser())){
			fillTable(new Client());
		}

	}
	private void clientDelActioPerformed(ActionEvent e) throws ParseException {
		if(!"1".equals(currentUser.getUpdate())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		int[] nums=clientTable.getSelectedRows();
		if(nums.length==0){
			JOptionPane.showMessageDialog(null, "请选择要删除的数据！");
			return ;
		}
		Date tempdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hiddenDateTextField.getText());
		Timestamp timestamp = new Timestamp(tempdate.getTime());
		int confNum=JOptionPane.showConfirmDialog(null, "您确定要删除吗？");
		if(confNum==0){
			Connection con=null;
			try {
				con = Dbutils.getCon();
				con.setAutoCommit(false);
				for(int num:nums){
					String id=(String) clientTable.getValueAt(num, 0);
					clientDao.delete(con, id,timestamp);
				}
				con.commit();
			} catch (Exception e1) {
				try {
					con.rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "删除失败！");
				throw new RuntimeException(e1);
			}finally {
				try {
					Dbutils.closeCon(con);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			JOptionPane.showMessageDialog(null, "删除成功！");
			resetVlaue();
			if(!"1".equals(currentUser.getBrowser())){
				clientTable.removeAll();
			}else{
				fillTable(new Client());
			}
		}
	}

	private void clientUpdateActionPerform(ActionEvent evt) throws ParseException {
		if(!"1".equals(currentUser.getUpdate())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		if(StringUtils.isEmpty(hiddenCnoTxt.getText())){
			JOptionPane.showMessageDialog(null, "请选择要修改的客户数据!");
			return ;
		}
		String cno=editcnoTxt.getText();
		String cName=editcnameTxt.getText();
		String cage=editcageTxt.getText().replace(" ", "");
		String cphone = editPhoneTxt.getText().replace(" ", "");
		String caddress = editaddressTxt.getText();
		Date cdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datePicker.getInnerTextField().getText());
		String cremark=editremarkTxt.getText();
		String csymtom = editsymptomTxt.getText();
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
		
		if(editmanJrb.isSelected()){
			sex="男";
		}else if(editfemaleJrb.isSelected()){
			sex="女";
		}
		Connection con=null;
		String id = hiddenCnoTxt.getText();
		Date tempdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hiddenDateTextField.getText());
		Timestamp timestamp = new Timestamp(tempdate.getTime());
		
		if(cdate== null){
			JOptionPane.showMessageDialog(null, "请选择日期！");
			return ;
		}
		Client client = new Client(cno, m, a, cName, sex, age, caddress, cphone, csymtom, new Timestamp(cdate.getTime()), cremark);
		
		try {
			con=Dbutils.getCon();
			clientDao.modify(con, client, id,timestamp);
			JOptionPane.showMessageDialog(null, "修改成功！");
			resetVlaue();
			if(!"1".equals(currentUser.getBrowser())){
				clientTable.removeAll();
			}else{
				fillTable(new Client());
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "修改失败！");
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
		editcnoTxt.setText("");
		editcnameTxt.setText("");
		editcageTxt.setText("");
		editPhoneTxt.setText("");
		editsymptomTxt.setText("");
		editaddressTxt.setText("");
		editremarkTxt.setText("");
		datePicker.getInnerTextField().setText("");
		editmanJrb.setSelected(true);
		if(medicineJcb.getItemCount()>0){
			medicineJcb.setSelectedIndex(0);
		}
		if(agencyJcb.getItemCount()>0){
			agencyJcb.setSelectedIndex(0);
		}
	}

	private void clientTableMouseEvent(MouseEvent evt) {
		int row=clientTable.getSelectedRow();
		hiddenCnoTxt.setText((String)clientTable.getValueAt(row, 0));
		hiddenDateTextField.setText((String)clientTable.getValueAt(row, 9));
		editcnoTxt.setText((String)clientTable.getValueAt(row, 0));
		editcnameTxt.setText((String)clientTable.getValueAt(row, 1));
		String sex=(String)clientTable.getValueAt(row, 2);
		if(sex.equals("男")){
			this.editmanJrb.setSelected(true);
		}else if(sex.equals("女")){
			this.editfemaleJrb.setSelected(true);
		}
		editcageTxt.setText((String)clientTable.getValueAt(row, 3));
		editaddressTxt.setText((String)clientTable.getValueAt(row, 4));
		editPhoneTxt.setText((String)clientTable.getValueAt(row, 5));
		editsymptomTxt.setText((String)clientTable.getValueAt(row, 6));
		int i = medicineJcb.getItemCount();
		String mname = (String)clientTable.getValueAt(row, 7);
		for (int j = 0; j < i; j++) {
			Medicine item = (Medicine) medicineJcb.getItemAt(j);
			if(mname.equals(item.getMname())){
				medicineJcb.setSelectedIndex(j);
			}
		}
		int n = agencyJcb.getItemCount();
		String aname = (String)clientTable.getValueAt(row, 8);
		for (int j = 0; j < n; j++) {
			Agency item = (Agency) agencyJcb.getItemAt(j);
			if(aname.equals(item.getAname())){
				agencyJcb.setSelectedIndex(j);
			}
		}
		datePicker.getInnerTextField().setText((String)clientTable.getValueAt(row, 9));
		editremarkTxt.setText((String)clientTable.getValueAt(row, 10));
	}

	private void clientSearchActioPerformed(ActionEvent evt) {
		if(!"1".equals(currentUser.getQuery())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		String cno = cnoTxt.getText();
		String cname = cnameTxt.getText();
		String sex = "";
		if(manrb.isSelected()){
			sex = "男";
		}else {
			sex = "女";
		}
		Client client = new Client();
		client.setCno(cno);
		client.setCname(cname);
		client.setCsex(sex);
		this.fillTable(client);
	}

	
	private void fillTable(Client client){
		DefaultTableModel dtm=(DefaultTableModel) clientTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=Dbutils.getCon();
			List<Client> rst=clientDao.search(con, client);
			for(Client a:rst) {
				Vector<String> v=new Vector<String>();
				v.add(a.getCno());
				v.add(a.getCname());
				v.add(a.getCsex());
				v.add(a.getCage().toString());
				v.add(a.getCaddress());
				v.add(a.getCphone());
				v.add(a.getCsymptom());
				v.add(a.getMedicine().getMname());
				v.add(a.getAgency().getAname());
				v.add(a.getCdate().toString().substring(0, 19));
				v.add(a.getCremark());
				dtm.addRow(v);
			}
			
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
		try {
			List<Medicine> rst=medicineDao.findAll(con);
			for(Medicine m:rst){
				medicineJcb.addItem(m);
			}
			List<Agency> rst1=agencyDao.findAll(con);
			for(Agency a:rst1){
				agencyJcb.addItem(a);
			}
		} finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
