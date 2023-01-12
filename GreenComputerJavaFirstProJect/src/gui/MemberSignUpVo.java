package gui;

public class MemberSignUpVo {
	private String username;
	private String password;
	private String name;
	private String gender;
	private String sortation;
	private String phone;

	public MemberSignUpVo() {
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.sortation = sortation;
		this.phone = phone;

	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getSortation() {
		return sortation;
	}
	public void setSortation(String sortation) {
		this.sortation = sortation;
	}

	public String getPhone() {
		return phone;
	}
	public void setphone(String string) {
		this.phone = phone;		
	}
	public String getSignUp() {
		return username + " " + password + " " + name + " " + gender + " " + sortation + " " + phone;
	}


}