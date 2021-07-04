import java.awt.BorderLayout;
import java.awt.Button;
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
	
	public GUI() {
		
		//Frame Details
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("To-Do List");
		frame.setLayout(new BorderLayout());
		
		//Declare
		JButton button = new JButton("Click Me");
		button.addActionListener(this);
		
		//Panel Details
		buttonPanel.setLayout(new GridLayout(3,1));
		
		listPanel.setLayout(new GridLayout(3,1));
		
		//panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		//panel.add(button);
				
		frame.add(buttonPanel, BorderLayout.EAST);
		frame.add(listPanel, BorderLayout.WEST);
		
		//frame.setSize(400, 500);
		
		//Labels
		JLabel header = new JLabel("To-Do List");
		header.setBounds(10, 20, 80, 25);
		buttonPanel.add(header);
		
		JTextField usertext = new JTextField(20);
		usertext.setBounds(100, 20, 165, 25);
		buttonPanel.add(usertext);
		
		
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		//new GUI();
	}
	
	public void displayTasks(ArrayList<String> Tasks) {
		
		for (String task : Tasks) {
			JCheckBox chk = new JCheckBox(task.toString());
		    chk.setSelected(false);
		    chk.setBounds(10, 80, 80, 25);
		    this.listPanel.add(chk);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox chk = new JCheckBox("Press");
	    chk.setSelected(false);
	    this.listPanel.add(chk);
	}

}
