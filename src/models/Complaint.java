package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import utils.ComplaintCategory;
import utils.ComplaintStatus;
import utils.ComplaintType;



/*This class will serve as the complaints domain object.
 * it will map to the complaints table in the mysql database
 * 
 * */

@Entity
@Table(name = "Complaints") //reference the user table in database. 
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "complaint_id")
	private int complaintID;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "cust_id")
	private User custID;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "emp_id")
	private User empID;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "complaint_category")
	private ComplaintCategory category;
	
	@Column(name = "complaint")
	private String complaint;
	
	@Column(name = "complaint_date")
	private Date complaintDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "complaint_type")
	private ComplaintType complaintType;

	@Enumerated(EnumType.STRING)
	@Column(name = "complaint_status")
	private ComplaintStatus complaintStatus;
	
	public Complaint() {
		this.custID = new User();
		this.empID = new User();
		this.category = null;
		this.complaint = "";
		this.complaintDate =  new Date();
		this.complaintType = null;
		this.complaintStatus = ComplaintStatus.OUTSTANDING;
	}
	
		
	
	public Complaint(User custID, User empID, ComplaintCategory category, String complaint, Date complaintDate,
			ComplaintType complaintType, ComplaintStatus complaintStatus) {
		this.custID = custID;
		this.empID = empID;
		this.category = category;
		this.complaint = complaint;
		this.complaintDate = complaintDate;
		this.complaintType = complaintType;
		this.complaintStatus = complaintStatus;
	}



	public Complaint(final Complaint c ) {
		this.complaintID = c.complaintID;
		this.custID = c.custID;
		this.empID = c.empID;
		this.category = c.category;
		this.complaint = c.complaint;
		this.complaintDate = c.complaintDate;
		this.complaintType = c.complaintType;
		this.complaintStatus = c.complaintStatus;
	}

	public int getComplaintID() {
		return complaintID;
	}

	public void setComplaintID(int complaintID) {
		this.complaintID = complaintID;
	}


	public User getCustID() {
		return custID;
	}



	public void setCustID(User custID) {
		this.custID = custID;
	}



	public User getEmpID() {
		return empID;
	}



	public void setEmpID(User empID) {
		this.empID = empID;
	}



	public ComplaintCategory getCategory() {
		return category;
	}

	public void setCategory(ComplaintCategory category) {
		this.category = category;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

	public ComplaintType getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(ComplaintType complaintType) {
		this.complaintType = complaintType;
	}
	
	public ComplaintStatus getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(ComplaintStatus complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	

	@Override
	public String toString() {
		return "\nComplaints \ncomplaintID: " + complaintID + "\ncustID: " + custID +
				"\nempID: " + empID + "\ncategory: "
				+ category + "\ncomplaint: " + complaint + "\ncomplaintDate: " + complaintDate + 
				"\ncomplaintType: "
				+ complaintType + "\n"
				+ complaintStatus+ "\n";
	}
	
	
	
}
