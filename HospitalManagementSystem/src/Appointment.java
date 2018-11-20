import java.time.LocalDate;

public class Appointment {
	private long id;
	private LocalDate date;
	private long or_id;
	private long patient_id;
	private long operation_id;
	private long doctor_id;
	private long nurse_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public long getOr_id() {
		return or_id;
	}
	public void setOr_id(long or_id) {
		this.or_id = or_id;
	}
	public long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
	}
	public long getOperation_id() {
		return operation_id;
	}
	public void setOperation_id(long operation_id) {
		this.operation_id = operation_id;
	}
	public long getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(long doctor_id) {
		this.doctor_id = doctor_id;
	}
	public long getNurse_id() {
		return nurse_id;
	}
	public void setNurse_id(long nurse_id) {
		this.nurse_id = nurse_id;
	}
	
}
