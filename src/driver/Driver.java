package driver;


//
//		User user = new User("","","","",Role.REPRESENTATIVE);
//		ResponseController rc = new ResponseController();
//		
/*
 		new Server();
 		
		User u1 = new User("Jonathon","Kent","jkent@gmail.com",
				"forever123",Role.CUSTOMER);
		UserController ucc = new UserController();
		
		try {
			ucc.createUser(u1);
=======

import server.Server;


public class Driver {

		AuthController ac = new AuthController();
		
		try {
			boolean t = ac.updatePassword(1700, "secrets","secret");
>>>>>>> develop
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
<<<<<<< HEAD
=======
		
		LOG.debug("Debug Message Logged"); 
        LOG.fatal("Sample fatal message");
        LOG.info("Info Message Logged");
        LOG.error("Error Message Logged");
        LOG.warn("Test Warn message");
        LOG.trace("TRACE MESSAGE");
        

		User user = new User("","","","",Role.REPRESENTATIVE);
>>>>>>> develop
		ResponseController rc = new ResponseController();
		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		UserController uc = new UserController();

//		uc.createUser(user);

		ComplaintController cc = new ComplaintController();	
		
		ArrayList <Complaint> dispList = new ArrayList<> ();
//		try {
//			dispList = cc.getAllComplaints();
//			System.out.println("Printing:\n"+dispList);
//		} catch (CustomizedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		/*
		try {
			Complaint searchC = cc.findById(14);
			System.out.println("Complaint: \n"+searchC);
			
			Response r2 = new Response(
					searchC, 
					sqlDate, 
					"Thanks for everything!!"
					);
			ResponseController rc1 = new ResponseController();
			rc1.addResponse(r2);
			System.out.println("Response added!");
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		ComplaintController cc1 = new ComplaintController();
		ArrayList <Complaint> mc = new ArrayList <> ();
		/*
		try {
			mc = cc1.getAllMildComplaints();
			System.out.println(mc);
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		try {
			
			Complaint c3 = cc1.viewComplaintAndResponses(14);
			System.out.println(c3);
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		
		new Server();
		
		/*
		int compIdGlobal = 27;
		String newStatus = "RESOLVED";
		String r1 = "RESOLVED";
   		String o1 = "OUTSTANDING";
   	
		try {
			Complaint oldComplaint =  cc1.findById(compIdGlobal);
			System.out.println("Complaint found:\n"+oldComplaint);
			System.out.println("\nConfirming new status: "+newStatus);
			
			if(newStatus.equals(r1)) {
				System.out.println("\nUpdating complaint status to RESOLVED");
				oldComplaint.setComplaintStatus(ComplaintStatus.RESOLVED);
				System.out.println("\nStatus set completed\n");
				
				Complaint newComplaint = cc1.updateComplaints(oldComplaint);
				System.out.println("Updated Complaint details: \n"+newComplaint);
				System.out.println("Successful!");
			}
			
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
				
				/*
				Complaint oldComplaint = cc.findById(compIdGlobal);
			System.out.println("Complaint found:\n"+oldComplaint);
			System.out.println("\nConfirming new status: "+newStatus);
			
			if(newStatus.equals(r1)) {
				System.out.println("Updating complaint status to RESOLVED");
				oldComplaint.setComplaintStatus(ComplaintStatus.RESOLVED);
				System.out.println("Status set completed\n");
				
				Complaint newComplaint = cc.updateComplaint(oldComplaint);
				System.out.println("Updated Complaint details: \n"+newComplaint);
				JOptionPane.showMessageDialog(NewJFrame.this,
	     			  	"Update successful!",
	     			    "Complaint View Tip",
	     			    JOptionPane.INFORMATION_MESSAGE);
				*/
		/*
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		
		try {
			Complaint fcomp = cc1.findById(35);
			Response r1 = new Response(
					fcomp,
					sqlDate,
					"Sample response 7"
					);
			ResponseController rc = new ResponseController();
			rc.addResponse(r1);
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
			
		
	}	
	
	public static void main(String[] args) {

 		new Server();
 		
	}


