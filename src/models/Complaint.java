package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import utils.ComplaintCategory;
import utils.ComplaintType;

/*This class will serve as the complaints domain object.
 * it will map to the complaints table in the mysql database
 * 
 * */

@Entity
@Table(name = "complaints") // reference the user table in database.
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "complaint_id")
	private int complaintID;

	@Column(name = "cust_id")
	private int custID;

	@Column(name = "emp_id")
	private int empID;

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

	public Complaint() {
		this.custID = 0;
		this.empID = 0;
		this.category = null;
		this.complaint = "";
		this.complaintDate = new Date();
		this.complaintType = null;
	}

	public Complaint(int custID, int empID, ComplaintCategory category, String complaint, Date complaintDate,
			ComplaintType complaintType) {
		this.custID = custID;
		this.empID = empID;
		this.category = category;
		this.complaint = complaint;
		this.complaintDate = complaintDate;
		this.complaintType = complaintType;
	}

	public Complaint(final Complaint c) {
		this.complaintID = c.complaintID;
		this.custID = c.custID;
		this.empID = c.empID;
		this.category = c.category;
		this.complaint = c.complaint;
		this.complaintDate = c.complaintDate;
		this.complaintType = c.complaintType;
	}

	public int getComplaintID() {
		return complaintID;
	}

	public void setComplaintID(int complaintID) {
		this.complaintID = complaintID;
	}

	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
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

	@Override
	public String toString() {
		return "\nComplaints \ncomplaintID: " + complaintID + "\ncustID: " + custID + "\nempID: " + empID
				+ "\ncategory: " + category + "\ncomplaint: " + complaint + "\ncomplaintDate: " + complaintDate
				+ "\ncomplaintType: " + complaintType + "\n";
	}

}
