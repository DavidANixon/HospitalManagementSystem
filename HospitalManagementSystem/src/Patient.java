import java.util.Date;
import java.time.LocalDate;

public class Patient {
	private int id;
	private String name;
	private Date DOB;
	private String address;
	private String phoneNumber;
	private String illness;
	
	public Patient(int id, String name, Date date, String address, String phoneNumber, String illness) {
		super();
		this.id = id;
		this.name = name;
		DOB = date;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.illness = illness;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
	}
}
