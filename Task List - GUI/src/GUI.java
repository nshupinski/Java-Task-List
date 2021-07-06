import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener{
	
	JFrame frame;
	JPanel buttonPanel = new JPanel();
	JPanel listPanel = new JPanel();
	JPanel titlePanel = new JPanel();
	
	public GUI() {
		
		//Frame Details
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("To-Do List");
		frame.setSize(350, 500);
		frame.setLayout(new BorderLayout());
		
		//Buttons
		JButton button = new JButton("Add");
		button.addActionListener(this);
		button.setSize(10, 20);
		buttonPanel.add(button);
		
		//Panel Details
		titlePanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new GridLayout(3,1));
		listPanel.setLayout(new GridLayout(3,1));
		
		titlePanel.setBackground(Color.GRAY);
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		
		//panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.EAST);
		frame.add(listPanel, BorderLayout.WEST);
		
		//Labels
		JLabel header = new JLabel("To-Do List");
		//header.setBounds(10, 20, 80, 25);
		header.setFont(new Font("Serif", Font.BOLD, 20));
		titlePanel.add(header);
		
		JTextField usertext = new JTextField(20);
		usertext.setBounds(100, 20, 165, 25);
		//buttonPanel.add(usertext);
		
		
		//frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		//new GUI();
	}
	
	public void displayTasks(ArrayList<String> Tasks) {
		
		for (String task : Tasks) {
			JCheckBox chk = new JCheckBox(task.toString());
		    chk.setSelected(false);
		    chk.setFont(new Font("Cobalt", Font.PLAIN, 16));
		    //chk.setBounds(10, 80, 80, 25);
		    listPanel.add(chk);
		}
		listPanel.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox chk = new JCheckBox("Press");
	    chk.setSelected(false);
	    this.listPanel.add(chk);
	}

}
