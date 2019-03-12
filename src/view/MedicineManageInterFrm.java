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
import dao.MedicineDao;
import model.Agency;
import model.Medicine;
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

public class MedicineManageInterFrm extends JInternalFrame {
	private User currentUser;
	
	private JRadioButton inrb;
	private JRadioButton outrb;
	
	private JTable medicineTable;
	private JTextField mnoTxt;
	private JTextField mnameTxt;

	private MedicineDao medicineDao=new MedicineDao();
	private JTextField editmnameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JTextArea editeffectTxt;
	private JRadioButton editInJrb;
	private JRadioButton editOutJrb;
	private JTextField hiddenMnoTxt;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicineManageInterFrm frame = new MedicineManageInterFrm(null);
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
	public MedicineManageInterFrm(User u) {
		currentUser = u;
		setClosable(true);
		setIconifiable(true);
		setTitle("药品管理");
		setBounds(100, 100, 839, 764);
		
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
		
		JLabel label_2 = new JLabel("名称：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editmnameTxt = new JTextField();
		editmnameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		editmnameTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("服用方法：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editInJrb = new JRadioButton("内服");
		editInJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		buttonGroup.add(editInJrb);
		editInJrb.setSelected(true);
		
		editOutJrb = new JRadioButton("外用");
		editOutJrb.setFont(new Font("宋体", Font.PLAIN, 17));
		buttonGroup.add(editOutJrb);
		
		JLabel label_7 = new JLabel("功效：");
		label_7.setFont(new Font("宋体", Font.PLAIN, 17));
		
		editeffectTxt = new JTextArea();
		editeffectTxt.setLineWrap(true);
		editeffectTxt.setFont(new Font("Monospaced", Font.PLAIN, 17));
		
		JButton button_1 = new JButton("修改");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicineUpdateActionPerform(e);
			}
		});
		button_1.setIcon(new ImageIcon(MedicineManageInterFrm.class.getResource("/images/modify.png")));
		
		JButton button_2 = new JButton("删除");
		button_2.setFont(new Font("宋体", Font.PLAIN, 17));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicineDelActioPerformed(e);
			}
		});
		button_2.setIcon(new ImageIcon(MedicineManageInterFrm.class.getResource("/images/delete.png")));
		
		hiddenMnoTxt = new JTextField();
		hiddenMnoTxt.setVisible(false);
		hiddenMnoTxt.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editInJrb)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editOutJrb))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editmnameTxt, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)))
							.addGap(340)
							.addComponent(hiddenMnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(23)
							.addComponent(button_1)
							.addGap(72)
							.addComponent(button_2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_7)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editeffectTxt, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)))
					.addGap(100))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(hiddenMnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(editmnameTxt, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(editInJrb)
						.addComponent(editOutJrb))
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(editeffectTxt, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("编号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		mnoTxt = new JTextField();
		mnoTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		mnoTxt.setColumns(10);
		
		JLabel label = new JLabel("名称：");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		
		mnameTxt = new JTextField();
		mnameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		mnameTxt.setColumns(10);
		
		JButton button = new JButton("查询");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicineSearchActioPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(MedicineManageInterFrm.class.getResource("/images/search.png")));
		
		JLabel label_1 = new JLabel("服用方法：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		inrb = new JRadioButton("内服");
		buttonGroup_1.add(inrb);
		inrb.setFont(new Font("宋体", Font.PLAIN, 17));
		inrb.setSelected(true);
		
		outrb = new JRadioButton("外用");
		buttonGroup_1.add(outrb);
		outrb.setFont(new Font("宋体", Font.PLAIN, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(13)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mnoTxt, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mnameTxt, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(inrb, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(outrb, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
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
						.addComponent(mnoTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(mnameTxt, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(inrb)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(outrb)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		
		medicineTable = new JTable();
		medicineTable.setFont(new Font("宋体", Font.PLAIN, 17));
		medicineTable.setRowHeight(25);
		medicineTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				medicineTableMouseEvent(e);
			}
		});
		medicineTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u540D\u79F0", "\u670D\u7528\u65B9\u6CD5", "\u529F\u6548"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(medicineTable);
		getContentPane().setLayout(groupLayout);
		// 设置文本域边框
		editeffectTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		
		if("1".equals(currentUser.getBrowser())){
			fillTable(new Medicine());
		}

	}
	private void medicineDelActioPerformed(ActionEvent e) {
		if(!"1".equals(currentUser.getUpdate())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		int[] nums=medicineTable.getSelectedRows();
		if(nums.length==0){
			JOptionPane.showMessageDialog(null, "请选择要删除的数据！");
			return ;
		}
		int confNum=JOptionPane.showConfirmDialog(null, "您确定要删除吗？");
		if(confNum==0){
			Connection con=null;
			try {
				con = Dbutils.getCon();
				con.setAutoCommit(false);
				for(int num:nums){
					String id=(String) medicineTable.getValueAt(num, 0);
					medicineDao.delete(con, id);
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
					e1.printStackTrace();
				}
			}
			JOptionPane.showMessageDialog(null, "删除成功！");
			resetVlaue();
			if(!"1".equals(currentUser.getBrowser())){
				medicineTable.removeAll();
			}else{
				fillTable(new Medicine());
			}
		}
	}

	private void medicineUpdateActionPerform(ActionEvent evt) {
		if(!"1".equals(currentUser.getUpdate())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		if(StringUtils.isEmpty(hiddenMnoTxt.getText())){
			JOptionPane.showMessageDialog(null, "请选择要修改的药品信息!");
		}
		String mno=hiddenMnoTxt.getText();
		String mname=editmnameTxt.getText();
		String meffect=editeffectTxt.getText();
		String mmode=null;
		if(StringUtils.isEmpty(mno)){
			JOptionPane.showMessageDialog(null, "编号不能为空！");
			return ;
		}
		if(mno.length()>12){
			JOptionPane.showMessageDialog(null, "编号长度超出限制！");
			return ;
		}
		if(StringUtils.isEmpty(mname)){
			JOptionPane.showMessageDialog(null, "名称不能为空！");
			return ;
		}
		if(editInJrb.isSelected()){
			mmode="内服";
		}else if(editOutJrb.isSelected()){
			mmode="外用";
		}
		
		Connection con=null;
		String id = hiddenMnoTxt.getText();
		
		Medicine medicine = new Medicine(mno, mname, mmode, meffect, null);
		try {
			con=Dbutils.getCon();
			medicineDao.modify(con, medicine, id);
			JOptionPane.showMessageDialog(null, "修改成功！");
			resetVlaue();
			if(!"1".equals(currentUser.getBrowser())){
				medicineTable.removeAll();
			}else{
				fillTable(new Medicine());
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
		editmnameTxt.setText("");
		editeffectTxt.setText("");
		editInJrb.setSelected(true);
	}

	private void medicineTableMouseEvent(MouseEvent evt) {
		int row=medicineTable.getSelectedRow();
		hiddenMnoTxt.setText((String)medicineTable.getValueAt(row, 0));
		editmnameTxt.setText((String)medicineTable.getValueAt(row, 1));
		String mmode=(String)medicineTable.getValueAt(row, 2);
		if(mmode.equals("内服")){
			this.editInJrb.setSelected(true);
		}else if(mmode.equals("外用")){
			this.editOutJrb.setSelected(true);
		}
		editeffectTxt.setText((String)medicineTable.getValueAt(row, 3));
	}

	private void medicineSearchActioPerformed(ActionEvent evt) {
		if(!"1".equals(currentUser.getQuery())){
			JOptionPane.showMessageDialog(null, "暂无权限！");
			return ;
		}
		String mno = mnoTxt.getText();
		String mname = mnameTxt.getText();
		String mode = "";
		if(inrb.isSelected()){
			mode = "内服";
		}else {
			mode = "外用";
		}
		Medicine medicine = new Medicine(mno, mname, mode, null, null);
		this.fillTable(medicine);
	}

	
	private void fillTable(Medicine medicine){
		DefaultTableModel dtm=(DefaultTableModel) medicineTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=Dbutils.getCon();
			List<Medicine> rst=medicineDao.search(con, medicine);
			for(Medicine a:rst) {
				Vector<String> v=new Vector<String>();
				v.add(a.getMno());
				v.add(a.getMname());
				v.add(a.getMmode());
				v.add(a.getMefficacy());
				dtm.addRow(v);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
