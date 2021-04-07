package complaintsViews;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class SearchComplaint extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button;
	private JLabel searchLabel1;
	private JTextField searchTextField; 
	
	public SearchComplaint() {
		this.setTitle("Search Complaint");
		
		this.searchLabel1 = new JLabel("Enter Complaint Id below");
		this.searchLabel1.setBounds(60,20,160,23);
		
		this.searchTextField = new JTextField();
		this.searchTextField.setBounds(60,50,160,23);
		
		this.button = new JButton("Submit");
		this.button.setBounds(93,90,95,40);
		
		this.setBackground(Color.WHITE);
		this.setBounds(550,260,295,200);
		this.setLayout(null);
		
		
		this.add(searchLabel1);
		this.add(searchTextField);
		this.add(button);
		
		button.addActionListener(this);
		
		this.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		new SearchComplaint();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {

			if (searchTextField.getText().equals("")){
           	 JOptionPane.showMessageDialog(SearchComplaint.this,
         			  	"You cannot submit an empty value.\nPlease try again.",
         			    "Search Complaint Status",
         			    JOptionPane.ERROR_MESSAGE);
            } else {
            	try {
					if (Integer.parseInt(searchTextField.getText()) > 0) {
			           	int input = Integer.parseInt(searchTextField.getText()); 
						System.out.println("Search Complaint Input: "+input);
						JOptionPane.showMessageDialog(SearchComplaint.this,
							  	"Complaint record was found",
							    "Search Complaint Status",
							    JOptionPane.INFORMATION_MESSAGE);
//						 fci.addRowsToJTable(input);
			           	  
					} else if (Integer.parseInt(searchTextField.getText()) < 0) {
		           	  JOptionPane.showMessageDialog(SearchComplaint.this,
						  	"Invalid input.\nId number must not be negative number."
						  	+ "\nPlease try again.",
						    "Search Complaint Status",
						    JOptionPane.WARNING_MESSAGE);
		           	  
		             } else if (Integer.parseInt(searchTextField.getText()) == 0) {
		            	 	JOptionPane.showMessageDialog(SearchComplaint.this,
							  	"Invalid input.\nId number must not be equal to zero."
							  	+ "\nPlease try again.",
							    "Search Complaint Status",
							    JOptionPane.WARNING_MESSAGE);
		            	
		             } 
				 
	            } catch (NumberFormatException e2) {
	            	JOptionPane.showMessageDialog(SearchComplaint.this,
	         			  	"You cannot submit letters.\nPlease try again.",
	         			    "Search Complaint Status",
	         			    JOptionPane.ERROR_MESSAGE);
	            }
            }

		}	
	
	}

	
}



