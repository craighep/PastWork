
package GUI;

/**
 *
 * @author Craig Heptinstall(Crh13)
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.*;
import javax.swing.border.Border;

import Data.Data;
//import Data.EncriptAndDecript;


/**
 * The Class LoadFrame.
 * This class opens a frame displaying a list of possible loads, then allows the
 * user to select one, getting this and sending it to the @FileIO class.
 * 
 */
public class Frame extends JFrame  {
	
    
    /** The Load frame. */
    /** The load p. */
    JPanel gameP = new JPanel();
    JPanel game = new JPanel();
    
    /** The cancel. */
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
    
    private JMenuBar mb = new JMenuBar();
    /**
     * The file menu.
     */
    private JMenu fileMenu = new JMenu("File");
    private static JLabel test,goes,warning;
    private int goesI = 5;
    /**
     * The edit menu.
     */

    public void gameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login");
        this.setLocationRelativeTo(null);
        gamePanel();
        Border raisedbevel;
    	raisedbevel = BorderFactory.createRaisedBevelBorder();
    	gameP.setBorder(raisedbevel);
        this.add(game);
        this.add(gameP);
        this.setJMenuBar(mb);
        test = new JLabel("Please Log In...");
        mb.add(test);
        this.pack();
        showIt();

    }

    /**
     * Load panel.
     */
    public void gamePanel() {
    	
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(500, 500)); //Set size of panel
        initComponents();
        setLayout();
        
   }

    /**
     * initializes the components.
     */
    public void initComponents() {
        
    	Border raisedbevel;
    	raisedbevel = BorderFactory.createRaisedBevelBorder();
    	
        
       empty1 = new JLabel("");
       empty2 = new JLabel("");
       user = new JLabel("User: ");
       pass = new JLabel("Pass: ");
       goes = new JLabel("You Have " + goesI +" Login Attempts Remaining.");
       warning = new JLabel("");


    	name = new JTextField("");
    	password = new JPasswordField("");
    	name.setBorder(raisedbevel);
    	password.setBorder(raisedbevel);

    	
    	name.setHorizontalAlignment(JTextField.CENTER);
    	password.setHorizontalAlignment(JTextField.CENTER);
    	user.setHorizontalAlignment(JTextField.RIGHT);
    	pass.setHorizontalAlignment(JTextField.RIGHT);
    	goes.setHorizontalAlignment(JTextField.CENTER);
    	warning.setHorizontalAlignment(JTextField.CENTER);

 		Font font = new Font("Serif", Font.BOLD, 24);
 		Font font2 = new Font("Serif", Font.ITALIC, 16);
 		Font font3= new Font("Serif", Font.ITALIC, 14);
 		
    	name.setFont(font);
    	goes.setFont(font2);
    	warning.setFont(font3);
    	
    	password.setFont(font);
        ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {  
            @Override
            public void actionPerformed(ActionEvent e)
            {

         	   Data data = new Data();
         	    if( data.checkName(name.getText(), pass.getText())== true){
            	mb.removeAll();
              	test = new JLabel("Welcome Back!");
                mb.add(test);
            	gameP.setVisible(false);}
         	    else{goesI--;
         	    if (goesI < 5){warning.setText("Too many incorrect attempts will result in closure of this screen");}
         	    if (goesI <4){goes.setForeground(Color.RED);}
         	    goes.setText("You Have " + goesI +" Login Attempts Remaining.");
         	    if (goesI <2){goes.setText("You Have " + goesI +" Login Attempt Remaining.");}
         	    if (goesI < 1){System.exit(0);}
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
            gameP.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        gameP.setLayout(new GridBagLayout());
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
        gameP.add(user, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 55; 
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        gameP.add(pass, c);
       
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 55; 
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        gameP.add(empty1, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 55; 
        c.gridx = 4;
        c.gridy = 4;
        c.gridwidth = 1;
        gameP.add(empty2, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 55; 
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        gameP.add(ok, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 55; 
        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 1;
        gameP.add(exit, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30; 
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 2;
        gameP.add(name, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30; 
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 2;
        gameP.add(password, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30; 
        c.gridx = 2;
        c.gridy = 6;
        c.gridwidth = 2;
        gameP.add(goes, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30; 
        c.gridx = 2;
        c.gridy = 7;
        c.gridwidth = 2;
        gameP.add(warning, c);
               
    }
        
    
    /**
     * Show it.
     */
    public void showIt() {
        //Display frame on screen
        this.setVisible(true);
    }
   
		}
	
    

