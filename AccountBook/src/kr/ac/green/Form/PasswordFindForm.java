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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kr.ac.green.model.User;
import kr.ac.green.util.AccountBookUtil;

public class PasswordFindForm extends JDialog{
	private StartForm owner;
	
	User user;
	
	private JLabel lblBack;
	private JLabel lblImg;
	private JLabel lblTip;
	
	private JPanel pnlNorth;
	private JPanel pnlCenter;
	private JPanel pnlSouth;
	
	private JButton btnOK;
	
	private JComboBox<String> cbQuestion;
	private JComboBox<String> cbAnswer;
	
	private String[] questionList = {
			"�����̳� ������ �̸���?",
			"���� �ٴϴ� �п���?",
			"�� ������?",
		};

	private String[] answer1List = { "����", "�̹�", "���" };
	private String[] answer2List = { "������Ӿ�ī����", "��������п�", "�׸���ǻ���п�"};
	private String[] answer3List = { "����", "�λ�", "�뱸" };

	
	public PasswordFindForm(StartForm owner) {
		super(owner, "findPassword", true);
		this.owner = owner;
		init();
		setDisplay();
		addListeners();
		showDlg();
	}
	
	private void init() {
		lblBack = new JLabel(AccountBookUtil.getImageIcon("Back.png", 30), JLabel.LEFT);
		lblImg = new JLabel(AccountBookUtil.getImageIcon("Pigh.png"), JLabel.CENTER);
		lblTip = new JLabel("��й�ȣ ���� �� ����ߴ� ������ ���� �����ϼ���.", JLabel.CENTER);
		
		cbQuestion = new JComboBox<String>(questionList);
		cbQuestion.setPreferredSize(AccountBookUtil.CBSIZE);
		cbQuestion.setBackground(AccountBookUtil.PASTELPINK);
		
		cbAnswer = new JComboBox<String>();
		cbAnswer.setPreferredSize(AccountBookUtil.CBSIZE);
		cbAnswer.setBackground(AccountBookUtil.PASTELPINK);
		
		btnOK = new JButton("Ȯ��");
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
		TitledBorder tBorder = new TitledBorder(new LineBorder(Color.GRAY, 0), "����");
		tBorder.setTitleJustification(TitledBorder.CENTER);
		pnlCombo.setBorder(tBorder);
		pnlCombo.setBackground(Color.WHITE);
		
		JPanel pnlCombo2 = new JPanel();
		pnlCombo2.add(cbAnswer);
		TitledBorder tBorder2 = new TitledBorder(new LineBorder(Color.GRAY, 0), "���� ��");
		tBorder2.setTitleJustification(TitledBorder.CENTER);
		pnlCombo2.setBorder(tBorder2);
		pnlCombo2.setBackground(Color.WHITE);
		
		JPanel pnlLabel = new JPanel();
		pnlLabel.add(lblTip);
		pnlLabel.setBackground(Color.WHITE);
		
		JPanel pnlCCenter = new JPanel(new GridLayout(0, 1));
		pnlCCenter.add(pnlLabel);
		pnlCCenter.add(pnlCombo);
		pnlCCenter.add(pnlCombo2);
		pnlCCenter.setBackground(Color.WHITE);
		
		
		JPanel pnlBtn = new JPanel();
		pnlBtn.add(btnOK);
		pnlBtn.setBackground(Color.WHITE);
		
		pnlSouth = new JPanel();
		pnlSouth.add(pnlBtn);
		pnlSouth.setBackground(Color.WHITE);
		
		pnlCenter = new JPanel(new BorderLayout());
		pnlCenter.add(pnlCNorth, BorderLayout.NORTH);
		pnlCenter.add(pnlCCenter, BorderLayout.CENTER);
		pnlCenter.setBackground(Color.WHITE);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
	}
	
	private void addListeners() {
		cbQuestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JComboBox cb = (JComboBox)ae.getSource();
				int idx = cb.getSelectedIndex();
				switch(idx) {
				case 0:	cbAnswer = new JComboBox<String>(answer1List);
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
		
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//������ �����ְ� Ʋ���� �ٽ� ������ �ϱ�
				if(ae.getSource() == btnOK) {
					System.out.println(owner.getQChoice());
					System.out.println(cbQuestion.getSelectedItem().toString());
					
					String question = cbQuestion.getSelectedItem().toString();
					String answer = cbAnswer.getSelectedItem().toString();
					
					if(question.equals(owner.getQChoice())) {
						if(answer.equals(owner.getAChoice())) {
							alert("��й�ȣ�� " + owner.getPassword() + " �Դϴ�.");
									
							dispose();
							owner.setVisible(true);
						} else {
							alert("���� ���� ��ġ���� �ʽ��ϴ�.");
						}
					} else {
						alert("������ ��ġ���� �ʽ��ϴ�.");
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
			PasswordFindForm.this,
			msg,
			"�˸�",
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
		setTitle("��й�ȣ ã��");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
}
