import javax.swing.Icon;
import javax.swing.*;
import java.awt.*;

public class StopBtn extends JLabel{
    private Icon stopIcon;
    public StopBtn(){
        try{
			stopIcon = new ImageIcon("images/stopbtn.png");
			
		}
		catch(Exception e){
			System.out.println(e);
		}

        this.setPreferredSize(new Dimension(100,150));
        this.setBackground(Color.black);
        this.setIcon(stopIcon);
        
    }
}
