import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Library {

	public static int ChoiceDriver(Scanner reader, String[] choiceArray, String prompt, String help) {
			boolean done = false;
			int ret = 0;
			int numOptions = choiceArray.length;
			
			Map<String, Integer> fullChoice = new HashMap<String, Integer>();
			Map<String, Integer> abbrevChoice = new HashMap<String, Integer>();
			
			for (int i = 0; i < numOptions; ++i) {
				String currentChoice = choiceArray[i].toUpperCase();
				int caretIdx = currentChoice.indexOf("^");
				if (caretIdx > -1) {
					fullChoice.put(currentChoice.substring(0, caretIdx), i);
					abbrevChoice.put(currentChoice.substring(caretIdx + 1), i);
				}
				else {
					fullChoice.put(currentChoice, i);
				}

			}
			
			while (!done) {
				System.out.println(prompt);
				
				String in = reader.next().toUpperCase(); // Scans the next token of the input
				
				if (in.equals("?")) {
					System.out.println();
					System.out.println(help);
					System.out.println();
					System.out.println("Select from the below list (enter number. abbreviation, or full text). Enter 0, q, or quit to quit.");
					
					for (int i = 1; i < numOptions; ++i) {
						String currentChoice = choiceArray[i].toUpperCase();
						int caretIdx = currentChoice.indexOf("^");
						if (caretIdx > -1) {
							System.out.println("\t" + i + " (" + currentChoice.substring(caretIdx + 1) + ") - " + currentChoice.substring(0, caretIdx));
						}
						else {
							System.out.println("\t" + i + " - " + currentChoice);
						}
						
					}
					System.out.println();
				}
				else if (in.equals("")) {
					done = true;
				}
				else if (fullChoice.containsKey(in)) {
					ret = fullChoice.get(in);
					done = true;
				}
				else if (abbrevChoice.containsKey(in)) {
					ret = abbrevChoice.get(in);
					done = true;
				}
				else {
					try {
						int inNum = Integer.parseInt(in);
						if (inNum < numOptions) {
							ret = inNum;
							done = true;
						}
					}
					catch(Exception e){
						System.out.println("Invalid input.");
					}
				}
			}
		   
			return ret;
	}
}
