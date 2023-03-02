package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kr.ac.green.Form.PasswordSetForm;
import kr.ac.green.data.DataCenter;
import kr.ac.green.util.AccountBookUtil;


//해시태그 검색 및 추가 수입 창 class
public class HashTagIncome extends JDialog {
	JLabel lblImg;	// 돼지 이미지

	JRadioButton rbIncome; //수입
	JRadioButton rbExpends; //지출
	
	JButton btnSearch1; //해시태그1-search버튼
	JButton btnSearch2; //해시태그2-search버튼
	JButton btnAdd1;
	JButton btnAdd2;
	JButton btnCheck;
	
	JTextField tf1;
	JTextField tf2;
	
	ArrayList<JToggleButton> tbtnTagIncome;
	ArrayList<JToggleButton> tbtnTagExpends;
	ArrayList<JToggleButton> tbtnTag2;
	
	TitledBorder tBorder1;
	TitledBorder tBorder2;
	JPanel pnlHashTagBox1;
	JPanel pnlHashTag1;
	JPanel pnlCenter;
	JPanel pnlHashTagBox2;
	JPanel pnlHashTagAdd1;
	JPanel pnlToggle1;
	ButtonGroup group1;
	ButtonGroup group2;
	ButtonGroup group3;
	private PageAdd owner;
	JPanel pnlToggle2;
	
	boolean flag = false;
	private DataCenter dataCenter;
	
	
	public HashTagIncome(PageAdd owner) {
		super(owner,"해시태그추가",true);
		this.owner = owner;
		this.dataCenter=DataCenter.getInstance();
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		init();
		setDisplay();
		addListeners();
		showFrame();
	}
	
	private void init() {

		tbtnTagIncome = new ArrayList<>();
		tbtnTagIncome = dataCenter.getHashTag1_1();

		tbtnTagExpends = new ArrayList<>();
		tbtnTagExpends = dataCenter.getHashTag1_2();
		
		
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("piggy-bank-icon.png");
		setIconImage(icon);
		
		lblImg = new JLabel(new ImageIcon("BigPig.png"), JLabel.CENTER);
		
		// 라디오버튼 
		rbIncome = new JRadioButton("수입", true);
		rbExpends = new JRadioButton("지출");
		
		ButtonGroup group = new ButtonGroup();
		group.add(rbIncome);
		group.add(rbExpends);
		
		btnSearch1 = new JButton("search");
		btnSearch1.setBackground(Color.PINK);
		btnSearch2 = new JButton("search");
		btnSearch2.setBackground(Color.PINK);
		
		btnAdd1 = new JButton("추가");
		btnAdd1.setBackground(new Color(0xFF7E7E));
		btnAdd2 = new JButton("추가");
		btnAdd2.setBackground(new Color(0xFF7E7E));
		
		btnCheck = new JButton("확인");
		btnCheck.setForeground(Color.WHITE);
		btnCheck.setBackground(new Color(0xFF4848));
		
		// search창
		tf1 = new JTextField(15);
		tf2 = new JTextField(15);

		group1 = new ButtonGroup();
		for(int i= 0; i < tbtnTagIncome.size(); i++ ) {
			group1.add(tbtnTagIncome.get(i));
		}

		group3 = new ButtonGroup();
		for(int i= 0; i < tbtnTagExpends.size(); i++ ) {
			group3.add(tbtnTagExpends.get(i));
		}

		
		
		tBorder1 = new TitledBorder("# HashTag 1");
		tBorder1.setBorder(new LineBorder(Color.BLACK));

		// 해시태그 2
		tbtnTag2 = new ArrayList<>();
		tbtnTag2 = dataCenter.getHashTag2();
		
		group2 = new ButtonGroup();
		for(int i= 0; i < tbtnTag2.size(); i++ ) {
			group2.add(tbtnTag2.get(i));
		}

		tBorder2 = new TitledBorder("# HashTag 2");
		tBorder2.setBorder(new LineBorder(Color.BLACK));
	}
	public JToggleButton getTbtn1(String name) {
		JToggleButton tb1 = new JToggleButton(name);
		return tb1;
	}
	
	
	public JToggleButton getTbtn2(String name) {
		JToggleButton tb2 = new JToggleButton(name);
		return tb2;
	}

	private void setDisplay() {
		pnlHashTag1 = new JPanel();
		pnlHashTagBox1 = new JPanel(new BorderLayout()); 
		pnlCenter = new JPanel(new BorderLayout());
		pnlToggle1 = new JPanel(new GridLayout(0,4,5,5));	
		
		//돼지그림
		JPanel pnlImg = new JPanel();
		pnlImg.add(lblImg);
		pnlImg.setBackground(Color.WHITE);
		
		//라디오버튼
		JPanel pnlRadioButton = new JPanel(); 
		rbIncome.setBackground(Color.WHITE);
		rbExpends.setBackground(Color.WHITE);
		pnlRadioButton.add(rbIncome);
		pnlRadioButton.add(rbExpends);
		pnlRadioButton.setBackground(Color.WHITE);
		
		JPanel pnlNorth = new JPanel(new BorderLayout());
		pnlNorth.add(pnlImg, BorderLayout.NORTH);
		pnlNorth.add(pnlRadioButton, BorderLayout.EAST);
		pnlNorth.setBackground(Color.WHITE);
		
		setToggleDisplay(tbtnTagIncome, pnlToggle1, tf1);
		
		pnlHashTagAdd1 = new JPanel(); 
		pnlHashTagAdd1.add(tf1);
		pnlHashTagAdd1.add(btnSearch1);
		pnlHashTagAdd1.add(btnAdd1);
		pnlHashTagAdd1.setBackground(Color.WHITE);
		
		pnlHashTagBox1.add(pnlToggle1, BorderLayout.CENTER);
		pnlHashTagBox1.add(pnlHashTagAdd1, BorderLayout.NORTH);
		pnlHashTagBox1.setBackground(Color.WHITE);
		
		pnlHashTag1.setBorder(tBorder1);
		pnlHashTag1.add(pnlHashTagBox1);
		pnlHashTag1.setBackground(Color.WHITE);
		
	

		//해시태그2의 상자에 들어가는 첫번째줄.
		JPanel pnlHashTagAdd2 = new JPanel(); 
		pnlHashTagAdd2.add(tf2);
		pnlHashTagAdd2.add(btnSearch2);
		pnlHashTagAdd2.add(btnAdd2);
		pnlHashTagAdd2.setBackground(Color.WHITE);
		
		//해시태그2의 토글버튼(해시태그)
		pnlToggle2 = new JPanel(new GridLayout(0,4,5,5));
		for(JToggleButton tbtn2 : tbtnTag2) {
			pnlToggle2.add(tbtn2);
			tbtn2.setPreferredSize(new Dimension(100,30));
		}
		pnlToggle2.setBackground(Color.WHITE);
	
		
		//해시태그2의 박스안에 들어가는 모든 것
		pnlHashTagBox2 = new JPanel(new BorderLayout()); 
		pnlHashTagBox2.add(pnlHashTagAdd2, BorderLayout.NORTH);
		pnlHashTagBox2.add(pnlToggle2, BorderLayout.CENTER);
		pnlHashTagBox2.setBackground(Color.WHITE);
		
		//해시태그2 부분 전체
		JPanel pnlHashTag2 = new JPanel();
		pnlHashTag2.setBorder(tBorder2);
		pnlHashTag2.add(pnlHashTagBox2);
		pnlHashTag2.setBackground(Color.WHITE);

		pnlCenter.add(pnlHashTag1, BorderLayout.NORTH);
		pnlCenter.add(pnlHashTag2, BorderLayout.CENTER);
		pnlCenter.setBackground(Color.WHITE);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnCheck);
	//	pnlSouth.setBorder(new EmptyBorder(10,10,2,10));
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

				if (rbtn == rbIncome) {
					for(JToggleButton button : tbtnTagIncome) {
						button.updateUI();
					}

					setToggleDisplay(tbtnTagIncome, pnlToggle1, tf1);
					flag = false;
					

					
					
				} else if (rbtn == rbExpends) {
					setToggleDisplay(tbtnTagExpends, pnlToggle1, tf1);
					flag = true;
					for(JToggleButton tbtn1 : tbtnTagExpends) {
						tbtn1.setSelected(false);
					}
				}

				if (!flag) {
					if (rbtn == btnAdd1) {
						checkHash(tbtnTagIncome, group1, tf1, pnlToggle1);
					}
				} else {
					if (rbtn == btnAdd1) {
						checkHash(tbtnTagExpends, group3, tf1, pnlToggle1);
					}
				}
				
				if (rbtn == btnAdd2) {
					checkHash(tbtnTag2, group2, tf2, pnlToggle2);
				}
				
				
				if(!flag) {
					if(rbtn == btnSearch1) {
						searchHash(tbtnTagIncome, tf1, pnlToggle1);
					}
				}else {
					if(rbtn == btnSearch1) {
						searchHash(tbtnTagExpends, tf1, pnlToggle1);
					}
				}
				
				if(rbtn == btnSearch2) {
					searchHash(tbtnTag2, tf2, pnlToggle2);
				}
				
				String str = "";

				if(rbtn == btnCheck) {
					if(rbIncome.isSelected()) {
						owner.getLblIncomeSpendArea().setText("#"+rbIncome.getText());
						if(getButtonValue(str, tbtnTagIncome).equals("") || getButtonValue(str, tbtnTag2).equals("")) {
							alert("선택되지 않은 해시태그가 있습니다.");
						}else {
							owner.getLblHashTagAreaA().setText(getButtonValue(str, tbtnTagIncome));
							owner.getLblHashTagAreaB().setText(getButtonValue(str, tbtnTag2));
							dispose();
							owner.getLblIncomeSpendArea().setForeground(Color.BLACK);
							owner.getLblHashTagAreaA().setForeground(Color.BLACK);
							owner.getLblHashTagAreaB().setForeground(Color.BLACK);
						}
					}else {
						owner.getLblIncomeSpendArea().setText("#"+rbExpends.getText());
						if(getButtonValue(str, tbtnTagExpends).equals("") || getButtonValue(str, tbtnTag2).equals("")) {
							alert("선택되지 않은 해시태그가 있습니다.");
						}else {
							owner.getLblHashTagAreaA().setText(getButtonValue(str, tbtnTagExpends));
							owner.getLblHashTagAreaB().setText(getButtonValue(str, tbtnTag2));
							dispose();
							owner.getLblIncomeSpendArea().setForeground(Color.BLACK);
							owner.getLblHashTagAreaA().setForeground(Color.BLACK);
							owner.getLblHashTagAreaB().setForeground(Color.BLACK);
						}
					}
				
				
				}
				
			}
		};
		for (JToggleButton button : tbtnTagIncome) {
			button.addActionListener(aListener);
		}
		btnCheck.addActionListener(aListener);
		btnSearch1.addActionListener(aListener);
		btnSearch2.addActionListener(aListener);
		btnAdd1.addActionListener(aListener);
		btnAdd2.addActionListener(aListener);
		rbIncome.addActionListener(aListener);
		rbExpends.addActionListener(aListener);
	}

	
	
	public void checkHash(ArrayList<JToggleButton> toggle, ButtonGroup group, JTextField tf, JPanel pnl) {
		if (AccountBookUtil.isEmpty(tf)) {
			information("추가할 해시태그를 입력해주세요");
		} else if (checkDup(tf1.getText(), toggle)) {
			information("중복된 해시태그가 있습니다.");
		} else {
			// JTextField에 입력한 값을 배열과 버튼에 추가
			addHash(toggle, group, tf);
			setToggleDisplay(toggle, pnl, tf);
		}
	}
	
	public String getButtonValue(String str, ArrayList<JToggleButton> toggle) {
		str = "";
		for(int i = 0 ; i < toggle.size(); i++) {
			if(toggle.get(i).isSelected()) {
				str = toggle.get(i).getText();
			}
		}
		return str;
	}
	
	
	public void addHash(ArrayList<JToggleButton> toggle, ButtonGroup group, JTextField tf) {
		toggle.add(new JToggleButton("#" + tf.getText()));
		group.add(toggle.get(toggle.size() - 1));
		information("입력하신 해시태그가 추가되었습니다.");
	}
	
	public void searchHash(ArrayList<JToggleButton> toggle, JTextField tf, JPanel pnl) {
		pnl.removeAll();
		pnl.updateUI();
		for(int i=0; i<toggle.size(); i++) {
			if(toggle.get(i).getText().contains(tf.getText())){
				pnl.add(toggle.get(i));
			}
		}
		pack();
	}
	public void setToggleDisplay(ArrayList<JToggleButton> toggle, JPanel pnl, JTextField tf) {
		pnl.removeAll();
		for(JToggleButton tbtn1 : toggle) {
			tbtn1.setSelected(false);
			pnl.add(tbtn1);
			tbtn1.setPreferredSize(new Dimension(100,30));
		}
		pnl.setBackground(Color.WHITE);
		pnl.updateUI();
		tf.setText("");
		pack();
	}
	

	public boolean checkDup(String str, ArrayList<JToggleButton> toggle) {
		boolean result = false;
		String hash = "#"+str;
		for(int i=0; i<toggle.size(); i++) {
			if(hash.equals(toggle.get(i).getText())) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	
	public void information(String msg) {
		JOptionPane.showMessageDialog(
				HashTagIncome.this,
				msg,
				"알림",
				JOptionPane.INFORMATION_MESSAGE
			);
	}
	
	
	public ArrayList<JToggleButton> getTbtnTag2() {
		return tbtnTag2;
	}

	public void setTbtnTag2(ArrayList<JToggleButton> tbtnTag2) {
		this.tbtnTag2 = tbtnTag2;
	}
	
	private void alert(String msg) {
		JOptionPane.showMessageDialog(
			HashTagIncome.this,
			msg,
			"알림",
			JOptionPane.INFORMATION_MESSAGE
		);
	}

	private void showFrame() {
		setTitle("거지탈출 가계부");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
}
	
