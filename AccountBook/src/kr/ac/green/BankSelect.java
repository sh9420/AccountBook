package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import kr.ac.green.util.AccountBookUtil;

// ���� �󼼳��� �߰� class
public class BankSelect extends JDialog {
	private JLabel lblImg;
	private JLabel lblSelect;
	
	private JTextField tfSearch;
	private JButton btnAdd;
	private String[] bankList = {"��������", "��������", "����", "īī����ũ"};
	private ArrayList<JToggleButton> tbtnTag;
	private JButton btnOk;
	private ButtonGroup group;
	private String bankName;
	
	private JPanel pnlMain = new JPanel();
	
	private PageAdd owner;
	
	public BankSelect(PageAdd owner, String bank) {
		super(owner, "BankSelect", false);
		this.owner = owner;
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		init();
		setDisplay();

		if (!(bank.trim().length() == 0)) {
			for(int i=0; i<tbtnTag.size(); i++) {
				if(tbtnTag.get(i).getText().equals(bank)) {
					tbtnTag.get(i).setSelected(true);
					break;
				}
			}
		}
		
		addListeners();
		showFrame();
	}
	
	private void init() {
		// ���� ������ �߰�
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("piggy-bank-icon.png");
		setIconImage(icon);
		
		// ���� ���� 
		lblImg = new JLabel(new ImageIcon("BigPig.png"), JLabel.CENTER);
		lblSelect = new JLabel("������ �����ϼ���", JLabel.CENTER);
		lblSelect.setFont(new Font("SERIF", Font.PLAIN, 17));
		lblSelect.setForeground(Color.BLACK);
		
	
////	tbtnTag = new JToggleButton[];
//		tbtnTag.setBackground(Color.WHITE);
//		tbtnTag.setMargin(new Insets(2,2,2,2));
//	}
		tfSearch = new JTextField(15);
		tfSearch.setBorder(new LineBorder(new Color(0xFF7E7E), 1));
		btnAdd = new JButton("�߰�");
		btnAdd.setBackground(new Color(0xFF7E7E));
		tbtnTag = new ArrayList<>();
		
		for(String name : bankList) {
			tbtnTag.add(getTbtn(name));
		}
		
		group = new ButtonGroup();
		for(int i= 0; i < tbtnTag.size(); i++ ) {
			group.add(tbtnTag.get(i));
		}
		
		btnOk = new JButton("Ȯ��");
		btnOk.setBackground(new Color(0xFF7E7E));
	}
	
	private JToggleButton getTbtn(String name) {
		JToggleButton tbtn = new JToggleButton(name);
		return tbtn;
	}

	private void setDisplay() {
	
		// �����ϼ��� �г�
		JPanel pnlSelect = new JPanel();
		pnlSelect.add(lblSelect);
		pnlSelect.setBackground(Color.WHITE);
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setPreferredSize(new Dimension(366,180));
		JPanel pnlLogo = new JPanel(new GridLayout(0, 1));
		pnlLogo.add(lblImg);
		pnlLogo.add(pnlSelect);
		pnlLogo.setBackground(Color.WHITE);
		
		pnlNorth.add(pnlLogo);
		pnlNorth.setBackground(Color.WHITE);
		
		JPanel pnlCenter = new JPanel();
		JPanel pnlAdd = new JPanel();
		pnlAdd.add(tfSearch);
		pnlAdd.add(btnAdd);
		pnlAdd.setBackground(Color.WHITE);
		
		JPanel pnlTag = new JPanel(new GridLayout(0, 4, 5, 5));
		for(JToggleButton tbtn : tbtnTag) {
			pnlTag.add(tbtn);
		}
		pnlTag.setBackground(Color.WHITE);
		
		JPanel pnlCCenter = new JPanel(new GridLayout(0, 1));
		pnlCCenter.add(pnlAdd);
		pnlCCenter.add(pnlTag);
		
		
		pnlCCenter.setBorder(new LineBorder(new Color(0xFF7E7E),1));
		pnlCenter.add(pnlCCenter);
		
		
		pnlCenter.setBorder(new EmptyBorder(0, 10, 0, 10));
		pnlCenter.setBackground(Color.WHITE);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnOk);
		pnlSouth.setBackground(Color.WHITE);
		
		pnlMain = new JPanel(new BorderLayout());
		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		
		add(pnlMain);	
	}

	
//	public void actionPerformed(ActionEvent e) {
//		JToggleButton button = (JToggleButton) e.getSource();
//		if (button.isSelected()) {
//			int selNum = Integer.parseInt(button.getText());
	
	private void addListeners() {
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = (Object)ae.getSource();
				if (src == btnOk) {
					for(int i=0; i<tbtnTag.size(); i++) {
						if(tbtnTag.get(i).isSelected()) {
							bankName = tbtnTag.get(i).getText();
						}
					}
					owner.getLblBankArea().setText(bankName);
					information("�����Ͻ� ������ �߰��߽��ϴ�.");
					dispose();
				} else if (src == btnAdd) {
					if (AccountBookUtil.isEmpty(tfSearch)) {
						information("������ �Է����ּ���");
					} else if (checkDup(tfSearch.getText())) {
						information("�ߺ��� ������ �ֽ��ϴ�.");
					} else {
						// JTextField�� �Է��� ���� �迭�� ��ư�� �߰�
						List<String> addList = new ArrayList<>(Arrays.asList(bankList));
						addList.add(tfSearch.getText());
						bankList = new String[addList.size()];
						addList.toArray(bankList);

						tbtnTag.add(getTbtn(tfSearch.getText()));
						information("�Է��Ͻ� ������ �߰��Ǿ����ϴ�.");
						update();
					}
				}
			}
		};

		btnOk.addActionListener(aListener);
		btnAdd.addActionListener(aListener);
	}
		

	public void update() {
		remove(pnlMain);
		
		setDisplay();
		pack();
	}
	
	public boolean checkDup(String str) {
		boolean result = false;
		
		for(int i=0; i<bankList.length; i++) {
			if(str.equals(bankList[i])) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	
	public void information(String msg) {
		JOptionPane.showMessageDialog(
				BankSelect.this,
				msg,
				"�˸�",
				JOptionPane.INFORMATION_MESSAGE
			);
	}
	
    private void showFrame() {
		setTitle("����Ż�� �����");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
}
