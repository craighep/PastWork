package uk.ac.aber.dcs.cs21120.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StepLadderGui {
public void CreateGui(){
	final JFrame jf=new JFrame();
	 jf.setTitle("Step Ladder");
	 jf.setSize(400,300);
	 
	 JPanel panel = new JPanel();
	 JLabel instr1 = new JLabel("Insert Word->");
	 JLabel instr2 = new JLabel("Insert Length of Ladder ->");
	 JTextField word1 = new JTextField();
	 JTextField length = new JTextField();
	 JLabel info = new JLabel("Laddered in .. moves");
	 JLabel result = new JLabel("Output:");
	 result.setBorder(BorderFactory.createEtchedBorder(Color.black, null));
	 JButton b1 = new JButton("Ladder Word");
	 JButton b2 = new JButton("Back to Menu");
	 b2.addActionListener( new ActionListener()
	 {
	     public void actionPerformed(ActionEvent e)
	     {
	jf.dispose();
	MainFrame mf = new MainFrame();
	try {
		mf.MainFrameCreate();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	     }
	 });
	 
	// panel.setBorder();
	 panel.setLayout(new GridLayout(4, 2));
	 panel.add(instr1);
	 panel.add(word1);
	 panel.add(instr2);
	 panel.add(length);
	 panel.add(b2);
	 panel.add(b1);
	 panel.add(info);
	 panel.add(result);
	 
	  jf.add(panel);
	 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 jf.setVisible(true);

	 }

	 
}
