package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.AuthController;
import utils.Role;

public class LoginForm extends JFrame implements ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel staffIDLabel;
	private JLabel custIDLabel;
	private JLabel passwordLabel;
	private JTextField idTextField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private AuthController authController;
	
	private Role role;
	
	
	public LoginForm() {
		super();
		
		this.staffIDLabel = new JLabel("STAFF ID");
		this.custIDLabel = new JLabel("CUSTOMER ID");
		this.passwordLabel = new JLabel("PASSWORD");
		this.idTextField = new JTextField();
		this.passwordField = new JPasswordField();
		this.loginButton = new JButton("LOGIN");
		
		 this.authController = new AuthController();
		 role = null;
	}
	
	
	 public void createWindow()
	    {
	       //Setting properties of JFrame; i.e this frame
	        this.setTitle("Login Form");
	        this.setBounds(550,100,400,220);
	        this.getContentPane().setBackground(Color.white);
	        this.getContentPane().setLayout(null);
	        this.setVisible(true);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setResizable(false);
	        
	    }
	 public void setLocationAndSize() {
		 this.staffIDLabel.setBounds(20,20,80,70);
		 this.custIDLabel.setBounds(20,20,150,70);
	     this.passwordLabel.setBounds(20,70,80,70);
	     
	     this.staffIDLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
	     this.custIDLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
	     this.idTextField.setFont(new Font("Times New Roman", Font.BOLD,12));
	     this.passwordField.setFont(new Font("Times New Roman", Font.BOLD,12));
	     this.passwordLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
	     this.loginButton.setFont(new Font("Times New Roman", Font.BOLD,12));
	     
	     this.idTextField.setBounds(180,43,165,23);
	     this.passwordField.setBounds(180,93,165,23);
	     
	     this.loginButton.setBounds(180,130,165,30); 
	    
	 }
	 
	 public void addComponentsToFrame(Role role) {
		 if(role == Role.REPRESENTATIVE || role == Role.TECHNICIAN) {
			 this.add(this.staffIDLabel);
		 }else {
			 this.add(this.custIDLabel);
		 }
		 this.add(this.passwordLabel);
		 this.add(this.idTextField);
		 this.add(this.passwordField);
		 this.add(this.loginButton);
		 
		 this.loginButton.addActionListener(this);
		 this.role = role;
	 }
	 
	 public void showForm(Role role) {
    	 this.createWindow(); 
    	 this.setLocationAndSize(); 
    	 this.addComponentsToFrame(role);
    }
	 
	 public void reset() {
		 this.idTextField.setText("");
		 this.passwordField.setText("");
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.loginButton) {
			try {
				int userId = Integer.parseInt(this.idTextField.getText());
				char[] password = this.passwordField.getPassword();
				
				this.authController.login(userId, String.copyValueOf(password),this.role);
				
				JOptionPane.showMessageDialog(null,"User logged in successfully.");
				this.reset();
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,e2.getMessage());
			}
			
		}
		
	}

}
