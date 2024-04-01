package anyDTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class logDTO {
	private String id;// id
	private String address;// ip
	private String startTime;// 접속시작 시간
	private String endTime;// 접속종료 시간
	private SimpleDateFormat sdf = null;//시간 받아오기
	private Calendar cal = null;//현재시간으로 받기
	
	public void set(String id, String address) {
		this.id = id;
		this.address = address;
		sdf = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
		cal = Calendar.getInstance();
		this.startTime = sdf.format(cal.getTime());
	}

	public void setEnd() {
		sdf = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
		cal = Calendar.getInstance();
		this.endTime = sdf.format(cal.getTime());
	}
	
	public void load(String id, String address, String start, String end) {
		this.id = id;
		this.address = address;
		this.startTime = start;
		this.endTime = end;
	}

	public String getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}


	public String getStart() {
		return startTime;
	}

	public String getEnd() {
		return endTime;
	}
}
