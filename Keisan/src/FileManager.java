import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManager {
	
	public void saveHistory(String str) {
		try {
			FileWriter fileW = new FileWriter("save/history.txt", true);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fileW), true);
			
			pw.println(str);
			
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] readHistory() {
		ArrayList<String> historyList = new ArrayList<>();
		
		try {
	        File file = new File("save/history.txt");
	        
	        if(file.exists()) {
	            FileReader fr = new FileReader(file);
	            BufferedReader br = new BufferedReader(fr);
	            
	            String content;
	            
	            while((content = br.readLine()) != null) {
	                historyList.add(content);
	            }
	            
	            br.close();
	        }
	    } catch(IOException e) {
	        e.printStackTrace();
	    }
	    
	    return historyList.toArray(new String[historyList.size()]);
	}
	
	public void deleteHistory() {
		try {
			FileWriter file = new FileWriter("save/history.txt");
			PrintWriter pw = new PrintWriter(new BufferedWriter(file), true);
			
			pw.println("");
			
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
