package kr.ac.green;

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

public class PageAddCalendar extends JDialog {
	// ��� ����
	JPanel bar = new JPanel();
	JButton lastMonth = new JButton("��");

	JComboBox<Integer> yearCombo = new JComboBox<Integer>();
	

	JLabel yLbl = new JLabel("�� ");

	JComboBox<Integer> monthCombo = new JComboBox<Integer>();
	
	JLabel mLbl = new JLabel("��");
	JButton nextMonth = new JButton("��");
	// �߾� ����
	JPanel center = new JPanel(new BorderLayout());
	// �߾� ��� ����
	JPanel cntNorth = new JPanel(new GridLayout(0, 7));
	// �߾� �߾� ����
	JPanel cntCenter = new JPanel(new GridLayout(0, 7));

	// ���� �Է�
	String dw[] = { "��", "��", "ȭ", "��", "��", "��", "��" };

	Calendar now = Calendar.getInstance();
	int year, month, date;

	
	private PageAdd owner;
	
	public PageAddCalendar(PageAdd owner) {
		super(owner,"�޷�",true);
		this.owner = owner;
		
		year = now.get(Calendar.YEAR);// 2021��
		month = now.get(Calendar.MONTH) + 1; // 0�� == 1��
		date = now.get(Calendar.DATE);
		for (int i = year; i <= year + 50; i++) {
			yearCombo.addItem(i);
		}
		for (int i = 1; i <= 12; i++) {
			monthCombo.addItem(i);
		}
		////////////////////////// ������///////////////////////////////////////////
		// ��� ����
		add("North", bar);
		bar.setLayout(new FlowLayout());
		bar.setSize(300, 400);
		bar.add(lastMonth);
		////////////////////////// �޷�/////////////////////////////////////////////
		bar.add(yearCombo);

		yearCombo.setSelectedItem(year);

		bar.add(yLbl);
		bar.add(monthCombo);
	
		monthCombo.setSelectedItem(month);

		bar.add(mLbl);
		bar.add(nextMonth);
		bar.setBackground(new Color(0, 210, 180));

		addListener();
		
		// �߾� ����
		add("Center", center);
		// �߾� ��� ����
		center.add("North", cntNorth);
		for (int i = 0; i < dw.length; i++) {
			JLabel dayOfWeek = new JLabel(dw[i], JLabel.CENTER);
			if (i == 0)
				dayOfWeek.setForeground(Color.red);
			else if (i == 6)
				dayOfWeek.setForeground(Color.blue);
			cntNorth.add(dayOfWeek);
		}

		// �߾� �߾� ����
		center.add("Center", cntCenter);
		dayPrint(year, month);

		// �̺�Ʈ
		


		// frame �⺻ ����
		setSize(400, 300);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// �̺�Ʈ ó��
	public void addListener() {
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj instanceof JButton) {
					JButton eventBtn = (JButton) obj;
					int yy = (Integer) yearCombo.getSelectedItem();
					int mm = (Integer) monthCombo.getSelectedItem();
					if (eventBtn.equals(lastMonth)) { // ����
						if (mm == 1 && yy == year) {
						} else if (mm == 1) {
							yy--;
							mm = 12;
						} else {
							mm--;
						}
					} else if (eventBtn.equals(nextMonth)) { // ������
						if (mm == 12) {
							yy++;
							mm = 1;
						} else {
							mm++;
						}
					}
					yearCombo.setSelectedItem(yy);
					monthCombo.setSelectedItem(mm);
				} else if (obj instanceof JComboBox) { // �޺��ڽ� �̺�Ʈ �߻���
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
		cntCenter.setVisible(false); // �г� �����
		cntCenter.removeAll(); // ��¥ ����� �� �����
		dayPrint((Integer) yearCombo.getSelectedItem(), (Integer) monthCombo.getSelectedItem());
		cntCenter.setVisible(true); // �г� �����
	}

	// ��¥ ���
	public void dayPrint(int y, int m) {
		Calendar cal = Calendar.getInstance();
		cal.set(y, m - 1, 1);
		int week = cal.get(Calendar.DAY_OF_WEEK); // 1�Ͽ� ���� ����
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1���� ���� ������ ����
		for (int i = 1; i < week; i++) { // 1�� 1�� ������ ������ ǥ���ض�
			cntCenter.add(new JLabel(""));
		}

		for (int i = 0; i <= lastDate - 1; i++) { // 1�� ������ ������ ���ڸ� �����, �׸��� ����� �Ͽ����� ������ ������
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


					// ���� "����"�� 1�ڸ��� 0�� �ٿ���
					if (str.equals(""));
					else if (str.length() == 1)
						str = "0" + str;

					// ���� "��"�� 1�ڸ��� 0�� �ٿ���
					if (m.length() == 1) {
						m = "0" + m;
					}
					owner.getLblDateArea().setText(y+"-"+m+"-"+str);
					setVisible(false);

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
}
