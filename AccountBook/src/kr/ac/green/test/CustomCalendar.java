package kr.ac.green.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomCalendar extends JDialog {
	// 상단 지역
	JPanel bar = new JPanel();
	JButton lastMonth = new JButton("◀");

	JComboBox<Integer> yearCombo = new JComboBox<Integer>();
	

	JLabel yLbl = new JLabel("년 ");

	JComboBox<Integer> monthCombo = new JComboBox<Integer>();
	
	JLabel mLbl = new JLabel("월");
	JButton nextMonth = new JButton("▶");
	// 중앙 지역
	JPanel center = new JPanel(new BorderLayout());
	// 중앙 상단 지역
	JPanel cntNorth = new JPanel(new GridLayout(0, 7));
	// 중앙 중앙 지역
	JPanel cntCenter = new JPanel(new GridLayout(0, 7));

	// 요일 입력
	String dw[] = { "일", "월", "화", "수", "목", "금", "토" };

	Calendar now = Calendar.getInstance();
	int year, month, date;

	public CustomCalendar() {
		year = now.get(Calendar.YEAR);// 2021년
		month = now.get(Calendar.MONTH) + 1; // 0월 == 1월
		date = now.get(Calendar.DATE);
		for (int i = year; i <= year + 50; i++) {
			yearCombo.addItem(i);
		}
		for (int i = 1; i <= 12; i++) {
			monthCombo.addItem(i);
		}
		////////////////////////// 프레임///////////////////////////////////////////
		// 상단 지역
		add("North", bar);
		bar.setLayout(new FlowLayout());
		bar.setSize(300, 400);
		bar.add(lastMonth);
		////////////////////////// 달력/////////////////////////////////////////////
		bar.add(yearCombo);

		yearCombo.setSelectedItem(year);

		bar.add(yLbl);
		bar.add(monthCombo);
	
		monthCombo.setSelectedItem(month);

		bar.add(mLbl);
		bar.add(nextMonth);
		bar.setBackground(new Color(0, 210, 180));

		addListener();
		
		// 중앙 지역
		add("Center", center);
		// 중앙 상단 지역
		center.add("North", cntNorth);
		for (int i = 0; i < dw.length; i++) {
			JLabel dayOfWeek = new JLabel(dw[i], JLabel.CENTER);
			if (i == 0)
				dayOfWeek.setForeground(Color.red);
			else if (i == 6)
				dayOfWeek.setForeground(Color.blue);
			cntNorth.add(dayOfWeek);
		}

		// 중앙 중앙 지역
		center.add("Center", cntCenter);
		dayPrint(year, month);

		// 이벤트
		


		// frame 기본 셋팅
		setSize(400, 300);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// 이벤트 처리
	public void addListener() {
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj instanceof JButton) {
					JButton eventBtn = (JButton) obj;
					int yy = (Integer) yearCombo.getSelectedItem();
					int mm = (Integer) monthCombo.getSelectedItem();
					if (eventBtn.equals(lastMonth)) { // 전달
						if (mm == 1 && yy == year) {
						} else if (mm == 1) {
							yy--;
							mm = 12;
						} else {
							mm--;
						}
					} else if (eventBtn.equals(nextMonth)) { // 다음달
						if (mm == 12) {
							yy++;
							mm = 1;
						} else {
							mm++;
						}
					}
					yearCombo.setSelectedItem(yy);
					monthCombo.setSelectedItem(mm);
				} else if (obj instanceof JComboBox) { // 콤보박스 이벤트 발생시
					createDayStart();
				}
			}
		};
		yearCombo.addActionListener(aListener);
		monthCombo.addActionListener(aListener);
		lastMonth.addActionListener(aListener);
		nextMonth.addActionListener(aListener);
		}

	private void createDayStart() {
		cntCenter.setVisible(false); // 패널 숨기기
		cntCenter.removeAll(); // 날짜 출력한 라벨 지우기
		dayPrint((Integer) yearCombo.getSelectedItem(), (Integer) monthCombo.getSelectedItem());
		cntCenter.setVisible(true); // 패널 재출력
	}

	// 날짜 출력
	public void dayPrint(int y, int m) {
		Calendar cal = Calendar.getInstance();
		cal.set(y, m - 1, 1);
		int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1월에 대한 마지막 요일
		for (int i = 1; i < week; i++) { // 1월 1일 전까지 공백을 표시해라
			cntCenter.add(new JLabel(""));
		}

		for (int i = 0; i <= lastDate - 1; i++) { // 1월 마지막 날까지 숫자를 적어라, 그리고 토요일 일요일은 색깔을 입혀라
			JLabel day = new JLabel();
			day.setHorizontalAlignment(JLabel.CENTER);
			if ((week + i) % 7 == 0) {
				cntCenter.add(day).setForeground(Color.blue);
				day.setText(1 + i + "");
			} else if ((week + i) % 7 == 1) {
				cntCenter.add(day).setForeground(Color.red);
				day.setText(1 + i + "");
			} else {
				cntCenter.add(day);
				day.setText(1 + i + "");
			}
			MouseListener mListener = new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JLabel mouseClick = (JLabel) e.getSource();
					String str = mouseClick.getText();
					String y = "" + yearCombo.getSelectedItem();
					String m = "" + monthCombo.getSelectedItem();


					// 받은 "요일"이 1자리면 0을 붙여라
					if (str.equals(""))
						;
					else if (str.length() == 1)
						str = "0" + str;

					// 받은 "월"이 1자리면 0을 붙여라
					if (m.length() == 1) {
						m = "0" + m;
					}
					System.out.println(str);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			}; 
			day.addMouseListener(mListener);
		}
	}
	
	public static void main(String[] args) {
		new CustomCalendar();
	}
}
