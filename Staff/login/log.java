package login;


//And you must save this source below with name "newframe.java"


import javax.swing.*;

import view.servicesComboBox;

import java.awt.*;
import java.awt.event.*;


public class log extends JFrame {

	JPanel panel = new JPanel();


	
}


ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
    @Override
    public void actionPerformed(ActionEvent e) {

        String choiceInt = (String) internet1.getSelectedItem();//get the selected item
        String choiceCable = (String) internet1.getSelectedItem();
        switch (choiceInt) {//check for a match
            case "3G":
                System.out.println("You're choice is 3G");
                break;
            case "4G":    
                System.out.println("You're choice is 4G");
                break;
            case "5G":
                System.out.println("You're choice is 5G");
                break;
            default:
                System.out.println("Invalid");
                break;
        }
        switch (choiceCable) {//check for a match
            case "fibre":
                System.out.println("You're choice is Fibre");
                break;
            case "cooper":    
                System.out.println("You're choice is Cooper");
                break;              
            default:
                System.out.println("Invalid");
                break;
    }
    }
};

internet1.addActionListener(cbActionListener);
cable1.addActionListener(cbActionListener);




public static void main(String[] args) {
    //SwingUtilities.invokeLater(new Runnable() {
        //@Override
       // public void run() {
            new servicesComboBox();
        }
   // });
//  }
}
