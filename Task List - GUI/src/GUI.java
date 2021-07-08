import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class GUI implements ActionListener{
	
	JFrame frame;
	JPanel buttonPanel = new JPanel();
	JPanel listContainer = new JPanel();
	JScrollPane listPanel = new JScrollPane(listContainer);
	JPanel titlePanel = new JPanel();
	
	public GUI() {
		
		//Frame Details
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("To-Do List");
		frame.setSize(350, 500);
		frame.setLayout(new BorderLayout());
		
		
		//Panel Details
		titlePanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		//listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
		
		titlePanel.setBackground(Color.GRAY);
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		
		listPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//listPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		listPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
		
		
		//Buttons		
		addButtons();
		
		//Add Panels
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.EAST);
		listPanel.setViewportView(listContainer);
		frame.add(listPanel, BorderLayout.WEST);
		
		
		//Labels
		JLabel header = new JLabel("To-Do List");
		header.setFont(new Font("Serif", Font.BOLD, 20));
		titlePanel.add(header);
		
		JTextField usertext = new JTextField(20);
		usertext.setBounds(100, 20, 165, 25);
		//buttonPanel.add(usertext);
		
		
		listPanel.add(Box.createRigidArea(new Dimension(1, 20)));	//Spacing
		
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
		    listContainer.add(chk);
		    
		    listContainer.add(Box.createRigidArea(new Dimension(1, 10)));	//Spacing
		}
		listContainer.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox chk = new JCheckBox("Press");
	    chk.setSelected(false);
	    this.listPanel.add(chk);
	}

	public void addButtons() {
		
		buttonPanel.add(Box.createRigidArea(new Dimension(1, 20)));	//Spacing
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);
		//btnAdd.setPreferredSize(new Dimension(10, 10));
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(btnAdd);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(1, 20)));	//Spacing
				
		JButton btnRemove = new JButton("Remove");
		//btnRemove.addActionListener(this);
		btnRemove.setPreferredSize(new Dimension(100, 50));
		btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(btnRemove);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(1, 20)));	//Spacing
		
		JButton btnEdit = new JButton("Edit");
		//btnRemove.addActionListener(this);
		btnEdit.setPreferredSize(new Dimension(100, 50));
		btnEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(btnEdit);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(1, 180)));	//Spacing
		
		JButton btnClose = new JButton("Close");
		//btnRemove.addActionListener(this);
		btnClose.setPreferredSize(new Dimension(100, 50));
		btnClose.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(btnClose);
	}
}
