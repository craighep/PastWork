package GUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Data.Data;

public class LogIn extends JPanel  {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel test;
	 private JButton  ok, exit;
	    
	    
	    private JLabel empty1, empty2, user, pass;
	    
	    /** The name. */
	    public static JTextField name;
	    public static JPasswordField password;
	    
	    /** The Constant RIGHT_TO_LEFT. */
	    final static boolean RIGHT_TO_LEFT = false;
	    
	    /** The Constant shouldFill. */
	    final static boolean shouldFill = true;
	    
	    /** The Constant shouldWeightX. */
	    final static boolean shouldWeightX = true;
	 public LogIn() {
    gamePanel();

}
	 public void gamePanel() {
	    	
	        initComponents();
	        setLayout();
	        
	   }
	 public void initComponents() {
	        
	    	Border raisedbevel;
	    	raisedbevel = BorderFactory.createRaisedBevelBorder();
	    	
	        
	       empty1 = new JLabel("");
	       empty2 = new JLabel("");
	       user = new JLabel("User: ");
	       pass = new JLabel("Pass: ");
	       

	    	name = new JTextField("");
	    	password = new JPasswordField("");
	    	name.setBorder(raisedbevel);
	    	password.setBorder(raisedbevel);

	    	
	    	name.setHorizontalAlignment(JTextField.CENTER);
	    	password.setHorizontalAlignment(JTextField.CENTER);
	    	user.setHorizontalAlignment(JTextField.RIGHT);
	    	pass.setHorizontalAlignment(JTextField.RIGHT);

	 		Font font = new Font("Serif", Font.BOLD, 24);
	    	name.setFont(font);
	    	password.setFont(font);
	        ok = new JButton("OK");
	        ok.addActionListener(new ActionListener() {  
	            @Override
	            public void actionPerformed(ActionEvent e)
	            {
	               
	         	   Data data = new Data();
	         	   
	          	   if (data.checkName(name.getText(),password.getText()) == true){
	            	}
	         	   
	                
	            }});
	               
	        exit = new JButton("Cancel");
	        exit.addActionListener(new ActionListener() {  
	            @Override
	            public void actionPerformed(ActionEvent e)
	            {
	              System.exit(0);
	            }
	        });
	        
	       
	        
	        
	}
	    
	/**
	 * Sets the layout.
	 */
	public void setLayout() {
	        if (RIGHT_TO_LEFT) {
	            this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	        }
	        this.setLayout(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();
	        if (shouldFill) {
	            //natural height, maximum width
	            c.fill = GridBagConstraints.HORIZONTAL;
	        }

	        if (shouldWeightX) {
	            c.weightx = 0.5;
	        }
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.ipady = 55; 
	        c.gridx = 1;
	        c.gridy = 2;
	        c.gridwidth = 1;
	        this.add(user, c);
	        
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.ipady = 55; 
	        c.gridx = 1;
	        c.gridy = 3;
	        c.gridwidth = 1;
	        this.add(pass, c);
	       
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.ipady = 55; 
	        c.gridx = 1;
	        c.gridy = 4;
	        c.gridwidth = 1;
	        this.add(empty1, c);
	        
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.ipady = 55; 
	        c.gridx = 4;
	        c.gridy = 4;
	        c.gridwidth = 1;
	        this.add(empty2, c);
	        
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.ipady = 55; 
	        c.gridx = 2;
	        c.gridy = 4;
	        c.gridwidth = 1;
	        this.add(ok, c);
	        
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.ipady = 55; 
	        c.gridx = 3;
	        c.gridy = 4;
	        c.gridwidth = 1;
	        this.add(exit, c);
	        
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.ipady = 30; 
	        c.gridx = 2;
	        c.gridy = 2;
	        c.gridwidth = 2;
	        this.add(name, c);
	        
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.ipady = 30; 
	        c.gridx = 2;
	        c.gridy = 3;
	        c.gridwidth = 2;
	        this.add(password, c);
	        
	               
	    }
}