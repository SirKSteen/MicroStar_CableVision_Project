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
@Table(name = "Responses") //reference the user table in database. 
public class Responses {
	
	/*use annotations to specify id column and set it to auto generate ID's. 
	 * we don't have to worry about keeping track of ID's for new users.
	 * 
	*/	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Automatically increments ID number based on previous record.
	@Column(name = "responses_id") //specify column name since variable name is different
	private int responses_id;
	
	 @ManyToOne
	 @JoinColumn(name = "complaint_id", referencedColumnName = "complaint_id")
	private Complaints complaint_id;
	
	@Column(name = "response_date")
	private Date response_date;
	
	@Column(name = "response")
	private String response;
	
	
	
	
	//constructors
	


	public Responses(int responses_id, Complaints complaint_id, Date response_date, String response) {
		this.responses_id = responses_id;
		this.complaint_id = complaint_id;
		this.response_date = response_date;
		this.response = response;
		
	}
	
	public Responses(final Responses responses) {
		this.responses_id = responses.responses_id;
		this.complaint_id = responses.complaint_id;
		this.response_date = responses.response_date;
		this.response = responses.response;
	}

//getters and setters

	public Responses() {
		this(0,null,null,""); // initialize variables using primary constructor to promote code reuse 
	}


	

	public int getResponces_id() {
		return responses_id;
	}


	public void setResponces_id(int responses_id) {
		this.responses_id = responses_id;
	}


	public int getComplaint_id() {
		return complaint_id;
	}


	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}


	public Date getResponce_date() {
		return response_date;
	}


	public void setResponce_date(Date response_date) {
		this.response_date = response_date;
	}


	public String getResponce() {
		return response;
	}


	public void setResponce(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "Responses [responses_id=" + responses_id + ", complaint_id=" + complaint_id + ", response_date="
				+ response_date + ", response=" + response + "]";
	}

	
	

}
