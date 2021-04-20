package models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import utils.PaymentStatus;

@Entity
@Table(name = "Accounts") // reference to the accounts table in the database
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically increments ID number based on previous record.
	private int acct_id;

	@Enumerated(EnumType.STRING) // map to enumerated role in database
	private PaymentStatus payment_status;

	private float amt_due;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user_id;

	// Constructors

	public Account() {

		this.payment_status = null;
		this.amt_due = 0;
		this.user_id = new User();
	}

	public Account(PaymentStatus payment_status, float amt_due, User user) {

		this.payment_status = payment_status;
		this.amt_due = amt_due;
		this.user_id = user;
	}

	public Account(Account a) {

		this.acct_id = a.acct_id;
		this.payment_status = a.payment_status;
		this.amt_due = a.amt_due;
		this.user_id = a.user_id;
	}

	// setters & getters
	public int getAcct_id() {
		return acct_id;
	}

	public void setAcct_id(int acct_id) {
		this.acct_id = acct_id;
	}

	public PaymentStatus getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(PaymentStatus payment_status) {
		this.payment_status = payment_status;
	}

	public float getAmt_due() {
		return amt_due;
	}

	public void setAmt_due(float amt_due) {
		this.amt_due = amt_due;
	}

	public User getUser() {
		return user_id;
	}

	public void setUser(User user) {
		this.user_id = user;
	}

	@Override
	public String toString() {
		return "Accounts [acct_id=" + acct_id + ", payment_status=" + payment_status + ", amt_due=" + amt_due
				+ ", user=" + user_id + "]";
	}

}
