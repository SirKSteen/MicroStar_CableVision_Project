package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import login.UserLogin;

public class registrationForm extends JFrame{
	
	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField email;
	private JPasswordField passwordField;
	private JPasswordField lblconfirmPassword;
	private JButton btnNewButton;
	
	public registrationForm() {
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 190, 450, 650);
        //setLocation(200,200,20,20);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
		JLabel header = new JLabel("Registration Form");
		header.setFont(new Font("Times New Roman", Font.PLAIN, 25));
	    header.setBounds(130, 30, 325, 50);
	    contentPane.add(header);
	    
	    JLabel lblName = new JLabel("First name");
        //lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(30, 150, 200, 20);
        contentPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Last name");
        //lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(30, 230, 200, 20);
        contentPane.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email\r\n address");
        //lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmailAddress.setBounds(30, 310, 200, 20);
        contentPane.add(lblEmailAddress);
        
        JLabel lblPassword = new JLabel("Password");
       // lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(30, 390, 200, 20);
        contentPane.add(lblPassword);
        
        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        //lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblConfirmPassword.setBounds(30, 470, 200, 20);
        contentPane.add(lblConfirmPassword);
        
        

        firstname = new JTextField();
        //firstname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        firstname.setBounds(230, 150, 200, 20);
        contentPane.add(firstname);
        firstname.setColumns(10);

        lastname = new JTextField();
        //lastname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lastname.setBounds(230, 230, 200, 20);
        contentPane.add(lastname);
        lastname.setColumns(10);
        
        email = new JTextField();
        //email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        email.setBounds(230, 310, 200, 20);
        contentPane.add(email);
        email.setColumns(10);
        
        passwordField = new JPasswordField();
       // passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(230, 390, 200, 20);
        contentPane.add(passwordField);
        
		
        lblconfirmPassword = new JPasswordField();
        lblconfirmPassword.setBounds(230,470,200,20);
        contentPane.add(lblconfirmPassword);
		
        
        btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//what to do here
				
			}
            
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton.setBounds(130, 550, 200, 50);
        contentPane.add(btnNewButton);
        
        setVisible(true);
        
    }
        
		public static void main(String[] args) {
			//UserLogin userlogin = new UserLogin();
		//log frameTabel = new log();
		new registrationForm();
		
	}
}

