import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Gary
 *
 */
public class ReadAndPrintMaze {
	//Class level variables
	private static int iMaxMazeX;
	private static int iMaxMazeY;
	//private static String strMaze [] [];
	private static LinkList strMaze = new LinkList();
	private static int strStartX;
	private static int strFinishX;
	private static String strMazeBlock;
	private static String strWall = "xxx";
	private static String strStart = ".S.";
	private static String strFinish = ".F.";
	private static String strPath = "...";
	private static int strStartY;
	private static int strFinishY;
	
	//no arg empty constructor
	public ReadAndPrintMaze() {

	}
	
	/*reads in the maze from the input file and adds the appropriate values to iMaxMazeLenth, iMaxMazeWidth, strMaze,
	strMazeStart, strMazeFinish, strMazeBlock */
	public void GenerateMaze(String f){
		//method level variables
		String strLine;
		String strNum = "";
		int iLineCounter = 1;
		int iX = 0;
		int iY = 0;
		
		try{
		//creates BufferedReader called br to read in the input_file.txt file.
		BufferedReader br = new BufferedReader(new FileReader(f));	
		
		//Reads in all the lines from the file 
		while ((strLine = br.readLine()) !=null){
			
			//sets x coordinate found to false and y coordinate found to true initially.
			boolean xFound = false;
			boolean yFound = true;
			
			//Reads strLine and checks for integer values
			for (int i = 0; i < strLine.length(); i++){

				//Adds digits to strNum to be added to x coordinate
				if (Character.isDigit(strLine.charAt(i)) && xFound == false){
				strNum += strLine.charAt(i);		
				}
				
				//Adds digits to strNum to be added to y coordinate
				if (Character.isDigit(strLine.charAt(i)) && yFound == false){
				strNum += strLine.charAt(i);
				}
				
				//if statement for adding coordinate to x or y
				if (xFound == false && strLine.charAt(i) == ',' && strNum != ""){
				//parses the current strNum string into the current x coordinate in the string	
				iX = Integer.parseInt(strNum);
				//sets xFound to true so that it processes the next integer found as the y coordinate
				xFound = true;
				//sets yFound to false so that it processes the next integer found as the y coordinate
				yFound = false;
				//resets strNum variable
				strNum = "";
				}else if (strLine.charAt(i) == ')'){
					//parses the current strNum string into the current y coordinate in the string
					iY = Integer.parseInt(strNum);
					//sets yFound to true so that it processes the next integer found as the x coordinate
					yFound = true;
					//sets xFound to false so that it processes the next integer found as the x coordinate
					xFound = false;
					//sets iX and iY in the strMaze array to blocked squares
					if (iLineCounter > 3 && CheckRange(iX, iY) == true){
					//strMaze [iX] [iY] = strWall;
					//Removes old link in linked list and adds a new one with the same coords but a strWall instead of path
					System.out.println(iX + " & " + iY + " should be removed..");
					strMaze.removeLink(iX, iY);
					strMaze.insertFLink(iX, iY, strWall);
					//System.out.println(strMaze.fLink.iX + " & " + strMaze.fLink.iY + " was added as a blocked path.");
					//Adds the MazeBlocked coordinates to the string strMazeBlock
					strMazeBlock += iX + ", "+ iY + "\n";
					}
					//resets strNum variable
					strNum = "";
				}				
			}
			
			switch(iLineCounter){
			//sets iMaxLength, iMaxWidth, and strMaze values and sets all squares to "." initially
			case 1:
				//Sets MaxMazeLength and MaxMazeLength
				iMaxMazeX = iX;
				iMaxMazeY =iY;
				//Initially sets all the fields in the array to open paths
				//strMaze = new String [iMaxMazeX] [iMaxMazeY];
				for (int c = 0; c < iMaxMazeY; c++){
					for (int l = 0; l < iMaxMazeX; l++){
						strMaze.insertFLink(l, c, strPath);
					}
				}
				break;
			/* sets strMazeStart, overrides blocked square with start square to strMaze, checks to make sure its not out of
			range */
			case 2:
				if (CheckRange(iX, iY) == true){
				strStartX = iX;
				strStartY = iY;
				}
				break;
			/* sets strMazeFinish, overrides blocked square with finish square to strMaze, checks to make sure its not out of
			range */
			case 3:
				if (CheckRange(iX, iY) == true){
				strFinishX = iX;
				strFinishY = iY;
				}
				break;
			
			}
			
		//adds 1 to the current line count
		iLineCounter ++;
		
			}
		}catch(IOException ioe){
		System.out.println("The file " + f + " was not found.");	
		}
	}
	
	//Prints the Maze to the output_file
	public void PrintMaze(){
	BufferedWriter opw = null;
	try {
		opw = new BufferedWriter(new FileWriter("output_file.txt"));
		//Prints the numbers for the first line of the file
		for (int x = 0; x < iMaxMazeX; x++){
			if (x == 0){
				opw.write("   " + x);
			}else
				opw.write("  " + x);
			}
		//This calculates the y axis of the file
		for (int c = 0; c < iMaxMazeY; c++){
			//Prints the appropriate value 3 times based on the position in the array
			for (int i = 0; i < 3; i++){
			//This creates a new line for each row	
			opw.newLine();	
			//This calculates the x axis of the file
			for (int l = 0; l < iMaxMazeX; l++){
			//this line sets the start
			if (i == 1 && l == strStartX  && c == strStartY){
			opw.write(c + " " + strStart);
			//this sets the Finish 
			}else if (i == 1 && l == strFinishX && c == strFinishY){
				opw.write(strFinish);	
			// this line writes all the middle lines from 1-9
			}else if (l == 0 && i == 1 && c < 10){
				opw.write(c + " " + strMaze.find(l, c).strPathType);
			//This line writes the middle line for the 10th row so that spacing is correct
			}else if (l == 0 && i == 1 && c == 10){
				opw.write(c + strMaze.find(l, c).strPathType);
			//This line writes the maze for the first column that is not the middle line
			}else if(l == 0){
				opw.write("  " + strMaze.find(l, c).strPathType);
			//This line writes the maze after the first column with no spaces
			}else{
					opw.write(strMaze.find(l, c).strPathType);
				}
			}
			}
			}
		opw.flush();
		opw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	//Returns false if x or y coordinate exceeds the maximum values for strMaze else returns true
	public boolean CheckRange(int x, int y){
		if (x > iMaxMazeX || y > iMaxMazeY){
			System.out.println("Coordinate out of range of Maze maximum length and width. Please check maze file.");
			return false;
		}
		return true;
	} 

	public void DisplayMazeLinkedList(){
		strMaze.Display();
	}
}

