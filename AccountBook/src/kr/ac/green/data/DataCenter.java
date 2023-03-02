package kr.ac.green.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import kr.ac.green.model.AccountBook;
import kr.ac.green.model.Table;
import kr.ac.green.util.AccountBookUtil;



/**
 *
 * 싱글턴 패턴을 활용하여, 하나의 인스턴스 보장
 * 데이터 관리는 여기서부터 활용이 필요하여, 내용 추가 시 ,주석 필수로 추가
 *
 * 사용 시, DataCenter.getInstance().메소드명(); 형태로 활용하여야 함.
 */

public class DataCenter {
	public static final int PER_PAGE = 5;

	private static DataCenter instance;
	private ArrayList<Table> listTable;
	private ArrayList<AccountBook> listAccountBookData;
	private ArrayList<JToggleButton> hashTag1_1;
	private ArrayList<JToggleButton> hashTag1_2;
	private ArrayList<JToggleButton> hashTag2;
	private ArrayList<JToggleButton> hashTag3;
	
	//private 
	
	private DataCenter() {
		listTable = new ArrayList<Table>();
		listAccountBookData = new ArrayList<AccountBook>();
		hashTag1_1 = new ArrayList<JToggleButton>();
		hashTag1_2 = new ArrayList<JToggleButton>();
		hashTag2 = new ArrayList<JToggleButton>();
		hashTag3 = new ArrayList<JToggleButton>();
		
		
		// 예시값 생성

		String str1_1[] = {"월급","부수입","용돈","상여","금융소득","보너스"};
		for(int i = 0 ; i < str1_1.length ; i++) {
			addHashTag1_1(str1_1[i]);
		}
		
		String str1_2[] = {"경조사","식비","교통비","미용","관리비"};
		for(int i = 0 ; i < str1_2.length ; i++) {
			addHashTag1_2(str1_2[i]);
		}

		String str2[] = {"부모님","회사","가족","로또","여자친구","데이트","맛집"};
		for(int i = 0 ; i < str2.length ; i++) {
			addHashTag2(str2[i]);
		}
		
		String str3[] = {"부모님","회사","가족","로또","여자친구","데이트","맛집"};
		for(int i = 0 ; i < str3.length ; i++) {
			addHashTag3(str3[i]);
		}
	}

	/**
	 * DataCenter 
	 *
	 * @return
	 */

	public static DataCenter getInstance() {
		if(instance == null) {
			instance = new DataCenter();
		}
		return instance;
	}

	public ArrayList<Table> getListTable() {
		return this.listTable;
	}

	public void updateListTable(int index, Table listTable) {
		this.listTable.set(index, listTable);
	}

	public void addListTable(Table table){
		this.listTable.add(table);
	}
	
	public void updateListAccountBookData(int index, AccountBook listAccountBookData) {
		this.listAccountBookData.set(index, listAccountBookData);
	}
	
	public void addListAccountBookData(int no, String year, String month, String date, int money, String io, String day, String property, String bank, String hash1, String hash2, String hash3, String memo) {
		this.listAccountBookData.add(new AccountBook(no, year, month, date, money, io, day, property, bank, hash1, hash2, hash3, memo));
	}
	
	public ArrayList<AccountBook> getListAccountBookData() {
		return listAccountBookData;
	}

	public void setListAccountBookData(ArrayList<AccountBook> listAccountBookData) {
		this.listAccountBookData = listAccountBookData;
	}

	public void setListTable(ArrayList<Table> listTable) {
		this.listTable = listTable;
	}

	public static void setInstance(DataCenter instance) {
		DataCenter.instance = instance;
	}
	
	
	// 해시태그
	public void addHashTag1_1(String str) {
		this.hashTag1_1.add(new JToggleButton("#"+str));
	}
	public void addHashTag1_2(String str) {
		this.hashTag1_2.add(new JToggleButton("#"+str));
	}
	public void addHashTag2(String str) {
		this.hashTag2.add(new JToggleButton("#"+str));
	}
	
	public void addHashTag3(String str) {
		this.hashTag3.add(new JToggleButton("#"+str));
	}
	
	public ArrayList<JToggleButton> getHashTag1_1() {
		return hashTag1_1;
	}

	public void setHashTag1_1(ArrayList<JToggleButton> hashTag1_1) {
		this.hashTag1_1 = hashTag1_1;
	}

	public ArrayList<JToggleButton> getHashTag2() {
		return hashTag2;
	}

	public void setHashTag2(ArrayList<JToggleButton> hashTag2) {
		this.hashTag2 = hashTag2;
	}

	public ArrayList<JToggleButton> getHashTag1_2() {
		return hashTag1_2;
	}

	public void setHashTag1_2(ArrayList<JToggleButton> hashTag1_2) {
		this.hashTag1_2 = hashTag1_2;
	}
	
	public ArrayList<JToggleButton> getHashTag3() {
		return hashTag3;
	}

	public void setHashTag3(ArrayList<JToggleButton> hashTag3) {
		this.hashTag3 = hashTag3;
	}
	public void read() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream("Account.dat");
			ois = new ObjectInputStream(fis);
			listAccountBookData = (ArrayList<AccountBook>) (ois.readObject());
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			System.out.println("error");
			System.exit(-1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			AccountBookUtil.closeAll(ois, fis);
		}
	}

	public void load() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream("Account.dat");
			oos = new ObjectOutputStream(fos);

			oos.writeObject(listAccountBookData);
			oos.flush();
			oos.reset();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error");
		} finally {
			AccountBookUtil.closeAll(oos, fos);
		}
	}
	

	/**
	 * 테이블 페이지 정보
	 */
	public List<Table> getPageListToTable (int pageNo) {
		int from = (pageNo-1)*PER_PAGE;
		int to = from+PER_PAGE;
		
		if(to > listTable.size()) {
			to = listTable.size();
		}
		return listTable.subList(from, to);
	}
	
	public int getTotalPageCount() {
		int size = listTable.size();
		int count = size / PER_PAGE;
		if(size % PER_PAGE !=0) {
			count++;
		}
		if(count == 0) {
			count = 1;
		}
		return count;
	}
}

