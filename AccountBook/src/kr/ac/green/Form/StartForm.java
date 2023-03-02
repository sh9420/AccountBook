package kr.ac.green.Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import kr.ac.green.data.DataCenter;
import kr.ac.green.model.User;
import kr.ac.green.util.AccountBookUtil;

public class StartForm extends JFrame implements Serializable {

	private JLabel lblImg;
	private JLabel lblTip;
	private JLabel lblJoin;

	private JPasswordField pfPassword;

	private JButton btnConnect;
	private JButton btnFindPw;

	private User list;

	private DataCenter dataCenter;
	

	public StartForm() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dataCenter = DataCenter.getInstance();
		dataCenter.read();
		load();
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	private void load() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream("user.dat");
			ois = new ObjectInputStream(fis);

			list = (User) (ois.readObject());
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "������ �о���� ���� ������ �߻��߽��ϴ�. ���α׷��� �����մϴ�.", "����",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			AccountBookUtil.closeAll(ois, fis);
		}
	}

	public boolean save() {
		boolean isOk = true;

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream("user.dat");
			oos = new ObjectOutputStream(fos);

			oos.writeObject(list);
			oos.flush();
			oos.reset();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "���� �� ������ �߻��߽��ϴ�. ��� �� �ٽ� �õ��ϼ���.", "����", JOptionPane.ERROR_MESSAGE);
			isOk = false;
		} finally {
			AccountBookUtil.closeAll(oos, fos);
		}
		return isOk;
	}

	private void init() {
		lblImg = new JLabel(new ImageIcon("Pig.png"), JLabel.CENTER);
		lblTip = new JLabel("������ ��й�ȣ�� �Է��ϼ���.", JLabel.CENTER);
		lblTip.setFont(new Font("SERIF", Font.PLAIN, 12));
		lblJoin = new JLabel("ó�� �����ϼ̳���?", JLabel.CENTER);
		lblJoin.setFont(new Font("SEFIF", Font.PLAIN, 12));
		lblJoin.setForeground(Color.RED);

		pfPassword = new JPasswordField(15);
		pfPassword.setHorizontalAlignment(JTextField.CENTER);
		pfPassword.setBorder(new LineBorder(Color.PINK, 1));
		pfPassword.setEchoChar('��');

		btnConnect = new JButton("�����ϱ�");
		btnFindPw = new JButton("��й�ȣ ã��");
	}

	private void setDisplay() {
		JPanel pnlBtn = new JPanel();
		pnlBtn.add(btnConnect);
		pnlBtn.add(btnFindPw);
		pnlBtn.setBackground(Color.WHITE);

		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblImg);
		pnlNorth.setBackground(Color.WHITE);

		JPanel pnlPf = new JPanel();
		pnlPf.add(pfPassword);
		pnlPf.setBackground(Color.WHITE);

		JPanel pnlCenter = new JPanel(new GridLayout(0, 1));
		pnlCenter.add(lblTip);
		pnlCenter.add(pnlPf);
		pnlCenter.add(pnlBtn);
		pnlCenter.setBackground(Color.WHITE);

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(lblJoin);
		pnlSouth.setBackground(Color.WHITE);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void addListeners() {
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource() == btnFindPw) {
					setVisible(false);
					new PasswordFindForm(StartForm.this);
				} else {
					// btnConnect�� �� ��й�ȣ �´��� Ȯ���ϰ� mainȭ������ �����ϱ�
					File f = new File("user.dat");
					if (!f.exists() && list == null) {
						JOptionPane.showMessageDialog(StartForm.this, "��й�ȣ�� ���� �Ǿ����� �ʽ��ϴ�.", "����",
								JOptionPane.WARNING_MESSAGE);
					} else {
						String pw = String.valueOf(pfPassword.getPassword());
						if (pw.equals(list.getPassword())) {
							JOptionPane.showMessageDialog(StartForm.this, "�α��� ����.", "�˸�",
									JOptionPane.INFORMATION_MESSAGE);
							// ����â����
							setVisible(false);
							new MainForm();

						} else {
							JOptionPane.showMessageDialog(StartForm.this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "�˸�",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		};

		MouseListener mListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				JLabel lbl = (JLabel) me.getSource();

				File f = new File("user.dat");

				if (lbl == lblJoin) {
					if (f.exists()) {
						JOptionPane.showMessageDialog(StartForm.this, "�̹� ��й�ȣ�� �����Ǿ��ֽ��ϴ�.", "�˸�",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						setVisible(false);
						new PasswordSetForm(StartForm.this);
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent me) {
				lblJoin.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblJoin.setBorder(new LineBorder(Color.PINK, 1));
			}

			@Override
			public void mouseExited(MouseEvent me) {
				lblJoin.setBorder(new LineBorder(Color.WHITE, 1));
			}
		};

		btnConnect.addActionListener(aListener);
		btnFindPw.addActionListener(aListener);
		lblJoin.addMouseListener(mListener);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int result = JOptionPane.showConfirmDialog(StartForm.this, "exit?", "question",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					boolean flag = true;

					if (flag) {
						System.exit(0);
					}
				}
			}
		});
	}

	private void showFrame() {
		setTitle("����Ż�� �����");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public void setList(User list) {
		this.list = list;
	}

	public User getList() {
		return list;
	}

	public void setPassword(String pw) {
		list.setPassword(pw);
	}

	public String getPassword() {
		return list.getPassword();
	}

	public String getQChoice() {
		return list.getQChoice();
	}

	public String getAChoice() {
		return list.getAChoice();
	}

	public static void main(String[] args) {
		new StartForm();
	}
}
