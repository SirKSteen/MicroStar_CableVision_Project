package driver;



import java.util.ArrayList;

import controllers.ResponseController;
import controllers.UserController;
import models.Response;
import models.User;
import utils.ControllerException;
import utils.Role;

public class Driver {

	public static void main(String[] args) throws ControllerException{
		
		User user = new User("","","","",Role.REPRESENTATIVE);
		UserController controller = new UserController();
		
//		controller.addUser(user);

		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		
		Response r1 = new Response(
				13,
				sqlDate,
				"This is a sample response"
				);
		
		Response r2 = new Response(
				14,
				sqlDate,
				"This is another sample response"
				);
		
		

		Response r3 = new Response(
				14,
				sqlDate,
				"This is another sample response"
				);
		
		
		ResponseController rc1 = new ResponseController();  
//		rc1.addResponse(r3);
		
		
		ArrayList <Response> responseArr = new ArrayList<Response>();
		responseArr = rc1.getAllResponses();
		System.out.println("Read Section\n");
		System.out.println(responseArr);
		
		Response resultFind = new Response();
		
		System.out.println("\nFind Section\n");
		resultFind = rc1.findById(5);
		System.out.println(resultFind);
		
		System.out.println("\nDelete Section\n");
		rc1.deleteResponse(4);
		
	}

}


























