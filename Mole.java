import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mole extends JButton {
    private Icon hole;
    private Icon mole;
    private Icon type1;
    private Icon type2;
    private Icon type3;
    private Icon type1_2;
    private Icon type2_2;
    private Icon type3_2;
    private Icon prepareToShow;
    private Icon prepareToHide;
    private String state = "hide"; // hide,perpareToShow,showing,perpareToHide
    private int TypeOfMole; // It can be Type 1,2,3 each type have different score
    private int id;
    AllButtonListener bl = new AllButtonListener();
    public static int type = 0;

    public Mole() {
     
        try {
            hole = new ImageIcon("image/hole.png");
            mole = new ImageIcon("image/showing.png");
            type1 = new ImageIcon("drill.jpg");
            type2 = new ImageIcon("star.png");
            type3 = new ImageIcon("bocci.jpg");
            type1_2 = new ImageIcon("lose.png");
            type2_2 = new ImageIcon("loseja.png");
            type3_2 = new ImageIcon("darkroot.ppg");
            prepareToShow = new ImageIcon("image/prepareToShow.png");
            prepareToHide = new ImageIcon("image/prepareToHide.png");
        } catch (Exception e) {
            System.out.println(e);
        }

        this.setPreferredSize(new Dimension(150, 150));
        this.setIcon(hole);
        this.addActionListener(bl);
    }

    public void prepareToShowing() {
        type = (int) (Math.random() * 3);
        if (type == 0){
            setIcon(type1);
        }
        else if(type == 1){
            setIcon(type2);
        }
        else if(type == 2){
            setIcon(type3);
        }   
        state = "prepareToShow";
        // setIcon(prepareToShow);
    }

    public void hideing() {
        state = "hole";
        setIcon(hole);
    }

    public void showing() {
        state = "showing";
        if (type == 0){
            setIcon(type1_2);
        }
        else if(type == 1){
            setIcon(type2_2);
        }
        else if(type == 2){
            setIcon(type3_2);
        }  
        // setIcon(mole);
    }

    public void prepareToHide() {
        state = "prepareToHide";
        setIcon(prepareToHide);
    }

    public String getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private class AllButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ev) {
            // JButton source = (JButton)ev.getSource();
            if (state.equals("showing")) {
                if(type == 0){
                    CoreGUI.scor++;
                    System.out.println("type1");
                }
                else if (type == 1){
                    CoreGUI.scor += 2;
                    System.out.println("type2");
                }
                else if (type == 2){
                    CoreGUI.scor += 3;
                    System.out.println("type3");
                }
                state = "hide";
                System.out.printf("" + "HIT\n");
                // CoreGUI.scor++;
                System.out.printf("" + "%d\n" , CoreGUI.scor);
                setIcon(hole);
                CoreGUI.scoreLabel.setText("Score: " + CoreGUI.scor);
            }
        }
    }
}