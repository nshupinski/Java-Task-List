import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Task_List {
	
	static Scanner kbd = new Scanner(System.in);   //keyboard scanner
	static ArrayList<String> Tasks = new ArrayList<String>();
	static String input;
	
	public static void main(String[] args) {

		PrintHeader();
		ReadFile();
		Print();
		
		while (true)  {
			input = kbd.nextLine();
			if (!input.equals("")) { 		//Makes sure input is not empty
				ReadInput(input);
			}
		}
	}
	
	public static void ReadInput(String input) {
		
		String command = "";
		String value = "";
		
		if (!input.substring(0,1).equals("/")) {		//Checks to make sure input starts with a '/'
			System.out.println("Please start each command with a /");
			input = kbd.nextLine();
		}
		else {

			int i = input.indexOf(' ');		//Checks if there's a value after the command
			if (i == -1) {
				command = input;
				
				if (command.equalsIgnoreCase("/quit")) {
					quit();
				}
				else if (command.equalsIgnoreCase("/help")) {
					help();
				}
				else {
					System.out.println("This command is not recognized. Please try again.");
				}
			}
			else {
				command = input.substring(0, i);		//Gets input command
				value = input.substring(i+1);		//Gets value after command
								
				if (command.equalsIgnoreCase("/add")) {
					AddTask(value);
				}
				else if (command.equalsIgnoreCase("/delete")) {
					CheckDeleteType(value);
				}
				else if (command.equalsIgnoreCase("/edit")) {
					CheckEditType(value);
				}
				else {
					System.out.println("This command is not recognized. Please try again.");
				}
			}
		}
	}
	
	public static boolean IsNumber(String value) {
		
		try {
			int index = Integer.parseInt(value);
			return true;
		}
		catch(NumberFormatException nfe) {
			return false;
		}
	}
	
	public static void PrintHeader() {
		System.out.print("\n~ Your To-Do List ~ \t\t For a list of commands, type '/help' \n-------------------");
	}
	
	public final static void ClearConsole()
	{
		System.out.println(System.lineSeparator().repeat(100));
	}
	
	
	// Add
	public static void AddTask(String value) {
		Tasks.add(value);
		ClearConsole();
		save();
		System.out.println("-" + "'" + value + "'" + " has been added to your list.");
		PrintHeader();
		Print();
	}
	
	
	// Print
	public static void Print() {
		for (int i=0; i<Tasks.size(); i++) {
			System.out.println("- " + Tasks.get(i));
		}
	}
	
	
	// Delete
	public static void CheckDeleteType(String delValue) {		//Check whether the user entered the list index or the task string
		
		if (IsNumber(delValue)) {
			int index = Integer.parseInt(delValue);
			DeleteByIndex(index);
		}
		else {
			DeleteByTask(delValue);
		}
	}
	
	public static void DeleteByIndex(int i) {		//If the user entered the task list number
		
		i--;
		if (!(i > Tasks.size() || i < 0)) {		//Check for valid index to delete
			String delTask = Tasks.get(i);
			Tasks.remove(i);
			ClearConsole();
			save();
			System.out.println("-" + "'" + delTask + "'" + " was deleted from the list.");
			PrintHeader();
			Print();
		}
		else {
			System.out.println("Please select a valid index or type in the task itself.");
		}
	}
	
	public static void DeleteByTask(String task) {		//If the user entered the task words
		for (int i=0; i<Tasks.size(); i++) {
			if (Tasks.get(i).equals(task)) {
				Tasks.remove(i);
				ClearConsole();
				save();
				System.out.println("-" + "'" + task + "'" + " was deleted from the list.");
				PrintHeader();
				Print();
				break;
			}
			System.out.println("Couldn't find that task in your list.");
		}
	}
	
	
	// Edit
	public static void CheckEditType(String editValue) {		//Check whether the user entered the list index or the task string
		
		if (IsNumber(editValue)) {
			int index = Integer.parseInt(editValue);
			EditByIndex(index);
		}
		else {
			EditByTask(editValue);
		}
	}
	
	public static void EditByIndex(int i) {		//If the user entered the task list number
		i--;
		if (!(i > Tasks.size() || i < 0)) {		//Check for valid index to edit
			String editTask = Tasks.get(i);
			System.out.println("Enter your changes to " + "'" + editTask + "'");
			input = kbd.nextLine();
			Tasks.set(i, input);
			ClearConsole();
			save();
			System.out.println("-" + "Your changes have been made.");
			PrintHeader();
			Print();
		}
		else {
			System.out.println("Please select a valid index or type in the task itself.");
		}
	}
	
	public static void EditByTask(String task) {		//If the user entered the task words
		for (int i=0; i<Tasks.size(); i++) {
			if (Tasks.get(i).equals(task)) {
				System.out.println("Enter your changes to " + "'" + task + "'");
				input = kbd.nextLine();
				Tasks.set(i, input);
				ClearConsole();
				save();
				System.out.println("-" + "Your changes have been made.");
				PrintHeader();
				Print();
				break;
			}
			System.out.println("Couldn't find that task in your list.");
		}
	}

	
	public static void quit() {
		if(save()) {
			System.out.println("Oki dokes! Have a great day!");
		    System.exit(0);
		}
	}
	
	public static boolean save() {
		try {	
			FileWriter writer = new FileWriter("savedList.txt"); 
			for(String str: Tasks) {
			  writer.write(str + System.lineSeparator());
			}
			writer.close();
			return true;
		}
		catch(Exception e) {
			System.out.println("*There was an issue saving.");
			return false;
		}
	}
	
	public static void help() {
		System.out.println("\n/Add <task> : Adds a task to your list. \n"
				+ "/Edit <task/number on list> : Allows you to change the wording of a task on your list. \n"
				+ "/Delete <task/number on list> : Removes task from your list. \n"
				+ "/Quit : Exits application.");
	}
	
	public static void ReadFile() {
	    try {
	      File myObj = new File("savedList.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        Tasks.add(myReader.nextLine());
	      }
	      myReader.close(); 
	     } 
	     catch (FileNotFoundException e) {

	    }
	  }
}
