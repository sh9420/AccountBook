package kr.ac.green.Form;
/* (swing1.1) (swing#1356,#1454) */

//package jp.gr.java_conf.tame.swing.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import kr.ac.green.PageAdd;
import kr.ac.green.data.DataCenter;
import kr.ac.green.model.AccountBook;
import kr.ac.green.model.AccountBookComparator;
import kr.ac.green.model.Table;
import kr.ac.green.table.AttributiveCellTableModel;
import kr.ac.green.table.CellSpan;
import kr.ac.green.table.MultiSpanCellTable;

/**
 * @version 1.0 03/06/99
 */
public class MainForm extends JFrame{
	// 현재 페이지
	private int pageNo = 1;

	// main데이터 : 전체 비용
	private Object[][] mainData;
	private Object[] mainColumn; 
	
	private DataCenter dataCenter;

	private MultiSpanCellTable mainTable;
	private MultiSpanCellTable listTable;

	private JPanel pnlCenter;
	private JPanel pnlCCenter;
	
	private JScrollPane mainScroll;
	private JScrollPane listScroll;

	private JPanel bar = new JPanel();
	private JLabel settingIcon;
	private JButton lastMonth;

	private JPanel pnlCNorth;
	
	private JComboBox<Integer> yearCombo;
	private DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();

	private JLabel yLbl;

	private JComboBox<Integer> monthCombo;
	private DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
	private JLabel mLbl;
	private JButton nextMonth;

	private Calendar now = Calendar.getInstance();
	private int year, month;
	
	
	private JButton btnPre;
	private JButton btnNext;
	private JComboBox<String> cBox;
	
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnCompare;
	private JButton btnStats;
	private StartForm owner;

	private int total;
	private int income;
	private int expends;
	private int card;
	private int lastMonthMoney;
	private JPanel pnlSouth;
	
	public MainForm() {

		this.dataCenter=DataCenter.getInstance();

		init();
		addTable();
		
		mainData = new Object[][] {{ "123", income , expends, card , total }};
		mainColumn = new Object[] { "전월 이월", "수입", "지출", "카드", "잔액" };
		
		setDisplay();
		addListener();
		showFrame();

	}

	public void init() {
		pageNo = 1;

		
		lastMonth = new JButton("◀");
		nextMonth = new JButton("▶");
		yearCombo = new JComboBox<Integer>();
		monthCombo = new JComboBox<Integer>();
		yLbl = new JLabel("년 ");
		mLbl = new JLabel("월");
		
		btnPre = new JButton("◀");
		btnNext = new JButton("▶");
		cBox = new JComboBox<String>();
		cBox.setPrototypeDisplayValue("XXXXXXXXXXX");
		
		
		btnSearch = new JButton("검색");
		btnAdd = new JButton("추가");
		btnCompare = new JButton("비교");
		btnStats = new JButton("통계");
		
		pnlCenter = new JPanel(new BorderLayout());
		ImageIcon icon = new ImageIcon("setting.png");
		Image img = icon.getImage();
		Image newImage = img.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
		settingIcon = new JLabel(new ImageIcon(newImage), JLabel.RIGHT);
		settingIcon.setOpaque(true);
		settingIcon.setBackground(Color.WHITE);

		nextMonth.setBackground(Color.WHITE);
		lastMonth.setBackground(Color.WHITE);

		year = now.get(Calendar.YEAR);// 2021년
		month = now.get(Calendar.MONTH) + 1; // 0월 == 1월
		
		for (int i = 1970; i <= year + 30; i++) {
			yearModel.addElement(i);
		}
		for (int i = 1; i <= 12; i++) {
			monthModel.addElement(i);
		}

	}

	public void setDisplay() {
		pnlSouth = new JPanel(new GridLayout(0,1));
		JPanel pnlNorth = new JPanel(new GridLayout(0, 1));

		pnlCNorth = new JPanel();
		


		pnlCCenter = new JPanel();
		pnlCCenter.setBackground(Color.WHITE);
		
		this.expense2();
		this.expense();


		// 달력
		bar.setLayout(new FlowLayout());
		bar.setSize(300, 400);
		bar.add(lastMonth);
		bar.add(yearCombo);
		yearCombo.setModel(yearModel);
		yearCombo.setSelectedItem(year);

		bar.add(yLbl);
		bar.add(monthCombo);
		monthCombo.setModel(monthModel);
		monthCombo.setSelectedItem(month);

		bar.add(mLbl);
		bar.add(nextMonth);
		bar.setBackground(Color.WHITE);
		
		pnlNorth.add(settingIcon);
		pnlNorth.add(bar);
		
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		paginate();
	}
	
	private void paginate() {
		pageNo = 1;
		setPages();
		JPanel pnlSSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel pnlSNorth = new JPanel();
		pnlSNorth.add(btnPre);
		pnlSNorth.add(cBox);
		pnlSNorth.add(btnNext);
		
		pnlSSouth.add(btnSearch);
		pnlSSouth.add(btnAdd);
		pnlSSouth.add(btnCompare);
		pnlSSouth.add(btnStats);
		
		pnlSouth.add(pnlSNorth);
		pnlSouth.add(pnlSSouth);
		add(pnlSouth,BorderLayout.SOUTH);
		pnlSSouth.setBackground(Color.WHITE);
		pnlSNorth.setBackground(Color.WHITE);
	}
	
	
	/**
	 * 	개인 지출 비용
	 *  현재 페이지 노출
	 */
	private void expense() {
		List<Table> tableList = dataCenter.getPageListToTable(pageNo);
		
		for(int i = 0 ; i < tableList.size(); i++) {
			
			AttributiveCellTableModel model2 = new AttributiveCellTableModel(
					tableList.get(i).getListData(),
					tableList.get(i).getListColumn()
			);
			
			CellSpan cellAtt2 = (CellSpan) model2.getCellAttribute();
			cellAtt2.combine(new int[] { 0, 1 }, new int[] { 0 });
			cellAtt2.combine(new int[] { 0 }, new int[] { 1, 2, 3 });
			cellAtt2.combine(new int[] { 0, 1 }, new int[] { 4 });
			cellAtt2.combine(new int[] { 1 }, new int[] { 1, 2, 3 });
			
			listTable = new MultiSpanCellTable(model2);
	
			listScroll = new JScrollPane(listTable, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			getContentPane().add(listScroll);
			listScroll.setPreferredSize(new Dimension(450, 62));

			DefaultTableCellRenderer renderer2 = (DefaultTableCellRenderer) listTable.getTableHeader()
					.getDefaultRenderer();
			renderer2.setHorizontalAlignment(SwingConstants.CENTER);
			listTable.getTableHeader().setDefaultRenderer(renderer2);

			JPanel pnlCCenter2 = new JPanel();
			pnlCCenter2.setBackground(Color.WHITE);
			pnlCCenter2.add(listScroll);
			pnlCCenter.add(pnlCCenter2);

			pnlCenter.add(pnlCCenter);
		}
	}
	private void expense2() {
		mainData = new Object[][] {{ lastMonthMoney, income , expends, card , total }};
		mainColumn = new Object[] { "전월 이월", "수입", "지출", "카드", "잔액" };
		
		AttributiveCellTableModel model1 = new AttributiveCellTableModel(mainData, mainColumn);
		mainTable = new MultiSpanCellTable(model1);
		mainScroll = new JScrollPane(mainTable);
		getContentPane().add(mainScroll);
		mainScroll.setPreferredSize(new Dimension(450, 46));
		pnlCNorth.add(mainScroll);
		pnlCenter.add(pnlCNorth, BorderLayout.NORTH);


		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) mainTable.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		mainTable.getTableHeader().setDefaultRenderer(renderer);

		pnlCNorth.setBackground(Color.WHITE);
	}
	// 이벤트 처리
	public void addListener() {
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();

				int yy;
				int mm;
				yy = (Integer) yearCombo.getSelectedItem();
				mm = (Integer) monthCombo.getSelectedItem();				
				
				if(obj == yearCombo) {
					year = yy;
					month = mm;
					allupadte();
					
				}
				if(obj == monthCombo) {
					year = yy;
					month = mm;
					allupadte();
				}
				
				if (obj == lastMonth) { // 전달
					if (mm == 1) {
						pageNo = 1;
						yy--;
						mm = 12;
						year = yy;
						month = mm;
						allupadte();

					} else {
						pageNo = 1;
						mm--;
						year = yy;
						month = mm;
						allupadte();
					}

				} else if (obj == nextMonth) { // 다음달
					if (mm == 12) {
						pageNo = 1;
						yy++;
						mm = 1;
						year = yy;
						month = mm;
						allupadte();

					} else {
						pageNo = 1;
						mm++;
						year = yy;
						month = mm;
						allupadte();
					}

				} else if (obj == btnPre) {
					pageNo--;
					if (pageNo == 0) {
						pageNo = 1;
						JOptionPane.showMessageDialog(MainForm.this, "첫페이지 입니다.");
					} else {
						updatePanel();
						expense();
					}
				} else if (obj == btnNext) {
					pageNo++;
					int last;
					if(dataCenter.getListTable().size() !=0 ) {
						last = Integer.parseInt(cBox.getItemAt(cBox.getItemCount() - 1));
					}else {
						last = 0 ;
					}
					if (pageNo > last) {
						JOptionPane.showMessageDialog(MainForm.this, "마지막페이지 입니다.");
						pageNo--;
					} else {
						updatePanel();
						expense();
					}
				} else if (obj == btnSearch) {

				} else if (obj == btnAdd) {
					dispose();
					new PageAdd();
				} else if (obj == btnCompare) {

				} else if (obj == btnStats) {

				}
				
				if(dataCenter.getListTable().size() != 0) {
					cBox.setSelectedIndex(pageNo - 1);
				}
				yearCombo.setSelectedItem(yy);
				monthCombo.setSelectedItem(mm);
			}
		};
		yearCombo.addActionListener(aListener);
		monthCombo.addActionListener(aListener);
		btnAdd.addActionListener(aListener);
		btnNext.addActionListener(aListener);
		btnPre.addActionListener(aListener);
		yearCombo.addActionListener(aListener);
		monthCombo.addActionListener(aListener);
		lastMonth.addActionListener(aListener);
		nextMonth.addActionListener(aListener);
		
		cBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.DESELECTED) {
					pageNo = Integer.parseInt(
						cBox.getSelectedItem().toString()
					);
					updatePanel();
					expense();
				}
			}
		});
		MouseListener mListener = new MouseAdapter() {
			// pressed -> released -> clicked (마우스 이벤트 발생 순서)
			
			@Override
			public void mouseClicked(MouseEvent me) {
				if(me.getClickCount() == 1) {
					setVisible(false);
					new SettingForm(owner);
				}
			}
		};
		settingIcon.addMouseListener(mListener);
	}
	
	public void addTable() {
		dataCenter.getListTable().clear();
		total = 0;
	
		Collections.sort(dataCenter.getListAccountBookData(), new AccountBookComparator());
		
		ArrayList<AccountBook> list = new ArrayList<AccountBook>();
		income = 0;
		expends = 0;
		
		for(int i=0;i<dataCenter.getListAccountBookData().size();i++) {
			AccountBook acb = dataCenter.getListAccountBookData().get(i);
			String date = acb.getYear()+"-"+acb.getMonth();
			String month2 = String.valueOf(month);
			
			int money = 0;
			int bankMoney = 0;
			int cardMoney = 0;
			String don = "";
		
			if(acb.getIo().equals("수입")) {
				money = acb.getMoney();
				don = "+"+acb.getMoney();
			}else if (acb.getIo().equals("지출")) {
				bankMoney = acb.getMoney();
				don = "-"+acb.getMoney();
			}else if (acb.getProperty().equals("카드")) {
				cardMoney = acb.getMoney();
				don = "-"+acb.getMoney();
			}
			
			String bank = "";
			if(!(acb.getProperty().equals("현금"))) {
				bank = "("+acb.getBank()+")";
			}else {
				bank = acb.getBank();
			}
			
			total = total + money - bankMoney;
			
			if(month2.length() == 1) {
				month2 = "0"+month2;
			}
			
			if(date.equals(year+"-"+month2)) {
				list.add(acb);
				
				Table table = new Table();
				String day = acb.getDate()+ " (" +acb.getDay()+")";
				table.setListData(new Object[][] {{acb.getHash1(),	acb.getMemo()+"    "+acb.getHash2()+"  "+acb.getHash3(), "", "",don },
					{"",acb.getProperty()+bank, "", ""}});
				table.setListColumn(new Object[] {day,money,bankMoney,cardMoney,total});
				dataCenter.addListTable(table);
			}
		}
		
		income = 0;
		expends = 0;
		card = 0;
		for(int i = 0; i< list.size(); i++) {
			int money = 0;
			int bankMoney = 0;
			int cardMoney = 0;
			if(list.get(i).getIo().equals("수입")) {
				money = list.get(i).getMoney();
			}else if (list.get(i).getIo().equals("지출")) {
				bankMoney = list.get(i).getMoney();
			}else if (list.get(i).getIo().equals("카드")) {
				cardMoney = list.get(i).getMoney();
			}
			income = income + money;
			expends = expends - bankMoney;
			card = card - cardMoney;
		}
		for(int i=0 ; i< dataCenter.getListAccountBookData().size() ; i++) {
			AccountBook acb = dataCenter.getListAccountBookData().get(i);
			String date = acb.getYear()+"-"+acb.getMonth();
			String month2 = String.valueOf(month);
			lastMonthMoney = 0;
			if(month2.length() == 1) {
				month2 = "0"+month2;
			}
			if(date.equals(year+"-"+month2)) {
				
				for(int y = 0; y < i ; y++) {
					int money = 0;
					int bankMoney = 0;
					AccountBook acb2 = dataCenter.getListAccountBookData().get(y);
					
					if(acb2.getIo().equals("수입")) {
						money = acb2.getMoney();
					}else if (acb2.getIo().equals("지출")) {
						bankMoney = acb2.getMoney();
					}
					lastMonthMoney = lastMonthMoney + money - bankMoney;
				}
				break;
			}
		}
	}
	
	public void updatePanel() {
		pnlCCenter.removeAll();
		pnlCCenter.updateUI();
	}
	public void updatePanel2() {
		pnlCNorth.removeAll();
		pnlCNorth.updateUI();
	}
	private void setPages() {
		int cBoxCnt = cBox.getItemCount();
		
		for(int idx = 2; idx <= cBoxCnt; idx ++) {
			cBox.removeItem(idx+"");
		}
		
		int count = dataCenter.getTotalPageCount();
		
		for(int i = 1; i <= count ; i++) {
			if ( !(i == 1 && cBoxCnt > 0)) {
				cBox.addItem(String.valueOf(i));
			}
		}
	}
	
	public void allupadte() {
		paginate();
		addTable();
		updatePanel();
		expense();
		updatePanel2();
		expense2();
	}
	
	public void showFrame() {
		setSize(500, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
