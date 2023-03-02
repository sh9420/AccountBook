package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import kr.ac.green.Form.MainForm;
import kr.ac.green.data.DataCenter;
import kr.ac.green.model.AccountBook;
import kr.ac.green.util.CashUtil;


// ���� �߰��� �ߴ� â class

public class PageAdd extends JDialog /* JDialog */ {
	private JLabel lblBack; // ���ư��� ���� ������

	private JLabel lblDate; // ��¥
	private JLabel lblDateArea; // ��¥ �� ��ĭ
	private JLabel lblProperty;
	private JComboBox<String> property; // �ڻ�
	private JLabel lblBank; // ����
	private JLabel lblBankArea; // ���� �� ��ĭ
	private JLabel lblMoney; // �ݾ�
	private JTextField tfMoney; // �ݾ� ���� �ʵ�

	private JLabel lblIncomeSpend; // ���� & ����
	private JLabel lblIncomeSpendArea; // ���� & ���� �� ��ĭ
	private JLabel lblHashTagA; // �ؽ��±�1
	private JLabel lblHashTagAreaA;
	private JLabel lblHashTagB; // �ؽ��±�2
	private JLabel lblHashTagAreaB;
	private JLabel lblHashTagC; // �ؽ��±�3
	private JLabel lblHashTagAreaC;
	
	private JPanel pnlMain = new JPanel();

	private JLabel lblMemo; // �޸� ���̺�
	private JTextArea taMemo; // �޸�
	private JButton btnAdd; // �߰�

	private CashUtil cashUtil;
	
	private MouseListener mListener;
	private AccountBook accountBook;
	private DataCenter dataCenter;
	private int num;
	
	
	public PageAdd() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dataCenter=DataCenter.getInstance();
	
		
		
		init();
		setDisplay();
		addListener();
		showFrame();
	}
	
	private void setComponentSize(JComponent comp) {
		comp.setPreferredSize(new Dimension(120, 25));
	}

	private void init() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("piggy-bank-icon.png");
		setIconImage(icon);
		
		cashUtil = new CashUtil();

		// �������� ���ư��� ����
		lblBack = cashUtil.setImageSize("Back.png", 50, 50);
		lblBack.setBorder(new EmptyBorder(new Insets(5, 0, 5, 0)));
		lblBack.setToolTipText("�������� ���ư���");

		// ��¥ ���̺�
		lblDate = new JLabel("��¥", JLabel.CENTER);
		lblDate.setFont(new Font("���� ���", Font.BOLD, 15));
		lblDate.setForeground(Color.BLACK);
		
	
		lblDateArea = new JLabel("", JLabel.CENTER);
		lblDateArea.setBorder(new LineBorder(Color.BLACK, 1));
		setComponentSize(lblDateArea);
		
		// �ڻ�
		String[] kinds = { "����", "����", "ī��" };
		property = new JComboBox<String>(kinds);
		property.setFont(new Font("���� ���", Font.BOLD, 15));
		setComponentSize(property);
		
		lblProperty = new JLabel("�ڻ�", JLabel.RIGHT);
		lblProperty.setFont(new Font("���� ���", Font.BOLD, 15));
		lblProperty.setForeground(Color.BLACK);
		lblProperty.add(property);
		
		

		// ����
		lblBank = new JLabel("����", JLabel.RIGHT);
		lblBank.setFont(new Font("���� ���", Font.BOLD, 15));
		lblBank.setForeground(Color.BLACK);
		lblBankArea = new JLabel();
		lblBankArea.setBorder(new LineBorder(Color.BLACK, 1));
		lblBankArea.setHorizontalAlignment(JTextField.CENTER);
		setComponentSize(lblBankArea);
		

		// �ݾ�
		lblMoney = new JLabel("�ݾ�", JLabel.RIGHT);
		lblMoney.setFont(new Font("���� ���", Font.BOLD, 15));
		lblMoney.setForeground(Color.BLACK);
		tfMoney = new JTextField("�ݾ��� �Է��ϼ���", 11);
		tfMoney.setHorizontalAlignment(JTextField.CENTER);
		tfMoney.setBorder(new LineBorder(Color.BLACK, 1));
		setComponentSize(tfMoney);


		// ���� & ����
		lblIncomeSpend = new JLabel("#����/����", JLabel.RIGHT);
		lblIncomeSpend.setFont(new Font("���� ���", Font.BOLD, 15));
		lblIncomeSpend.setForeground(Color.BLACK);
		
		lblIncomeSpendArea = new JLabel("", JLabel.CENTER);
		lblIncomeSpendArea.setFont(new Font("���� ���", Font.BOLD, 15));
		lblIncomeSpendArea.setBorder(new LineBorder(Color.BLACK, 1));
		setComponentSize(lblIncomeSpendArea);
		
		// �ؽ��±� 1
		lblHashTagA = new JLabel("#�ؽ��±�1", JLabel.RIGHT);
		lblHashTagA.setFont(new Font("���� ���", Font.BOLD, 15));
		lblHashTagA.setForeground(Color.BLACK);
		lblHashTagAreaA = new JLabel("", JLabel.CENTER);
		lblHashTagAreaA.setFont(new Font("���� ���", Font.BOLD, 15));
		lblHashTagAreaA.setBorder(new LineBorder(Color.BLACK, 1));
		setComponentSize(lblHashTagAreaA);
		
		// �ؽ��±�2
		lblHashTagB = new JLabel("#�ؽ��±�2", JLabel.RIGHT);
		lblHashTagB.setFont(new Font("���� ���", Font.BOLD, 15));
		lblHashTagB.setForeground(Color.BLACK);
		lblHashTagAreaB = new JLabel("", JLabel.CENTER);
		lblHashTagAreaB.setFont(new Font("���� ���", Font.BOLD, 15));
		lblHashTagAreaB.setBorder(new LineBorder(Color.BLACK, 1));
		setComponentSize(lblHashTagAreaB);

		// �ؽ��±�3
		lblHashTagC = new JLabel("#�ؽ��±�3", JLabel.RIGHT);
		lblHashTagC.setFont(new Font("���� ���", Font.BOLD, 15));
		lblHashTagC.setForeground(Color.BLACK);
		lblHashTagAreaC = new JLabel("", JLabel.CENTER);
		lblHashTagAreaC.setFont(new Font("���� ���", Font.BOLD, 15));

		lblHashTagAreaC.setBorder(new LineBorder(Color.BLACK, 1));
		setComponentSize(lblHashTagAreaC);

		// �޸�
		lblMemo = new JLabel("�޸� ", JLabel.RIGHT);
		lblMemo.setFont(new Font("���� ���", Font.BOLD, 15));
		taMemo = new JTextArea(10,33);
		taMemo.setBorder(new LineBorder(Color.BLACK, 1));

		// �߰�
		btnAdd = new JButton("�߰�");
	}

	private void setDisplay() {
		
		// ���ư��� ���� (�г� ����)
		JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlNorth.add(lblBack);
		pnlNorth.setBackground(Color.WHITE);

    	
		// �г� ���� (��¥, �ڻ�, ����, ��)
		JPanel pnlWest = new JPanel(new GridLayout(0, 1));
		
		JPanel pnlDateArea = new JPanel();
		pnlDateArea.add(lblDate);
		pnlDateArea.add(lblDateArea);
		pnlDateArea.setBackground(Color.WHITE);
		
		
		JPanel pnlProp = new JPanel();
		pnlProp.add(lblProperty);
		pnlProp.add(property);
		pnlProp.setBackground(Color.WHITE);
		
		JPanel pnlBankArea = new JPanel();
		pnlBankArea.add(lblBank);
		pnlBankArea.add(lblBankArea);
		pnlBankArea.setBackground(Color.WHITE);
		
		
		JPanel pnlTfMoney = new JPanel();
		pnlTfMoney.add(lblMoney);
		pnlTfMoney.add(tfMoney);
		pnlTfMoney.setBackground(Color.WHITE);
		
		pnlWest.add(pnlDateArea);
		pnlWest.add(pnlProp);
		pnlWest.add(pnlBankArea);
		pnlWest.add(pnlTfMoney);
		pnlWest.setBackground(Color.WHITE);

		
		// �г� ���� 
		JPanel pnlEast = new JPanel(new GridLayout(0,1));
		
		// �г� ������
		JPanel pnlIncomSpend = new JPanel();
		pnlIncomSpend.add(lblIncomeSpend);
		pnlIncomSpend.add(lblIncomeSpendArea);
		pnlIncomSpend.setBackground(Color.WHITE);

		JPanel pnlA = new JPanel();
		pnlA.add(lblHashTagA);
		pnlA.add(lblHashTagAreaA);
		pnlA.setBackground(Color.WHITE);
		
		JPanel pnlB = new JPanel();
		pnlB.add(lblHashTagB);
		pnlB.add(lblHashTagAreaB);
		pnlB.setBackground(Color.WHITE);
		
		JPanel pnlC = new JPanel();
		pnlC.add(lblHashTagC);
		pnlC.add(lblHashTagAreaC);
		pnlC.setBackground(Color.WHITE);
		
		pnlEast.add(pnlIncomSpend);
		pnlEast.add(pnlA);
		pnlEast.add(pnlB);
		pnlEast.add(pnlC);
		pnlEast.setBackground(Color.WHITE);
		
		// �г� Center
		JPanel pnlCenter = new JPanel(new GridLayout(1, 2));
		pnlCenter.add(pnlWest);
		pnlCenter.add(pnlEast);
		pnlCenter.setBackground(Color.WHITE);
		
		// �г� ����
		JPanel pnlSouth = new JPanel(new BorderLayout());

		
		JPanel pnlSHalfEast = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlSHalfEast.add(lblMemo);
		pnlSHalfEast.add(taMemo);
		pnlSHalfEast.setBackground(Color.WHITE);

		JPanel pnlSHalfSouth = new JPanel();
		pnlSHalfSouth.add(btnAdd);
		pnlSHalfSouth.setBackground(Color.WHITE);
		
		pnlSouth.add(pnlSHalfEast, BorderLayout.CENTER);
		pnlSouth.add(pnlSHalfSouth, BorderLayout.SOUTH);
	
		pnlMain = new JPanel(new BorderLayout());
		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		
		add(pnlMain);
	}
	
	private void addListener() {
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) { 
                new MainForm();
                dispose();
            }
		});
		
		MouseListener meListener = new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent me) {
				Object src = (Object)me.getSource();
				if(src == lblDateArea) {
					new PageAddCalendar(PageAdd.this);
				} else if(src == tfMoney) {
					tfMoney.selectAll();
				} else if(src == lblIncomeSpendArea || src == lblHashTagAreaA || src == lblHashTagAreaB) {
					new HashTagIncome(PageAdd.this);
				} else if(src == lblHashTagAreaC) {
					new HashTagSearchAddThird(PageAdd.this);
				}
			}
		};
		lblDateArea.addMouseListener(meListener);
		tfMoney.addMouseListener(meListener);
		lblIncomeSpendArea.addMouseListener(meListener);
		lblHashTagAreaA.addMouseListener(meListener);
		lblHashTagAreaB.addMouseListener(meListener);
		lblHashTagAreaC.addMouseListener(meListener);
		
		
		ActionListener aListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object btn = (Object) e.getSource();
				if(btn == btnAdd) {
					if(property.getSelectedItem().toString().equals("����")) {
						if(lblDateArea.getText().equals("") || 	tfMoney.getText().equals("") || 
								lblIncomeSpendArea.getText().equals("") || lblHashTagAreaA.getText().equals("") ||
								lblHashTagAreaB.getText().equals("") || taMemo.getText().trim().contentEquals("")){
							
							alert("�Էµ��� ���� ������ �ֽ��ϴ�. �ٽ� Ȯ���� �ּ���.");
						}else {
							addAccount();
							new MainForm();
							dispose();
						}
					}else if(!(property.getSelectedItem().toString().equals("����"))) {
						if(lblDateArea.getText().equals("") || 	tfMoney.getText().equals("") || lblBankArea.getText().equals("")||
								lblIncomeSpendArea.getText().equals("") || lblHashTagAreaA.getText().equals("") ||
								lblHashTagAreaB.getText().equals("") || taMemo.getText().trim().contentEquals("")){
							
							alert("�Էµ��� ���� ������ �ֽ��ϴ�. �ٽ� Ȯ���� �ּ���.");
						}else {
							addAccount();
							new MainForm();
							dispose();
						}
					}
				}
			}
		};
		btnAdd.addActionListener(aListener);
		
		
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				JLabel lbl = (JLabel)me.getSource();
				if(lbl == lblBack) {
					new MainForm();
					dispose();
				}
			}
		});

		property.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JComboBox cb = (JComboBox)ae.getSource();
				int idx = cb.getSelectedIndex();
				if(idx == 1) {
					lblBankArea.setText("");
					lblBankArea.setBorder(new LineBorder(Color.GRAY, 1));
					lblBankArea.setOpaque(true);
					lblBankArea.setBackground(Color.LIGHT_GRAY);
					
					lblBankArea.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent me) {
							lblBankArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					});
					lblBankArea.removeMouseListener(mListener);
					update();
				} else {
					lblBankArea.removeMouseListener(mListener);
					lblBankArea.setText("");
					lblBankArea.setBorder(new LineBorder(Color.BLACK, 1));
					lblBankArea.setOpaque(true);
					lblBankArea.setBackground(Color.WHITE);
					mListener = new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent me) {
							JLabel lbl = (JLabel)me.getSource();
							if(lbl == lblBankArea) {
								if(lblBankArea.getText().trim().length() == 0) {
									new BankSelect(PageAdd.this, "");
								} else {
									new BankSelect(PageAdd.this, lblBankArea.getText());
								}
							}
							if(lbl == lblBack) {
								new MainForm();
							}
						}
						
						@Override
						public void mouseEntered(MouseEvent me) {
							lblBankArea.setCursor(new Cursor(Cursor.HAND_CURSOR));
						}
					};
					lblBankArea.addMouseListener(mListener);
					update();
				}
			}
		});

	}
	
	public void addAccount() {
	
		
		String propertyStr = property.getSelectedItem().toString();
		System.out.println(propertyStr);
		String year = lblDateArea.getText().substring(0,4);
		String month = lblDateArea.getText().substring(5,7);
		String date = lblDateArea.getText().substring(8,10);
		System.out.println("year=" +year+ "month=" + month + "date= "+ date);
		
		
		LocalDate paradate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
		DayOfWeek dayOfWeek = paradate.getDayOfWeek();
		System.out.println(dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN));

		String day = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN);


		dataCenter.addListAccountBookData(num, year, month, date, Integer.parseInt(tfMoney.getText()),
				lblIncomeSpendArea.getText().substring(1), day, propertyStr, lblBankArea.getText(),
				lblHashTagAreaA.getText().substring(1), lblHashTagAreaB.getText(), lblHashTagAreaC.getText(),
				taMemo.getText());

		dataCenter.load();
	}
	
	private void alert(String msg) {
		JOptionPane.showMessageDialog(
			PageAdd.this,
			msg,
			"�˸�",
			JOptionPane.INFORMATION_MESSAGE
		);
	}

	
	public void update() {
		remove(pnlMain);
		
		setDisplay();
		pack();
	}

	private void showFrame() {
		setTitle("����Ż�� �����");
		pack();
		getContentPane().setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JLabel getLblBankArea() {
		return lblBankArea;
	}
	
	public void setLblBankArea(JLabel lblBankArea) {
		this.lblBankArea = lblBankArea;
	}
	
	public JLabel getLblDateArea() {
		return lblDateArea;
	}

	public void setLblDateArea(JLabel lblDateArea) {
		this.lblDateArea = lblDateArea;
	}

	public JLabel getLblIncomeSpendArea() {
		return lblIncomeSpendArea;
	}

	public void setLblIncomeSpendArea(JLabel lblIncomeSpendArea) {
		this.lblIncomeSpendArea = lblIncomeSpendArea;
	}

	public JLabel getLblHashTagAreaA() {
		return lblHashTagAreaA;
	}

	public void setLblHashTagAreaA(JLabel lblHashTagAreaA) {
		this.lblHashTagAreaA = lblHashTagAreaA;
	}

	public JLabel getLblHashTagAreaB() {
		return lblHashTagAreaB;
	}

	public void setLblHashTagAreaB(JLabel lblHashTagAreaB) {
		this.lblHashTagAreaB = lblHashTagAreaB;
	}

	public JLabel getLblHashTagAreaC() {
		return lblHashTagAreaC;
	}

	public void setLblHashTagAreaC(JLabel lblHashTagAreaC) {
		this.lblHashTagAreaC = lblHashTagAreaC;
	}
}
