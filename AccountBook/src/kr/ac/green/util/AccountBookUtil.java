package kr.ac.green.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.Closeable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

public class AccountBookUtil {
	
	public static Color PASTELPINK = new Color(255, 220, 220);
	
	public static Dimension CBSIZE = new Dimension(250, 25);
	
	public static Dimension sizeOfLabel = new Dimension(130, 18);
	
	public static Font SERIF = new Font("SERIF", Font.PLAIN, 12);
	
	public static JLabel getLabel(String text) {
		JLabel lbl = new JLabel(text, JLabel.LEFT);
		lbl.setPreferredSize(sizeOfLabel);
		return lbl;
	}
	
	public static JLabel getRightLabel(String text) {
		JLabel lbl = new JLabel(text, JLabel.RIGHT);
		lbl.setPreferredSize(new Dimension(80, 20));
		return lbl;
	}
	
	public static ImageIcon getImageIcon(String fileName, int size) {
		Image img = Toolkit.getDefaultToolkit().getImage(fileName);
		Image newImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}
	
	public static ImageIcon getImageIcon(String fileName) {
		Image img = Toolkit.getDefaultToolkit().getImage(fileName);
		return new ImageIcon(img);
	}
	
	public static boolean isEmpty(JTextComponent input) {
		String text = input.getText().trim();
		return (text.length() == 0) ? true : false;
	}
	
	public static void closeAll(Closeable... c) {
		for(Closeable temp : c) {
			try {
				temp.close();
			} catch(Exception e) {}
		}
	}
	
	
}
