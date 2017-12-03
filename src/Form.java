import javax.swing.*;
import java.awt.*;


public class Form {
    public static void  main (String args[]){
        JFrame frame = new JFrame();
        frame.setSize(300, 400);
        frame.setTitle("Файловий менеджер");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        JButton button1 = new JButton();
        button1.setText("Часовий проміжок");
        button1.setSize(40,20);
        frame.add(button1);


    }
}
