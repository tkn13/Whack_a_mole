import javax.swing.*;
import java.awt.*;

public class Heart extends JLabel {
    public Icon heart;
    Heart(){
        try{
			heart = new ImageIcon("images/heart.png");
			
		}
		catch(Exception e){
			System.out.println(e);
		}
        this.setPreferredSize(new Dimension(30,30));
        this.setIcon(heart);
        this.setBackground(new Color(135,206,235));
    }
    
}
