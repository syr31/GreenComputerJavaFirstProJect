package gui;

public class HealthRecordVO {
	
	private String Health_date;
	private String Health_pk;
	private String Health_event;
	private String Health_wei;
	private String Health_set;
	private String Health_num;

	public HealthRecordVO() {
		this.Health_date = Health_date;
		this.Health_event = Health_event;
		this.Health_pk = Health_pk;
		this.Health_wei = Health_wei;
		this.Health_set = Health_set;
		this.Health_num = Health_num;
	}
	
	public String getHealth_date() {
		return Health_date;
	}
	public void setHealth_date(String Health_date) {
		this.Health_date = Health_date;
	}
	public String getHealth_pk() {
		return Health_pk;
	}
	public void setHealth_pk(String Health_pk) {
		this.Health_pk = Health_pk;
	}
	public String getHealth_event() {
		return Health_event;
	}
	public void setHealth_event(String Health_event) {
		this.Health_event = Health_event;
	}
	public String getHealth_wei() {
		return Health_wei;
	}
	public void setHealth_wei(String Health_wei) {
		this.Health_wei = Health_wei;
	}
	public String getHealth_set() {
		return Health_set;
	}
	public void setHealth_set(String Health_set) {
		this.Health_set=Health_set;
	}
	public String getHealth_num() {
		return Health_num;
	}
	public void setHealth_num(String Health_num) {
		this.Health_num = Health_num;
	}
	public String getRegistration() {
		return Health_date + " " + Health_event + " " + Health_wei + " " + Health_set + " " + Health_num;
	}
}