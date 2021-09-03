import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI implements ActionListener{
	
	public int timesSelectedDDL = 0;
	
	public static String currentListTitle = "";
	public static int currentListIndex = -1;
	
	public static ArrayList<String> listTitleCollection = new ArrayList<String>();
	JList settingsList = new JList();
	
	public boolean selectingTasksToDelete = false;
	
	public static JComboBox<String> ddl = new JComboBox<String>();
	
	//Create new instances
	JFrame frame = new JFrame();
	JPanel buttonPanel = new JPanel();
	JPanel contentPanel = new JPanel();
	Box listBox = Box.createVerticalBox();
	JScrollPane scrollPane = new JScrollPane(listBox);
	JPanel titlePanel = new JPanel();
	
	JLabel header = new JLabel();
	
	JButton btnAdd = new JButton();
	JButton btnRemove = new JButton();
	JButton btnEdit = new JButton();
	JButton btnSettings = new JButton();
	JButton btnClose = new JButton();
	
	JTextField newItem;
	
	boolean hasTextField = false;
	
	public GUI() {	
		createGUI();
	}
	
	public void createGUI() {
				
		setFrameDetails();
		
		SetPanelDetails();
		
		addButtons();
		
		addDropDown();
		
		//Add Panels
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.EAST);
		frame.add(contentPanel);
		scrollPane.setViewportView(listBox);
		contentPanel.add(scrollPane);
		
		//Labels
		header.setText("To-Do List");
		header.setFont(new Font("Serif", Font.BOLD, 20));
		titlePanel.add(header);
		
		JTextField usertext = new JTextField(20);
		usertext.setBounds(100, 20, 165, 25);

		scrollPane.add(Box.createRigidArea(new Dimension(1, 20)));	//Spacing
		
		frame.setVisible(true);
	}
	
	public void setFrameDetails() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("To-Do List");
		frame.setSize(350, 500);
		frame.setLocation(1390, 547);
		frame.setLayout(new BorderLayout());
	}
	
	public void SetPanelDetails() {
		titlePanel.setLayout(new FlowLayout());
		//buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.setLayout(new BorderLayout());
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		
		titlePanel.setBackground(Color.GRAY);
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		listBox.setBorder(BorderFactory.createLineBorder(Color.black));
		//listBox.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	public void addButtons() {
		//Create button Panels
		JPanel buttonPanelNORTH = new JPanel();
		buttonPanelNORTH.setLayout(new BoxLayout(buttonPanelNORTH, BoxLayout.PAGE_AXIS));
		buttonPanelNORTH.setBackground(Color.LIGHT_GRAY);
		
		JPanel buttonPanelSOUTH = new JPanel();
		buttonPanelSOUTH.setLayout(new BoxLayout(buttonPanelSOUTH, BoxLayout.PAGE_AXIS));
		buttonPanelSOUTH.setBackground(Color.LIGHT_GRAY);
				
		buttonPanelNORTH.add(Box.createRigidArea(new Dimension(1, 40)));	//Spacing
		
		//Add Buttons
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanelNORTH.add(btnAdd);
		
		buttonPanelNORTH.add(Box.createRigidArea(new Dimension(1, 20)));	//Spacing
				
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(this);
		btnRemove.setPreferredSize(new Dimension(100, 50));
		btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanelNORTH.add(btnRemove);
		
		buttonPanelNORTH.add(Box.createRigidArea(new Dimension(1, 20)));	//Spacing
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(this);
		btnEdit.setPreferredSize(new Dimension(100, 50));
		btnEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanelNORTH.add(btnEdit);
		
					//Bottom Buttons
				
		btnSettings = new JButton("Settings");
		btnSettings.addActionListener(this);
		btnSettings.setPreferredSize(new Dimension(100, 50));
		btnSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanelSOUTH.add(btnSettings);
		
		buttonPanelSOUTH.add(Box.createRigidArea(new Dimension(1, 20)));	//Spacing
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		btnClose.setPreferredSize(new Dimension(100, 50));
		btnClose.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanelSOUTH.add(btnClose);
		
		//Add Panels to buttonPanel
		buttonPanel.add(buttonPanelNORTH, BorderLayout.NORTH);
		buttonPanel.add(buttonPanelSOUTH, BorderLayout.SOUTH);
	}
	
	public void addDropDown() {
		ddl.setVisible(true);
		ddl.setMaximumSize(new Dimension(200, 50));
		contentPanel.add(Box.createRigidArea(new Dimension(1, 5)));	//Spacing
	    contentPanel.add(ddl);
	    contentPanel.add(Box.createRigidArea(new Dimension(1, 5)));	//Spacing
	    ddl.addItemListener(new ItemListener(){
	        @Override
	        public void itemStateChanged(ItemEvent e) {
	        	if (e.getStateChange() == ItemEvent.SELECTED && timesSelectedDDL > 0) {
	        		String selection = (String)ddl.getSelectedItem();
	        		for (int i=0; i<Task_List.Lists.size(); i++) {
	        			if (Task_List.Lists.get(i).Title.equals(selection)) {
	            		displayTasks(Task_List.Lists.get(i), i);
	        			}
	        		}
	        		timesSelectedDDL++;
	        	}
	        }
	    });
	}

	
	public void displayTasks(List list, int listIndex) {
		listBox.removeAll();	//Clear Box
		currentListTitle = list.Title;	//Establish current list title
		currentListIndex = listIndex;	//Establish current list title
		
		timesSelectedDDL++;
		
		if (list.Tasks.size() > 0) {	//Make sure list isn't null
			
			for (String task : list.Tasks) {
				
				if (list.Tasks.get(0).equals("")) {		//If it's an empty list
					Component[] comps = contentPanel.getComponents();	//Get all components from Box
					int x = comps.length;
					for (Component comp : comps) {
						if (comp instanceof JCheckBox) {
							System.out.println(comp);
						}
					}
					//listBox.remove(comps[0]);		//Remove first component (the extra one that generates)
				}
				
				JCheckBox chk = new JCheckBox(task.toString());
			    chk.setSelected(false);
			    chk.setFont(new Font("Cobalt", Font.PLAIN, 16));
			    
			    //Check box Action Listener
			    chk.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent event) {
			            JCheckBox cb = (JCheckBox) event.getSource();
			            if (cb.isSelected()) {
			            	if (!list.Title.equals("Completed")) {
			            		if (selectingTasksToDelete == false) {
			            			String completedTask = cb.getText();
					                for (int i=0; i<Task_List.Lists.size(); i++) {
					                	if (Task_List.Lists.get(i).Title.equals("Completed")) {
					                		Task_List.Lists.get(i).AddTask(completedTask);
					                		Task_List.DeleteByTask(currentListIndex, completedTask);
					                		displayTasks(list, listIndex);
					                		listBox.revalidate();
					                		listBox.repaint();
					                		break;
					                	}
					                }
			            		}
			            		else {
			            			btnRemove.setText("Remove");
			            		}
				                
			            	}
			            	else {
			            		if (selectingTasksToDelete) {
			            			btnRemove.setText("Remove");
			            		}
			            	}
			            } 
			            else {
			                btnRemove.setText("Cancel");
			            }
			        }
			    });
			    if (!chk.getText().equals("")) {
			    	listBox.add(chk);
				    listBox.add(Box.createRigidArea(new Dimension(1, 10)));	//Spacing
			    }
			}	
		}
		listBox.revalidate();
		listBox.repaint();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Add
		if (e.getActionCommand().equals("Add")) {
			if (hasTextField == false) {
				btnAdd_Clicked();
			}
		}
		//Done (Add)
		else if (e.getActionCommand().equals("Done")) {
			btnDone_Clicked();
		}
		//Edit
		else if (e.getActionCommand().equals("Edit")) {
			btnEdit_Clicked();
		}
		//Save (Edit)
		else if (e.getActionCommand().equals("Save")) {
			btnSave_Clicked();
		}
		//Remove
		else if (e.getActionCommand().equals("Remove")) {
			btnRemove_Clicked();
		}
		//Cancel (Remove)
		else if (e.getActionCommand().equals("Cancel")) {
			btnCancel_Clicked();
		}
		//Settings
		else if (e.getActionCommand().equals("Settings")) {
			btnSettings_Clicked();
		}
		//Close
		else if (e.getActionCommand().equals("Close")) {
			System.exit(0);
		}
		//Back (Close)
		else if (e.getActionCommand().equals("Back")) {
			btnBack_Clicked();
		}
	}

	
	
	public void btnAdd_Clicked() {
		newItem = new JTextField();	//Create new TextField
		newItem.setMaximumSize(new Dimension(300, 30));
		
		listBox.add(newItem);
		
		listBox.revalidate();
		listBox.repaint();
		newItem.requestFocus();
		
		hasTextField = true;
		btnAdd.setText("Done");
		btnRemove.setEnabled(false);
		btnEdit.setEnabled(false);
	}
	
	public void btnDone_Clicked() {
		
		String itemText = newItem.getText();
		
		ArrayList<List> Lists = Task_List.Lists;
		
		if (!itemText.equals("Completed")) {
			hasTextField = false;
			btnAdd.setText("Add");
			btnRemove.setEnabled(true);
			btnEdit.setEnabled(true);
		}
		if (!itemText.equals("")) {		//Check if input is empty
			if (header.getText().equals("Settings")) {		//Check if on Settings page
				if (!itemText.equals("Completed")) {
					listTitleCollection.add(itemText);
					Task_List.AddNewList(itemText);		//Add new list to master lists
					populateSettingsListTitles();		//Add it to scroll pane
					contentPanel.revalidate();
					contentPanel.repaint();
				}
			}	
			else {											//If not on Settings Page
				Task_List.AddTask(currentListTitle, itemText);
				displayTasks(Task_List.GetListByName(currentListTitle), currentListIndex);
			}
		}
		else {
			displayTasks(Task_List.GetListByName(currentListTitle), currentListIndex);
		}
	}
	
	public void btnRemove_Clicked() {
		
		List currentList = Task_List.Lists.get(currentListIndex);	//Get current list reference
		
		//If on To-Do List page
		if (header.getText().equals("To-Do List")) {	
			if (selectingTasksToDelete == true) {
				Component[] comps = listBox.getComponents();	//Get all components from Box
				for (Component comp : comps) {	//For each component...
					if (comp instanceof JCheckBox) {	//If it's a check box...
			            JCheckBox box = (JCheckBox) comp;
			            if (box.isSelected()) {		//And it's selected...
			            	String text = box.getText();	//Get what's in it...
			            	for (int i=0; i<currentList.Tasks.size(); i++) {	//For each task in the current list...
			            		if (currentList.Tasks.get(i).equals(text)) {	//If it's text matches...
		
			            			Task_List.DeleteByTask(currentListIndex, text);
			            			break;
			            		}
			            	}
			            	listBox.remove(comp);  //Remove that component.
			            	btnAdd.setEnabled(true);
			    			btnEdit.setEnabled(true);
			            	listBox.revalidate();
			            	listBox.repaint();
			            	
			            	selectingTasksToDelete = false;
			            }
					}
				}
			}
			else {
				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				selectingTasksToDelete = true;
				btnRemove.setText("Cancel");
			}
		}
		//If on Settings page
		else if (header.getText().equals("Settings")){					
			Component[] comps = listBox.getComponents();	//Get all components from Box
			for (int i=0; i<settingsList.getModel().getSize(); i++) {	//For each component...
				if (settingsList.isSelectedIndex(i)) {
					
					listTitleCollection.remove(i);
					Task_List.DeleteList(i);
					populateSettingsListTitles();
					listBox.revalidate();
	            	listBox.repaint();
				}
			}
		}
	}
	
	public void btnCancel_Clicked() {
		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
		selectingTasksToDelete = false;
		btnRemove.setText("Remove");
	}
	
	public void btnEdit_Clicked() {
		//Change Buttons
		btnAdd.setEnabled(false);
		btnRemove.setEnabled(false);
		btnEdit.setText("Save");
		hasTextField = true;
	
		//Draw text boxes
		listBox.removeAll();
		listBox.add(Box.createRigidArea(new Dimension(1, 10)));	//Spacing
		
		//Figure out which list to display by which page you're on
		ArrayList<String> listToDisplay = null;
		if (header.getText().equals("Settings")) {
			listToDisplay = listTitleCollection;
		}
		else {
			listToDisplay = Task_List.Lists.get(currentListIndex).Tasks;
		}
		
		//Display list
		for (String task : listToDisplay) {
			newItem = new JTextField(task);	//Create new TextField
			if (task.equals("Completed")) {
				newItem.setEnabled(false);
			}
			newItem.setMaximumSize(new Dimension(300, 30));		
			
			listBox.add(newItem);
			listBox.add(Box.createRigidArea(new Dimension(1, 10)));	//Spacing
			listBox.revalidate();
			listBox.repaint();
		}
	}
	
	public void btnSave_Clicked() {
		
		btnAdd.setEnabled(true);
		btnRemove.setEnabled(true);
		btnEdit.setText("Edit");
		hasTextField = false;
		
		int GUITasksIndex = 0;
		
		Component[] comps = listBox.getComponents();	//Get all components from Box
		for (int i=0; i<comps.length; i++) {	//For each component...
			if (comps[i] instanceof JTextField) {	//If it's a text field...
	            JTextField textField = (JTextField) comps[i];
	            FindListToDisplay().set(GUITasksIndex, textField.getText());	//Set each item
	            GUITasksIndex++;
			}
		}
		if (FindListToDisplay() == listTitleCollection) {	//Save titles if in Settings
			Task_List.SaveListNames(listTitleCollection);
			populateSettingsListTitles();
		}
		else if (FindListToDisplay() == Task_List.Lists.get(currentListIndex).Tasks) {		//Save tasks if in To_Do List
			Task_List.save();
			displayTasks(Task_List.GetListByName(currentListTitle), currentListIndex);
		}
		listBox.revalidate();
		listBox.repaint();
	}
	
	public void btnSettings_Clicked() {		
		//Labels
		header.setText("Settings");
		
		//Content
		contentPanel.removeAll();
		contentPanel.add(Box.createRigidArea(new Dimension(50, 15)));		//Add spacing above scroll panel

		Box titleScrollBox = Box.createHorizontalBox();		//Create new Box container
		titleScrollBox.add(Box.createRigidArea(new Dimension(40, 1)));		//Add spacing on left of scrollPane
		titleScrollBox.add(scrollPane);
		titleScrollBox.add(Box.createRigidArea(new Dimension(40, 1)));		//Add spacing on right of scrollPane
		contentPanel.add(titleScrollBox);
		
		listBox.removeAll();
		contentPanel.add(Box.createRigidArea(new Dimension(1, (int)(scrollPane.getViewport().getSize().height/1.4))));	//To set size of scroll pane
		
		//Scroll panel of lists
		populateSettingsListTitles();
		
		//Change buttons
		btnClose.setText("Back");
		btnSettings.setVisible(false);
		
		contentPanel.revalidate();
		contentPanel.repaint();
	}
	
	public void btnBack_Clicked() {
		Task_List.Lists.clear();
		frame.dispose();
		Task_List.newGUI();
	}
	
	public static void AddListsDropDown(ArrayList<String> listTitles) {
		ddl.removeAllItems();
		listTitleCollection.clear();
				
		for (String title : listTitles) {
			listTitleCollection.add(title);
			ddl.addItem(title);
		}
	}
	
	public void populateSettingsListTitles() {
		settingsList = new JList(listTitleCollection.toArray());
				
		settingsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				Component[] comps = listBox.getComponents();	//Get all components from Box
				for (int i=0; i<settingsList.getModel().getSize(); i++) {	//For each component...
					if (settingsList.isSelectedIndex(i)) {		//If this item is selected
						if (settingsList.getModel().getElementAt(i).equals("Completed")) {		//If that item says "Completed"
							btnRemove.setEnabled(false);
						}
						else {
							if (!btnRemove.isEnabled()) {
								btnRemove.setEnabled(true);
							}
						}
					}
					
				}
			}
		});
		
		listBox.removeAll();
		Color color = UIManager.getColor("contentPanel.background");
		settingsList.setBackground(color);
		listBox.add(settingsList);
	}
	
	public ArrayList<String> FindListToDisplay() {
		if (header.getText().equals("Settings")) {
			return listTitleCollection;
		}
		else {
			return Task_List.Lists.get(currentListIndex).Tasks;
		}
	}
}
