import java.time.LocalDate;

public class Patient {
	private int id;
	private String name;
	private LocalDate DOB;
	private String address;
	private long phoneNumber;
	private String illness;
	
	public Patient(int id, String name, LocalDate dOB, String address, long phoneNumber, String illness) {
		super();
		this.id = id;
		this.name = name;
		DOB = dOB;
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
	public LocalDate getDOB() {
		return DOB;
	}
	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
	}
}
