/*
 * Date: 2021/05/26
 * Names: Daniel Yuan, Nikhil Sachdev, Jayden Huynh, Emily Ma
 * Teacher: Mr. Ho
 * Task: Create an anti-cheat test randomizer program
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

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

		public static void main(String[] args) {

		String equation; // Variable for holding the equation itself
		int problemType; // Variable for determining whether the question is a word problem or normal
							// problem
		int questionNumber = 0;
		int keepGoing = 0;
		int calculationQuestionNumber = 0;
		int wordProblemQuestionNumber = 0;
		int[] testNumbers = null; // Array for holding the numbers to be randomized
		// int[] finalAnswers = null;
		String[] testQuestions = null; // Array for holding the completed, calculated questions
		int[] randomizedTestQuestions = null;
		String[] randomizedWordQuestions = null;

		while(keepGoing == 0) {
			do {
				problemType = questionTypeSelection();
			} while (problemType < 1 || problemType > 2); // Error check
			if (problemType == 1) { // Calls number gatherer method for equation question
				System.out.println("Enter your equation:");
				testNumbers = numberGatherer(reader.nextLine());
				randomizedTestQuestions = randomizer(testNumbers);
				// finalAnswers = problemCalculator(randomizedTestQuestions);
				fileCreation(randomizedTestQuestions, questionNumber);
				calculationQuestionNumber++;
				
			}

			if (problemType == 2) { // Calls word problem and number gatherer methods for word problem
				System.out.println("Enter your word problem.");
				testNumbers = numberGatherer(reader.nextLine());
				randomizedTestQuestions = randomizer(testNumbers);
				wordProblemVariables(testNumbers);
				// finalAnswers = problemCalculator(randomizedTestQuestions);
				fileCreation(randomizedWordQuestions, questionNumber);
				wordProblemQuestionNumber++;
			}
	
		System.out.println("Enter 0 to continue, or enter any other number to exit");
		keepGoing = reader.nextInt();			
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

	public static int[] problemCalculator(String[] randomizedTestQuestions) {
		return null; // Calculator method
		/*
		 * This method will need to take the randomized numbers and equation from the
		 * previous method and calculate them
		 */
	}

	public static void fileCreation(String[] randomizedTestQuestions, int[] finalAnswers, int questionNumber) { // End method
		/*
		 * If the teacher tells the program not to continue, the program will print out
		 * the exam file and end the program.
		 */
	}

		public static void wordProblemVariables(int[] testNumbers) { // Word problem method

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
		return testNumbers;
	}
}
