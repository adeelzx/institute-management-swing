import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MyDialog extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnOK;
	private JLabel lblMsg;

	public MyDialog(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMsg = new JLabel(msg);
		lblMsg.setBounds(50, 23, 331, 31);
		contentPane.add(lblMsg);
		
		btnOK = new JButton("OK");
		btnOK.setBounds(184, 65, 70, 22);
		btnOK.setFocusPainted(false);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnOK)
				{
					setVisible(false);
				}
			}
		});
		contentPane.add(btnOK);
		
		setVisible(true);
		setResizable(false);
	}

}
