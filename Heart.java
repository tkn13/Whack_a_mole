import javax.swing.*;
import java.awt.*;

public class Heart extends JLabel {
    private Icon heart;
    private Icon blackHeart;
    private int id;
    Heart(){
        try{
			heart = new ImageIcon("images/heart.png");
			blackHeart = new ImageIcon("images/blackHeart.png");
			
		}
		catch(Exception e){
			System.out.println(e);
		}
        setPreferredSize(new Dimension(30,30));
        setIcon(heart);
        setOpaque(false);
    }
    public void setHeartId(int i){
        id = i;
    }
    public int getHeartId(){
        return id;
    }

    public void deleteHeart(){
        setIcon(blackHeart);
    }
    public void plusHeart(){
        setIcon(heart);
    }
}
