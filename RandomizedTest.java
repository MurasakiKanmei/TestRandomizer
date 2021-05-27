/*
 * Date: 2021/05/26
 * Names: Daniel Yuan, Nikhil Sachdev, Jayden Huynh, Emily Ma
 * Teacher: Mr. Ho
 * Task: Create an anti-cheat test randomizer program
 */ 

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class RandomizedTest {
	
	/* 
	 * 1. Program opens up and gives the user (teacher) a UI (method created)
	 * 
	 * 2. Teacher is presented with a question type selection screen (word problem or normal math problem) (method created)
	 * 
	 * 3. If normal math problem, program asks teacher for equation (method created)
	 * 4. Program takes numbers in the equations
	 * 
	 * 5. Program presents the numbers that can be randomized, and asks teacher to select some of the numbers (method created)
	 * 6. Program asks the teacher to specify the range by which to randomize the number (e.g. randomize a number from 1-10)
	 * 7. Program asks teacher to confirm choices. If no, the UI box containing the confirmation dialogue will close and allow the teacher to reinput.
	 * 
	 * 8. If yes, program will input the randomized numbers, and then calculate the answers. (method created)
	 * 
	 * 9. Afterwards, program will ask teacher to continue or not. If yes, program will loop back and repeat question creation process. (method created)
	 * 10. If no, program ends, and prints out the exam answer file.
	 * 
	 * 11. If teacher selects word problem, the program will ask the teacher to put the word problem itself (method created)
	 * 
	 * 12. Program presents the numbers that can be randomized, and asks teacher to select some of the numbers (repeat of step 5)
	 * 13. Program assigns letter variables (e.g. x, y, z) to the numbers in the word problem that will be randomized
	 * 14. Asks the teacher to input the corresponding equation to the word problem, using the variables instead of numbers. 
	 * 
	 * 15. Repeat of step 6 and 7
	 * 
	 * 16. Once question creation process is complete, the word problem will update in accordance with the randomized numbers.
	 */
	
	static Scanner reader = new Scanner(System.in);
	
	public static void main(String[] args) {
	
		String equation; 	// Variable for holding the equation itself
		int problemType;		// Variable for determining whether the question is a word problem or normal problem
		int[] testNumbers = null;	// Array for holding the numbers to be randomized		
		String[] testQuestions = null;	// Array for holding the completed, calculated questions
			
		problemType = questionTypeSelection();
		if (problemType == 1) {
			System.out.println("Enter your equation:");
			testNumbers = numberGatherer(reader.nextLine());
		}
		else {
			
		}
	}
	
	// User interface graphics method	
	
	public static void userInterface() {
		
	}
	
	// Question type selection method
	
	public static int questionTypeSelection() { // complete
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		int questionType;
		
		System.out.println("Select a math question type. "
				+ "Type 1 to input an equation problem. "
				+ "Type 2 to input a word problem.");
		
		questionType = scan.nextInt();
		return questionType;
	}
	
	// Number gatherer method
	
	  public static int[] numberGatherer(String mathProblem) {
	        String numbersOnly;
	        String[] numbersOnlySplit;
	        int[] numbers;
	        
	        numbersOnly = mathProblem.replaceAll("[^0-9]"," ");
	        numbersOnlySplit = numbersOnly.trim().split("\\s+");
	        numbers = new int[numbersOnlySplit.length];
	        for (int i = 0; i < numbersOnlySplit.length; i++) {
	            numbers[i] = Integer.parseInt(numbersOnlySplit[i]);
	            System.out.println(numbers[i]);
	        }
	        return numbers;
	}
	
	// Test randomizer method
	
	public static void randomizer() {
	/*
		 * This method will need to take the numbers from the numberGatherer method 
		 * It will take the numbers and randomize them, before asking the teacher to select a number, saved as a variable
		 * The teacher will specify the range by which they want to randomize the number (e.g. 1-10)
		 */	
	}
	
	// Calculator method
	
	public static void problemCalculator() {
	/*
	 * This method will need to take the randomized numbers and equation from the previous method and calculate them
	 */
	}
	
	// End method
	
	public static void fileCreation() {
		/*
		 * If the teacher tells the program not to continue, the program will print out the exam file. 
		 */
	}
	
	// Word problem method
	
	public static void wordProblemVariables() {
		/*
		 * This method takes the numbers from word problems and assigns letter variables to them
		 * The user will be given the numbers and the corresponding variables (e.g. 1 = x, 2 = y)
		 * The method will ask the user for the equation, and to substitute the numbers in the equation with the variables
		 */
		
	}
		
}
