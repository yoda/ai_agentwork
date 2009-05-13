package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Dgeneration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		String command = "";
		
		System.out.println("Valid commands:");
		System.out.println("\tInput board directories:");
		System.out.println("\t\tinput <dir1>");
		System.out.println("\t\tinput <dir1> <dir2> ...");
		System.out.println("\tBuild Decision Tree:");
		System.out.println("\t\tdtreebuild <inputfile.result.x>");
		System.out.println("\tHelp:");
		System.out.println("\t\thelp");
		System.out.println("\tExit:");
		System.out.println("\t\texit");
		
		while(true) {
			try {
				System.out.print("Enter command: ");
				command = br.readLine();
				System.out.println();
				System.out.println();
			} catch (IOException e) {
				System.err.println("Critical Error");
				System.exit(1);
			}
			if(command == "") {
				continue;
			} else {
				// Insert logic here.
				boolean hadMoreThanTwo = false;
				StringTokenizer st = new StringTokenizer(command," ",false);
				if(st.countTokens() > 1) {
					String com = st.nextToken();
					if(com.compareTo("input") == 0) {
						hadMoreThanTwo = true;
						System.out.println("Doing input.");
						StringTokenizer zt = new StringTokenizer(command," ",false);
						zt.nextToken();
						ArrayList<String> input = new ArrayList<String>();
						while(zt.hasMoreTokens()) input.add(zt.nextToken());
						DataProcessor scanner = new DataProcessor(input);
						scanner.scanData();
						scanner.writeData("gamedata");
					} else if(com.compareTo("dtreebuild") == 0 && st.countTokens() < 2) {
						System.out.println("Doing dtreebuild.");
						hadMoreThanTwo = true;
						StringTokenizer zt = new StringTokenizer(command," ",false);
						zt.nextToken();
						DataDTreeGenerator gen = new DataDTreeGenerator(zt.nextToken(), "result");
						try {
							if(gen.generateTree()) {
								System.out.println("Complete!");
							}
						} catch (IOException e) {
							System.err.println("Error: Probable bad filename!");
							
						}
					} else {
						hadMoreThanTwo = true;
						continue;
					}
				}
				st = new StringTokenizer(command," ",false);
				if(st.hasMoreTokens()) {
				String com = st.nextToken();
				if(com.compareTo("exit") == 0 && hadMoreThanTwo == false) {
					System.out.println("Exiting...");
					break;
				} else if(com.compareTo("help") == 0 && hadMoreThanTwo == false) {
					System.out.println("Valid commands:");
					System.out.println("\tInput board directories:");
					System.out.println("\t\tinput <dir1>");
					System.out.println("\t\tinput <dir1> <dir2> ...");
					System.out.println("\tBuild Decision Tree:");
					System.out.println("\t\tdtreebuild <inputfile.result.x>");
					System.out.println("\tHelp:");
					System.out.println("\t\thelp");
					System.out.println("\tExit:");
					System.out.println("\t\texit");
					
				}
				}
				hadMoreThanTwo = true;
			}
		}
	}
}
