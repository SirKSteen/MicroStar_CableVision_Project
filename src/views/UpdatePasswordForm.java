package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.AuthController;

public class UpdatePasswordForm  extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel oldPasswordLabel;
    private JLabel newPasswordLabel;
    private JLabel confirmPasswordLabel;
    
    private JTextField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton updateButton;
	
    private AuthController authController;
    
    public UpdatePasswordForm() {
    	super();
    	this.oldPasswordLabel = new JLabel("OLD PASSWORD");
    	this.newPasswordLabel = new JLabel("NEW PASSWORD");
    	this.confirmPasswordLabel = new JLabel("CONFIRM PASSWORD");
    	this.updateButton = new JButton("SAVE CHANGES");
    	this.oldPasswordField = new JTextField();
    	this.newPasswordField = new JPasswordField();
    	this.confirmPasswordField = new JPasswordField();
    	this.authController = new AuthController();
    }
    
    public void createWindow()
    {
       //Setting properties of JFrame; i.e this frame
        this.setTitle("Update Password Form");
        this.setBounds(550,100,400,300);
        this.getContentPane().setBackground(Color.white);
        this.getContentPane().setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
    }
    
    public void setLocationAndSize() {
    	this.oldPasswordLabel.setBounds(20,20,150,70);
	    this.newPasswordLabel.setBounds(20,70,150,70);
	    this.confirmPasswordLabel.setBounds(20,120,150,70);
	    
	    this.oldPasswordField.setFont(new Font("Times New Roman", Font.BOLD,12));
	    this.newPasswordField.setFont(new Font("Times New Roman", Font.BOLD,12));
	    this.confirmPasswordField.setFont(new Font("Times New Roman", Font.BOLD,12));
	    this.oldPasswordLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
	    this.newPasswordLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
	    this.confirmPasswordLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
	     
	    this.oldPasswordField.setBounds(180,43,165,23);
	    this.newPasswordField.setBounds(180,93,165,23);
	    this.confirmPasswordField.setBounds(180,143,165,23);
	    this.updateButton.setBounds(180,180,165,30);
	    this.updateButton.addActionListener(this);
    }
    
    public void addComponentsToFrame() {
    	
    	this.add(this.oldPasswordLabel);
    	this.add(this.newPasswordLabel);
    	this.add(this.confirmPasswordLabel);
    	this.add(this.oldPasswordField);
    	this.add(this.newPasswordField);
    	this.add(this.confirmPasswordField);
    	this.add(this.updateButton);
    }
    
    public void showForm() {
   	 this.createWindow(); 
   	 this.setLocationAndSize(); 
   	 this.addComponentsToFrame();
   }
    
    public void reset() {
    	this.oldPasswordField.setText("");
    	this.newPasswordField.setText("");
    	this.confirmPasswordField.setText("");
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		char[] newPassword = this.newPasswordField.getPassword();
		String oldPassword = this.oldPasswordField.getText();
		if(Arrays.equals(this.newPasswordField.getPassword(), this.confirmPasswordField.getPassword())) {
			try {
				this.authController.updatePassword(0, oldPassword, String.copyValueOf(newPassword));
				JOptionPane.showMessageDialog(null,"Password updated succesfully");
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,e2.getMessage());
			}
		}else {
			JOptionPane.showMessageDialog(null,"Confirm Password must match Password.");
		}
		
	}

	
}
