package gui;

public class MemberRegistrationVo {
	private String re_code;
	private String re_name;
	private String re_age;
	private String re_height;
	private String re_weight;

	public MemberRegistrationVo() {
		this.re_code = re_code;
		this.re_name = re_name;
		this.re_age = re_age;
		this.re_height = re_height;
		this.re_weight = re_weight;
	}


	public String getRe_code() {
		return re_code;
	}

	public void setRe_code(String re_code) {
		this.re_code = re_code;
	}

	public String getRe_name() {
		return re_name;
	}

	public void setRe_name(String re_name) {
		this.re_name = re_name;
	}

	public String getRe_age() {
		return re_age;
	}

	public void setRe_age(String re_age) {
		this.re_age = re_age;
	}

	public String getRe_height() {
		return re_height;
	}

	public void setRe_height(String re_height) {
		this.re_height = re_height;
	}

	public String getRe_weight() {
		return re_weight;
	}

	public void setRe_weight(String re_weight) {
		this.re_weight = re_weight;
	}

	public String getRegistration() {
		return re_code + " " + re_name + " " + re_age + " " + re_height + " " + re_weight;
	}

}