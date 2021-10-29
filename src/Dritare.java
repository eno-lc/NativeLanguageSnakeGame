import javax.swing.JFrame;

public class Dritare extends JFrame{

    Dritare(){

        this.add(new PaneliLojes());
        this.setTitle("Gjarpri");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}