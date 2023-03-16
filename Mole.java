import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mole extends JButton {
    private Icon hole;
    private Icon mole;
    private Icon prepareToShow;
    private Icon prepareToHide;
    private String state = "hide"; // hide,perpareToShow,showing,perpareToHide
    private int TypeOfMole; // It can be Type 1,2,3 each type have different score
    private int id;
    AllButtonListener bl = new AllButtonListener();

    public Mole() {
        try {
            hole = new ImageIcon("images/hole.png");
            mole = new ImageIcon("images/showing.png");
            prepareToShow = new ImageIcon("images/prepareToShow.png");
            prepareToHide = new ImageIcon("images/prepareToHide.png");
        } catch (Exception e) {
            System.out.println(e);
        }

        this.setPreferredSize(new Dimension(150, 150));
        this.setIcon(hole);
        this.addActionListener(bl);
    }

    public void prepareToShowing() {
        state = "prepareToShow";
        setIcon(prepareToShow);
    }

    public void hideing() {
        state = "hole";
        setIcon(hole);
    }

    public void showing() {
        state = "showing";
        setIcon(mole);
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
                state = "hide";
                System.out.printf("" + "HIT\n");
                setIcon(hole);
            }
        }
    }
}