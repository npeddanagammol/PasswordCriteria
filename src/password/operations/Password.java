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
				else if ("!@#$%^&*()_-.,".contains(password.substring(i, i + 1))) {
					hasSpecial = true;
				} else {
					try {
						throw new InvalidCharacterException(password.substring(i, i + 1));
					} catch (InvalidCharacterException e) {
						e.toString();
						break;

					}
				}
			}
			try {
				if (!hasNumber) {
					throw new InvalidNumberCriteria(password);
				} else if (!hasLetter) {
					throw new InvalidCharacterCriteria(password);
				} else if(!hasSpecial) {
					throw new InvalidSpecialCharacterCriteria(password);
				} else System.out.println("Valid Password");
				
			} catch(InvalidNumberCriteria | InvalidCharacterCriteria | InvalidSpecialCharacterCriteria e  ) {
				System.out.println("Invalid Password");
				System.out.println(e.toString());
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
		return "InvalidCharacterException : " + ch;
	}
}

class InvalidNumberCriteria extends Exception {
	String str;

	InvalidNumberCriteria(String str) {
		this.str = str;
	}

	public String toString() {
		return "Invalid Number Criteria : " + str;
	}

}

class InvalidCharacterCriteria extends Exception {
	String str;

	InvalidCharacterCriteria(String str) {
		this.str = str;
	}

	public String toString() {
		return "Invalid Character Criteria : " + str;
	}

}

class InvalidSpecialCharacterCriteria extends Exception {
	String str;

	InvalidSpecialCharacterCriteria(String str) {
		this.str = str;
	}

	public String toString() {
		return "Invalid Special Character Criteria : " + str;
	}

}
