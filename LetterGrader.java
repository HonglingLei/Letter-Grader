package lettergrader;

import java.util.*;
import java.io.*;
import java.io.File;
import java.io.IOException;

public class LetterGrader {

	/**
	 * global variables:
	 * student number, student name
	 * scores in quiz1, quiz2, quiz3, quiz4, midterm1, midterm2, and final
	 * total score calculated using the weighted average formula
	 * corresponding letter grade
	 */
	static int stuNum = 0;
	static int stuNum2 = 100; 
	//stuNum2 sets a max possible number of students 
	//to make sure the arrays are big enough to contain those data
	static String[] Name = new String[stuNum2];
	static int[] Q1 = new int[stuNum2];
	static int[] Q2 = new int[stuNum2];
	static int[] Q3 = new int[stuNum2];
	static int[] Q4 = new int[stuNum2];
	static int[] Mid1 = new int[stuNum2];
	static int[] Mid2 = new int[stuNum2];
	static int[] Final = new int[stuNum2];
	static float[] TotalScore = new float[stuNum2];
	static char[] Grade = new char[stuNum2];

	/**
	 * readScore method
	 * @param args
	 * this method reads scores from the input file
	 * and stores the data in member variables
	 */
	public void readScore(String[] args) {

		//checks if the user command line arguments are valid
		if (args.length < 2) {
			//if not valid, tells the user how to use this program
			System.out.println("Usage: java TestLetterGrader inputFile outputFile");
		}
		else {
			//if valid, displays what the program will do
			File input = new File(args[0]);
			File output = new File(args[1]);
			System.out.println("Letter grade has been calculated for students listed in input file " + input.getName()
					+ " and written to output file " + output.getName());
		}

		//reads from the input .txt file using BufferedReader
		try {
			File inputFile = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			int i = 0;
			String s = br.readLine(); //reads line by line
			while(s != null) {
				//stores data in the right places (corresponding arrays)
				//i means the number i student
				String[] info = s.split(",");
				Name[i] = new String();
				Name[i] = info[0];
				Q1[i] = Integer.valueOf(info[1].trim());
				Q2[i] = Integer.valueOf(info[2].trim());
				Q3[i] = Integer.valueOf(info[3].trim());
				Q4[i] = Integer.valueOf(info[4].trim());
				Mid1[i] = Integer.valueOf(info[5].trim());
				Mid2[i] = Integer.valueOf(info[6].trim());
				Final[i] = Integer.valueOf(info[7].trim());
				i++;
				stuNum++; //stuNum counts the total #of lines, i.e., total #of students in the input .txt file
				s = br.readLine();
			}
		} catch (FileNotFoundException e) {//error checking & exception handling
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Something wrong with the input file.\n");
			e.printStackTrace();
        }	
	}

	/**
	 * calcLetterGrade method
	 * this method calculates the total score for each student
	 * based on each exam's grade and weight
	 * and determines the letter grade (A/B/C/D/F)
	 */
	public void calcLetterGrade() {
		for (int i = 0; i < stuNum; i++) {//for every student
			//counts their total score
			TotalScore[i] = (float) (0.10*Q1[i] + 0.10*Q2[i] + 0.10*Q3[i] + 0.10*Q4[i] + 0.20*Mid1[i] + 0.15*Mid2[i] + 0.25*Final[i]);
			//transform their total score to a letter grade
			if (TotalScore[i] >= 90) Grade[i] = 'A';
			else if (TotalScore[i] >= 80) Grade[i] = 'B';
			else if (TotalScore[i] >= 70) Grade[i] = 'C';
			else if (TotalScore[i] >= 60) Grade[i] = 'D';
			else Grade[i] = 'F';
		}
	}
	
	/**
	 * sortName method
	 * sorts the lines alphabetically ascending
	 * using bubble sort approach
	 */
	private void sortName() {
		String tmp1 = null;
		char tmp2 = 'N';
		for (int i = 0; i < stuNum; i++) {
			for (int j = 0; j < stuNum; j++) {
				//sort by name alphabetically, ascending
				if (Name[i].compareTo(Name[j]) < 0) {
					tmp1 = Name[i];
					tmp2 = Grade[i];
					Name[i] = Name[j];
					Grade[i] = Grade[j];
					Name[j] = tmp1;
					Grade[j] = tmp2;
				}
			}
		}
	}
	
	/**
	 * printGrade method
	 * @param args
	 * this method writes the grade in the output file using PrintWriter
	 */
	public void printGrade(String[] args) {
		sortName();//sorts the lines by name
		File outputFile = new File(args[1]);//selects where to export the output data
		try{
			PrintWriter textPrintStream = new PrintWriter(outputFile);
			//prints a sentence like: 
			//"Letter grades for 8 students given in the input_data.txt file is:"
			textPrintStream.print("Letter grades for ");
			textPrintStream.print(stuNum);
			textPrintStream.print(" students given in the ");
			textPrintStream.print(args[0]);
			textPrintStream.print(" file is:\n");
			for (int i = 0; i < stuNum; i++) {//for every student, displays their name and letter grade
				textPrintStream.printf(Name[i] + "\t\t%20c\n", Grade[i]);
			}
			textPrintStream.close();
		} catch (FileNotFoundException e) {//error checking & exception handling
            e.printStackTrace();
        } catch (Exception e) { 
        	System.out.println("Something wrong with the output file.\n");
			e.printStackTrace();
		}
	}

	/**
	 * calcAvg, calcMax, calcMin methods
	 * @param array
	 * @return avg, max, min
	 * these methods calculate Average, Max, and Min of an array
	 */
	//calculates average of an array
	private static float calcAvg(int[] array) {
		float sum = 0;
		for (int i = 0; i < stuNum; i++) {
			sum = sum + array[i];
		}
		float avg = sum / stuNum;
		return avg;
	}
	
	//calculates max of an array
	private static int calcMax(int[] array) {
		int max = 0;
		for (int i = 0; i < stuNum; i++) {
			if (max < array[i]) max = array[i];
		}
		return max;
	}
	
	//calculates min of an array
	private static int calcMin(int[] array) {
		int min = 200;
		for (int i = 0; i < stuNum; i++) {
			if (min > array[i]) min = array[i];
		}
		return min;
	}

	/**
	 * displayAverages method
	 * using the operation functions above, this method
	 * assigns avg, min, and max to corresponding variables
	 * and displays the results in console with special formats
	 */
	public void displayAverages() {
		System.out.println("Here are the class averages:");
		System.out.println("                   Q1\t       Q2\t   Q3\t       Q4\t Mid1\t     Mid2\tFinal");
		System.out.printf("Average: %12.2f%12.2f%12.2f%12.2f%12.2f%12.2f%12.2f\n", calcAvg(Q1), calcAvg(Q2), calcAvg(Q3), calcAvg(Q4), calcAvg(Mid1), calcAvg(Mid2), calcAvg(Final));
		System.out.printf("Minimum: %12d%12d%12d%12d%12d%12d%12d\n", calcMin(Q1), calcMin(Q2), calcMin(Q3), calcMin(Q4), calcMin(Mid1), calcMin(Mid2), calcMin(Final));
		System.out.printf("Maximum: %12d%12d%12d%12d%12d%12d%12d\n", calcMax(Q1), calcMax(Q2), calcMax(Q3), calcMax(Q4), calcMax(Mid1), calcMax(Mid2), calcMax(Final));
	//things like %12.2, %12 are for formatting purposes
	}

	/**
	 * I did not separately write a doCleanup method (I suppose it's not compulsory?)
	 * instead, I inserted the close file operations in readScore and printGrade methods
	 */

}

