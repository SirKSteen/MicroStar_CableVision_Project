package AccountView;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class deleteAccount extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	
	
	public deleteAccount() {
		showForm();
	}
	
	public void createWindow() {
		this.setTitle("Delete Account");
		this.setBounds(0,0,490,580);
		this.getContentPane().setLayout(null);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    this.setResizable(false);
	}
	 
	
	public void showForm() {
		createWindow();

	}
	

	public static void main(String[] args) {
		new deleteAccount();

	}

}
