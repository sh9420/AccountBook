package kr.ac.green.util;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// 이미지 변환 class

public class CashUtil {
	
    public JLabel setImageSize(String imgName, int width, int height) {
        ImageIcon icon = new ImageIcon(imgName);
        Image img = icon.getImage();
        Image changeImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);
        return new JLabel(changeIcon);
    }
}

