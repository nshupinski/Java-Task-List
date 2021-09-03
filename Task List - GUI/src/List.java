import java.util.ArrayList;

class List {

	String Title;
	ArrayList<String> Tasks;
	
	public List(String title, ArrayList<String> tasks) {
		
		Title = title;
		Tasks = tasks; 
	}
	
	public String GetTitle() {
		return Title;
	}
	
	public ArrayList<String> GetTasks() {
		return Tasks;
	}
	
	public void AddTask(String task) {
		Tasks.add(task);
	}
}