import java.awt.*;
import javax.swing.*;

public class CoreGUI {
    private JFrame f;
    private JPanel headPanel;
    private JPanel bodyPanel;
    private JPanel gridPanel;
    private JPanel headLeftContainer;
    private JPanel headRightContainer;
    private JPanel bodyLeftContainer;
    private JPanel bodyRightContainer;
    private JPanel gridPanelContainer;
    private JLabel scoreLabel;
    private JPanel heart;
    private JButton stopbtn;

    public CoreGUI(){
        f = new JFrame("Whack a Mole!");
        f.setSize(1280,720);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        detailComponents();
        f.setVisible(true);
    }

    private void detailComponents(){
        drawGrid();
        drawHeadPanel();
        drawBodyPanel();
        drawLeftHeadContainer();
        drawRightHeadContainer();

        headLeftContainer.add(scoreLabel);
        headLeftContainer.add(heart);
        headLeftContainer.setLayout(new GridLayout(2,1));

        headRightContainer.setLayout(new BorderLayout());
        headRightContainer.add(stopbtn,BorderLayout.LINE_END);
        

        bodyLeftContainer = new JPanel();
        bodyRightContainer = new JPanel();
        bodyLeftContainer.setPreferredSize(new Dimension(150,720));
        bodyRightContainer.setPreferredSize(new Dimension(150,720));
        bodyPanel.add(gridPanelContainer);
        f.setLayout(new BorderLayout());
        f.add(headPanel,BorderLayout.PAGE_START);
        f.add(bodyLeftContainer,BorderLayout.LINE_START);
        f.add(bodyRightContainer,BorderLayout.LINE_END);
        f.add(bodyPanel,BorderLayout.CENTER);

        
        bodyPanel.add(gridPanelContainer);
        
    }

    private void drawHeadPanel(){
        headPanel = new JPanel();
        headLeftContainer = new JPanel();
        headRightContainer = new JPanel();

        headPanel.setPreferredSize(new Dimension(1280, 100));
        headLeftContainer.setPreferredSize(new Dimension(300,140));
        headRightContainer.setPreferredSize(new Dimension(300,140));
        headLeftContainer.setBackground(Color.blue);
        headRightContainer.setBackground(Color.blue);

        headPanel.setLayout(new BorderLayout());
        headPanel.add(headLeftContainer,BorderLayout.WEST);
        headPanel.add(headRightContainer,BorderLayout.EAST);

    }

    private void drawBodyPanel(){
        bodyPanel = new JPanel();
        bodyPanel.setBackground(Color.red);
    }

    private void drawGrid(){
        gridPanel = new JPanel();
        gridPanelContainer = new JPanel();
        for(int i = 0; i < 9; i++){
            gridPanel.add(new Mole());
        }
        gridPanel.setLayout(new GridLayout(3,3));
  
        gridPanelContainer.add(gridPanel);
    }

    private void drawLeftHeadContainer(){
        scoreLabel = new JLabel("Score: ");
        heart = new JPanel();
        heart.setLayout(new GridLayout(1,3));
        for(int i = 0; i < 3; i++){
            heart.add(new JLabel("<3"));
        }
    }

    private void drawRightHeadContainer(){
        stopbtn = new JButton("STOP BTN");
    }
}
