package Window_Übung;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyController  implements ActionListener{

    private MyWindow window;

    public void startGui() {
        window = new MyWindow(this);
        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        String command = e.getActionCommand();


        if (command.equals("Send")) {
            System.out.println("SEND");
            window.ShowMessage("SEND");
            
        }else if (command.equals("Close")) {
            window.ShowMessage("CLOSE");
            System.out.println("CLOSE");
        }

    }

    
}


