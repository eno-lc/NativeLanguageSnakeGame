import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class PaneliLojes extends JPanel implements ActionListener{

    static final int gjeresia = 1024;
    static final int gjatsia = 768;
    static final int njesi = 50;
    static final int njesiLoje = (gjeresia*gjatsia)/(njesi*njesi);
    static final int DELAY = 150;
    final int x[] = new int[njesiLoje];
    final int y[] = new int[njesiLoje];
    int pjeseTrupore = 6;
    int mollaTeNgrena;
    int mollaX;
    int mollaY;
    char drejtimi = 'R';
    boolean poLevize = false;
    Timer timer;
    Random random;

    PaneliLojes(){
        random = new Random();
        this.setPreferredSize(new Dimension(gjeresia,gjatsia));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new tastet());
        filloLojen();
    }
    public void filloLojen() {
        mollaERe();
        poLevize = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics vizatimi) {
        super.paintComponent(vizatimi);
        vizato(vizatimi);
    }
    public void vizato(Graphics vizatimi) {

        if(poLevize) {

            vizatimi.setColor(Color.red);
            vizatimi.fillOval(mollaX, mollaY, njesi, njesi);

            for(int i = 0; i< pjeseTrupore;i++) {
                if(i == 0) {
                    vizatimi.setColor(Color.green);
                    vizatimi.fillRect(x[i], y[i], njesi, njesi);
                }
                else {
                   // vizatimi.setColor(new Color(45,180,0));
                    vizatimi.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    vizatimi.fillRect(x[i], y[i], njesi, njesi);
                }
            }
            vizatimi.setColor(Color.red);
            vizatimi.setFont( new Font("Calibri",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(vizatimi.getFont());
            vizatimi.drawString("Totali : "+mollaTeNgrena, (gjeresia - metrics.stringWidth("Totali: "+mollaTeNgrena))/2, vizatimi.getFont().getSize());
        }
        else {
            lojaMbaroi(vizatimi);
        }

    }
    public void mollaERe(){
        mollaX = random.nextInt((int)(gjeresia/njesi))*njesi;
        mollaY = random.nextInt((int)(gjatsia/njesi))*njesi;
    }
    public void move(){
        for(int i = pjeseTrupore;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (drejtimi) {
            case 'U' -> y[0] = y[0] - njesi;
            case 'D' -> y[0] = y[0] + njesi;
            case 'L' -> x[0] = x[0] - njesi;
            case 'R' -> x[0] = x[0] + njesi;
        }

    }
    public void kontrolloMollen() {
        if((x[0] == mollaX) && (y[0] == mollaY)) {
            pjeseTrupore++;
            mollaTeNgrena++;
            mollaERe();
        }
    }
    public void kontrolloPerseritjet() {
        for(int i = pjeseTrupore;i>0;i--) {
            if((x[0] == x[i])&& (y[0] == y[i])) {
                poLevize = false;
            }
        }
        if(x[0] < 0) {
            poLevize = false;
        }
        if(x[0] > gjeresia) {
            poLevize = false;
        }
        if(y[0] < 0) {
            poLevize = false;
        }
        if(y[0] > gjatsia) {
            poLevize = false;
        }

        if(!poLevize) {
            timer.stop();
        }
    }
    public void lojaMbaroi(Graphics g) {
        g.setColor(Color.red);
        g.setFont( new Font("Calibri",Font.CENTER_BASELINE, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Totali: "+mollaTeNgrena, (gjeresia - metrics1.stringWidth("Totali: "+mollaTeNgrena))/2, g.getFont().getSize());


        g.setColor(Color.red);
        g.setFont( new Font("Calibri",Font.CENTER_BASELINE, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Loja mbaroi!!!", (gjeresia - metrics2.stringWidth("Loja mbaroi!!!"))/2, gjatsia/2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(poLevize) {
            move();
            kontrolloMollen();
            kontrolloPerseritjet();
        }
        repaint();
    }

    public class tastet extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(drejtimi != 'R') {
                        drejtimi = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(drejtimi != 'L') {
                        drejtimi = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(drejtimi != 'D') {
                        drejtimi = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(drejtimi != 'U') {
                        drejtimi = 'D';
                    }
                    break;
            }
        }
    }
}