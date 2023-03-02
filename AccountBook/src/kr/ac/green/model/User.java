package kr.ac.green.model;

import java.io.Serializable;

public class User implements Serializable{

	private String qChoice;
	private String aChoice;

	private String password;
	
	public User() {
		
	}
	
	public User(String password) {
		setPassword(password);
	}

	public User(String qChoice, String aChoice, String password) {
		setQChoice(qChoice);
		setAChoice(aChoice);
		setPassword(password);
	}

	public String getQChoice() {
		return qChoice;
	}

	public void setQChoice(String qChoice) {
		this.qChoice = qChoice;
	}

	public String getAChoice() {
		return aChoice;
	}

	public void setAChoice(String aChoice) {
		this.aChoice = aChoice;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [qChoice=" + qChoice + ", aChoice=" + aChoice + ", password=" + password + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof User)) {
			return false;
		}
		
		User user = (User)o;
		return password.equals(user.getPassword());
	}
}
