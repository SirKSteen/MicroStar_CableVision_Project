package models;

import java.io.Serializable;

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

import utils.Role;

@Entity
@Table(name = "Users") // reference the user table in database.
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * use annotations to specify id column and set it to auto generate ID's. we
	 * don't have to worry about keeping track of ID's for new users.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically increments ID number based on previous record.
	@Column(name = "user_id") // specify column name since variable name is different
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

	@Column(name = "contact_number")
	private String contactNum;
	
	// constructors

	public User() {
		this("", "", "", "", null,""); // initialize variables using primary constructor to promote code reuse
	}

	public User(String firstName, String lastName, 
			String email, String password, Role role, String contactNum) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contactNum = contactNum;
	}

	public User(final User user) {
		this.userId = user.userId;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.role = user.role;
		this.password = user.password;
		this.contactNum = user.contactNum;
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

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	
	@Override
	public String toString() {
		return "\nUser \nUser Id: " + userId + "\nfirstName: " + firstName + "\nlastName: " + lastName + "\nemail: "
				+ email + "\npassword: " + password + "\nrole: " + role + "\n"
						+ contactNum + "\n";
	}

}
