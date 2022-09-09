package Main;

import javax.swing.*;
import java.awt.*;

public class Info extends JFrame {
    public Info(String title, StringBuilder info) {
        super(title);

        JTextArea area = new JTextArea(String.valueOf(info), 18, 18);
        area.setBackground(new Color(0xe6e6e6));
        area.setEditable(false);
        area.setBorder(BorderFactory.createLineBorder(Color.gray));

        this.add(area);
    }
}
