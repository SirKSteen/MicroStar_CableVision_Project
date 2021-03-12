package models;

/*This class will serve as the user domain object.
 * it will map to the users table in the mysql database
 * 
 * */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import factories.HibernateConnectorSessionFactory;
import utils.Role;


@Entity
@Table(name = "Users") //reference the user table in database. 
public class User {
	
	/*use annotations to specify id column and set it to auto generate ID's. 
	 * we don't have to worry about keeping track of ID's for new users.
	 * 
	*/	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Automatically increments ID number based on previous record.
	@Column(name = "user_id") //specify column name since variable name is different
	private int userId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING) // map to enumerated role in database
	@Column(name = "user_role")
	 private Role role;
	
	
	//constructors
	
	public User() {
		this("","","","",null); // initialize variables using primary constructor to promote code reuse 
	}


	public User(String firstName, String lastName, String email, String password, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public User(final User user) {
		this.userId = user.userId;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.role = user.role;
		this.password = user.password;
	}

//getters and setters
	public int getUserId() {
		return this.userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return this.firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return this.lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return this.password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
  
	
	public Role getRole() {
		return this.role;
	}


	public void setRole(Role role) {
		this.role = role;
	}
	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", role=" + role + "]";
	}
	
	
	
	

}
