package uk.ac.aber.dcs.cs21120.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordLadderGui {
public void CreateGui(){
	 JFrame jf=new JFrame();
	 jf.setTitle("Word Ladder");
	 jf.setSize(400,300);
	 
	 JPanel panel = new JPanel();
	 JLabel instr1 = new JLabel("Insert Word->");
	 JLabel instr2 = new JLabel("Insert Word 2 ->");
	 JTextField word1 = new JTextField();
	 JTextField word2 = new JTextField();
	 JLabel result = new JLabel();
	 JButton b1 = new JButton("Bridge Words");
	 
	// panel.setBorder();
	 panel.setLayout(new GridLayout(3, 2));
	 panel.add(instr1);
	 panel.add(word1);
	 panel.add(instr2);
	 panel.add(word2);
	 panel.add(b1);
	 panel.add(result);
	 
	  jf.add(panel);
	 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 jf.setVisible(true);

	 }

	 
}

