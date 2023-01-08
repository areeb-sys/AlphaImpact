package FareCalculator;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FareCalculator {
    
    private JFrame frame;
    private JPanel panel;
    
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem red;
    private JMenuItem green;
    private JMenuItem yellow;
    
    private JLabel departLabel;
    private JLabel StateLabel;
    private JComboBox departCombo;
    private JComboBox StateCombo;
    
    
    private JLabel Class;
    private JCheckBox economy;
    private JCheckBox AC;
    private ButtonGroup bGroup;
    
    private JLabel arrLabel;
    private JComboBox arrCombo;
    
    private JLabel seatLabel;
    private JTextField seatText;
    
    private JButton computeFare;
    
    private JLabel totalFare;
    private JTextField totalFareText;

//    private void StateComboActionPerformed(java.awt.event.ActionEvent evt)
//    {
//    	if(StateCombo.getSelectedItem()=="Punjab")
//        {
//    		departCombo.addItem(null);
//    		departCombo.addItem("a");
//            departCombo.addItem("b");
//            
//        }
//        else
//        {
//        	departCombo.addItem("c");
//            departCombo.addItem("d");
//            
//        }
//    }
    public static void main(String[] args) {
        
        new FareCalculator();
        
    }
    
    public FareCalculator () {
        
        frame = new JFrame ("Fare Calculator");
        frame.setLayout(new GridLayout(0, 1));
        panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        
        
        menuBar = new JMenuBar();
        menu = new JMenu("Background");
        red = new JMenuItem("RED");
        green = new JMenuItem("Green");
        yellow = new JMenuItem("Yellow");
        menu.add(red);
        menu.add(green);
        menu.add(yellow);
        menuBar.add(menu);
        
        GridLayout grdLayout = new GridLayout(0, 2);
        
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.red);
                panel1.setBackground(Color.red);
                panel2.setBackground(Color.red);
                panel3.setBackground(Color.red);
            }
        });
        
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.green);
                panel1.setBackground(Color.green);
                panel2.setBackground(Color.green);
                panel3.setBackground(Color.green);
            }
        });
   
        yellow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.yellow);
                panel1.setBackground(Color.yellow);
                panel2.setBackground(Color.yellow);
                panel3.setBackground(Color.yellow);
            }
          
        });
     
        
        departLabel = new JLabel("Departure city: ");
        StateLabel =new JLabel("State: ");
        StateCombo= new JComboBox();
        StateCombo.addItem(null);
        StateCombo.addItem("Khyber Pakhtunkhwa");
        StateCombo.addItem("Punjab");
        departCombo = new JComboBox();
        //departCombo.addItem("a");
       
        
        Class = new JLabel("Class");
        economy = new JCheckBox("Economy");
        AC = new JCheckBox("AC");
        bGroup = new ButtonGroup();
        bGroup.add(economy);
        bGroup.add(AC);
        StateCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(StateCombo.getSelectedItem()=="Punjab")
                  {
            		departCombo.removeAllItems();
            		
              		departCombo.addItem("Lahore");
                      
                      
                  }
                  else
                  {
                	  departCombo.removeAllItems();
              		
                  	departCombo.addItem("Peshawar");
                     
                      
                  }

            }
        });
        
        arrLabel = new JLabel("Arrival City: ");
        arrCombo = new JComboBox();
        arrCombo.addItem("Islamabad");
        arrCombo.addItem("Gujranwala");
        arrCombo.addItem("Faisalabad");
        
        seatLabel = new JLabel("Total Seats");
        seatText = new JTextField("");
        
        computeFare = new JButton("Compute Fare");
        
        computeFare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int eco = 0;
                int acClass = 0;
                int seatNum = 0;
                
                try {
                    if (seatText.getText().equals("")) {
                        totalFareText.setText("missing input. Try Again");
                    }
                    else {
                        seatNum = Integer.parseInt(seatText.getText().trim());
                        if (departCombo.getSelectedItem() == "Lahore" ) {
                            if (arrCombo.getSelectedItem() == "Islamabad") {
                                eco = 500;
                                acClass = 1000;
                                if (economy.isSelected()) {
                                    totalFareText.setText(String.format("%d", eco*seatNum));
                                }
                                else if (AC.isSelected()) {
                                    totalFareText.setText(String.format("%d", acClass*seatNum));
                                }
                                else {
                                    totalFareText.setText("missing input. Try Again");
                                }
                            }
                            else if (arrCombo.getSelectedItem() == "Gujranwala") {
                                eco = 600;
                                acClass = 1500;
                                if (economy.isSelected()) {
                                    totalFareText.setText(String.format("%d", eco*seatNum));
                                }
                                else if (AC.isSelected()) {
                                    totalFareText.setText(String.format("%d", acClass*seatNum));
                                }
                                else {
                                    totalFareText.setText("missing input. Try Again");
                                }
                            }
                            else if (arrCombo.getSelectedItem() == "Faisalabad") {
                                eco = 700;
                                acClass = 200;
                                if (economy.isSelected()) {
                                    totalFareText.setText(String.format("%d", eco*seatNum));
                                }
                                else if (AC.isSelected()) {
                                    totalFareText.setText(String.format("%d", acClass*seatNum));
                                }
                                else {
                                    totalFareText.setText("missing input. Try Again");
                                }
                                
                            }
                        FileWriter file = new FileWriter("output.txt");
                        file.write(totalFareText.getText());
                        file.close();
                        }
                        
                        else if (departCombo.getSelectedItem() == "Peshawar" ) {
                            if (arrCombo.getSelectedItem() == "Islamabad") {
                                eco = 500;
                                acClass = 1000;
                                if (economy.isSelected()) {
                                    totalFareText.setText(String.format("%d", eco*seatNum));
                                }
                                else if (AC.isSelected()) {
                                    totalFareText.setText(String.format("%d", acClass*seatNum));
                                }
                                else {
                                    totalFareText.setText("missing input. Try Again");
                                }
                            }
                            else if (arrCombo.getSelectedItem() == "Gujranwala") {
                                eco = 600;
                                acClass = 1500;
                                if (economy.isSelected()) {
                                    totalFareText.setText(String.format("%d", eco*seatNum));
                                }
                                else if (AC.isSelected()) {
                                    totalFareText.setText(String.format("%d", acClass*seatNum));
                                }
                                else {
                                    totalFareText.setText("missing input. Try Again");
                                }
                            }
                            else if (arrCombo.getSelectedItem() == "Faisalabad") {
                                eco = 700;
                                acClass = 200;
                                if (economy.isSelected()) {
                                    totalFareText.setText(String.format("%d", eco*seatNum));
                                }
                                else if (AC.isSelected()) {
                                    totalFareText.setText(String.format("%d", acClass*seatNum));
                                }
                                else {
                                    totalFareText.setText("missing input. Try Again");
                                }
                            }
                        FileWriter file = new FileWriter("output.txt");
                        file.write(totalFareText.getText());
                        file.close();
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }   
        });
        
        totalFare = new JLabel("Total Fare");
        totalFareText = new JTextField("");
        
        frame.add(panel);
        frame.add(panel1);
        frame.add(panel3);
        frame.add(panel2);

        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
        panel3.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
        panel.setLayout(grdLayout);
        panel.add(StateLabel);
        panel.add(StateCombo);
        panel.add(departLabel);
        
        panel.add(departCombo);
        panel1.add(Class);
        panel1.add(economy);
        panel1.add(AC);
        panel.add(arrLabel);
        panel.add(arrCombo);
        panel.add(seatLabel);
        panel.add(seatText);
        panel2.setLayout(new FlowLayout());
        panel2.add(computeFare);
        panel3.add(totalFare);
        panel3.add(totalFareText);
        panel3.setLayout(new GridLayout(0, 2));
        
        frame.setBounds(0, 0, 800, 550);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
}
