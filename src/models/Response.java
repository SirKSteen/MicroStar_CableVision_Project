package models;

/*This class will serve as the user domain object.
 * it will map to the users table in the mysql database
 * 
 * */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "Responses") //reference the user table in database. 
public class Response{
	
	/*use annotations to specify id column and set it to auto generate ID's. 
	 * we don't have to worry about keeping track of ID's for new users.
	 * 
	*/	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Automatically increments ID number based on previous record.
	@Column(name = "response_id") //specify column name since variable name is different
	private int response_id;
	
//	@ManyToOne
	@JoinColumn(name = "complaint_id")
	private int complaint_id;
	
	@Column(name = "response_date")
	private Date response_date;
	
	@Column(name = "response")
	private String response;
	
	
	
	
	//constructors

	public Response() {
		this.complaint_id = 0;
		this.response_date = new Date();
		this.response = "";
	}

	
	public Response(int complaint_id, Date response_date, String response) {
		this.complaint_id = complaint_id;
		this.response_date = response_date;
		this.response = response;
	}


	public Response(final Response r) {
		this.complaint_id = r.complaint_id;
		this.response_date = r.response_date;
		this.response = r.response;
	}


	
	//getters and setters
	
	public int getResponse_id() {
		return response_id;
	}


	public void setResponse_id(int response_id) {
		this.response_id = response_id;
	}


	public int getComplaint_id() {
		return complaint_id;
	}


	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}


	public Date getResponse_date() {
		return response_date;
	}


	public void setResponse_date(Date response_date) {
		this.response_date = response_date;
	}


	public String getResponse() {
		return response;
	}


	public void setResponse(String response) {
		this.response = response;
	}


	@Override
	public String toString() {
		return "Response [response_id=" + response_id + ", complaint_id=" + complaint_id + ", response_date="
				+ response_date + ", response=" + response + "]";
	}
	


}


















