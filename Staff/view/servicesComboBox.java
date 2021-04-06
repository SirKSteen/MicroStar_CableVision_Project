package view;
/*package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;    
public class servicesComboBox {    
JFrame serviceFrame;    
JComboBox b,cb; 
  
	
	public servicesComboBox() {
	
		serviceFrame=new JFrame();    
	    String[] internet ={"1st","2nd","3rd","4th",};          
	    cb.addActionListener(cb);
	    cb.setBounds(5, 5,90,20);
	    cb.setSelectedIndex(0);
	    
	    String[] cable={"cable1","cable2","cable3","cable4"};        
	    //b = new JComboBox(cable);    
	   // b.setBounds(110, 5,90,20);
	    b.addActionListener(b);
	    
	    serviceFrame.add(cb); 
	    serviceFrame.add(b);       
	    
	    serviceFrame.setLayout(null);    
	    serviceFrame.setSize(300,150); 
	    serviceFrame.setTitle("Services");
	    serviceFrame.setVisible(true);
	    
	    final JComboBox internet1 = new JComboBox(internet); 
		final JComboBox cable1 = new JComboBox(cable); 
			
			ActionListener intcb = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					String internet = (String) cb.getSelectedItem();
					String cable = (String) b.getSelectedItem();
					
					switch (internet) {
						case "1st":
							System.out.println("Opens pane with the first Option");
							break;
						case "2st":
							System.out.println("Opens pain with the second Option");
							break;
						case "3st":
							System.out.println("Opens pain with the third Option");
							break;
						case "4st":
							System.out.println("Opens pain with the fourth Option");
							break;
						default:
							System.out.println("Nothing choosen");
							break;
					}//close internet switch
					
					switch (cable) {
					case "cable1":
						System.out.println("Opens pain with the cable1 Option");
						break;
					case "cable2":
						System.out.println("Opens pain with the cable2 Option");
						break;
					case "cable3":
						System.out.println("Opens pain with the cable3 Option");
						break;
					case "cable4":
						System.out.println("Opens pain with the cable3 Option");
						break;
					default:
						System.out.println("Nothing choosen");
						break;
				}
				
			}
			
		};
		internet1.addActionListener(intcb);
		cable1.addActionListener(intcb);
	}

	
	public static void main(String[] args) {    
	    new servicesComboBox();         
	}    
}   
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class servicesComboBox extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	
	public servicesComboBox() {
		establish();
	}
	
    private void establish() {
		content();
		
	}

	JFrame serviceFrame;
    
    public void content() {
    	
        serviceFrame = new JFrame("Services");
    	final JLabel header = new JLabel();
    	header.setText("Please select a service.");
    	//header.setBounds(15,10,200,40);
    	header.setHorizontalAlignment(JLabel.CENTER);
    	
        String internet[] = {"3G", "4G", "5G"};
        String cable[] = {"fibre", "Cooper"};
        
        //serviceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
	    serviceFrame.setTitle("Services");
        //serviceFrame.setSize(700,400);
        serviceFrame.setVisible(true);

        final JComboBox internet1 = new JComboBox(internet);
        final JComboBox cable1 = new JComboBox(cable);
        
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
                        break;
            }
            }
        };

        internet1.addActionListener(cbActionListener);
        cable1.addActionListener(cbActionListener);
        
        internet1.setBounds(20,40,110,20);
        cable1.setBounds(130,40,110, 20);
     
        serviceFrame.add(header);
        serviceFrame.add(cable1);
        serviceFrame.add(internet1);

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new servicesComboBox();
            }
       	});
      }
    }
