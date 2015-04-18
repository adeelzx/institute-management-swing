import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;



public class AdminLogin extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JLabel lblPassword,lblAdminLogin,lblUsername;
	private JButton btnEnter;
	private JPasswordField passwordField;
	

	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setBounds(155, 28, 75, 24);
		contentPane.add(lblAdminLogin);
		
		lblUsername = new JLabel("Username :");
		lblUsername.setBounds(53, 73, 75, 24);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password :");
		lblPassword.setBounds(53, 108, 75, 24);
		contentPane.add(lblPassword);
		
		username = new JTextField();
		username.setBounds(126, 71, 144, 24);
		contentPane.add(username);
		username.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(126, 106, 144, 24);
		contentPane.add(passwordField);
		
		btnEnter = new JButton("Enter");
		btnEnter.setBounds(182, 164, 89, 24);
		btnEnter.addActionListener(this);
		contentPane.add(btnEnter);
		
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					try{
						
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
						SwingUtilities.updateComponentTreeUI(frame);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnEnter)
		{
			if(Database.authenticate(username.getText(), passwordField.getSelectedText()))
			{
				this.setVisible(false);
				
				new StudentDetail().setLocationRelativeTo(null);
			}
			else
			{
				new MyDialog("Username or password is wrong !").setLocationRelativeTo(null);
			}
		}
	}
}
