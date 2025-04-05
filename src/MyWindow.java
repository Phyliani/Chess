import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyWindow  extends JFrame{
    public MyWindow(){
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(400, 400);

        JPanel p = new JPanel();
        add(p);

        JButton b1 = new JButton("Send");
        JButton b2 = new JButton("Close");
        
        p.add(b1);
        p.add(b2);

        pack();
    }
}
