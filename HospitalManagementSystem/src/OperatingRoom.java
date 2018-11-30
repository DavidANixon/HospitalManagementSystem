
public class OperatingRoom {
	private int id;
	private int roomNumber;
	private String building;
	
	public OperatingRoom(int id, String building,  int roomNumber) {
		this.id = id;
		this.roomNumber = roomNumber;
		this.building = building;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
}
