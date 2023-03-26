import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

public class GameCore extends JFrame{
    private JFrame f;
    private JLabel headLabel;
    private JPanel bodyPanel;
    private JLabel TextLable = new JLabel();
    private JPanel gridPanel;
    private JLabel headLeftContainer;
    private JLabel headRightContainer;
    private JLabel bodyLeftContainer;
    private JLabel bodyRightContainer;
    private JPanel gridPanelContainer;
    private JLabel heartContainer;
    private boolean gameRunable = false;
    private String pathSeparator = System.getProperty("file.separator");
    private txtRW scoreRW = new txtRW();

    private ArrayList<Mole> arrMole = new ArrayList<>();
    private ArrayList<Heart> arrHearts = new ArrayList<>();

    private int multiHole = 0;
    private int probMultiHole =0;
    private int heartCount = 0;

    private static JLabel scoreLabel;
    public static int score = 0;
    
    public void initialize() {
        f = new JFrame("Whack a Mole!");
        f.setSize(1280, 780);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        detailComponents();
        f.setVisible(true);
    }

    private void detailComponents() {
        drawheadLabel();
        drawBodyPanel();
        setCursor();
        drawPressSpaceBar();

        bodyLeftContainer = new JLabel(new ImageIcon("images" + pathSeparator + "leftBG.png"));
        bodyRightContainer = new JLabel(new ImageIcon("images" + pathSeparator + "rightBG.png"));
        bodyLeftContainer.setPreferredSize(new Dimension(370, 720));
        bodyRightContainer.setPreferredSize(new Dimension(370, 720));

        f.setLayout(new BorderLayout());
        f.add(headLabel, BorderLayout.PAGE_START);
        f.add(bodyLeftContainer, BorderLayout.LINE_START);
        f.add(bodyRightContainer, BorderLayout.LINE_END);
        f.add(bodyPanel, BorderLayout.CENTER);
        //gameStart();
        f.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    gameStart();
                }
            }
        });
        f.setFocusable(true);      
    }

    private void setCursor(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image hammerImage = toolkit.getImage("images" + pathSeparator + "hammer.png");
        Cursor c = toolkit.createCustomCursor(hammerImage, new Point(f.getX(), f.getY()), "hammer");
        f.setCursor(c);
    }

    private void drawheadLabel() {
        headLabel = new JLabel(new ImageIcon("images" + pathSeparator + "sky.png"));
        headLeftContainer = new JLabel();
        headRightContainer = new JLabel();

        drawLeftHeadContainer();
        drawRightHeadContainer();
        drawGameOverLable();

        headLabel.setPreferredSize(new Dimension(1280, 150));
        headLeftContainer.setPreferredSize(new Dimension(300, 140));

        headLabel.setLayout(new BorderLayout());
        headLabel.add(headLeftContainer, BorderLayout.WEST);
        headLabel.add(TextLable, BorderLayout.CENTER);
        headLabel.add(headRightContainer, BorderLayout.EAST);
    }

    private void drawCenterLabel(String s){
        TextLable.setText(s);
    }

    private void drawGameOverLable() {
        drawCenterLabel("          GAME OVER");
        TextLable.setPreferredSize(new Dimension(500, 140));
        TextLable.setFont(new Font("Zapfino", Font.BOLD, 60));
        TextLable.setForeground(new Color(128,0,0));
        TextLable.setVisible(true);
    }

    private void drawPressSpaceBar(){
        drawCenterLabel("                        Press Spacebar to start");
        TextLable.setFont(new Font("Zapfino", Font.BOLD, 50));
        TextLable.setForeground(new Color(255, 204, 153));
    }

    private void drawLeftHeadContainer() {
        scoreLabel = new JLabel("  Score: 0");
        scoreLabel.setFont(new Font("Zapfino", Font.BOLD, 30));
        
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
        headLeftContainer.setVisible(false);
    }

    private void drawRightHeadContainer(){
        headRightContainer.setText("Hight Score: " + scoreRW.read() + "  ");
        headRightContainer.setFont(new Font("Zapf Dingbats", Font.BOLD, 35)); 
        headRightContainer.setForeground(new Color(128, 128, 128));
    }

    private void drawBodyPanel() {
        bodyPanel = new JPanel();
        bodyPanel.setPreferredSize(new Dimension(100, 570));
        drawGrid();
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

    private void drawGameOver() {
        int oldScore = Integer.valueOf(scoreRW.read());
        if (score > oldScore){
            scoreRW.write(String.valueOf(score));
            headRightContainer.setText("Hight Score: " + scoreRW.read() + "  ");
        }
        gameRunable = false;
        drawGameOverLable();
        String[] options = {"Yes", "No"};
        ImageIcon icon = new ImageIcon("images/gameOver.png");
        int result = JOptionPane.showOptionDialog(null, "game over wanna try again?       ","you lose", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);
        if(result == 0){
            gameRestart();
        }
        else{
            System.exit(0);
        }
        System.out.println(result);
    }

    private void gameStart(){
        gameRunable = true;
        TextLable.setVisible(false);
        headLeftContainer.setVisible(true);
    }

    private void gameRestart() {
        gameRunable = true;
        score = 0;
        TextLable.setVisible(false);
        setscore(0);
        for (Heart h : arrHearts) {
            h.plusHeart();
        }
        for(Mole m : arrMole){
            m.hideing();
        }
        heartCount = 0;
    }

    public void playing() {
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
                multiHole = 1;
                break;
            case 8:
            case 9:
                multiHole = 2;
                break;
            case 0:
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
        delay(900);

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
                if(damage()){
                    break;
                }
            }
            arrMole.get(arrPos[m]).hideing();
        }
    }

    private boolean damage() {
        heartCount += 1;
        if (heartCount < 3) {
            arrHearts.get(heartCount - 1).deleteHeart();
        } else {
            arrHearts.get(heartCount - 1).deleteHeart();
            System.out.println("END");
            drawGameOver();
            return true;
            
        }
        return false;
    }

    public boolean getGameRunable(){
        return gameRunable;
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
        scoreLabel.setText("  Score: " + score);
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