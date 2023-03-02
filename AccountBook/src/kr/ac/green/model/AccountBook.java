package kr.ac.green.model;

import java.io.Serializable;
import java.util.Date;

public class AccountBook implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String year;
	private String month;
	private String date;
	private int money;
	private String io;   //false = 지출   true = 수입
	private String day;
	private String property;
	private String bank;
	private String hash1;
	private String hash2;
	private String hash3;
	private String memo;
	private String allDay;
	
	public AccountBook(int no, String year, String month, String date, int money, String io, String day, String property, String bank, String hash1, String hash2, String hash3, String memo) {
		setNo(no);
		setYear(year);
		setMonth(month);
		setDate(date);
		setMoney(money);
		setIo(io);
		setDay(day);
		setProperty(property);
		setBank(bank);
		setHash1(hash1);
		setHash2(hash2);
		setHash3(hash3);
		setMemo(memo);
		allDay = year+"-"+month+"-"+date;
		setAllDay(allDay);
	}



	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}


	public String getIo() {
		return io;
	}

	public void setIo(String io) {
		this.io = io;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getHash1() {
		return hash1;
	}

	public void setHash1(String hash1) {
		this.hash1 = hash1;
	}

	public String getHash2() {
		return hash2;
	}

	public void setHash2(String hash2) {
		this.hash2 = hash2;
	}

	public String getHash3() {
		return hash3;
	}

	public void setHash3(String hash3) {
		this.hash3 = hash3;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public String getAllDay() {
		return allDay;
	}

	public void setAllDay(String allDay) {
		this.allDay = allDay;
	}


	@Override
	public String toString() {
		return "AccountBook [no=" + no + ", year=" + year + ", month=" + month + ", date=" + date + ", money=" + money
				+ ", io=" + io + ", day=" + day + ", property=" + property + ", bank=" + bank + ", hash1=" + hash1
				+ ", hash2=" + hash2 + ", hash3=" + hash3 + ", memo=" + memo + "]";
	}
	
	
}
