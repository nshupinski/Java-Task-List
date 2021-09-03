import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Task_List { 

	//GUI
	static GUI newGUI;
	
	//Logic
	static Scanner kbd = new Scanner(System.in);   //keyboard scanner
	static ArrayList<List> Lists = new ArrayList<List>();
	static String input;
	
	
	public static void main(String[] args) {
		newGUI();
	}
	
	public static void newGUI() {
		ReadFile();
		newGUI = new GUI();
		newGUI.displayTasks(Lists.get(0), 0);
	}

	public static void AddTask(String listName, String value) {
		for (List L : Lists) {
			if (L.Title.equals(listName)) {
				L.AddTask(value);
				break;
			}
		}
		save();
	}
	
	public static void AddNewList(String newTitle) {
		List newList = new List(newTitle, new ArrayList<String>());
		Lists.add(newList);
		save();
	}
	
	public static void DeleteByTask(int listIndex, String task) {
		List currentList = Lists.get(listIndex);	//Establish current list
		
		int index = -1;
		for (int i=0; i<Lists.get(listIndex).Tasks.size(); i++) {
			if (currentList.Tasks.get(i).equals(task)) {
				Lists.get(listIndex).Tasks.remove(i);
				save();
				break;
			}
		}
	}
	
	public static void DeleteList(int listIndex) {
		Lists.remove(listIndex);
		save();
	}
	
	public static void SaveListNames(ArrayList<String> listTitleCollection) {
		for (int i=0; i<listTitleCollection.size(); i++) {		//Override Task_List list titles
			Lists.get(i).Title = listTitleCollection.get(i);
		}
		save();
	}
	
	public static void quit() {
		if(save()) {
		    System.exit(0);
		}
	}
	
	public static List GetListByName(String listName) {
		
		for (List L : Lists) {
			if (L.Title.equals(listName)) {
				return L;
			}
		}
		return null;
	}
	
	public static boolean save() {
		//Cloud Save
		try {	
			FileWriter fw = new FileWriter(new File("C:\\Users\\bjenk\\Google Drive", "TaskList_SaveFile.txt"));
			for(List list : Lists) {
				fw.write(list.Title + "|");		//Write title of list
				for (String task : list.Tasks) {
					fw.write(task + ",");	//Write each task in list
				}	
				fw.write(System.lineSeparator());	//New line for new list
			}
			fw.close();
		}
		catch(Exception e) {}
		
		//Internal save
		try {
			FileWriter writer = new FileWriter("savedList.txt"); 
			for(List list : Lists) {
				writer.write(list.Title + "|");		//Write title of list
				for (String task : list.Tasks) {
					writer.write(task + ",");	//Write each task in list
				}	
				writer.write(System.lineSeparator());	//New line for new list
			}
			writer.close();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	

	public static void ReadFile() {
		
		ArrayList<String> listTitles = new ArrayList<String>();
		
	    try {
	      File myObj = new File("savedList.txt");
	      Scanner myReader = new Scanner(myObj);
	      
	      while (myReader.hasNextLine()) {
	    	  String line = myReader.nextLine();	//Get full line (ArrayList)
	    	  int titleEnd = line.indexOf("|");		//Find index of title separator
	    	  String title = line.substring(0, titleEnd);	//Get title
	    	  listTitles.add(title);
	    	  String tasks = line.substring(titleEnd+1);	//Get tasks in single string
	    	  ArrayList<String> items = new ArrayList<String>(Arrays.asList(tasks.split(",")));		//Separate tasks by comma
	    	 
	    	  List newList = new List(title, items);
	    	  
	    	  Lists.add(newList);
	      }
	      GUI.AddListsDropDown(listTitles);
	      myReader.close(); 
	     } 
	     catch (FileNotFoundException e) {
	    	 System.out.println(e);
	    }
	  }
}