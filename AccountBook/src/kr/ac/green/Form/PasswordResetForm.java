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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kr.ac.green.util.AccountBookUtil;

public class PasswordResetForm extends JDialog {
	
	private StartForm owner;
	
	private JLabel lblBack;
	private JLabel lblImg;
	private JLabel lblNowPw;
	private JLabel lblNewPw;
	private JLabel lblNewRe;
	
	private JTextField tfNowPw;
	private JTextField tfNewPw;
	private JTextField tfCheckPw;
	
	private JButton btnChange;
	
	public PasswordResetForm(StartForm owner) {
		super(owner, "setPassword", true);
		this.owner = owner;
		init();
		setDisplay();
		addListeners();
		showDlg();
	}
	
	private void init() {
		lblBack = new JLabel(AccountBookUtil.getImageIcon("Back.png", 30), JLabel.LEFT);
		lblImg = new JLabel(AccountBookUtil.getImageIcon("PigH.png"), JLabel.CENTER);
		lblNowPw = AccountBookUtil.getLabel("���� ��й�ȣ");
		lblNewPw = AccountBookUtil.getLabel("�� ��й�ȣ");
		lblNewRe = AccountBookUtil.getLabel("�� ��й�ȣ Ȯ��");
		
		tfNowPw = new JTextField(10);
		tfNewPw = new JTextField(10);
		tfCheckPw = new JTextField(10);
		
		btnChange = new JButton("����");
	}
	
	private void setDisplay() {
		JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlNorth.add(lblBack);
		pnlNorth.setBackground(Color.WHITE);
		
		JPanel pnlCNorth = new JPanel();
		pnlCNorth.add(lblImg);
		pnlCNorth.setBackground(Color.WHITE);
		
		JPanel pnlNowPw = new JPanel(new GridLayout(1, 2));
		pnlNowPw.add(lblNowPw);
		pnlNowPw.add(tfNowPw);
		pnlNowPw.setBorder(new EmptyBorder(5, 10, 10, 5));
		pnlNowPw.setBackground(Color.WHITE);
		
		JPanel pnlNewPw = new JPanel(new GridLayout(1, 2));
		pnlNewPw.add(lblNewPw);
		pnlNewPw.add(tfNewPw);
		pnlNewPw.setBorder(new EmptyBorder(5, 10, 10, 5));
		pnlNewPw.setBackground(Color.WHITE);
		
		JPanel pnlNewRe = new JPanel(new GridLayout(1, 2));
		pnlNewRe.add(lblNewRe);
		pnlNewRe.add(tfCheckPw);
		pnlNewRe.setBorder(new EmptyBorder(5, 10, 10, 5));
		pnlNewRe.setBackground(Color.WHITE);
		
		JPanel pnlCCenter = new JPanel(new GridLayout(3, 1));
		pnlCCenter.add(pnlNowPw);
		pnlCCenter.add(pnlNewPw);
		pnlCCenter.add(pnlNewRe);
		pnlCCenter.setBackground(Color.WHITE);
		
		JPanel pnlCenter = new JPanel(new BorderLayout());
		pnlCenter.add(pnlCNorth, BorderLayout.NORTH);
		pnlCenter.add(pnlCCenter, BorderLayout.CENTER);
		pnlCenter.setBackground(Color.WHITE);
		
		JPanel pnlBtn = new JPanel();
		pnlBtn.add(btnChange);
		pnlBtn.setBackground(Color.WHITE);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(pnlBtn);
		pnlSouth.setBackground(Color.WHITE);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
	}
	
	private void addListeners() {
		btnChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(ae.getSource() == btnChange) {
					if(!(tfNowPw.getText().equals(owner.getPassword()))) {
						alert("���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
						tfNowPw.requestFocus();
					} else if(AccountBookUtil.isEmpty(tfNewPw)) {
						alert("�� ��й�ȣ�� �Է����ּ���.");
						tfNewPw.requestFocus();
					} else if(AccountBookUtil.isEmpty(tfCheckPw)) {
						alert("�� ��й�ȣ Ȯ���� �Է����ּ���.");
						tfCheckPw.requestFocus();
					} else if(!(tfNewPw.getText().equals(tfCheckPw.getText()))) {
						alert("��й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.");
						tfNewPw.requestFocus();
					}	else {
						alert("������ �Ϸ� �Ǿ����ϴ�.");
						owner.setPassword(tfNewPw.getText());
						owner.save();
						System.out.println(owner.getList());
					}
					//�� ��й�ȣ�� �� ��й�ȣ Ȯ���� ������ Ȯ���ϰ�
					//������ ����â���� �ǵ��ư����� �����Ѵ�.
				}
			}
		});
		
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				dispose();
				new SettingForm(owner);
			}
			
			@Override
			public void mouseEntered(MouseEvent me) {
				lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
	}
	
	private void alert(String msg) {
		JOptionPane.showMessageDialog(
			PasswordResetForm.this,
			msg,
			"�˸�",
			JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	private void showDlg() {
		setTitle("��й�ȣ ã��");
		pack();
		setLocationRelativeTo(null);
		//JDialog�� �ٲٰ� DISPOSE_ON_CLOSE�� �ٲٱ�
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
}
