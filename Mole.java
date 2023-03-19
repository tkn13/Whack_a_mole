import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mole extends JButton {
    private Icon hole;
    private Icon preType1;
    private Icon preType2;
    private Icon preType3;
    private Icon showType1;
    private Icon showType2;
    private Icon showType3;
    private Icon preBomb;
    private Icon showBomb;
    private Icon hitType1;
    private Icon hitType2;
    private Icon hitType3;
    private Icon bomb;
    private String state = "hide"; // hide,perpareToShow,showing,perpareToHide
    private int TypeOfMole; // It can be Type 1,2,3 each TypeOfMole have different score
    private int id;
    AllButtonListener bl = new AllButtonListener();

    public Mole() {

        try {
            hole = new ImageIcon("images/hole.png");
            preType1 = new ImageIcon("images/preShowType1.png");
            preType2 = new ImageIcon("images/preShowType2.png");
            preType3 = new ImageIcon("images/preShowType3.png");
            showType1 = new ImageIcon("images/showType1.png");
            showType2 = new ImageIcon("images/showType2.png");
            showType3 = new ImageIcon("images/showType3.png");
            hitType1 = new ImageIcon("images/hitType1.png");
            hitType2 = new ImageIcon("images/hitType2.png");
            hitType3 = new ImageIcon("images/hitType3.png");
            preBomb = new ImageIcon("images/preBomb.png");
            showBomb = new ImageIcon("images/showBomb.png");
            bomb = new ImageIcon("images/bomb.png");

        } catch (Exception e) {
            System.out.println(e);
        }

        setPreferredSize(new Dimension(150, 150));
        setIcon(hole);
        setBorder(null);
        addActionListener(bl);
    }

    public void prepareToShowing() {
        TypeOfMole = (int) (Math.random() * 4);
        if (TypeOfMole == 0) {
            setIcon(preType1);
        } else if (TypeOfMole == 1) {
            setIcon(preType2);
        } else if (TypeOfMole == 2) {
            setIcon(preType3);
        } else if (TypeOfMole == 3){
            setIcon(preBomb);
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
        if (TypeOfMole == 0) {
            setIcon(showType1);
        } else if (TypeOfMole == 1) {
            setIcon(showType2);
        } else if (TypeOfMole == 2) {
            setIcon(showType3);
        } else if (TypeOfMole == 3){
            setIcon(showBomb);
        }
        // setIcon(mole);
    }

    public void prepareToHide() {
        state = "prepareToHide";
        if (TypeOfMole == 0) {
            setIcon(preType1);
        } else if (TypeOfMole == 1) {
            setIcon(preType2);
        } else if (TypeOfMole == 2) {
            setIcon(preType3);
        } else if (TypeOfMole == 3){
            setIcon(preBomb);
        }
    }

    public void bombb(){
        state = "bomb";
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
    public int getTypeOfMole(){
        return TypeOfMole;
    }
   

    private class AllButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ev) {
            // JButton source = (JButton)ev.getSource();
            if (state.equals("showing")) {
                state = "hide";
                if (TypeOfMole == 0) {
                    CoreGUI.setscore(100);
                    System.out.println("preType1");
                    setIcon(hitType1);
                } else if (TypeOfMole == 1) {
                    CoreGUI.setscore(250);
                    System.out.println("preType2");
                    setIcon(hitType2);
                } else if (TypeOfMole == 2) {
                    CoreGUI.setscore(400);
                    System.out.println("preType3");
                    setIcon(hitType3);
                } else if (TypeOfMole == 3){
                    setIcon(bomb);
                    bombb();
                    System.out.println(state);
                }
                System.out.printf("" + "HIT\n");
                // CoreGUI.score++;
                System.out.printf("" + "%d\n", CoreGUI.score);
            }
        }
    }
}