/*
 * Date: 2021/05/26
 * Names: Daniel Yuan, Nikhil Sachdev, Jayden Huynh, Emily Ma
 * Teacher: Mr. Ho
 * Task: Create an anti-cheat test randomizer program
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;  
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

class RandomizedTest {

	/*
	 * 1. Program opens up and gives the user (teacher) a UI (userInterface)
	 * 
	 * 2. Teacher is presented with a question type selection screen (word problem
	 * or normal math problem) (questionTypeSelection)
	 * 
	 * 3. If normal math problem, program asks teacher for equation (method created)
	 * 4. Program takes numbers in the equations
	 * 
	 * 5. Program presents the numbers that can be randomized, and asks teacher to
	 * select some of the numbers (method created) 6. Program asks the teacher to
	 * specify the range by which to randomize the number (e.g. randomize a number
	 * from 1-10) 7. Program asks teacher to confirm choices. If no, the UI box
	 * containing the confirmation dialogue will close and allow the teacher to
	 * reinput.
	 * 
	 * 8. If yes, program will input the randomized numbers, and then calculate the
	 * answers. (method created)
	 * 
	 * 9. Afterwards, program will ask teacher to continue or not. If yes, program
	 * will loop back and repeat question creation process. (method created) 10. If
	 * no, program ends, and prints out the exam answer file.
	 * 
	 * 11. If teacher selects word problem, the program will ask the teacher to put
	 * the word problem itself (method created)
	 * 
	 * 12. Program presents the numbers that can be randomized, and asks teacher to
	 * select some of the numbers (repeat of step 5) 13. Program assigns letter
	 * variables (e.g. x, y, z) to the numbers in the word problem that will be
	 * randomized 14. Asks the teacher to input the corresponding equation to the
	 * word problem, using the variables instead of numbers.
	 * 
	 * 15. Repeat of step 6 and 7
	 * 
	 * 16. Once question creation process is complete, the word problem will update
	 * in accordance with the randomized numbers.
	 */

	static Scanner reader = new Scanner(System.in);
	static int problemType; // Variable for determining whether the question is a word problem or normal
	  			// problem
	static int[] randomizedTestQuestions = null;
	static String[] randomizedWordQuestions = null;
	static int[] testNumbers = null; // Array for holding the numbers to be randomized
	static String equate;   // Holds what type of equation will be created i.e. addition, subtraction, multiplication, division

		public static void main(String[] args) throws IOException {

		String equation; // Variable for holding the equation itself
		int questionNumber = 0;
		int keepGoing = 0;
		int calculationQuestionNumber = 0;
		int wordProblemQuestionNumber = 0;
		String[] testQuestions = null; // Array for holding the completed, calculated questions

		while(keepGoing == 0) {
			do {
				problemType = questionTypeSelection();
			} while (problemType < 1 || problemType > 2); // Error check
			if (problemType == 1) { // Calls number gatherer method for equation question
				System.out.println("Enter your equation:");
				testNumbers = numberGatherer(reader.nextLine());
				randomizedTestQuestions = randomizer(testNumbers);
				fileCreation();
				calculationQuestionNumber++;
				
			}

			if (problemType == 2) { // Calls word problem and number gatherer methods for word problem
				System.out.println("Enter your word problem.");
				testNumbers = numberGatherer(reader.nextLine());
				randomizedTestQuestions = randomizer(testNumbers);
				wordProblemVariables(testNumbers);
				fileCreation();
				wordProblemQuestionNumber++;
			}
	
		System.out.println("Enter 0 to continue, or enter any other number to exit");
		keepGoing = reader.nextInt();
		reader.nextLine();	
		}
	}

	/* 
	 * @Author - Emily
	 * JTextField and JButton for graphics
	 * 
	 * @param : text and message
	 * @return : graphics of the button or text field
	 */

	public static void userInterface() { // User interface graphics method
	/*
	 * This method will be used to handle the GUI and user interface. This method
	 * will likely not be finished until the command line is done.
	 */

	// JFrame and JLabel will be important
	// myFrame frame = new MyFrame();
	// myFrame frame = new MyFrame2(); 


		// textbox field
		MyFrame(){
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLayout(new 	FlowLayout());

			// Button to submit
			JButton button = new JButton("Submit");
			button.addActionListener(this);

			// Textfield
			JTextField textField = new JTextField();
			textField.setPreferredSize(new Dimension(250,40));

			this.add(button);
			this.add(textField);
			this.pack();
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button) {
				System.out.println("Welcome " + textField.getText());
				//button.setEnabled(false);
				//textField.setEditable(false)
			}

			//

		}

		// Button Field

		MyFrame2(){
		
			ImageIcon icon = new ImageIcon("point.png");
			ImageIcon icon2 = new ImageIcon("face.png");
			
			label = new JLabel();
			label.setIcon(icon2);
			label.setBounds(150, 250, 150, 150);
			label.setVisible(false);
			
			// Button Field JButton
			button = new JButton();
			button.setBounds(100, 100, 250, 100);
			button.addActionListener(this);

			// Button Text
			button.setText("I'm a button!");
			
			//JButton
			button.setFocusable(false);
			button.setIcon(icon);
			button.setHorizontalTextPosition(JButton.CENTER);
			button.setVerticalTextPosition(JButton.BOTTOM);
			button.setFont(new Font("Comic Sans",Font.BOLD,25));
			button.setIconTextGap(-15);
			button.setForeground(Color.cyan);
			button.setBackground(Color.lightGray);
			button.setBorder(BorderFactory.createEtchedBorder());
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLayout(null);
			this.setSize(500,500);
			this.setVisible(true);
			this.add(button);
			this.add(label);
		}
	}

	public static int questionTypeSelection() { // Question type selection method

		/*
		 * This method will handle the selection of the question type (word or normal
		 * equation)
		 */

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		int questionType;

		System.out.println("Select a math question type. " + "Type 1 to input an equation problem. "
				+ "Type 2 to input a word problem.");

		questionType = scan.nextInt();
		return questionType;
	}

	public static int[] numberGatherer(String mathProblem) { // Number gatherer method

		/*
		 * This method will split the numbers from the user's inputs for the later
		 * methods
		 */

		String numbersOnly;
		String[] numbersOnlySplit;
		int[] numbers;

		numbersOnly = mathProblem.replaceAll("[^0-9]", " ");
		numbersOnlySplit = numbersOnly.trim().split("\\s+");
		numbers = new int[numbersOnlySplit.length];
		for (int i = 0; i < numbersOnlySplit.length; i++) {
			numbers[i] = Integer.parseInt(numbersOnlySplit[i]);
			System.out.println(numbers[i]);
		}
		return numbers;
	}

	public static int[] randomizer(int[] testNumbers) { // Test randomizer method
		/*
		 * This method will need to take the numbers from the numberGatherer method It
		 * will take the numbers and randomize them, before asking the teacher to select
		 * a number, saved as a variable The teacher will specify the range by which
		 * they want to randomize the number (e.g. 1-10)
		 */
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		int randomizedNumber = 0;
		String confirmation = "yes";

		while (confirmation.equals("yes")) {
			System.out.print("Choose a number you want to randomize: ");
			randomizedNumber = s.nextInt();
			s.nextLine();
			int i = 0;

			for (i = 0; i < testNumbers.length; i++) {
				if (randomizedNumber == testNumbers[i]) {
					System.out.print("Enter a range for the number to be randomized to (ex:4-15): ");
					String range = s.nextLine();

					int index = range.indexOf("-");
					String min = range.substring(0, index);
					String max = range.substring(index + 1, range.length());
					int x = Integer.parseInt(min);
					int y = Integer.parseInt(max);
					int rand = r.nextInt(y) + x;
					testNumbers[i] = rand;
				}
			}
			System.out.print("Do you want to randomizer another number (yes or no): ");
			confirmation = s.nextLine();
		}
		System.out.println("These are the new numbers: " + Arrays.toString(testNumbers));
		return testNumbers;
	}
	/* Changes the type of equation to be +,-,*,/ in the form of a string message
	 * 
	 * @param rand - creates a random number in the range of 1-5
	 * @param change - is the value being created and returned
	 * 
	 * @return - returns the message based on the random number to determine equation type
	* */
	public static String changingEquation(String change){
		Random rand = new Random();
		int random = rand.nextInt(5)+1;

		if (random == 1){
		    return " plus ";
		}
		else if (random == 2){
		    return " subtracted by ";
		}
		else if (random == 3){
		    return " multiplied by ";
		}
		else{
		    return " divided by ";
		}
	    }
	/* creates the file including the title "Randomized Test, mark values table, preamble for test, and test questions
	 * created inside code in the form of a .docx file (word file)
	 * 
	 * @param p(1,2,3,4) - used for creating a new paragraph as well as settings for paragraph
	 * @param r(1,2,3,4) - used to set values inside paragraph i.e. font size, bold, text, etc.
	 * @param tab - used to create the mark table
	 * @param row - used to generate and add values inside the rows of the table
	 * @param element - used as a temporary variable to store array values and use them inside loop
	 * @param out - creates the file with all the code inside this method
	 * */
	public static void fileCreation() throws IOException { // End method
		/*
		 * If the teacher tells the program not to continue, the program will print out
		 * the exam file and end the program.
		 */
		// Beginning of code, creates a new XWPF Document (word file)
		try (XWPFDocument doc = new XWPFDocument()) {
			
		    // Creates the Date and Name at the top of the page
		    // Creates the paragraph using variable p1	
		    XWPFParagraph p1 = doc.createParagraph();
		    // Setting alignment
		    p1.setAlignment(ParagraphAlignment.LEFT);
		    // Generating the text line
		    doc.createParagraph();
		    XWPFRun r1 = p1.createRun();
		    // Set Text to Bold and font size to 10 for the Date and Name
		    r1.setBold(true);                                // Adding bold to text
		    r1.setFontSize(10);                              // Setting font size
		    r1.setText("Date:________________________");     // Inputting the text
		    r1.setFontFamily("Courier");                     // Setting the font style
		    doc.createParagraph();
		    r1.setBold(true);
		    r1.setFontSize(10);
		    r1.setText("                 Name:________________________");
		    r1.setFontFamily("Courier");

		    // Creating and aligning the title to the center with a underline beneath it
		    XWPFParagraph p2 = doc.createParagraph();
		    p2.setAlignment(ParagraphAlignment.CENTER);
		    doc.createParagraph();

		    XWPFRun r2 = p2.createRun();
		    r2.setBold(true);
		    // Adding italics to text
		    r2.setItalic(true);
		    r2.setFontSize(22);
		    r2.setText("Randomized Test");
		    doc.createParagraph();
		    r2.setText("                  ________________________________");    // Cosmetic only
		    r2.setFontFamily("Courier");

		    // Creating Table for mark values 
		    XWPFTable tab = doc.createTable();  
		    XWPFTableRow row = tab.getRow(0); // First row  
		    // Columns  
		    row.getCell(0).setText("Categories:");  
		    row.addNewTableCell().setText("Mark:");  
		    row.addNewTableCell().setText("Mark out of:");  
		    row = tab.createRow(); // Second Row  
		    row.getCell(0).setText("Knowledge/Understanding");  
		    row.getCell(1).setText("");  
		    row.getCell(2).setText(" " + 15);          // Setting baseline maximum mark, can be changed in code if needed
		    row = tab.createRow(); // Third Row  
		    row.getCell(0).setText("Thinking");  
		    row.getCell(1).setText("");  
		    row.getCell(2).setText(" " + 13); 
		    row = tab.createRow(); // Fourth Row  
		    row.getCell(0).setText("Communication");  
		    row.getCell(1).setText("");  
		    row.getCell(2).setText(" " + 8); 
		    row = tab.createRow(); // Fifth Row  
		    row.getCell(0).setText("Application");  
		    row.getCell(1).setText("");  
		    row.getCell(2).setText(" " + 14);
		    doc.createParagraph(); 

		    // Creating the preamble in the colour red with a underline beneath it
		    XWPFParagraph p3 = doc.createParagraph();
		    //Set color for second paragraph
		    XWPFRun r3 = p3.createRun();
		    r3.setText("Please be advised that this test is completely different from any of your peers. You will not be able to cheat. ");
		    r3.setText("You are permitted only the use of a calculator, pencil, eraser, highlighters, and pens. ");
		    r3.setText("You may begin as soon as I say so. Write your name and date first and then you may begin.");
		    doc.createParagraph();
		    r3.setText("                          ___________________________________________________________________________");    // Cosmetic only
		    // Changes text colour to red
		    r3.setColor("ff0000");

		    // Outputing choices
		    // Outputing the number calculations into file
		    if(problemType == 1){
			// Changes the array values to be able to be iterated
			for(int element : testNumbers) {
			    for(int rando : randomizedTestQuestions){

				XWPFParagraph p = doc.createParagraph();
				XWPFRun run = p.createRun();

				//Printing the inputted number and the randomized numbers
				run.setText("What is " + element + changingEquation(equate) + rando + "?");
				// Added to create spacing between each question    // the new method if needed
				for(int i = 0; i < 10; i++){
				    doc.createParagraph();                          // Spaces between each question, 10 line space between each question
				}
			    }
			}
		    }
		    // Outputing the word problems into file
		    else if(problemType == 2){
			for(String element : randomizedWordQuestions){

			    XWPFParagraph p = doc.createParagraph();
			    XWPFRun run = p.createRun();

			    // Printing inputted word questions
			    run.setText(element);
			    for(int i = 0; i < 10; i++){ 
				doc.createParagraph();
			    }
			}
		    }
		    // Creating the final message to confirm the file and its contents have ended
		    XWPFParagraph p4 = doc.createParagraph();
		    XWPFRun r4 = p4.createRun();
		    p4.setAlignment(ParagraphAlignment.CENTER);

		    // Set Text to Bold and font size to 15
		    r4.setBold(true);
		    r4.setFontSize(15);
		    r4.setText("This is the end of the test");
		    r4.setFontFamily("Courier");
		    p4.setPageBreak(true);  

		    // In order to produce a new File, must close the current file
		    // Prints everything within this method into the file
		    // The destination path needs to be changed based on user and specific file path
		    // IMPORTANT: to generate file, must change the C:\ path to computer that is using code
		    try (FileOutputStream out = new FileOutputStream("C:\\Users\\RandomizedTest.docx")) {
			doc.write(out);
		    }
		}
	}

	public static String[] wordProblemVariables(int[] testNumbers) { // Word problem method

		/*
		 * This method takes the numbers from word problems and assigns letter variables
		 * to them The user will be given the numbers and the corresponding variables
		 * (e.g. 1 = x, 2 = y) The method will ask the user for the equation, and to
		 * substitute the numbers in the equation with the variables
		 */
		Scanner s = new Scanner(System.in);

		String[] strtestNumbers = new String[testNumbers.length];
		for (int i = 0; i < testNumbers.length; i++) {
			strtestNumbers[i] = String.valueOf(testNumbers[i]);
		}

		System.out.println("Input variables (e.g. x,y,z) for each number (Don't use the same variable more than once)");
		for (int i = 0; i < strtestNumbers.length; i++) {
			System.out.print("Input variable for " + strtestNumbers[i] + ": ");
			String variable = s.nextLine();
			strtestNumbers[i] = variable;
		}
		System.out.println(Arrays.toString(strtestNumbers));
		return strtestNumbers;
	}
}
