import javax.swing.*;


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

        MyController c = new MyController();
        b1.addActionListener(c);
        b2.addActionListener(c);

        pack();
    }
}
