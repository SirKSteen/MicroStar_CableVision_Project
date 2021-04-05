package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import AccountView.*;
import ComplaintsView.*;
import ResponseView.*;
import UserView.*;

public class MicroMainFrame extends JFrame implements ActionListener{
	
	private JMenu menu1, menu2, menu3, menu4, clock;
	private JButton logoutBtn;
	private JMenuBar menuBar;
	private JMenuItem i1, i2, i3, i4, i5;
	private JMenuItem c1, c2, c3, c4;
	private JMenuItem r1, r2, r3, r4, r5, r6;
	private JMenuItem a1, a2, a3, a4, a5;
	private JDesktopPane desktopPane;
	private JDesktopPane sidePane;
	private JLabel heading; 
	private JPanel contentPane;
	private JInternalFrame intFrame;
//	private JPanel panel; 
	
	
	public MicroMainFrame() { 
		
		menu1 = new JMenu("Users");
		menu1.setPreferredSize(new Dimension(200, menu1.getPreferredSize().height));
		//menu1.setVerticalAlignment(WIDTH);
		menu2 = new JMenu("Complaints");
		menu2.setPreferredSize(new Dimension(200, menu2.getPreferredSize().height));
		menu3 = new JMenu("Responses");
		menu3.setPreferredSize(new Dimension(200, menu3.getPreferredSize().height));
		menu4 = new JMenu("Accounts");
		menu4.setPreferredSize(new Dimension(200, menu4.getPreferredSize().height));
		clock = new JMenu();
		clock.setPreferredSize(new Dimension(370, menu1.getPreferredSize().height));
		logoutBtn = new JButton("LOGOUT");
		
		menuBar= new JMenuBar();
		
		//user drop down menu
		i1 = new JMenuItem("Create User");
		i1.setPreferredSize(new Dimension(200, menu1.getPreferredSize().height));
		i2 = new JMenuItem("View all Users");
		i3 = new JMenuItem("Find user ID");
		i4 = new JMenuItem("Update User");
		i5 = new JMenuItem("Delete User");
		//desktopPane = new JDesktopPane(); 
	    //showForm();
	    
	    //complaints drop down menu
	    c1 = new JMenuItem("Add complaint");
		c1.setPreferredSize(new Dimension(200, menu1.getPreferredSize().height));
		c2 = new JMenuItem("View all Complaints");
		c3 = new JMenuItem("Update Complaint");
		c4 = new JMenuItem("Delete Complaint");
		//desktopPane = new JDesktopPane(); 
	   // showForm();
	    
	  //ResponseView drop down menu
  		r1 = new JMenuItem("Add Response");
  		r1.setPreferredSize(new Dimension(150, r1.getPreferredSize().height));
  		r2 = new JMenuItem("Update Response");
  		r3 = new JMenuItem("All Responses");
  		r4 = new JMenuItem("Response per Complaint");
  		r5 = new JMenuItem("find Complaint by ID");
  		r6 = new JMenuItem("Delete Response");
  		//desktopPane = new JDesktopPane(); 
  	    //showForm();
  	    
  	//AccountView drop down menu
  		a1 = new JMenuItem("Add Acoount");
  		a1.setPreferredSize(new Dimension(200, menu4.getPreferredSize().height));
  		a2 = new JMenuItem("All Accounts");
  		a3 = new JMenuItem("Find Account");
  		a4 = new JMenuItem("Update Account");
  		a5 = new JMenuItem("Delete Account");
  		
  		
  		desktopPane = new JDesktopPane(); 
  		sidePane = new JDesktopPane();
  	    showForm();

	    
	}
	
	public void createWindow() {
		 this.setTitle("MicroStar Frame");
		 this.setBounds(0,0,1500,800);
		 this.getContentPane().setLayout(null);
	     this.setVisible(true);
	     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     this.setResizable(false);
	}
	
	public void setLocationAndSize() { 
		this.desktopPane.setBounds(360,10,900,700);
		this.desktopPane.setBackground(Color.white);
		
//		JInternalFrame intFrame = new JInternalFrame("Our Frame", true, true, true, true);
//	    intFrame.add(sidePane);
		sidePane.setBounds(5, 10, 400, 700);
		sidePane.setBackground(Color.gray);
		
		JLabel label = new JLabel("Micro Star Cable Company", JLabel.CENTER);
		sidePane.add(label);
		setVisible(true);
		
		//actionListeners for the users
		this.i1.addActionListener(this);
		this.i2.addActionListener(this);
		this.i3.addActionListener(this);
		this.i4.addActionListener(this);
		this.i5.addActionListener(this);
		
		//actionListeners for the complaint
		this.c1.addActionListener(this);
		this.c2.addActionListener(this);
		this.c3.addActionListener(this);
		this.c4.addActionListener(this);
		
		//actionListerners for 
		this.r1.addActionListener(this);
		this.r2.addActionListener(this);
		this.r3.addActionListener(this);
		this.r4.addActionListener(this);
		this.r5.addActionListener(this);
		this.r6.addActionListener(this);
		
		//actionListerners for accounts
		this.a1.addActionListener(this);
		this.a2.addActionListener(this);
		this.a3.addActionListener(this);
		this.a4.addActionListener(this);
		this.a5.addActionListener(this);

		//actionListerners for logout button
		this.logoutBtn.addActionListener(this);
	}
	
	public void addComponentsToFrame() {
		menu1.add(i1);
		menu1.add(i2);
		menu1.add(i3);
		menu1.add(i4);
		menu1.add(i5);
		menuBar.add(menu1);
		
		// second menu bar for the complaint
		menu2.add(c1);
		menu2.add(c2); 
		menu2.add(c3);
		menu2.add(c4); 
		menuBar.add(menu2);
		  
		//menu for response
		menu3.add(r1);
		menu3.add(r2);
		menu3.add(r3);
		menu3.add(r4);
		menu3.add(r5);
		menu3.add(r6);
		menuBar.add(menu3);
		 
		//menu for accounts
		menu4.add(a1);
		menu4.add(a2);
		menu4.add(a3);
		menu4.add(a4);
		menu4.add(a5);
		menuBar.add(menu4);
		  
		//clock.add(clock); 
		menuBar.add(clock);
		  
		menuBar.add(logoutBtn);
		 
		
		this.setJMenuBar(menuBar);
		this.add(desktopPane);
		this.add(sidePane);
	}
	
	public void showForm() {
		createWindow();
		setLocationAndSize();
		addComponentsToFrame();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) {
			
			System.out.println("Logout Button clicked!");
		}
		else if (e.getSource() == i1) {
			System.out.println("Create UserView");
			desktopPane.removeAll();
			CreateUser f1 = new CreateUser();
			desktopPane.add(f1).setVisible(true);
			desktopPane.moveToFront(f1);
			f1.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			f1.setLocation(0,0);
		}
		else if (e.getSource() == i2) {
			System.out.println("View all user");
			desktopPane.removeAll();
			viewAllUsers f2 = new viewAllUsers();
			desktopPane.add(f2).setVisible(true);
			desktopPane.moveToFront(f2);
			f2.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			f2.setLocation(0,0);
		}
		else if (e.getSource() == i3) {
			System.out.println("Find user");
			desktopPane.removeAll();
			findByID f3 = new findByID();
			desktopPane.add(f3).setVisible(true);
			desktopPane.moveToFront(f3);
			f3.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			f3.setLocation(0,0);
		}
		else if (e.getSource() == i4) {
			System.out.println("Update UserView");
			desktopPane.removeAll();
			UpdateUser f4 = new UpdateUser();
			desktopPane.add(f4).setVisible(true);
			desktopPane.moveToFront(f4);
			f4.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			f4.setLocation(0,0);
		}
		
		else if (e.getSource() == i5) {
			System.out.println("Delete UserView");
			desktopPane.removeAll();
			deleteUser f5 = new deleteUser();
			desktopPane.add(f5).setVisible(true);
			desktopPane.moveToFront(f5);
			f5.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			f5.setLocation(0,0);
		}
		
		//complaint section
		
		  else if (e.getSource() ==  this.c1) { 
			 System.out.println("Add Complaint");
			 desktopPane.removeAll(); 
			 addComplaint fr1 = new addComplaint(); desktopPane.add(fr1).setVisible(true);
			 desktopPane.moveToFront(fr1); fr1.setSize(desktopPane.getWidth(),
			 desktopPane.getHeight()); fr1.setLocation(0,0);
				 
		  	}
		  else if (e.getSource() ==  this.c2) { 
			 System.out.println("View all Complaints");
			 desktopPane.removeAll(); 
			 viewAllComplaints fr2 = new viewAllComplaints();
			 desktopPane.add(fr2).setVisible(true); 
			 desktopPane.moveToFront(fr2);
			 fr2.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			 fr2.setLocation(0,0); 
			} 
		  else if (e.getSource() ==  this.c3) { 
			 System.out.println("Update Complaint");
			 desktopPane.removeAll(); 
			 updateComplaint fr3 = new updateComplaint();
			 desktopPane.add(fr3).setVisible(true); 
			 desktopPane.moveToFront(fr3);
			 fr3.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			 fr3.setLocation(0,0); 
			} 
		  else if (e.getSource() ==  this.c4) { 
			 System.out.println("Delete Complaint");
			 desktopPane.removeAll(); 
			 deleteComplaint fr4 = new deleteComplaint();
			 desktopPane.add(fr4).setVisible(true); 
			 desktopPane.moveToFront(fr4);
			 fr4.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			 fr4.setLocation(0,0); 
			} 
//ResponseView section
		
		  else if (e.getSource() ==  this.r1) { 
			 System.out.println("Add ResponseView");
			 desktopPane.removeAll(); 
			 addResponse fra1 = new addResponse();
			 desktopPane.add(fra1).setVisible(true); 
			 desktopPane.moveToFront(fra1);
			 fra1.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			 fra1.setLocation(0,0); 
		  	}
		  else if (e.getSource() ==  this.r2) { 
			 System.out.println("View all Complaints");
			 desktopPane.removeAll(); 
			 updateResponse fra2 = new updateResponse();
			 desktopPane.add(fra2).setVisible(true); 
			 desktopPane.moveToFront(fra2);
			 fra2.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			 fra2.setLocation(0,0); 
			} 
		  else if (e.getSource() ==  this.r3) { 
			 System.out.println("View all Complaints");
			 desktopPane.removeAll(); 
			 viewAllResponse fra3 = new viewAllResponse();
			 desktopPane.add(fra3).setVisible(true); 
			 desktopPane.moveToFront(fra3);
			 fra3.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			 fra3.setLocation(0,0); 
			} 
		  else if (e.getSource() ==  this.r4) { 
			 System.out.println("View per Complaint");
			 desktopPane.removeAll(); 
			 viewPerResponse fra4 = new viewPerResponse();
			 desktopPane.add(fra4).setVisible(true); 
			 desktopPane.moveToFront(fra4);
			 fra4.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			 fra4.setLocation(0,0); 
			} 
		  else if (e.getSource() ==  this.r5) { 
			 System.out.println("Find complaint by ID");
			 desktopPane.removeAll(); 
			 findResponse fra5 = new  findResponse();
			 desktopPane.add(fra5).setVisible(true); 
			 desktopPane.moveToFront(fra5);
			 fra5.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			 fra5.setLocation(0,0); 
			} 
		  else if (e.getSource() ==  this.r6) { 
			 System.out.println("Find complaint by ID");
			 desktopPane.removeAll(); 
			 deleteResponse fra6 = new  deleteResponse();
			 desktopPane.add(fra6).setVisible(true); 
			 desktopPane.moveToFront(fra6);
			 fra6.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			 fra6.setLocation(0,0); 
			} 
		
		//AccountView
		  else if (e.getSource() == a1) {
				System.out.println("Create Account");
				desktopPane.removeAll();
				CreateAccount AccFrame1 = new CreateAccount();
				desktopPane.add(AccFrame1).setVisible(true);
				desktopPane.moveToFront(AccFrame1);
				AccFrame1.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				AccFrame1.setLocation(0,0);
			}
			else if (e.getSource() == a2) {
				System.out.println("View all Accounts");
				desktopPane.removeAll();
				allAccount AccFrame2 = new allAccount();
				desktopPane.add(AccFrame2).setVisible(true);
				desktopPane.moveToFront(AccFrame2);
				AccFrame2.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				AccFrame2.setLocation(0,0);
			}
			else if (e.getSource() == a3) {
				System.out.println("Find Account");
				desktopPane.removeAll();
				findAccByID AccFrame3 = new findAccByID();
				desktopPane.add(AccFrame3).setVisible(true);
				desktopPane.moveToFront(AccFrame3);
				AccFrame3.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				AccFrame3.setLocation(0,0);
			}
			else if (e.getSource() == a4) {
				System.out.println("Update Account");
				desktopPane.removeAll();
				updateAccount AccFrame4 = new updateAccount();
				desktopPane.add(AccFrame4).setVisible(true);
				desktopPane.moveToFront(AccFrame4);
				AccFrame4.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				AccFrame4.setLocation(0,0);
			}
			
			else if (e.getSource() == a5) {
				System.out.println("Delete Account");
				desktopPane.removeAll();
				deleteAccount AccFrame5 = new deleteAccount();
				desktopPane.add(AccFrame5).setVisible(true);
				desktopPane.moveToFront(AccFrame5);
				AccFrame5.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				AccFrame5.setLocation(0,0);
			}
		
		
	}
	
	
	
	public void clockFeature() {
		Thread clock = new Thread() {
			public void run() {
				try {
					while(true) {
						GregorianCalendar date = new GregorianCalendar();
			    		
			            Calendar now = Calendar.getInstance();
			            int h = now.get(Calendar.HOUR_OF_DAY);
			            int m = now.get(Calendar.MINUTE);
			            int s = now.get(Calendar.SECOND);
			            //date
			            int day = date.get(Calendar.DAY_OF_MONTH);
			            int month = date.get(Calendar.MONTH);
			            int year = date.get(Calendar.YEAR);
			           
			            //clockField.setText("("+day+"/"+(month+1)+"/"+year+")"+ " Time:" + h + ":" + m + ":" + s);
			            
						sleep(1000);
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of run
		};//end of thread
		clock.start();
    }


	public static void main(String[] args) {
		new MicroMainFrame();

	}

	
	
	
}
