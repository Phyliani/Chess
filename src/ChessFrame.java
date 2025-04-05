import javax.swing.*;
import java.awt.*;

public class ChessFrame extends JFrame{

    public ChessFrame(){
        setTitle("Chess with Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        //Titel Stuff
        JLabel titleLabel = new JLabel("Chess");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        //Main Panel
        JPanel mainPanel = new JPanel();


        //Bottom Buttons 
        JPanel bottomPanel = new JPanel();

        add(titleLabel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        pack();

    }

}
