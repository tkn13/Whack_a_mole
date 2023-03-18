import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;


public class CoreGUI {
    private JFrame f;
    private JLabel headPanel;
    private JPanel bodyPanel;
    private JLabel TextLable;
    private JPanel gridPanel;
    private JLabel headLeftContainer;
    private JLabel bodyLeftContainer;
    private JLabel bodyRightContainer;
    private JPanel gridPanelContainer;
    private JLabel heartContainer;
    public boolean stopBtnBoolean = true;

    private ArrayList<Mole> arrMole = new ArrayList<>();
    private ArrayList<Heart> arrHearts = new ArrayList<>();

    private int multiHole = 0;
    private int probMultiHole;
    
    private static JLabel scoreLabel;
    public static int score = 0;
    public static int heartcount = 0;
    

    public void initialize() {
        f = new JFrame("Whack a Mole!");
        f.setSize(1280, 780);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        detailComponents();
        f.setVisible(true);
        f.setResizable(false);
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_SPACE) {
                    
                }
            }
        });

        // Set the panel as focusable so it can receive keyboard events
        f.setFocusable(true);
    }

    private void detailComponents() {
        drawHeadPanel();
        drawBodyPanel();

        bodyLeftContainer = new JLabel(new ImageIcon("images/leftBG.png"));
        bodyRightContainer = new JLabel(new ImageIcon("images/rightBG.png"));
        bodyLeftContainer.setPreferredSize(new Dimension(370, 720));
        bodyRightContainer.setPreferredSize(new Dimension(370, 720));

        f.setLayout(new BorderLayout());
        f.add(headPanel, BorderLayout.PAGE_START);
        f.add(bodyLeftContainer, BorderLayout.LINE_START);
        f.add(bodyRightContainer, BorderLayout.LINE_END);
        f.add(bodyPanel, BorderLayout.CENTER);
    }

    private void drawHeadPanel() {
        headPanel = new JLabel(new ImageIcon("images/sky.png"));
        headLeftContainer = new JLabel();
        drawLeftHeadContainer();
        drawTextLable();

        headPanel.setPreferredSize(new Dimension(1280, 150));
        headLeftContainer.setPreferredSize(new Dimension(300, 140));

        headPanel.setLayout(new BorderLayout());
        headPanel.add(headLeftContainer, BorderLayout.WEST);
        headPanel.add(TextLable, BorderLayout.CENTER);
    }
    
    private void drawTextLable(){
        TextLable = new JLabel(" Press Spacebar to start");
        TextLable.setPreferredSize(new Dimension(500, 140));
        TextLable.setFont(new Font("Hiragino Kaku Gothic Pro", Font.BOLD, 60));
        TextLable.setForeground(Color.RED);
    }

    private void drawLeftHeadContainer() {
        scoreLabel = new JLabel("Score: ");
        scoreLabel.setFont(new Font("Hiragino Kaku Gothic Pro", Font.BOLD, 30));

        heartContainer = new JLabel();
        heartContainer.setLayout(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++) {
            Heart heart = new Heart();
            heart.setHeartId(i);
            arrHearts.add(heart);
            heartContainer.add(heart);
        }

        headLeftContainer.add(scoreLabel);
        headLeftContainer.add(heartContainer);
        headLeftContainer.setLayout(new GridLayout(2, 1));
        //headLeftContainer.setOpaque(false);
    }

    private void drawBodyPanel() {
        bodyPanel = new JPanel();
        drawGrid();
        bodyPanel.setPreferredSize(new Dimension(100, 570));
        bodyPanel.add(gridPanelContainer);
        bodyPanel.setBackground(new Color(118, 83, 64));
        gridPanelContainer.setBackground(new Color(118, 83, 64));
        gridPanel.setBackground(new Color(118, 83, 64));
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

    private void drawGameOver(){
        stopBtnBoolean = false;
        TextLable.setVisible(true);
        int result = JOptionPane.showConfirmDialog(null, "Game Over Wanna try again?");
        if (result == JOptionPane.YES_OPTION) {
            gameRestart();
        }
        else if (result == JOptionPane.NO_OPTION){
            System.exit(0);
        }
        
    }

    private void gameRestart(){
        
    }

    public void playingDelayVersion() {
        // generate probabilities how many mole will show
        int[] arrPos = new int[3];
        probMultiHole = (int) (Math.random() * 10);
        for (int i = 0; i < arrPos.length; i++) {
            arrPos[i] = -1;
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
            if (isInArr(arrPos, temppos)) {
                j--;
                continue;
            } else {
                arrPos[j] = temppos;
            }
        }
        System.out.println(Arrays.toString(arrPos));

        // Cycle of showing mole
        for (int m = 0; m < multiHole; m++) {
            delay(50);
            arrMole.get(arrPos[m]).prepareToShowing();
        }
        delay(300);

        for (int m = 0; m < multiHole; m++) {
            delay(50);
            arrMole.get(arrPos[m]).showing();
        }
        delay(650);

        for (int m = 0; m < multiHole; m++) {
            if ((arrMole.get(arrPos[m]).getState()).equals("showing")) {
                delay(50);
                arrMole.get(arrPos[m]).prepareToHide();
            }
        }
        delay(300);

        for (int m = 0; m < multiHole; m++) {
            boolean notBomb = arrMole.get(arrPos[m]).getTypeOfMole() != 3;
            boolean hideingMole = arrMole.get(arrPos[m]).getState().equals("prepareToHide");
            boolean bomb = arrMole.get(arrPos[m]).getState().equals("bomb");
            if ((notBomb && hideingMole) || bomb) {
                arrMole.get(arrPos[m]).hideing();
                damage();
            }
            arrMole.get(arrPos[m]).hideing();
        }
    }
    private void damage(){
        heartcount += 1;
        if (heartcount < 3) {
            arrHearts.get(heartcount - 1).deleteHeart();
        } else {
            arrHearts.get(heartcount - 1).deleteHeart();
            System.out.println("END");
            drawGameOver();
        }
    }
    public static void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void setscore(int s) {
        score += s;
        scoreLabel.setText("Score: " + score);
    }

    private static boolean isInArr(int[] arr, int pos) {
        for (int i = 0; i < 3; i++) {
            if (pos == arr[i]) {
                return true;
            }
        }
        return false;
    }
}
