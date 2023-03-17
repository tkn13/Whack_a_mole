import javax.swing.*;
import java.awt.*;

public class Heart extends JLabel {
    public Icon heart;
    public int id;
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
    public void setid(int i){
        this.id = i;
    }

    public void setNohard(){
        this.setIcon(null);
    }
}
