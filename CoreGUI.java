import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import javax.swing.*;

public class CoreGUI {
    private JFrame f;
    private JPanel headPanel;
    private JPanel bodyPanel;
    private JPanel footPanel;
    public JPanel gridPanel;
    private JPanel headLeftContainer;
    private JPanel headRightContainer;
    private JPanel bodyLeftContainer;
    private JPanel bodyRightContainer;
    private JPanel gridPanelContainer;
    public static JLabel scoreLabel;
    private JPanel heart;
    private ArrayList<Mole> arrMole = new ArrayList<>();
    private ArrayList<Heart> arrHearts = new ArrayList<>();
    int multiHole = 0;
    int probMultiHole;
    boolean stopBtnBoolean = true;
    public static int scor = 0;
    public static int heartcount = 0;

    public void initialize() {
        f = new JFrame("Whack a Mole!");
        f.setSize(1280, 720);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        detailComponents();
        f.setVisible(true);
        f.setResizable(false);
        // devMode();
    }

    private void devMode() {
        headPanel.setBackground(new Color(135, 206, 235));
        headLeftContainer.setBackground(new Color(135, 206, 235));
        headRightContainer.setBackground(new Color(135, 206, 235));
        bodyLeftContainer.setBackground(new Color(155, 118, 83));
        bodyRightContainer.setBackground(new Color(155, 118, 83));
        bodyPanel.setBackground(new Color(116, 62, 12));
        gridPanelContainer.setBackground(new Color(85, 47, 18));
        gridPanel.setBackground(new Color(70, 46, 26));
        footPanel.setBackground(new Color(70, 37, 10));
    }

    private void detailComponents() {
        drawHeadPanel();
        drawBodyPanel();
        drawFootPanel();

        bodyLeftContainer = new JPanel();
        bodyRightContainer = new JPanel();
        bodyLeftContainer.setPreferredSize(new Dimension(200, 720));
        bodyRightContainer.setPreferredSize(new Dimension(200, 720));

        f.setLayout(new BorderLayout());
        f.add(headPanel, BorderLayout.PAGE_START);
        f.add(bodyLeftContainer, BorderLayout.LINE_START);
        f.add(bodyRightContainer, BorderLayout.LINE_END);
        f.add(bodyPanel, BorderLayout.CENTER);
        f.add(footPanel, BorderLayout.PAGE_END);
        devMode();

        // timer.schedule(new TimerTask() {
        // @Override
        // public void run() {
        // for (Component c : gridPanel.getComponents()) {
        // if (c instanceof Mole){
        // Mole dp = (Mole) c;
        // if (dp.clickme){
        // dp.tohole();
        // System.out.printf(""+"HIDE\n");
        // dp.clickme = false;
        // }
        // }
        // }
        // movemole();
        // }
        // }, 0, 1500);
    }

    private void drawHeadPanel() {
        headPanel = new JPanel();
        headLeftContainer = new JPanel();
        headRightContainer = new JPanel();
        drawLeftHeadContainer();
        drawRightHeadContainer();

        headPanel.setPreferredSize(new Dimension(1280, 100));
        headLeftContainer.setPreferredSize(new Dimension(300, 140));
        headRightContainer.setPreferredSize(new Dimension(300, 140));

        headPanel.setLayout(new BorderLayout());
        headPanel.add(headLeftContainer, BorderLayout.WEST);
        headPanel.add(headRightContainer, BorderLayout.EAST);

    }
    public void setscor(){
        scoreLabel.setText("Score: " + scor);
    }
    private void drawLeftHeadContainer() {
        scoreLabel = new JLabel("Score: ");
        heart = new JPanel();
        heart.setLayout(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++) {
            Heart hard = new Heart();
            hard.setid(i);
            arrHearts.add(hard);
            heart.add(hard);
        }
        headLeftContainer.add(scoreLabel);
        headLeftContainer.add(heart);
        headLeftContainer.setLayout(new GridLayout(2, 1));
    }

    private void drawRightHeadContainer() {
        StopBtn stopbtn = new StopBtn();
        headRightContainer.setLayout(new BorderLayout());
        headRightContainer.add(stopbtn, BorderLayout.LINE_END);
    }

    private void drawBodyPanel() {
        bodyPanel = new JPanel();
        drawGrid();
        bodyPanel.setPreferredSize(new Dimension(700, 570));
        bodyPanel.add(gridPanelContainer);
    }

    private void drawGrid() {
        gridPanel = new JPanel();
        gridPanelContainer = new JPanel();
        for (int i = 0; i < 9; i++) {
            Mole m = new Mole();
            m.setId(i);
            arrMole.add(m);
            gridPanel.add(m);
        }
        gridPanel.setLayout(new GridLayout(3, 3, 50, 30));
        gridPanelContainer.add(gridPanel);
        gridPanelContainer.setPreferredSize(new Dimension(700, 570));

    }

    private void drawFootPanel() {
        footPanel = new JPanel();
        footPanel.setPreferredSize(new Dimension(1280, 50));
    }

    public ArrayList<Mole> getMoles() {
        return arrMole;
    }
    public ArrayList<Heart> getHearts() {
        return arrHearts;
    }

    // private void movemole(){
    // Random random = new Random();
    // Integer x = random.nextInt(9);
    // // for(int i = 0; i<9; i++){

    // // }
    // for (Component c : gridPanel.getComponents()) {
    // if (c instanceof Mole){
    // Mole dp = (Mole) c;
    // int h = dp.getID();
    // if (h == x){
    // dp.toel();
    // dp.clickme = true;
    // System.out.printf(""+"SHOW\n");
    // }
    // else {
    // dp.tohole();
    // }
    // }
    // }
    // }
    public void playingDelayVersion() {
        // generate probabilities how many mole will show
        int[] arrpos = new int[3];
        probMultiHole = (int) (Math.random() * 10);
        for (int i = 0; i < arrpos.length; i++) {
            arrpos[i] = -1;
        }
        switch (probMultiHole) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                // System.out.println("1 hole: " + probMultiHole);
                multiHole = 1;
                break;
            case 8:
            case 9:
                // System.out.println("2 hole: " + probMultiHole);
                multiHole = 2;
                break;
            case 0:
                // System.out.println("3 hole: " + probMultiHole);
                multiHole = 3;
                break;
        }// End of generate probabilities how many mole will show

        // Random which hole mole will show
        for (int j = 0; j < multiHole; j++) {
            int temppos = -1;
            temppos = (int) Math.round((Math.random() * 7) + 1);
            if (isInArr(arrpos, temppos)) {
                j--;
                continue;
            } else {
                arrpos[j] = temppos;
            }
        }
         System.out.println(Arrays.toString(arrpos));

        // Cycle of showing mole
        for (int m = 0; m < multiHole; m++) {
            arrMole.get(arrpos[m]).prepareToShowing();
        }
        delay(500);

        for (int m = 0; m < multiHole; m++) {
            arrMole.get(arrpos[m]).showing();
        }
        delay(1000);

        for (int m = 0; m < multiHole; m++) {
            if ((arrMole.get(arrpos[m]).getState()).equals("showing")) {
                arrMole.get(arrpos[m]).prepareToHide();
            }
        }
        delay(500);

        for (int m = 0; m < multiHole; m++) {
            arrMole.get(arrpos[m]).hideing();
            heartcount += 1;
            if(heartcount <= 3){
                arrHearts.get(heartcount-1).setNohard();
            }
            else{
                System.out.println("END");
            }
            ;
        }
    }

    public static void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean isInArr(int[] arr, int pos) {
        for (int i = 0; i < 3; i++) {
            if (pos == arr[i]) {
                return true;
            }
        }
        return false;
    }
}
