package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


// �ؽ��±� �˻� �� �߰� ���� â class
public class HashTagSearchAdd extends JFrame{
	JLabel lblImg;	// ���� �̹���

	JRadioButton rbIncome; //����
	JRadioButton rbExpends; //����
	
	JButton btnSearch1; //�ؽ��±�1-search��ư
	JButton btnSearch2; //�ؽ��±�2-search��ư
	JButton btnAdd1;
	JButton btnAdd2;
	JButton btnCheck;
	
	JTextField tf1;
	JTextField tf2;
	
	ArrayList<JToggleButton> tbtnTag1;
	ArrayList<JToggleButton> tbtnTag2;
	
	TitledBorder tBorder1;
	TitledBorder tBorder2;
	
	public HashTagSearchAdd() {
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
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("piggy-bank-icon.png");
		setIconImage(icon);
		
		lblImg = new JLabel(new ImageIcon("BigPig.png"), JLabel.CENTER);
		
		// ������ư 
		rbIncome = new JRadioButton("����",true);
		rbExpends = new JRadioButton("����");
		
		ButtonGroup group = new ButtonGroup();
		group.add(rbIncome);
		group.add(rbExpends);
	
		btnSearch1 = new JButton("search");
		btnSearch1.setBackground(Color.PINK);
		btnSearch2 = new JButton("search");
		btnSearch2.setBackground(Color.PINK);
		
		btnAdd1 = new JButton("�߰�");
		btnAdd1.setBackground(new Color(0xFF7E7E));
		btnAdd2 = new JButton("�߰�");
		btnAdd2.setBackground(new Color(0xFF7E7E));
		
		btnCheck = new JButton("Ȯ��");
		btnCheck.setForeground(Color.WHITE);
		btnCheck.setBackground(new Color(0xFF4848));
		
		// searchâ
		tf1 = new JTextField(15);
		tf2 = new JTextField(15);
		
		// �ؽ��±� 1
		tbtnTag1 = new ArrayList<>();
		tbtnTag1.add(getTbtn1("#�ĺ�"));
		tbtnTag1.add(getTbtn1("#������"));
		tbtnTag1.add(getTbtn1("#����"));
		tbtnTag1.add(getTbtn1("#�����"));
		
		ButtonGroup group1 = new ButtonGroup();
		for(int i= 0; i < tbtnTag1.size(); i++ ) {
			group.add(tbtnTag1.get(i));
		}
		
		tBorder1 = new TitledBorder("# HashTag 1");
		tBorder1.setBorder(new LineBorder(Color.BLACK));

		// �ؽ��±� 2
		tbtnTag2 = new ArrayList<>();
		tbtnTag2.add(getTbtn2("#����Ʈ"));
		tbtnTag2.add(getTbtn2("#����ģ��"));
		tbtnTag2.add(getTbtn2("#����ģ��"));
		tbtnTag2.add(getTbtn2("#�θ��"));
		tbtnTag2.add(getTbtn2("#�׸���"));
		tbtnTag2.add(getTbtn2("#�ܽ�"));
		tbtnTag2.add(getTbtn2("#����"));
		tbtnTag2.add(getTbtn2("#�����"));
		tbtnTag2.add(getTbtn2("#����"));
		tbtnTag2.add(getTbtn2("#���"));
		tbtnTag2.add(getTbtn2("#�Ұ��"));
		tbtnTag2.add(getTbtn2("#�������"));
		
		ButtonGroup group2 = new ButtonGroup();
		for(int i= 0; i < tbtnTag2.size(); i++ ) {
		group.add(tbtnTag2.get(i));
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
		//�����׸�
		JPanel pnlImg = new JPanel();
		pnlImg.add(lblImg);
		pnlImg.setBackground(Color.WHITE);
		
		//������ư
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
		
		//�ؽ��±�1�� ���ڿ� ���� ù��°��.
		JPanel pnlHashTagAdd1 = new JPanel(); 
		pnlHashTagAdd1.add(tf1);
		pnlHashTagAdd1.add(btnSearch1);
		pnlHashTagAdd1.add(btnAdd1);
		pnlHashTagAdd1.setBackground(Color.WHITE);
		
		//�ؽ��±�1�� ��۹�ư(�ؽ��±�)
		JPanel pnlToggle1 = new JPanel(new GridLayout(0,4,5,5));	
		for(JToggleButton tbtn1 : tbtnTag1) {
			pnlToggle1.add(tbtn1);
		}
		pnlToggle1.setBackground(Color.WHITE);
		
		
		JPanel pnlCCenter = new JPanel(new BorderLayout());
		pnlCCenter.add(pnlHashTagAdd1,BorderLayout.NORTH);
		pnlCCenter.add(pnlToggle1,BorderLayout.CENTER);


		//�ؽ��±�2�� ���ڿ� ���� ù��°��.
		JPanel pnlHashTagAdd2 = new JPanel(); 
		pnlHashTagAdd2.add(tf2);
		pnlHashTagAdd2.add(btnSearch2);
		pnlHashTagAdd2.add(btnAdd2);
		pnlHashTagAdd2.setBackground(Color.WHITE);
		
		//�ؽ��±�2�� ��۹�ư(�ؽ��±�)
		JPanel pnlToggle2 = new JPanel(new GridLayout(0,4,5,5));
		for(JToggleButton tbtn2 : tbtnTag2) {
			pnlToggle2.add(tbtn2);
		}
		pnlToggle2.setBackground(Color.WHITE);
		
		//�ؽ��±�1�� �ڽ��ȿ� ���� ��� ��
		JPanel pnlHashTagBox1 = new JPanel(new BorderLayout()); 
		pnlHashTagBox1.add(pnlHashTagAdd1, BorderLayout.NORTH);
		pnlHashTagBox1.add(pnlToggle1, BorderLayout.CENTER);
		pnlHashTagBox1.setBackground(Color.WHITE);
		
		//�ؽ��±�2�� �ڽ��ȿ� ���� ��� ��
		JPanel pnlHashTagBox2 = new JPanel(new BorderLayout()); 
		pnlHashTagBox2.add(pnlHashTagAdd2, BorderLayout.NORTH);
		pnlHashTagBox2.add(pnlToggle2, BorderLayout.CENTER);
		pnlHashTagBox2.setBackground(Color.WHITE);
		
		//�ؽ��±�1 �κ� ��ü
		JPanel pnlHashTag1 = new JPanel(new GridLayout(0,1));
		pnlHashTag1.setBorder(tBorder1);
		pnlHashTag1.add(pnlHashTagBox1);
		pnlHashTag1.setBackground(Color.WHITE);
		

		//�ؽ��±�2 �κ� ��ü
		JPanel pnlHashTag2 = new JPanel(new GridLayout(0,1));
		pnlHashTag2.setBorder(tBorder2);
		pnlHashTag2.add(pnlHashTagBox2);
		pnlHashTag2.setBackground(Color.WHITE);

		JPanel pnlCenter = new JPanel(new BorderLayout());
		pnlCenter.add(pnlCCenter);
		pnlCenter.add(pnlHashTag1, BorderLayout.NORTH);
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));							
		pnlCenter.add(pnlHashTag2, BorderLayout.CENTER);
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
		setTitle("����Ż�� �����");
		pack();
		//setSize(200, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new HashTagSearchAdd();
	}
	
	
}
