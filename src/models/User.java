package models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import utils.Role;


@Entity
@Table(name = "Users") //reference the user table in database. 
public class User {
	
	/*use annotations to specify id column and set it to auto generate ID's. 
	 * we don't have to worry about keeping track of ID's for new users.
	 * 
	*/	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
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
	
	
	public User() {
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.role = null;
		this.password = "";
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


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
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
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
  
	
	public Role getRole() {
		return role;
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
