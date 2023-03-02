package kr.ac.green.Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import kr.ac.green.HashTagSearchOne;
import kr.ac.green.util.AccountBookUtil;

public class SearchForm extends JDialog{
	
	private JLabel lblBack;
	private JLabel lblDate;
	private JLabel lblProperty;
	private JLabel lblIncomeSpend;
	private JLabel lblHashTag1;
	private JLabel lblHashTag2;
	private JLabel lblHashTag3;
	private JLabel lblMoney;
	private JLabel lblMoney2;
	
	private JLabel lblPickDate;
	
	private JComboBox<Integer> cbYear;
	private DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();

	private JComboBox<Integer> cbMonth;
	private DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
	private Calendar now = Calendar.getInstance();
	int year, month;
	private JLabel lblYear;
	private JLabel lblMonth;
	
	private JComboBox<String> cbProperty;
	private String[] property = {"전체", "은행", "현금", "카드"};
	private JComboBox<String> cbIncomeSpend;
	private String[] IncomeSpend = {"전체", "수입", "지출"};
	private JLabel lblHashTag1Area;
	private JLabel lblHashTag2Area;
	private JLabel lblHashTag3Area;
	private JLabel lblMoneyArea;
	private JLabel lblMoney2Area;
	private JTextField tfMoneyArea;
	private JTextField tfMoney2Area;
	
	private JButton btnSearch;
	
	private MainForm owner;
	
	public SearchForm(MainForm owner) {
		super(owner, "search", false);
		this.owner = owner;
		init();
		setDisplay();
		addListeners();
		showDlg();
	}
	
	private void init() {
		lblBack = new JLabel(AccountBookUtil.getImageIcon("Back.png", 30), JLabel.LEFT);
		lblDate = AccountBookUtil.getRightLabel("날짜");
		lblProperty = AccountBookUtil.getRightLabel("자산");
		lblIncomeSpend = AccountBookUtil.getRightLabel("#수입/지출");
		lblHashTag1 = AccountBookUtil.getRightLabel("#해시태그1");
		lblHashTag2 = AccountBookUtil.getRightLabel("#해시태그2");
		lblHashTag3 = AccountBookUtil.getRightLabel("#해시태그3");
		lblMoney = AccountBookUtil.getRightLabel("금액");
		lblMoney2 = new JLabel("      ~");
		lblMoney2.setPreferredSize(new Dimension(80, 20));
		
		lblPickDate = new JLabel("");
		lblPickDate.setPreferredSize(new Dimension(60, 20));
		lblPickDate.setBorder(new LineBorder(Color.GRAY));
		lblYear = new JLabel("년");
		year = now.get(Calendar.YEAR);
		cbYear = new JComboBox<Integer>();
		for(int i = 1970; i<year+30; i++) {
			yearModel.addElement(i);
		}
		
		lblMonth = new JLabel("월");
		month = now.get(Calendar.MONTH) + 1;
		cbMonth = new JComboBox<Integer>();
		for(int i = 1; i < 12; i++) {
			monthModel.addElement(i);
		}
		
		cbProperty = new JComboBox<String>(property);
		cbProperty.setPreferredSize(new Dimension(140, 28));
		((JLabel)cbProperty.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		cbIncomeSpend = new JComboBox<String>(IncomeSpend);
		cbIncomeSpend.setPreferredSize(new Dimension(140, 28));
		((JLabel)cbIncomeSpend.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		lblHashTag1Area = new JLabel("");
		lblHashTag2Area = new JLabel("", JLabel.RIGHT);
		lblHashTag3Area = new JLabel("");
		lblMoneyArea = new JLabel("", JLabel.RIGHT);
		lblMoney2Area = new JLabel("");
		tfMoneyArea = new JTextField(12);
		tfMoneyArea.setBorder(new LineBorder(Color.BLACK));
		tfMoneyArea.setPreferredSize(new Dimension(130, 22));
		tfMoneyArea.setHorizontalAlignment(SwingConstants.CENTER);
		tfMoney2Area = new JTextField(12);
		tfMoney2Area.setBorder(new LineBorder(Color.BLACK));
		tfMoney2Area.setPreferredSize(new Dimension(130, 22));
		tfMoney2Area.setHorizontalAlignment(SwingConstants.CENTER);
		
		setLblDesign(lblHashTag1Area);
		setLblDesign(lblHashTag2Area);
		setLblDesign(lblHashTag3Area);
		setLblDesign(lblMoneyArea);
		setLblDesign(lblMoney2Area);
		
		btnSearch = new JButton("search");
	}
	
	private void setLblDesign(JLabel lbl) {
		lbl.setPreferredSize(new Dimension(135, 22));
		lbl.setBorder(new LineBorder(Color.BLACK));
		lbl.setOpaque(true);
		lbl.setBackground(Color.WHITE);
	}
	
	private void setDisplay() {
		JPanel pnlBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlBack.add(lblBack);
		pnlBack.setBackground(Color.WHITE);
		
		JPanel pnlBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlBar.add(cbYear);
		pnlBar.add(lblYear);
		pnlBar.add(cbMonth);
		pnlBar.add(lblMonth);
		cbYear.setModel(yearModel);
		cbYear.setSelectedItem(year);
		pnlBar.setBackground(Color.WHITE);
		
		cbMonth.setModel(monthModel);
		cbMonth.setSelectedItem(month);
		
		JPanel pnlDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlDate.add(lblDate);
		pnlDate.add(pnlBar);
		pnlDate.setBackground(Color.WHITE);
		
		JPanel pnlcbProp = new JPanel();
		pnlcbProp.add(cbProperty);
		pnlcbProp.setBackground(Color.WHITE);
		
		JPanel pnlProperty = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlProperty.add(lblProperty);
		pnlProperty.add(pnlcbProp);
		pnlProperty.setBackground(Color.WHITE);
		
		JPanel pnlHash2Area = new JPanel();
		pnlHash2Area.add(lblHashTag2Area);
		pnlHash2Area.setBorder(new EmptyBorder(0, 2, 0, 0));
		pnlHash2Area.setBackground(Color.WHITE);
		
		JPanel pnlHash2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlHash2.add(lblHashTag2);
		pnlHash2.add(pnlHash2Area);
		pnlHash2.setBackground(Color.WHITE);
		
		JPanel pnlMoneyArea = new JPanel();
		pnlMoneyArea.add(tfMoneyArea);
		pnlMoneyArea.setBorder(new EmptyBorder(0, 2, 0, 0));
		pnlMoneyArea.setBackground(Color.WHITE);
		
		JPanel pnlMoney = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlMoney.add(lblMoney);
		pnlMoney.add(pnlMoneyArea);
		pnlMoney.setBackground(Color.WHITE);
		
		JPanel pnlLeft = new JPanel(new GridLayout(0, 1));
		pnlLeft.add(pnlDate);
		pnlLeft.add(pnlProperty);
		pnlLeft.add(pnlHash2);
		pnlLeft.add(pnlMoney);
		pnlLeft.setBackground(Color.WHITE);
		
		
		JPanel pnlISArea = new JPanel();
		pnlISArea.add(cbIncomeSpend);
		pnlISArea.setBackground(Color.WHITE);
		
		JPanel pnlIncomeSpend = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlIncomeSpend.add(lblIncomeSpend);
		pnlIncomeSpend.add(pnlISArea);
		pnlIncomeSpend.setBackground(Color.WHITE);
		
		JPanel pnlHash1Area = new JPanel();
		pnlHash1Area.add(lblHashTag1Area);
		pnlHash1Area.setBorder(new EmptyBorder(0, 2, 0, 0));
		pnlHash1Area.setBackground(Color.WHITE);
		
		JPanel pnlHash1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlHash1.add(lblHashTag1);
		pnlHash1.add(pnlHash1Area);
		pnlHash1.setBackground(Color.WHITE);
		
		JPanel pnlHash3Area = new JPanel();
		pnlHash3Area.add(lblHashTag3Area);
		pnlHash3Area.setBorder(new EmptyBorder(0, 2, 0, 0));
		pnlHash3Area.setBackground(Color.WHITE);
		
		JPanel pnlHash3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlHash3.add(lblHashTag3);
		pnlHash3.add(pnlHash3Area);
		pnlHash3.setBackground(Color.WHITE);
		
		JPanel pnlMoney2Area = new JPanel();
		pnlMoney2Area.add(tfMoney2Area);
		pnlMoney2Area.setBorder(new EmptyBorder(0, 2, 0, 0));
		pnlMoney2Area.setBackground(Color.WHITE);
		
		JPanel pnlMoney2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlMoney2.add(lblMoney2);
		pnlMoney2.add(pnlMoney2Area);
		pnlMoney2.setBackground(Color.WHITE);
		
		JPanel pnlRight = new JPanel(new GridLayout(0, 1));
		pnlRight.add(pnlIncomeSpend);
		pnlRight.add(pnlHash1);
		pnlRight.add(pnlHash3);
		pnlRight.add(pnlMoney2);
		pnlRight.setBorder(new EmptyBorder(0, 0, 0, 20));
		pnlRight.setBackground(Color.WHITE);
		
		
		JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlBtn.add(btnSearch);
		pnlBtn.setBackground(Color.WHITE);
		
		
		JPanel pnl = new JPanel(new BorderLayout());
		pnl.add(pnlBack, BorderLayout.NORTH);
		pnl.add(pnlLeft, BorderLayout.WEST);
		pnl.add(pnlRight, BorderLayout.EAST);
		pnl.add(pnlBtn, BorderLayout.SOUTH);
		pnl.setBackground(Color.WHITE);
		
		add(pnl);
	}
	
	private void addListeners() {
		MouseListener mListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				JLabel lbl = (JLabel)me.getSource();
				if(lbl == lblBack) {
					dispose();
					owner.setVisible(true);
				} else if(lbl == lblHashTag1Area) {
					new HashTagSearchOne();
				}
			}
		};
		
		lblBack.addMouseListener(mListener);
		lblHashTag1Area.addMouseListener(mListener);
		
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(ae.getSource() == btnSearch) {
					System.out.println("검색");
				}
			}
		};
		
		btnSearch.addActionListener(aListener);
	}
	
	private void showDlg() {
		setTitle("검색");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
}
