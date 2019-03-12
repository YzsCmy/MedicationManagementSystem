package view;

import java.awt.Component;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;

import utils.Dbutils;
import utils.StringUtils;
import dao.GuideDao;
import dao.UserDao;
import model.Guide;
import model.User;
import sun.swing.table.DefaultTableCellHeaderRenderer;

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
import javax.swing.SwingConstants;

public class UserGuideInterFrm extends JInternalFrame {
	private User currentUser;
	private JTable guideTable;
	private JTextField modelNameTxt;
	
	private JCheckBox query;
	private JCheckBox update;
	private JCheckBox report;
	private JCheckBox browser;

	private GuideDao guideDao=new GuideDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGuideInterFrm frame = new UserGuideInterFrm(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private class TableViewRenderer extends JTextArea implements TableCellRenderer {
		public TableViewRenderer(){
			//将表格设为自动换行
			setLineWrap(true);//利用JTextArea的自动换行方法
		}
		public Component getTableCellRendererComponent(JTable jtable,
				Object obj, boolean isSelected, 
				boolean hasFocus, int row, int column){
			setFont(new Font("宋体", Font.BOLD, 20));
			setText(obj == null ? "" : obj.toString());
			return this;
		}
	}
	/**
	 * Create the frame.
	 */
	public UserGuideInterFrm(User user) {
		currentUser = user;
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 17));
		setClosable(true);
		setIconifiable(true);
		setTitle("使用说明");
		setBounds(100, 100, 835, 714);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label = new JLabel("以上操作均需当前用户拥有对应的权限才可执行");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_1 = new JLabel("当前用户权限：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		query = new JCheckBox("查询");
		query.setFont(new Font("宋体", Font.PLAIN, 17));
		
		browser = new JCheckBox("浏览");
		browser.setFont(new Font("宋体", Font.PLAIN, 17));
		
		update = new JCheckBox("更新");
		update.setFont(new Font("宋体", Font.PLAIN, 17));
		
		report = new JCheckBox("报表");
		report.setFont(new Font("宋体", Font.PLAIN, 17));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
								.addComponent(label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(query, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(browser, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(update, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(report, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(156))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(96)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(query, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(browser, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(update, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(report, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("功能名称：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		modelNameTxt = new JTextField();
		modelNameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		modelNameTxt.setColumns(10);
		
		JButton button = new JButton("查询");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userSearchActioPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(UserGuideInterFrm.class.getResource("/images/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(13)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(modelNameTxt, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
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
						.addComponent(modelNameTxt, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		guideTable = new JTable();
		guideTable.setRowHeight(50);
		guideTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u529F\u80FD\u540D\u79F0", "\u4F7F\u7528\u8BF4\u660E"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		guideTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		guideTable.getColumnModel().getColumn(1).setPreferredWidth(500);
		//设置内容换行
		TableViewRenderer viewRenderer = new TableViewRenderer();
		guideTable.setDefaultRenderer(Object.class, viewRenderer);
		//设置表头居中
		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		guideTable.getTableHeader().setDefaultRenderer(hr);
		
		guideTable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 20));
		scrollPane.setViewportView(guideTable);
		getContentPane().setLayout(groupLayout);
		fillTable("browser");
		fillAuthority();
	}

    //界面下方权限的显示
	private void fillAuthority() {
		// TODO Auto-generated method stub
		if(currentUser.getUpdate().equals("1")){
			update.setSelected(true);
		}
		if(currentUser.getBrowser().equals("1")){
			browser.setSelected(true);
		}
		if(currentUser.getQuery().equals("1")){
			query.setSelected(true);
		}
		if(currentUser.getReport().equals("1")){
			report.setSelected(true);
		}
		query.setEnabled(false);
		update.setEnabled(false);
		browser.setEnabled(false);
		report.setEnabled(false);
	}

	//使用说明查询功能的实现
	private void userSearchActioPerformed(ActionEvent evt) {
		String modelName=modelNameTxt.getText();
		if(StringUtils.isEmpty(modelName)){
			fillTable("browser");
		}else{
			if(modelName.contains("增")){
				this.fillTable("add");
				return;
			}
			if(modelName.contains("删")){
				this.fillTable("delete");
				return;
			}
			if(modelName.contains("改")||modelName.contains("更")||modelName.contains("新")){
				this.fillTable("update");
				return;
			}
			if(modelName.contains("报")||modelName.contains("表")){
				this.fillTable("report");
				return;
			}
		}
		
	}
	
	//表格显示帮助内容
	private void fillTable(String condition){
		DefaultTableModel dtm=(DefaultTableModel) guideTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		con=Dbutils.getCon();
		Guide guide;
		try {
			guide = guideDao.queryGuides(con, condition);
		} finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		Vector<String> v=new Vector<String>();
		if(!StringUtils.isEmpty(guide.getAdd())){
			v.add("增加");
			v.add(guide.getAdd());
			dtm.addRow(v);
		}
		if(!StringUtils.isEmpty(guide.getUpdate())){
			v=new Vector<String>();
			v.add("修改");
			v.add(guide.getUpdate());
			dtm.addRow(v);
		}
		if(!StringUtils.isEmpty(guide.getDelete())){
			v=new Vector<String>();
			v.add("删除");
			v.add(guide.getDelete());
			dtm.addRow(v);
		}
		if(!StringUtils.isEmpty(guide.getReport())){
			v=new Vector<String>();
			v.add("报表");
			v.add(guide.getReport());
			dtm.addRow(v);
		}
	}
}
