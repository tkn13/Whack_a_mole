import javax.swing.*;
import java.awt.*;

public class Mole extends JButton {
    public Icon holepic;
    public Mole(){
        try{
			holepic = new ImageIcon("hole.png");
			
		}
		catch(Exception e){
			System.out.println(e);
		}
       
        this.setPreferredSize(new Dimension(100,100));
        this.setIcon(holepic);
        this.setBackground(Color.green);
    }  
}