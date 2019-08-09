package password.operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Password {

	public static void main(String[] args) {

		String filePath = "//Users//apple//Downloads//File//Read//Passwords.txt";
		String[] passwords = new String[14];

		File file = new File(filePath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			for (int i = 0; i < passwords.length; i++) {
				passwords[i] = br.readLine();
//			System.out.println("Success");	
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Input Output Exception");
			e.printStackTrace();
		}
		for (String password : passwords) {
			System.out.println("******\n" + password);
			boolean hasLetter = false;
			boolean hasNumber = false;
			boolean hasSpecial = false;
			boolean hasInvalidChar = false;

			for (int i = 0; i < passwords.length; i++) {
				// 1: Contains letter
				if ("abcdefghijklmnopqrstuvwxyz".contains(password.substring(i, i + 1).toLowerCase())) {
					hasLetter = true;
				}
				// 2: Contains a number
				else if ("1234567890".contains(password.substring(i, i + 1))) {
					hasNumber = true;
				}
				// 3: Contains a special character
				else if ("!@#$%^&*()".contains(password.substring(i, i + 1))) {
					hasSpecial = true;
				} else {
					try {
						throw new InvalidCharacterException(password.substring(i, i + 1));
					} catch (InvalidCharacterException e) {
						e.toString();

					}
				}
			}
//			if (hasInvalidChar) {
//				System.out.println("Invalid Character");
//			} 
			if (!hasLetter || !hasNumber || !hasSpecial) {
				System.out.println("Missing Criteria");

			} else {
				System.out.println("Valid Password");
			}

		}

	}
}

class InvalidCharacterException extends Exception {

	String ch;

	public InvalidCharacterException(String ch) {
		this.ch = ch;
	}

	public String toString() {
		return "InvalidCharacterException" + ch;
	}
}
