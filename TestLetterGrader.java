package lettergrader;

/**
 * driver application
 * @author hongling
 * this class calls the LetterGrader class and its methods
 */
public class TestLetterGrader {
	public static void main(String[] args) {
		LetterGrader letterGrader = new LetterGrader();
		letterGrader.readScore(args);
		letterGrader.calcLetterGrade();
		letterGrader.printGrade(args);
		letterGrader.displayAverages();
	}
}
    	

