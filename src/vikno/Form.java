package vikno;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.time.chrono.JapaneseDate;
import javax.swing.text.*;
import java.text.*;

public class Form {

    public static void  main (String args[]) throws InstantiationException{
        JFrame frame = new JFrame("Форма");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        JButton buttonn = new JButton("Дата");

        JTextField textField = new JTextField("xdsvd");
        JTextField textField2 = new JTextField("vewvdv");

        frame.add(buttonn);
        frame.add(textField);
        textField.setSize(30,10);
        frame.add(textField2);
        textField2.setSize(30,10);
        frame.setVisible(true);


     /*
      int i;
      for(i = 0; i < 3; i++){
      System.out.println("" + i);
      }
      */
    }


}
