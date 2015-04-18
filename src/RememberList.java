import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;


public class RememberList extends JFrame implements ActionListener{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnOk;
	String[][] data = new String[50][4];
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public RememberList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		String[] colHeads = { "Name", "Fee Paid","Pending","Due Date"};
		for (int i = 0; i < 50; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				data[i][j] = "";
			}
		}
		contentPane.setLayout(null);
		
		table = new JTable();
		table.disable();
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
		jsp.setBounds(16, 22, 504, 328);
		jsp.setViewportBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		contentPane.add(jsp);
		table.setDragEnabled(false);
		table.setFillsViewportHeight(true);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(219, 362, 90, 28);
		btnOk.addActionListener(this);
		contentPane.add(btnOk);
		setVisible(true);
		setResizable(false);
		populateTable();
	}
	
	
	// fetch value from database
	public void populateTable()
	{
		Connection con;
		try{
			con = Database.getConnection();
			String fetch = "select name, fee_paid, pending, due_date from student_details where pending > 0 order by pending desc";
			PreparedStatement ps = con.prepareStatement(fetch);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next())
			{
				i++;
			}			
			rs.absolute(0);
			for (int j = 0; j < 50; j++)
			{
				for (int k = 0; k < 4; k++)
				{
					table.setValueAt("", j, k);
				}
			}
			int row= 0;
			while(rs.next())
			{
				for(int col=0;col<4;col++)
				{
					table.setValueAt(rs.getString(col+1),row, col);
				}
				row++;
				if(row >=i)
					break;
			}
		}catch(SQLException e)
		{
			System.out.println(e.getMessage() + " from poulateTable method");
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnOk)
		{
			setVisible(false);
		}
		
	}
}
