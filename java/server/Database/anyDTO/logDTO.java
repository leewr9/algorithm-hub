package anyDTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class logDTO {
	private String id;// id
	private String address;// ip
	private String startTime;// ���ӽ��� �ð�
	private String endTime;// �������� �ð�
	private SimpleDateFormat sdf = null;//�ð� �޾ƿ���
	private Calendar cal = null;//����ð����� �ޱ�
	
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
