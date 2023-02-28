import javax.swing.*;
import java.awt.*;

public class Mole extends JLabel {
    public Icon holepic;
    public Mole(){
        try{
			holepic = new ImageIcon("images\\hole.png");
			
		}
		catch(Exception e){
			System.out.println(e);
		}
       
        this.setPreferredSize(new Dimension(150,150));
        this.setIcon(holepic);
        this.setBackground(Color.pink);
    }  
}