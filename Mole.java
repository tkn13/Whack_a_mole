import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Mole extends JButton {
    public Icon holepic;
    public Integer Id = 0;
    public Icon el;
    AllButtonListener bl = new AllButtonListener();
    public Mole(){
        try{
			holepic = new ImageIcon("images/hole.png");
			el = new ImageIcon("images/el.png");
		}
		catch(Exception e){
			System.out.println(e);
		}
       
        this.setPreferredSize(new Dimension(150,150));
        this.setIcon(holepic);
        this.setBackground(Color.green);
        this.addActionListener(bl);
    }
    public void toel(){
        this.setIcon(el);
    }
    public void tohole(){
        this.setIcon(holepic);
    }
    public void setID(int id){
        this.Id = id;
    }
    public int getID(){
        return(Id);
    }
    private class AllButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ev){
            // JButton source = (JButton)ev.getSource();
            System.out.println(getID());
        }
    }
}