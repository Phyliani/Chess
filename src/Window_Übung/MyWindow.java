package Window_Übung;
import javax.swing.*;
import java.awt.event.ActionListener;


public class MyWindow  extends JFrame{

    JLabel label;

    public MyWindow(ActionListener listener){
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(400, 400);

        JPanel p = new JPanel();
        add(p);

        JButton b1 = new JButton("Send");
        JButton b2 = new JButton("Close");
        label = new JLabel();
        label.setText("----");
        
        p.add(b1);
        p.add(b2);
        p.add(label);

        //MyController c = new MyController();
        b1.addActionListener(listener);
        b2.addActionListener(listener);

        pack();
    }

    public void ShowMessage(String msg) {
        label.setText(msg);
    }

}
