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

import dao.AgencyDao;
import model.Agency;
import model.User;
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

public class AgencyManageInterFrm extends JInternalFrame {
	private User currentUser;
	
	private JRadioButton manrb;
	private JRadioButton femalerb;
	
	private JTable agencyTable;
	private JTextField agencynoTxt;
	private JTextField agencynameTxt;

	private AgencyDao agencyDao=new AgencyDao();
	private JTextField editnameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField editphoneTxt;
	
	private JTextArea editremarkTxt;
	private JRadioButton editmanJrb;
	private JRadioButton editfemaleJrb;
	private JTextField hiddenAnoTxt;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgencyManageInterFrm frame = new AgencyManageInterFrm(null);
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
	public AgencyManageInterFrm(User u) {
		currentUser = u;
		setClosable(true);
		setIconifiable(true);
		setTitle("经办人管理");
		setBounds(100, 100, 815, 764);
		
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
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 740, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel label_2 = new JLabel("姓名：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editnameTxt = new JTextField();
		editnameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		editnameTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("性别：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editmanJrb = new JRadioButton("男");
		editmanJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		buttonGroup.add(editmanJrb);
		editmanJrb.setSelected(true);
		
		editfemaleJrb = new JRadioButton("女");
		editfemaleJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		buttonGroup.add(editfemaleJrb);
		
		JLabel label_4 = new JLabel("手机号：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editphoneTxt = new JTextField();
		editphoneTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		editphoneTxt.setColumns(10);
		
		JLabel label_7 = new JLabel("备注：");
		label_7.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editremarkTxt = new JTextArea();
		editremarkTxt.setLineWrap(true);
		editremarkTxt.setFont(new Font("Monospaced", Font.PLAIN, 17));
		
		JButton button_1 = new JButton("修改");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agencyUpdateActionPerform(e);
			}
		});
		button_1.setIcon(new ImageIcon(AgencyManageInterFrm.class.getResource("/images/modify.png")));
		
		JButton button_2 = new JButton("删除");
		button_2.setFont(new Font("宋体", Font.PLAIN, 17));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agencyDelActioPerformed(e);
			}
		});
		button_2.setIcon(new ImageIcon(AgencyManageInterFrm.class.getResource("/images/delete.png")));
		
		hiddenAnoTxt = new JTextField();
		hiddenAnoTxt.setVisible(false);
		hiddenAnoTxt.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(23)
							.addComponent(button_1)
							.addGap(72)
							.addComponent(button_2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editnameTxt, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
									.addComponent(hiddenAnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editmanJrb)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editfemaleJrb)
									.addGap(61)
									.addComponent(label_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editphoneTxt, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
									.addComponent(label_7)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editremarkTxt, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)))))
					.addGap(100))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(hiddenAnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(editnameTxt, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(editmanJrb)
						.addComponent(editfemaleJrb)
						.addComponent(label_4)
						.addComponent(editphoneTxt, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(editremarkTxt, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("经办人编号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		agencynoTxt = new JTextField();
		agencynoTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		agencynoTxt.setColumns(10);
		
		JLabel label = new JLabel("姓名：");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		
		agencynameTxt = new JTextField();
		agencynameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		agencynameTxt.setColumns(10);
		
		JButton button = new JButton("查询");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agencySearchActioPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(AgencyManageInterFrm.class.getResource("/images/search.png")));
		
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
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(13)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(agencynoTxt, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(agencynameTxt, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(manrb, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(femalerb, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button)
					.addGap(45))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(agencynoTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(agencynameTxt, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(manrb)
						.addComponent(femalerb)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		
		agencyTable = new JTable();
		agencyTable.setFont(new Font("宋体", Font.PLAIN, 17));
		agencyTable.setRowHeight(25);
		agencyTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				agencyTableMouseEvent(e);
			}
		});
		agencyTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u624B\u673A\u53F7", "\u5907\u6CE8"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(agencyTable);
		getContentPane().setLayout(groupLayout);
		// 设置文本域边框
		editremarkTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		
		if("1".equals(currentUser.getBrowser())){
			fillTable(new Agency());
		}

	}
	private void agencyDelActioPerformed(ActionEvent e) {
		if(!"1".equals(currentUser.getUpdate())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		int[] nums=agencyTable.getSelectedRows();
		if(nums.length==0){
			JOptionPane.showMessageDialog(null, "请选择要删除的数据！");
			return ;
		}
		int confNum=JOptionPane.showConfirmDialog(null, "您确定要删除吗？");
		if(confNum==0){
			Connection con=null;
			con=Dbutils.getCon();
			try {
				con.setAutoCommit(false);
				for(int num:nums){
					String id=(String) agencyTable.getValueAt(num, 0);
					agencyDao.delete(con, id);
				}
				con.commit();
			} catch (Exception e1) {
				try {
					con.rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "删除失败，顾客信息中存在该记录！");
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
				agencyTable.removeAll();
			}else{
				fillTable(new Agency());
			}
		}
	}

	private void agencyUpdateActionPerform(ActionEvent evt) {
		if(!"1".equals(currentUser.getUpdate())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		if(StringUtils.isEmpty(hiddenAnoTxt.getText())){
			JOptionPane.showMessageDialog(null, "请选择要修改的经办人数据!");
			return ;
		}
		String ano=hiddenAnoTxt.getText();
		String aname=editnameTxt.getText();
		String aphone=editphoneTxt.getText().replace(" ", "");
		String aremark=editremarkTxt.getText();
		String sex=null;
		if(StringUtils.isEmpty(ano)){
			JOptionPane.showMessageDialog(null, "编号不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(aname)){
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(aphone)){
			JOptionPane.showMessageDialog(null, "手机号不能为空！");
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
		if(editmanJrb.isSelected()){
			sex="男";
		}else if(editfemaleJrb.isSelected()){
			sex="女";
		}
		String id=hiddenAnoTxt.getText();;
		Connection con=null;
		
		Agency agency = new Agency(ano, aname, sex, aphone, aremark, null);
		try {
			con=Dbutils.getCon();
			agencyDao.modify(con, agency, id);
			JOptionPane.showMessageDialog(null, "修改成功！");
			resetVlaue();
			if(!"1".equals(currentUser.getBrowser())){
				agencyTable.removeAll();
			}else{
				fillTable(new Agency());
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "修改失败！");
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
		editnameTxt.setText("");
		editphoneTxt.setText("");
		editremarkTxt.setText("");
		editmanJrb.setSelected(true);
	}

	private void agencyTableMouseEvent(MouseEvent evt) {
		int row=agencyTable.getSelectedRow();
		hiddenAnoTxt.setText((String)agencyTable.getValueAt(row, 0));
		editnameTxt.setText((String)agencyTable.getValueAt(row, 1));
		String sex=(String)agencyTable.getValueAt(row, 2);
		if(sex.equals("男")){
			this.editmanJrb.setSelected(true);
		}else if(sex.equals("女")){
			this.editfemaleJrb.setSelected(true);
		}
		editphoneTxt.setText((String)agencyTable.getValueAt(row, 3));
		editremarkTxt.setText((String)agencyTable.getValueAt(row, 4));
	}

	private void agencySearchActioPerformed(ActionEvent evt) {
		if(!"1".equals(currentUser.getQuery())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		String ano = agencynoTxt.getText();
		String aname = agencynameTxt.getText();
		String sex = "";
		if(manrb.isSelected()){
			sex = "男";
		}else {
			sex = "女";
		}
		Agency agency = new Agency(ano, aname, sex, null, null, null);
		this.fillTable(agency);
	}

	
	private void fillTable(Agency agency ){
		DefaultTableModel dtm=(DefaultTableModel) agencyTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=Dbutils.getCon();
			List<Agency> rst=agencyDao.search(con, agency);
			for(Agency a:rst) {
				Vector<String> v=new Vector<String>();
				v.add(a.getAno());
				v.add(a.getAname());
				v.add(a.getAsex());
				v.add(a.getAphone());
				v.add(a.getAremark());
				dtm.addRow(v);
			}
			
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
