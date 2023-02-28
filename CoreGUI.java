import java.awt.*;
import java.util.ArrayList;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class CoreGUI {
    private JFrame f;
    private JPanel headPanel;
    private JPanel bodyPanel;
    private JPanel footPanel;
    private JPanel gridPanel;
    private JPanel headLeftContainer;
    private JPanel headRightContainer;
    private JPanel bodyLeftContainer;
    private JPanel bodyRightContainer;
    private JPanel gridPanelContainer;
    private JLabel scoreLabel;
    private JPanel heart;
    private ArrayList<Mole>arrM = new ArrayList<>();
    private Timer timer = new Timer();

    public void initialize(){
        f = new JFrame("Whack a Mole!");
        f.setSize(1280,720);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        detailComponents();
        f.setVisible(true);
        f.setResizable(false);
        //devMode();
    }
    private void devMode(){
        headPanel.setBackground(Color.blue);
        headLeftContainer.setBackground(Color.green);
        headRightContainer.setBackground(Color.green);
        bodyLeftContainer.setBackground(Color.yellow);
        bodyRightContainer.setBackground(Color.yellow);
        bodyPanel.setBackground(Color.red);
        footPanel.setBackground(Color.gray);
    }

    private void detailComponents(){
        drawHeadPanel();
        drawBodyPanel();
        drawFootPanel();
        
        bodyLeftContainer = new JPanel();
        bodyRightContainer = new JPanel();
        bodyLeftContainer.setPreferredSize(new Dimension(200,720));
        bodyRightContainer.setPreferredSize(new Dimension(200,720));
        
        f.setLayout(new BorderLayout());
        f.add(headPanel,BorderLayout.PAGE_START);
        f.add(bodyLeftContainer,BorderLayout.LINE_START);
        f.add(bodyRightContainer,BorderLayout.LINE_END);
        f.add(bodyPanel,BorderLayout.CENTER);
        f.add(footPanel,BorderLayout.PAGE_END);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (Component c : gridPanel.getComponents()) {
                    if (c instanceof Mole){
                        Mole dp = (Mole) c;
                        if (dp.clickme){
                            dp.tohole();
                            System.out.printf(""+"HIDE\n");
                            dp.clickme = false;
                        }
                        }
                    }
               movemole();
            }
        }, 0, 1500);
    }

    private void drawHeadPanel(){
        headPanel = new JPanel();
        headLeftContainer = new JPanel();
        headRightContainer = new JPanel();
        drawLeftHeadContainer();
        drawRightHeadContainer();

        headPanel.setPreferredSize(new Dimension(1280, 100));
        headLeftContainer.setPreferredSize(new Dimension(300,140));
        headRightContainer.setPreferredSize(new Dimension(300,140));

        headPanel.setLayout(new BorderLayout());
        headPanel.add(headLeftContainer,BorderLayout.WEST);
        headPanel.add(headRightContainer,BorderLayout.EAST);

    }

    private void drawLeftHeadContainer(){
        scoreLabel = new JLabel("Score: ");
        heart = new JPanel();
        heart.setLayout(new GridLayout(1,3));
        for(int i = 0; i < 3; i++){
            heart.add(new JLabel("<3"));
        }
        headLeftContainer.add(scoreLabel);
        headLeftContainer.add(heart);
        headLeftContainer.setLayout(new GridLayout(2,1));
    }

    private void drawRightHeadContainer(){
        StopBtn stopbtn = new StopBtn();
        headRightContainer.setLayout(new BorderLayout());
        headRightContainer.add(stopbtn,BorderLayout.LINE_END);
    }

    private void drawBodyPanel(){
        bodyPanel = new JPanel();
        drawGrid();
        bodyPanel.setPreferredSize(new Dimension(700,570));
        bodyPanel.add(gridPanelContainer);
    }

    private void drawGrid(){
        gridPanel = new JPanel();
        gridPanelContainer = new JPanel();
        for(int i = 0; i < 9; i++){
            Mole m = new Mole();
            m.setID(i);
            arrM.add(m);
            gridPanel.add(m);
        }
        gridPanel.setLayout(new GridLayout(3,3,50,30));
        gridPanelContainer.add(gridPanel);
        gridPanelContainer.setPreferredSize(new Dimension(700,570));
       
    }

    private void drawFootPanel(){
        footPanel = new JPanel();
        footPanel.setPreferredSize(new Dimension(1280,50));
    }

    public ArrayList<Mole> getMoles(){
        return arrM;
    }

    private void movemole(){
        Random random = new Random();   
        Integer x = random.nextInt(9);   
        // for(int i = 0; i<9; i++){

        // }
        for (Component c : gridPanel.getComponents()) {
            if (c instanceof Mole){
                Mole dp = (Mole) c;
                int h = dp.getID();
                if (h == x){
                    dp.toel();
                    dp.clickme = true;
                    System.out.printf(""+"SHOW\n");
                }
                else {
                    dp.tohole();
                }
            }
        }
    }
}
