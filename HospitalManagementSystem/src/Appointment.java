import java.util.Date;

public class Appointment {
	private int id;
	private Date date;
	private int time;
	private int or_id;
	private int patient_id;
	private int operation_id;
	private int doctor_id;
	private int nurse_id;

	public Appointment(int id, Date date,  int time, int or_id, int patient_id, int operation_id, int doctor_id,
			int nurse_id) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.or_id = or_id;
		this.patient_id = patient_id;
		this.operation_id = operation_id;
		this.doctor_id = doctor_id;
		this.nurse_id = nurse_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getOr_id() {
		return or_id;
	}

	public void setOr_id(int or_id) {
		this.or_id = or_id;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public int getOperation_id() {
		return operation_id;
	}

	public void setOperation_id(int operation_id) {
		this.operation_id = operation_id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public int getNurse_id() {
		return nurse_id;
	}

	public void setNurse_id(int nurse_id) {
		this.nurse_id = nurse_id;
	}

}
