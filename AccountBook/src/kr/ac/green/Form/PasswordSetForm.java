package kr.ac.green.Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kr.ac.green.model.User;
import kr.ac.green.util.AccountBookUtil;

public class PasswordSetForm extends JDialog{
	private StartForm owner;
	private String[] questionList = {
		"승훈이네 강아지 이름은?",
		"지금 다니는 학원은?",
		"내 고향은?",
	};

	private String[] answer1List = { "누리", "미미", "모모" };
	private String[] answer2List = { "레드게임아카데미", "블루드로잉학원", "그린컴퓨터학원"};
	private String[] answer3List = { "서울", "부산", "대구" };
	
	private JLabel lblImg;
	private JLabel lblBack;
	private JLabel lblSetPw;
	private JLabel lblCheckPw;
	private JLabel lblTip;
	
	private JPanel pnlSouth = new JPanel();
	private JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel pnlCenter = new JPanel(new BorderLayout());
	
	private JComboBox<String> cbQuestion;
	private JComboBox<String> cbAnswer;
	
	private JTextField tfSetPw;
	private JTextField tfCheckPw;

	
	private JButton btnChange;
	
	public PasswordSetForm(StartForm owner) {
		super(owner, "setPassword", true);
		this.owner = owner;
		init();
		setDisplay();
		addListeners();
		showDlg();
	}

	
	private void init() {
		lblImg = new JLabel(AccountBookUtil.getImageIcon("PigH.png"), JLabel.CENTER);
		lblBack = new JLabel(AccountBookUtil.getImageIcon("Back.png", 30), JLabel.LEFT);
		lblSetPw = AccountBookUtil.getLabel("새 비밀번호");
		lblCheckPw = AccountBookUtil.getLabel("새 비밀번호 확인");
		lblTip = new JLabel("비밀번호 분실 시, 설정 할 질문을 선택하고, 기입하세요.", JLabel.CENTER);
		
		cbQuestion = new JComboBox<String>(questionList);
		cbQuestion.setPreferredSize(AccountBookUtil.CBSIZE);
		cbQuestion.setBackground(AccountBookUtil.PASTELPINK);
		
		cbAnswer = new JComboBox<String>(answer1List);
		cbAnswer.setPreferredSize(AccountBookUtil.CBSIZE);
		cbAnswer.setBackground(AccountBookUtil.PASTELPINK);
		
		
		tfSetPw = new JTextField(10);
		tfCheckPw = new JTextField(10);

		
		btnChange = new JButton("확인");
	}
	
	private void setDisplay() {
		pnlNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlNorth.add(lblBack);
		pnlNorth.setBackground(Color.WHITE);
		
		JPanel pnlCNorth = new JPanel(new GridLayout(0, 1));
		pnlCNorth.add(lblImg);
		pnlCNorth.setBackground(Color.WHITE);
		
		JPanel pnlCombo = new JPanel();
		pnlCombo.add(cbQuestion);
		TitledBorder tBorder = new TitledBorder(new LineBorder(Color.GRAY, 0), "질문");
		tBorder.setTitleJustification(TitledBorder.CENTER);
		pnlCombo.setBorder(tBorder);
		pnlCombo.setBackground(Color.WHITE);
		
		JPanel pnlCombo2 = new JPanel();
		pnlCombo2.add(cbAnswer);
		TitledBorder tBorder2 = new TitledBorder(new LineBorder(Color.GRAY, 0), "질문 답");
		tBorder2.setTitleJustification(TitledBorder.CENTER);
		pnlCombo2.setBorder(tBorder2);
		pnlCombo2.setBackground(Color.WHITE);
		
		JPanel pnlSet = new JPanel();
		pnlSet.add(lblSetPw);
		pnlSet.add(tfSetPw);
		pnlSet.setBackground(Color.WHITE);
		
		JPanel pnlCheck = new JPanel();
		pnlCheck.add(lblCheckPw);
		pnlCheck.add(tfCheckPw);
		pnlCheck.setBackground(Color.WHITE);
		
		JPanel pnlCCenter = new JPanel(new GridLayout(0, 1));
		pnlCCenter.add(lblTip);
		pnlCCenter.add(pnlCombo);
		pnlCCenter.add(pnlCombo2);
		pnlCCenter.setBorder(new EmptyBorder(0, 10, 0, 10));
		pnlCCenter.setBackground(Color.WHITE);
		
		JPanel pnlCSouth = new JPanel(new GridLayout(0, 1));
		pnlCSouth.add(pnlSet);
		pnlCSouth.add(pnlCheck);
		pnlCSouth.setBackground(Color.WHITE);
		
		pnlCenter = new JPanel(new BorderLayout());
		pnlCenter.add(pnlCNorth, BorderLayout.NORTH);
		pnlCenter.add(pnlCCenter, BorderLayout.CENTER);
		pnlCenter.add(pnlCSouth, BorderLayout.SOUTH);
		pnlCenter.setBackground(Color.WHITE);
		
		JPanel pnlBtn = new JPanel();
		pnlBtn.add(btnChange);
		pnlBtn.setBackground(Color.WHITE);
		
		pnlSouth = new JPanel();
		pnlSouth.add(pnlBtn);
		pnlSouth.setBackground(Color.WHITE);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
		
	}
	
	public void update() {
		remove(pnlNorth);
		remove(pnlCenter);
		remove(pnlSouth);
		
		setDisplay();
		pack();
	}
	
	private void addListeners() {
		cbQuestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JComboBox cb = (JComboBox)ae.getSource();
				int idx = cb.getSelectedIndex();
				switch(idx) {
				case 0:	cbAnswer = new JComboBox<String>(answer1List);
						cbAnswer.updateUI();
						setCb();
						break;
				case 1:	cbAnswer = new JComboBox<String>(answer2List);
						setCb();
						break;
				case 2:	cbAnswer = new JComboBox<String>(answer3List);
						setCb();
						break;
				}
			}
		});
		
		btnChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(ae.getSource() == btnChange) {
					if(AccountBookUtil.isEmpty(tfSetPw)) {
						alert("새 비밀번호를 입력해주세요.");
						tfSetPw.requestFocus();
					} else if(AccountBookUtil.isEmpty(tfCheckPw)) {
						alert("새 비밀번호 확인을 입력해주세요.");
						tfCheckPw.requestFocus();
					} else {
						if(!(tfSetPw.getText().equals(tfCheckPw.getText()))) {
							alert("비밀번호와 확인이 일치하지 않습니다.");
							tfSetPw.requestFocus();
						} else {
							User user = new User(
								cbQuestion.getSelectedItem().toString(),
								cbAnswer.getSelectedItem().toString(),
								tfSetPw.getText()
							);
							
							owner.setList(user);
							System.out.println(user);
							
							FileOutputStream fos = null;
							ObjectOutputStream oos = null;
							
							try {
								fos = new FileOutputStream("user.dat");
								oos = new ObjectOutputStream(fos);
								
								oos.writeObject(owner.getList());
								oos.flush();
								oos.reset();
								
							} catch(IOException e) {
								alert("저장 중 문제가 발생했습니다. 잠시 후 다시 시도하세요.");
							} finally {
								AccountBookUtil.closeAll(oos, fos);
							}
							
							dispose();
							owner.setVisible(true);
						}
					}
				}
			}
		});
		
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				JLabel lbl = (JLabel)me.getSource();
				if(lbl == lblBack) {
					dispose();
					owner.setVisible(true);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent me) {
				lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
	}
	
	private void alert(String msg) {
		JOptionPane.showMessageDialog(
			PasswordSetForm.this,
			msg,
			"알림",
			JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	private void setCb() {
		cbAnswer.setPreferredSize(AccountBookUtil.CBSIZE);
		cbAnswer.setBackground(AccountBookUtil.PASTELPINK);
		
		remove(pnlNorth);
		remove(pnlCenter);
		remove(pnlSouth);
		
		setDisplay();
		pack();
	}
	
	private void showDlg() {
		setTitle("비밀번호 설정");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

}
