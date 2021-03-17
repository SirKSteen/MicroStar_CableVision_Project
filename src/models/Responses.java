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
import java.util.Date;


@Entity
@Table(name = "Responces") //reference the user table in database. 
public class Responses {
	
	/*use annotations to specify id column and set it to auto generate ID's. 
	 * we don't have to worry about keeping track of ID's for new users.
	 * 
	*/	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Automatically increments ID number based on previous record.
	@Column(name = "responces_id") //specify column name since variable name is different
	private int responces_id;
	
	@Column(name = "complaint_id")
	private int complaint_id;
	
	@Column(name = "responce_date")
	private Date responce_date;
	
	@Column(name = "responce")
	private String responce;
	
	
	
	
	//constructors
	


	public Responses(int responces_id, int complaint_id, Date responce_date, String responce) {
		this.responces_id = responces_id;
		this.complaint_id = complaint_id;
		this.responce_date = responce_date;
		this.responce = responce;
		
	}
	
	public Responses(final Responses responces) {
		this.responces_id = responces.responces_id;
		this.complaint_id = responces.complaint_id;
		this.responce_date = responces.responce_date;
		this.responce = responces.responce;
	}

//getters and setters

	public Responses() {
		this(0,0,null,""); // initialize variables using primary constructor to promote code reuse 
	}


	

	public int getResponces_id() {
		return responces_id;
	}


	public void setResponces_id(int responces_id) {
		this.responces_id = responces_id;
	}


	public int getComplaint_id() {
		return complaint_id;
	}


	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}


	public Date getResponce_date() {
		return responce_date;
	}


	public void setResponce_date(Date responce_date) {
		this.responce_date = responce_date;
	}


	public String getResponce() {
		return responce;
	}


	public void setResponce(String responce) {
		this.responce = responce;
	}

	@Override
	public String toString() {
		return "responces [responces_id=" + responces_id + ", complaint_id=" + complaint_id + ", responce_date="
				+ responce_date + ", responce=" + responce + "]";
	}

	
	

}
