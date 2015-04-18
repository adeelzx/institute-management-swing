import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class StudentDetail extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField course;
	private JTextField batch;
	private DateTextField joinDate;
	private DateTextField endDate;
	private JTextField feePaid;
	private JTextField pending;
	private DateTextField dueDate;
	private GroupLayout groupPanel;
	private JTable table;
	private JButton btnAddStudent ,btnUpdateStudent,btnReset, btnDelete, btnUpdate, btnReminderList,btnExportToExcel;
	private JComboBox<String> comboBox;
	private JComboBox<String> timeModel;
	String newName;

	/**
	 * Create the frame.
	 */
	public StudentDetail() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1152, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 337, 441);
		panel.setBorder(BorderFactory.createTitledBorder(null, "Fill Details", 4, 2));
		contentPane.add(panel);

		JLabel lblName = new JLabel("Name :");

		name = new JTextField();
		name.addKeyListener( new KeyAdapter() {
			public void keyTyped(KeyEvent e)
			{
				char key = e.getKeyChar();
				if(!((key >= 'a' && key <= 'z') || (key >= 'A' && key <= 'Z') || key == ' '))
				{
					e.consume();					
				}
				
				if(name.getText().length() == 0)
				{
					if(key == ' ')
						e.consume();
				}
				
				if(name.getText().length() > 0 && name.getText().contains(" ") )
				{
					if(key == ' ')
						e.consume();
				}
			}
			
		});
		name.setColumns(30);

		JLabel lblCourse = new JLabel("Course :");

		course = new JTextField();
		course.setEditable(false);
		course.setColumns(10);

		JLabel lblBatchTime = new JLabel("Batch Time :");

		batch = new JTextField();
		batch.setEditable(false);
		batch.setColumns(10);

		JLabel lblJoinDate = new JLabel("Join Date :");

		JLabel lblEndDate = new JLabel("End Date :");

		JLabel lblTotalFee = new JLabel("Fee Paid :");

		JLabel lblPending = new JLabel("Pending:");

		joinDate = new DateTextField();
		joinDate.setColumns(10);

		endDate = new DateTextField();
		endDate.setColumns(10);

		feePaid = new JTextField();
		feePaid.setColumns(10);
		feePaid.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e)
			{
				char k = e.getKeyChar();
				if ((k < '0') || (k > '9'))
					e.consume();
			}
		});

		pending = new JTextField();
		pending.setColumns(10);
		pending.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e)
			{
				char k = e.getKeyChar();
				if((k<'0') || (k>'9'))
					e.consume();
			}
		});

		JLabel lblDueDate = new JLabel("Due Date :");

		dueDate = new DateTextField();
		dueDate.setColumns(10);

		btnAddStudent = new JButton("Add Student");
		btnAddStudent.setFocusPainted(false);
		btnAddStudent.addActionListener(this);

		btnUpdateStudent = new JButton("Update Student");
		btnUpdateStudent.setFocusPainted(false);
		btnUpdateStudent.addActionListener(this);

		btnReset = new JButton("Reset");
		btnReset.setFocusPainted(false);
		btnReset.addActionListener(this);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				course.setText(" " + ((JComboBox<?>)e.getSource()).getSelectedItem());
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Core Java", "Adv Java", "C/C++", ".NET", "OCJP"}));
		
		timeModel = new JComboBox<String>();
		timeModel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				batch.setText(" " + ((JComboBox<?>)e.getSource()).getSelectedItem());
			}
		});
		
		timeModel.setModel(new DefaultComboBoxModel<String>(new String[] {"7-11 AM", "12-4 PM", "5-9PM"}));

		groupPanel = new GroupLayout(panel);
		groupPanel.setHorizontalGroup(
			groupPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(groupPanel.createSequentialGroup()
					.addGroup(groupPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(groupPanel.createSequentialGroup()
							.addGap(7)
							.addGroup(groupPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDueDate)
								.addGroup(groupPanel.createSequentialGroup()
									.addGroup(groupPanel.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupPanel.createSequentialGroup()
											.addGroup(groupPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(groupPanel.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(lblName))
												.addComponent(lblCourse)
												.addComponent(lblBatchTime))
											.addGap(7))
										.addGroup(groupPanel.createSequentialGroup()
											.addGroup(groupPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblTotalFee, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
												.addComponent(lblJoinDate, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
												.addComponent(lblPending, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
											.addGap(4))
										.addGroup(groupPanel.createSequentialGroup()
											.addComponent(lblEndDate, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
											.addGap(12)))
									.addGroup(groupPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupPanel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(dueDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(feePaid)
										.addComponent(endDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(joinDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(groupPanel.createSequentialGroup()
											.addGroup(groupPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(batch, Alignment.LEADING)
												.addComponent(course, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(timeModel, 0, 96, Short.MAX_VALUE)
												.addComponent(comboBox, 0, 96, Short.MAX_VALUE)))
										.addComponent(name, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
										.addComponent(pending))
									.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE))))
						.addGroup(groupPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAddStudent)
							.addGap(27)
							.addComponent(btnUpdateStudent, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupPanel.createSequentialGroup()
							.addGap(111)
							.addComponent(btnReset)))
					.addContainerGap())
		);
		groupPanel.setVerticalGroup(
			groupPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(groupPanel.createSequentialGroup()
					.addGap(7)
					.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCourse)
						.addComponent(course, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBatchTime)
						.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(batch, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(timeModel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblJoinDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(joinDate, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndDate, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(endDate, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalFee, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(feePaid, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPending, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(pending, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDueDate, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(dueDate, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddStudent)
						.addComponent(btnUpdateStudent))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addComponent(btnReset)
					.addGap(20))
		);
		panel.setLayout(groupPanel);
		
		String[][] data = new String[150][8];
		String[] colHeads = { "Name", "Course", "Batch", "Join Date", "End Date", "Fee Paid","Pending","Due Date" };
		for (int i = 0; i < 150; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				data[i][j] = "";
			}
		}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(data, colHeads)
		{
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false,false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		JScrollPane jsp = new JScrollPane(this.table);
		jsp.setViewportBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		jsp.setBounds(364, 34, 741, 328);
		contentPane.add(jsp);
		table.setDragEnabled(false);
		table.setFillsViewportHeight(true);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(397, 397, 90, 28);
		btnDelete.setFocusPainted(false);
		btnDelete.addActionListener(this);
		contentPane.add(btnDelete);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(534, 397, 90, 28);
		btnUpdate.setFocusPainted(false);
		btnUpdate.addActionListener(this);
		contentPane.add(btnUpdate);
		
		btnReminderList = new JButton("Reminder List");
		btnReminderList.setBounds(663, 397, 114, 28);
		btnReminderList.setFocusPainted(false);
		btnReminderList.addActionListener(this);
		contentPane.add(btnReminderList);
		
		btnExportToExcel = new JButton("Export to Excel");
		btnExportToExcel.addActionListener(this);
		btnExportToExcel.setFocusPainted(false);
		btnExportToExcel.setBounds(800, 400, 131, 23);
		contentPane.add(btnExportToExcel);
		setVisible(true);
		setResizable(false);
		updateTable();
	}
	
	// method to insert data to database table
	public void insertRow()
	{
		
		Connection con;
		try{
			con = Database.getConnection();
			String Name = name.getText();
			char ch = Name.charAt(0);
			if ((ch >= 'a') && (ch <= 'z'))
			{
				ch = (char)(ch - ' ');
				Name = ch + Name.substring(1);
				newName = Name;
				if(Name.contains(" "))
				{
					int a = Name.indexOf(" ");
					 a = a+1;
					 
					 char secondName = Name.charAt(a);
					 if (secondName >= 'a' && secondName <= 'z')
						{
							secondName = (char)(secondName - ' ');
							StringBuilder myName = new StringBuilder(Name);
							myName.setCharAt(a, secondName);
							newName = myName.toString();
						}
				}
			}
			
			
			String insert ="insert into student_details (name, course, batch_time, join_date, end_date, fee_paid, pending, due_date) values (?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setString(1, newName);
			ps.setString(2, course.getText());
			ps.setString(3, batch.getText());
			ps.setString(4, joinDate.getText());
			ps.setString(5, endDate.getText());
			ps.setString(6, feePaid.getText());
			ps.setString(7, pending.getText());
			ps.setString(8, dueDate.getText());
			ps.executeUpdate();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	//method to show data in table
	public void updateTable()
	{
		Connection con;
		try {
			con = Database.getConnection();
			String fetch ="select * from student_details where 1";
			PreparedStatement ps = con.prepareStatement(fetch);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next())
			{
				i++;
			}			
			rs.absolute(0);
			for (int j = 0; j < 150; j++)
			{
				for (int k = 0; k < 8; k++)
				{
					table.setValueAt("", j, k);
				}
			}
			int row =0;
			while(rs.next())
			{ 
				for(int col=0;col<8;col++)
				{
					table.setValueAt(rs.getString(col+1), row, col);
				}
				row++;
				if(row >= i)
					break;
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage()+ " from updateTable method");
		}

	}

	// method to reset text field
	public void reset()
	{
		name.setText(null);
		course.setText(null);
		batch.setText(null);
		feePaid.setText(null);
		pending.setText(null);	
	}
	
	// method to modify details
	public void modifyDetail()
	{
		Connection con;
		try {
			con = Database.getConnection();
			String modify = "update student_details set name =?,course=?, batch_time=?,join_date=?,end_date=?,fee_paid=?, pending=?, due_date=? where name =?";
			PreparedStatement ps = con.prepareStatement(modify);
			ps.setString(1, name.getText());
			ps.setString(2, course.getText());
			ps.setString(3, batch.getText());
			ps.setString(4, joinDate.getText());
			ps.setString(5, endDate.getText());
			ps.setString(6, feePaid.getText());
			ps.setString(7, pending.getText());
			ps.setString(8,dueDate.getText());
			ps.setString(9, name.getText());
			ps.executeUpdate();
			updateTable();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " from modifyDetails method");
		}
	}
	
	// select row data to update the table
	public void updateRow()
	{	
		String name = (String)table.getValueAt(table.getSelectedRow(), 0);
		String course = (String)table.getValueAt(table.getSelectedRow(), 1);
		String batch = (String)table.getValueAt(table.getSelectedRow(), 2);
		String join = (String)table.getValueAt(table.getSelectedRow(), 3);
		String end = (String)table.getValueAt(table.getSelectedRow(), 4);
		String feePaid = (String)table.getValueAt(table.getSelectedRow(), 5);
		String pending = (String)table.getValueAt(table.getSelectedRow(), 6);
		String dueDate = (String)table.getValueAt(table.getSelectedRow(), 7);
		
		this.name.setText(name);
		this.course.setText(course);
		this.batch.setText(batch);
		this.joinDate.setText(join);
		this.endDate.setText(end);
		this.feePaid.setText(feePaid);
		this.pending.setText(pending);
		this.dueDate.setText(dueDate);
	}
	
	// delete the obsolete record
	public void deleteRow()
	{
		Connection con;
		try {
			con = Database.getConnection();
			String name = (String)table.getValueAt(table.getSelectedRow(), 0);
			String delete = "delete from student_details where name=? ";
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setString(1,name);
			ps.executeUpdate();
			updateTable();
		} catch (SQLException e) {
			System.out.println(e.getMessage() +" from deleteRow method");
		}
	}

	public void toExcel(JTable table, File file){
		try{
			TableModel model = table.getModel();
			FileWriter excel = new FileWriter(file);

			for(int i = 0; i < model.getColumnCount(); i++){
				excel.write(model.getColumnName(i) + "\t");
			}

			excel.write("\n");

			for(int i=0; i< model.getRowCount(); i++) {
				for(int j=0; j < model.getColumnCount(); j++) {
					excel.write(model.getValueAt(i,j).toString()+"\t");
				}
				excel.write("\n");
			}

			excel.close();
		}catch(IOException e){ System.out.println(e); }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == btnAddStudent)
		{
			if(name.getText().length() == 0 || course.getText().length() == 0 || batch.getText().length() == 0 || joinDate.getText().length() == 0 || endDate.getText().length() == 0 || feePaid.getText().length() == 0 || pending.getText().length() == 0 || dueDate.getText().length() == 0)
			{	
				new MyDialog("All fields are mandatory.Please fill the empty fields! ").setLocationRelativeTo(null);
			}
			else if(name.getText().charAt(0) == ' '){
				
				new MyDialog("First character cannot be special character!");
			}
			else
			{
				insertRow();
				updateTable();				
			}
		}
		
		if(e.getSource() == btnDelete)
		{
			if(table.getValueAt(table.getSelectedRow(), 0).toString().length() == 0)
			{
				new MyDialog("          Its empty row! Please select another row..").setLocationRelativeTo(null);
			}
			else
			{
				deleteRow();							
			}
			
		}
		
		if(e.getSource() == btnReset)
		{
			reset();
		}
		
		if(e.getSource() == btnUpdate)
		{
			if(table.getValueAt(table.getSelectedRow(), 0).toString().length() == 0)
			{
				new MyDialog("          Its empty row! Please select another row..").setLocationRelativeTo(null);
			}
			else
			{
				updateRow();				
			}
		}
		
		if(e.getSource() == btnUpdateStudent)
		{
			modifyDetail();
		}
		
		if(e.getSource() == btnReminderList)
		{
			new RememberList().setLocationRelativeTo(null);
		}
		
		if(e.getSource() == btnExportToExcel)
		{
			JFileChooser fc = new JFileChooser();
			int option = fc.showSaveDialog(StudentDetail.this);
			if(option == JFileChooser.APPROVE_OPTION){
				String filename = fc.getSelectedFile().getName(); 
				String path = fc.getSelectedFile().getParentFile().getPath();

				int len = filename.length();
				String ext = "";
				String file = "";

				if(len > 4){
					ext = filename.substring(len-4, len);
				}

				if(ext.equals(".xls")){
					file = path + "\\" + filename; 
				}else{
					file = path + "\\" + filename + ".xls"; 
				}
				toExcel(table,new File(file));
			}
		
		}
	}
}
