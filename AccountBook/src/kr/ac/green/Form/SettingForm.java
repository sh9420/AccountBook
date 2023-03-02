package kr.ac.green.Form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.green.util.AccountBookUtil;

public class SettingForm extends JDialog{
	
	private StartForm owner;
	
	private JLabel lblBack;
	private JLabel lblSetting;
	
	private JButton btnFileOut;
	private JButton btnFileIn;
	private JButton btnChangePw;
	
	private JFileChooser chooser;
	
	public SettingForm(StartForm owner) {
		super(owner, "setting", false);
		this.owner = owner;
		init();
		setDisplay();                             
		addListeners();
		showDlg();
	}
	
	private void init() {
		lblBack = new JLabel(AccountBookUtil.getImageIcon("Back.png", 30), JLabel.LEFT);
		lblSetting = new JLabel(AccountBookUtil.getImageIcon("Setting.png"), JLabel.CENTER);
		
		btnFileOut = new JButton("파일 내보내기");
		btnFileIn = new JButton("파일 불러오기");
		btnChangePw = new JButton("비밀번호 변경");
	}
	
	private void setDisplay() {
		JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlNorth.add(lblBack);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.add(lblSetting);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnFileOut);
		pnlSouth.add(btnFileIn);
		pnlSouth.add(btnChangePw);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}
	
	private void addListeners() {
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				chooser = new JFileChooser(".");
				if(ae.getSource() == btnFileOut) {
					int choice = chooser.showSaveDialog(null);
					
					if(choice == JFileChooser.APPROVE_OPTION) { 
						
					}
					//JFileChooser save
				} else if(ae.getSource() == btnFileIn) {
					int choice = chooser.showOpenDialog(null);
					//JFileChooser Open
					//하던거 저장할지 물어보는 JOptionPane
				} else {
					setVisible(false);
					new PasswordResetForm(owner);
				}
			}
		};
		btnFileOut.addActionListener(aListener);
		btnFileIn.addActionListener(aListener);
		btnChangePw.addActionListener(aListener);
		
		MouseListener mListener = new MouseAdapter() {
			// pressed -> released -> clicked (마우스 이벤트 발생 순서)
			
			@Override
			public void mouseClicked(MouseEvent me) {
				dispose();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
		};
		lblBack.addMouseListener(mListener);
	}
	
	private void showDlg() {
		setTitle("설정");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
}
