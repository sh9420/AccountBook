package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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

public class HashTagSearchAddThird extends JDialog{
	JLabel lblImg;	// 돼지 이미지

	JButton btnSearch3; //해시태그3 -search버튼
	JButton btnAdd3;

	JButton btnCheck;
	
	JTextField tf3;

	ArrayList<JToggleButton> tbtnTag3;
	
	TitledBorder tBorder3;

	private PageAdd owner;
	private DataCenter dataCenter;
	
	public HashTagSearchAddThird(PageAdd owner) {
		super(owner,"해시태그3",true);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.owner = owner;
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
		
		lblImg = new JLabel(new ImageIcon("BigPig.png"), JLabel.CENTER);
		
		btnSearch3 = new JButton("search");
		btnSearch3.setBackground(Color.PINK);

		btnAdd3 = new JButton("추가");
		btnAdd3.setBackground(new Color(0xFF7E7E));

		btnCheck = new JButton("확인");
		btnCheck.setForeground(Color.WHITE);
		btnCheck.setBackground(new Color(0xFF4848));
		
		// search창
		tf3 = new JTextField(15);
		
		// 해시태그 1
		tbtnTag3 = new ArrayList<JToggleButton>();
		tbtnTag3 = dataCenter.getHashTag3();
		
		ButtonGroup group3 = new ButtonGroup();
		for(int i= 0; i < tbtnTag3.size(); i++ ) {
		group3.add(tbtnTag3.get(i));
		}

		tBorder3 = new TitledBorder("# HashTag 3");
		tBorder3.setBorder(new LineBorder(Color.BLACK));
	}
	
	public JToggleButton getTbtn3(String name) {
		JToggleButton tbtn3 = new JToggleButton(name);
		
		return tbtn3;
	}
	

	private void setDisplay() {
		//돼지그림
		JPanel pnlImg = new JPanel();
		pnlImg.add(lblImg);
		pnlImg.setBackground(Color.WHITE);
		
		JPanel pnlNorth = new JPanel(new BorderLayout());
		pnlNorth.add(pnlImg, BorderLayout.NORTH);
		pnlNorth.setBackground(Color.WHITE);
		
		//해시태그3의 상자에 들어가는 첫번째줄.
		JPanel pnlHashTagAdd3 = new JPanel(); 
		pnlHashTagAdd3.add(tf3);
		pnlHashTagAdd3.add(btnSearch3);
		pnlHashTagAdd3.add(btnAdd3);
		pnlHashTagAdd3.setBackground(Color.WHITE);
		
		//해시태그3의 토글버튼(해시태그)
		JPanel pnlToggle3 = new JPanel(new GridLayout(0,4,5,5));	
		for(JToggleButton tbtn3 : tbtnTag3) {
			pnlToggle3.add(tbtn3);
		}
		pnlToggle3.setBackground(Color.WHITE);
		
		
		JPanel pnlCCenter = new JPanel(new BorderLayout());
		pnlCCenter.add(pnlHashTagAdd3,BorderLayout.NORTH);
		pnlCCenter.add(pnlToggle3,BorderLayout.CENTER);


		//해시태그3의 박스안에 들어가는 모든 것
		JPanel pnlHashTagBox3 = new JPanel(new BorderLayout()); 
		pnlHashTagBox3.add(pnlHashTagAdd3, BorderLayout.NORTH);
		pnlHashTagBox3.add(pnlToggle3, BorderLayout.CENTER);
		pnlHashTagBox3.setBackground(Color.WHITE);
		
		//해시태그3 부분 전체
		JPanel pnlHashTag3 = new JPanel(new GridLayout(0,1));
		pnlHashTag3.setBorder(tBorder3);
		pnlHashTag3.add(pnlHashTagBox3);
		pnlHashTag3.setBackground(Color.WHITE);
		

//		//해시태그2 부분 전체
//		JPanel pnlHashTag2 = new JPanel(new GridLayout(0,1));
//		pnlHashTag2.setBorder(tBorder2);
//		pnlHashTag2.add(pnlHashTagBox2);
//		pnlHashTag2.setBackground(Color.WHITE);

		JPanel pnlCenter = new JPanel(new BorderLayout());
		pnlCenter.add(pnlCCenter);
		pnlCenter.add(pnlHashTag3, BorderLayout.NORTH);
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

		ActionListener aListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object rbtn = (Object) e.getSource();
				String str3 = "";
				if(rbtn == btnCheck) {
					owner.getLblHashTagAreaC().setText(getButtonValue(str3, tbtnTag3));
					owner.getLblHashTagAreaC().setForeground(Color.BLACK);
					dispose();
				}
			}
		};
		btnCheck.addActionListener(aListener);
	}	

	public String getButtonValue(String str, ArrayList<JToggleButton> toggle) {
		str = "";
		for (int i = 0; i < toggle.size(); i++) {
			if (toggle.get(i).isSelected()) {
				str = toggle.get(i).getText();
			}
		}
		return str;
	}
	private void showFrame() {
		setTitle("거지탈출 가계부");
		pack();
		//setSize(200, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
}

	

