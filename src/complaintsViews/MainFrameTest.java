package complaintsViews;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import complaintsViews.ComplaintView;
import controllers.ComplaintController;
import models.Complaint;
import utils.CustomizedException;

public class MainFrameTest extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu menu, menu2, menu3, menu4, i2;
	private JButton logoutBtn;
	private JMenuBar menuBar;
	private JMenuItem i1,i2000, i2001, i2002, i2003, i3, i4, i5, i6, i7;
	static private JDesktopPane desktopPane;
	
	
	public MainFrameTest() {
		menu  = new JMenu("Users");
		menu2 = new JMenu("Complaints");
		i2 = new JMenu("Display All Complaints");
		menu3 = new JMenu("Responses");
		menu4 = new JMenu("Accounts");
		menuBar= new JMenuBar();
		logoutBtn = new JButton("LOGOUT");
		i1 = new JMenuItem("Submit a Complaint");
		i2000 = new JMenuItem("All Complaints");
		i2001 = new JMenuItem("Mild");
		i2002 = new JMenuItem("Moderate"); 
		i2003 = new JMenuItem("Severe");
		i3 = new JMenuItem("Get Complaints Per Customer");
		i4 = new JMenuItem("Find Complaint By Id ");
		i5 = new JMenuItem("Update a Complaint");
		i6 = new JMenuItem("Assign Technician");
		i7 = new JMenuItem("Delete a Complaint");
		desktopPane = new JDesktopPane();
	    showForm();
	}
	
	public void createWindow() {
		 this.setTitle("MicroStar Frame");
		 this.setBounds(45,40,1200,700);
		 this.getContentPane().setLayout(null);
	     this.setVisible(true);
	     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     this.setResizable(false);
	}
	
	public void setLocationAndSize() {
		desktopPane.setBounds(315,7,870,620);
		desktopPane.setBackground(Color.white);
		
		this.i1.addActionListener(this);
		this.i2.addActionListener(this);
		this.i2000.addActionListener(this);
		this.i2001.addActionListener(this);
		this.i2002.addActionListener(this);
		this.i2003.addActionListener(this);
		this.i3.addActionListener(this);
		this.i4.addActionListener(this);
		this.i5.addActionListener(this);
		this.i6.addActionListener(this);
		this.i7.addActionListener(this);
		this.logoutBtn.addActionListener(this);
		
	}
	
	public void addComponentsToFrame() {
		
		menu2.add(i1);
		menu2.add(i2);
		menu2.add(i3);
		menu2.add(i4);
		menu2.add(i5);
		menu2.add(i6);
		menu2.add(i7);
		i2.add(i2000);
		i2.add(i2001);
		i2.add(i2002);
		i2.add(i2003);
		
		menuBar.add(menu);
		menuBar.add(menu2);	
		menuBar.add(menu3);
		menuBar.add(menu4);
		
		menuBar.add(logoutBtn);
		
		this.setJMenuBar(menuBar);
		this.add(desktopPane);
	}
	
	public void showForm() {
		createWindow();
		setLocationAndSize();
		addComponentsToFrame();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) {
			System.out.println("\nLogout Button clicked!");
			
		}
		else if (e.getSource() == i1) {
			System.out.println("\nSubmit a Complaint clicked!");
			desktopPane.removeAll();
			ComplaintView cv = new ComplaintView();
			desktopPane.add(cv).setVisible(true);
			desktopPane.moveToFront(cv);
			cv.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			cv.setLocation(0,0);
			
		}
		else if (e.getSource() == i2000) {
			System.out.println("\nDisplay All Complaints clicked!");
			desktopPane.removeAll();
			int num = 0;
			DisplayAllComplaintsView cvAll = new DisplayAllComplaintsView();
			cvAll.addRowsToJTable(num);
			desktopPane.add(cvAll).setVisible(true);
			desktopPane.moveToFront(cvAll);
			cvAll.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			cvAll.setLocation(0,0);
			
		}
		else if (e.getSource() == i2001) {
			System.out.println("\nDisplay All Mild Complaints clicked!");
			desktopPane.removeAll();
			int num = 1;
			DisplayAllComplaintsView cvAll = new DisplayAllComplaintsView();
			cvAll.addRowsToJTable(num);
			desktopPane.add(cvAll).setVisible(true);
			desktopPane.moveToFront(cvAll);
			cvAll.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			cvAll.setLocation(0,0);
			
		}
		else if (e.getSource() == i2002) {
			System.out.println("\nDisplay All Moderate Complaints clicked!");
			desktopPane.removeAll();
			int num = 2;
			DisplayAllComplaintsView cvAll = new DisplayAllComplaintsView();
			cvAll.addRowsToJTable(num);
			desktopPane.add(cvAll).setVisible(true);
			desktopPane.moveToFront(cvAll);
			cvAll.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			cvAll.setLocation(0,0);
			
		}
		else if (e.getSource() == i2003) {
			System.out.println("\nDisplay All Severe Complaints clicked!");
			desktopPane.removeAll();
			int num = 3;
			DisplayAllComplaintsView cvAll = new DisplayAllComplaintsView();
			cvAll.addRowsToJTable(num);
			desktopPane.add(cvAll).setVisible(true);
			desktopPane.moveToFront(cvAll);
			cvAll.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			cvAll.setLocation(0,0);
			
		}
		else if (e.getSource() == i3) {
			System.out.println("\nGet Complaints Per Customer clicked!");
			desktopPane.removeAll();

		}
		else if (e.getSource() == i4) {
			System.out.println("\nFind Complaint By Id clicked!");
			desktopPane.removeAll();
			new SearchComplaint();
			FindComplaintById fbi = new FindComplaintById();
			desktopPane.add(fbi).setVisible(true);
			desktopPane.moveToFront(fbi);
			fbi.setSize(desktopPane.getWidth(), desktopPane.getHeight());
			fbi.setLocation(0,0);
			
		}
		else if (e.getSource() == i5) {
			System.out.println("\nUpdate a Complaint clicked!");
			desktopPane.removeAll();

		}
		else if (e.getSource() == i6) {
			System.out.println("\nAssign Technician clicked!");
			desktopPane.removeAll();

		}
		else if (e.getSource() == i7) {
			System.out.println("\nDelete a Complaint clicked!");
			desktopPane.removeAll();

		}
		
	}

	public static void main(String[] args) {
		new MainFrameTest();

	}
}
