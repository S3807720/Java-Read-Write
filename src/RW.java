import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RW {
	public static List<String> list = new ArrayList<>();
	public static String in = "in.txt";
	public static String out = "out.txt";
	public final static String PATTERN = "\t|\n";
	public final static int MAX_LEN = 8;
	
	public static void main(String[] args) {	
		Read();
		Write();
	}
	
	public static void Read() {
		try {
			File myObj = new File(in);
			Scanner myReader = new Scanner(myObj);
			myReader.useDelimiter(PATTERN); // delim for tab, nl
			int i = 0;
			while (myReader.hasNextLine()) {
				String data = myReader.next(); // read data
				if (data.trim().isBlank()) continue; // if blank, skip
				if (data.length() < MAX_LEN) { // pad small entries
					while (data.length() < MAX_LEN) data += " ";
					list.add(data);
				}
				else if (data.length() >= MAX_LEN) { // split big strings
					String temp = data.substring(MAX_LEN); // new string from max
					data = data.substring(0, MAX_LEN); // old string up to max 
					list.add(data);
					if (!temp.trim().isEmpty()) { // second string if it's not blank space 
						while (temp.length() < MAX_LEN) temp += " "; //pad with spaces
						if (++i % 5 == 0) list.add("\n"); // newline
						list.add(temp); 
					}
				}
				if (++i % 5 == 0) list.add("\n"); // newline
			}
			myReader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void Write() {
		try {
			FileWriter myWriter = new FileWriter(out);
			for(String s : list) myWriter.write(s); 
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
