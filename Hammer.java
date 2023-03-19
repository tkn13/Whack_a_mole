import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Hammer extends JLabel {
    private Icon hammer;

    Hammer(){
        try{
            hammer = new ImageIcon("images/hammer-export.png");
        } catch (Exception e) {
            System.out.println(e);
        }
        setIcon(hammer);
        setPreferredSize(new Dimension(150,150));
        setVisible(true);
    }
}
