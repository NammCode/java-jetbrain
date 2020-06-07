package problem.syntax.array;

import java.util.Scanner;

public class PrettyColor {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] array = new String[4];
		for (int i = 0; i < array.length; i++) {
			array[i] = scanner.next();
		}
		isLookingPretty(array);
		scanner.close();
	}

	private static void isLookingPretty(String[] array) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(array[i].charAt(j) == array[i].charAt(j+1) && array[i].charAt(j) == array[i+1].charAt(j+1)
						&& array[i].charAt(j) == array[i+1].charAt(j)) {
					System.out.println("No");
					return;
				}
			}
		}
		System.out.println("YES");
	}
	
}
