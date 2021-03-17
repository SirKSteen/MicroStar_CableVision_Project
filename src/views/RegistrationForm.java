package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.AuthController;
import controllers.UserController;
import models.User;
import utils.CustomizedException;
import utils.Role;

public class RegistrationForm extends JFrame implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//local variables
	private String [] roles;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel roleLabel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JComboBox<String> roleComboBox;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailTextField;
    private JButton registerButton;
    private JButton resetButton;
    private AuthController authController;
    
    
	public RegistrationForm()
	    {
		
		super();
		//labels
		this.roles = new String[] {"Customer","Representative","Technician"};
		this.firstNameLabel=new JLabel("FIRST NAME");
	    this.lastNameLabel=new JLabel("LAST NAME");
	    this.emailLabel=new JLabel("EMAIL");
	    this.passwordLabel=new JLabel("PASSWORD");
	    this.confirmPasswordLabel=new JLabel("CONFIRM PASSWORD");
	    this.roleLabel = new JLabel("Role");
	    
	    //input fields
	    this.firstNameTextField=new JTextField();
	    this.lastNameTextField=new JTextField();
	    this.emailTextField=new JTextField();
	    this.passwordField=new JPasswordField();
	    this.confirmPasswordField=new JPasswordField();
	    this.roleComboBox=new JComboBox<String>(this.roles);
	    this.roleComboBox.setSelectedIndex(0);
	    this.registerButton=new JButton("REGISTER");
	    this.resetButton=new JButton("RESET");
	    this.authController = new AuthController();
	      
	    }
	
    public void createWindow()
    {
       //Setting properties of JFrame; i.e this frame
        this.setTitle("Registration Form");
        this.setBounds(550,100,400,550);
        this.getContentPane().setBackground(Color.white);
        this.getContentPane().setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
    }
    
    
    public void setLocationAndSize()
    {
        //Setting Location and Size of Each Component
        this.firstNameLabel.setBounds(20,20,80,70);
        this.lastNameLabel.setBounds(20,70,80,70);
        this.emailLabel.setBounds(20,120,100,70);
        this.passwordLabel.setBounds(20,170,100,70);
        this.confirmPasswordLabel.setBounds(20,220,140,70);
        this.roleLabel.setBounds(20,270,100,70);
        
        //set label fonts
        this.firstNameLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
        this.lastNameLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
        this.emailLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
        this.passwordLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
        this.confirmPasswordLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
        this.roleLabel.setFont(new Font("Times New Roman", Font.BOLD,12));
        
        //set field fonts
        this.firstNameTextField.setFont(new Font("Times New Roman", Font.BOLD,12));
        this.lastNameTextField.setFont(new Font("Times New Roman", Font.BOLD,12));
        this.emailTextField.setFont(new Font("Times New Roman", Font.BOLD,12));
        this.passwordField.setFont(new Font("Times New Roman", Font.BOLD,12));
        this.confirmPasswordField.setFont(new Font("Times New Roman", Font.BOLD,12));
        this.roleComboBox.setFont(new Font("Times New Roman", Font.BOLD,12));
        
        this.firstNameTextField.setBounds(180,43,165,23);
        this.lastNameTextField.setBounds(180,93,165,23);
        this.emailTextField.setBounds(180,143,165,23);
        this.passwordField.setBounds(180,193,165,23);
        this.confirmPasswordField.setBounds(180,243,165,23);
        this.roleComboBox.setBounds(180,293,165,23);
        this.registerButton.setBounds(70,400,100,35);
        this.resetButton.setBounds(220,400,100,35);     
        
    }
    
    public void showForm() {
    	 this.createWindow(); 
    	 this.setLocationAndSize(); 
    	 this.addComponentsToFrame();
    }
    
    public void addComponentsToFrame() {
    	this.add(this.firstNameLabel);
    	this.add(this.lastNameLabel);
    	this.add(this.emailLabel);
    	this.add(this.passwordLabel);
    	this.add(this.confirmPasswordLabel);
    	this.add(this.roleLabel);
    	
    	this.add(this.firstNameTextField);
    	this.add(this.lastNameTextField);
    	this.add(this.emailTextField);
    	this.add(this.passwordField);
    	this.add(this.confirmPasswordField);
    	this.add(this.roleComboBox);
    	
    	this.add(this.registerButton);
    	this.add(this.resetButton);
    	
    	this.registerButton.addActionListener(this);
    	this.resetButton.addActionListener(this);
    	

    
    }
	
    
    private void reset() {
    	this.firstNameTextField.setText("");
    	this.lastNameTextField.setText("");
    	this.emailTextField.setText("");
    	this.passwordField.setText("");
    	this.confirmPasswordField.setText("");
    	this.roleComboBox.setSelectedIndex(0);
    }
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		if(e.getSource() == this.registerButton) {
			User user = new User();
			user.setFirstName(this.firstNameTextField.getText());
			user.setLastName(this.lastNameTextField.getText());
			user.setEmail(this.emailTextField.getText());
			user.setPassword(this.passwordField.getPassword().toString()) ;
			switch (((String) this.roleComboBox.getSelectedItem()).toLowerCase()) {
		    case "customer": 
			user.setRole(Role.CUSTOMER);
			break;
			case "representative":
				user.setRole(Role.REPRESENTATIVE);
				break;
			case "technician":
				user.setRole(Role.TECHNICIAN);
				break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.roleComboBox.getSelectedItem());
		}
			
			//TODO validate form
			if(Arrays.equals(this.passwordField.getPassword(), this.confirmPasswordField.getPassword())) {
				try {
					int userId = this.authController.register(user);
					if(userId > 0) {
						 JOptionPane.showMessageDialog(null,"User Registered Successfully");
						 this.reset();
						
					}
				} catch (CustomizedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					 JOptionPane.showMessageDialog(null,e1.getMessage());
				}
				
			}else {
				 JOptionPane.showMessageDialog(null,"Confirm password does not match.");
			}
		
			
		}else if(e.getSource() == this.resetButton) {
			this.reset();
		}
		
	}

	
}
