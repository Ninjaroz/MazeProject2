/**
 * 
 */

/**
 * @author Gary
 *
 */
public class RunMaze {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	//Creates an instance of the ReadAndPrintMaze class
	ReadAndPrintMaze rapm = new ReadAndPrintMaze();
	//runs the GenerateMaze method in the ReadAndPrintMaze class
	rapm.GenerateMaze("input_file.txt");
	//runs the PrintMaze method in the ReadAndPrintMaze class
	rapm.PrintMaze();
	//rapm.DisplayMazeLinkedList();
	}

}
