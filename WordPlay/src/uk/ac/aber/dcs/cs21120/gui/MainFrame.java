package uk.ac.aber.dcs.cs21120.gui;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


 public class MainFrame implements ActionListener {


 public void MainFrameCreate() throws IOException{
 final JFrame jf=new JFrame();
 jf.setTitle("Make It Visual");
 jf.setSize(400,300);
 
 JPanel panel = new JPanel();
 JButton b1 = new JButton("WordLadder (Ladder Between Two Words)");
 JButton b2 = new JButton("StepLadder (Choose A Word And Choose Step Amount)");
 b1.addActionListener( new ActionListener()
 {
     public void actionPerformed(ActionEvent e)
     {
jf.dispose();
WordLadderGui wlg = new WordLadderGui();
wlg.CreateGui();
     }
 });
 b2.addActionListener( new ActionListener()
 {
     public void actionPerformed(ActionEvent e)
     {
         jf.dispose();
         StepLadderGui slg = new StepLadderGui();
         slg.CreateGui();
     }
 });
 
 
 panel.add(b1);
 panel.add(b2);
 
 panel.setLayout(new GridLayout(2, 1));
  jf.add(panel);
 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 jf.setVisible(true);

 }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
 

 }