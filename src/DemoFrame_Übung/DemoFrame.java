package DemoFrame_Übung;
import javax.swing.*;
import java.awt.*;

public class DemoFrame extends JFrame{

    public DemoFrame(){
        setTitle("Layout demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Passwort-Fenster");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        //Main Panel
        JPanel mainPanel = new JPanel();
        
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        mainPanel.setLayout(new GridLayout(2,1));
        mainPanel.add(p1);
        mainPanel.add(p2);

        JLabel l1 = new JLabel("Login:");
        JTextField tf1 = new JTextField(12);
        p1.add(l1);
        p1.add(tf1);

        JLabel l2 = new JLabel("Password:");
        JTextField tf2 = new JTextField(12);
        p2.add(l2);
        p2.add(tf2);


        //Buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton closeButton = new JButton("Close");
        buttonPanel.add(okButton);
        buttonPanel.add(closeButton);

        //Top-Level Layout
        add(titleLabel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }
}
