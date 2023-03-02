package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kr.ac.green.data.DataCenter;

public class HashTagTwoThree extends JFrame {
	// 돼지 이미지
	JLabel lblImg;
//	JRadioButton rbIncome; //수입
//	JRadioButton rbExpends; //지출
	//해시태그1-search버튼
	JButton btnSearch1;
	// 확인 버튼 
	JButton btnCheck;
	// 검색 창
	JTextField tf1;
	
	ArrayList<JToggleButton> tbtnTag1;
	TitledBorder tBorder1;

	private DataCenter dataCenter;
	
	
	public HashTagTwoThree() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.dataCenter=DataCenter.getInstance();
		
		init();
		setDisplay();
		addListeners();
		showFrame();
	}
	
	private void init() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("piggy-bank-icon.png");
		setIconImage(icon);
		
		lblImg = new JLabel(new ImageIcon("searchPig.png"), JLabel.CENTER);
		
		// 라디오버튼 
//		rbIncome = new JRadioButton("수입");
//		rbExpends = new JRadioButton("지출",true);
		
//		ButtonGroup group = new ButtonGroup();
//		group.add(rbIncome);
//		group.add(rbExpends);
	
		btnSearch1 = new JButton("search");
		btnSearch1.setBackground(Color.PINK);

		btnCheck = new JButton("확인");
		btnCheck.setForeground(Color.WHITE);
		btnCheck.setBackground(new Color(0xFF4848));
		
		// search창
		tf1 = new JTextField(15);
		
		
		// 해시태그 1
		tbtnTag1 = new ArrayList<>();
		tbtnTag1 = dataCenter.getHashTag3();
		
		ButtonGroup group1 = new ButtonGroup();
		for(int i= 0; i < tbtnTag1.size(); i++ ) {
		group1.add(tbtnTag1.get(i));
		}
		
		tBorder1 = new TitledBorder("# HashTag 2 or 3");
		tBorder1.setBorder(new LineBorder(new Color(0xFF7E7E),2));
	}
	
	public JToggleButton getTbtn1(String name) {
		JToggleButton tb1 = new JToggleButton(name);
		
		return tb1;
	}
	
	private void setDisplay() {
		//돼지그림
		JPanel pnlImg = new JPanel();
		pnlImg.add(lblImg);
		pnlImg.setBackground(Color.WHITE);
		
		//라디오버튼
//		JPanel pnlRadioButton = new JPanel(); 
//		rbIncome.setBackground(Color.WHITE);
//		rbExpends.setBackground(Color.WHITE);
//		pnlRadioButton.add(rbIncome);
//		pnlRadioButton.add(rbExpends);
//		pnlRadioButton.setBackground(Color.WHITE);
		
		JPanel pnlNorth = new JPanel(new BorderLayout());
		pnlNorth.add(pnlImg, BorderLayout.NORTH);
//		pnlNorth.add(pnlRadioButton, BorderLayout.EAST);
		pnlNorth.setBackground(Color.WHITE);
		
		//해시태그1의 상자에 들어가는 첫번째줄.
		JPanel pnlHashTagAdd1 = new JPanel(); 
		pnlHashTagAdd1.add(tf1);
		pnlHashTagAdd1.add(btnSearch1);
		pnlHashTagAdd1.setBackground(Color.WHITE);
		
		//해시태그1의 토글버튼(해시태그)
		JPanel pnlToggle1 = new JPanel(new GridLayout(0,4,5,5));	
		for(JToggleButton tbtn1 : tbtnTag1) {
			pnlToggle1.add(tbtn1);
		}
		pnlToggle1.setBackground(Color.WHITE);
		
		
		JPanel pnlCCenter = new JPanel(new BorderLayout());
		pnlCCenter.add(pnlHashTagAdd1,BorderLayout.NORTH);
		pnlCCenter.add(pnlToggle1,BorderLayout.CENTER);

		//해시태그1의 박스안에 들어가는 모든 것
		JPanel pnlHashTagBox1 = new JPanel(new BorderLayout()); 
		pnlHashTagBox1.add(pnlHashTagAdd1, BorderLayout.NORTH);
		pnlHashTagBox1.add(pnlToggle1, BorderLayout.CENTER);
		pnlHashTagBox1.setBackground(Color.WHITE);
		
		//해시태그1 부분 전체
		JPanel pnlHashTag1 = new JPanel(new GridLayout(0,1));
		pnlHashTag1.setBorder(tBorder1);
		pnlHashTag1.add(pnlHashTagBox1);
		pnlHashTag1.setBackground(Color.WHITE);
		

		JPanel pnlCenter = new JPanel(new BorderLayout());
		pnlCenter.add(pnlCCenter);
		pnlCenter.add(pnlHashTag1, BorderLayout.NORTH);
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));							
		pnlCenter.setBackground(Color.WHITE);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnCheck);
		pnlSouth.setBorder(new EmptyBorder(10,10,2,10));
		pnlSouth.setBackground(Color.WHITE);
							
		JPanel pnlMain = new JPanel(new BorderLayout());
		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
	
		pnlMain.setBorder(new EmptyBorder(10,10,10,10));
		pnlMain.setBackground(Color.WHITE);
		
		add(pnlMain);
		
	}
	private void addListeners() {
	
	}
	private void showFrame() {
		setTitle("거지탈출 가계부");
		pack();
		//setSize(200, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new HashTagTwoThree();
	}
}
	
