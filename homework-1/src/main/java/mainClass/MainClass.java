package mainClass;

import java.util.Map;
import java.util.Scanner;

import fileUtils.FileUtils;
import projectConstants.Constants;

public class MainClass {

	private static Scanner scanIn;

	public static void main(String[] args) {
		Map<String, String> usersInformation = FileUtils.readUserInfoFromFile(Constants.inputFilePath);
		String nameString = "";
		String passString = "";
		scanIn = new Scanner(System.in);
		boolean logedIn = false;
		System.out.println(usersInformation.toString());
		
		if (usersInformation != null) {
			while (true) {
				if (logedIn == false) {
					System.out.println("User Name: ");
					nameString = scanIn.nextLine();

					if (nameString != null) {

						System.out.println("Password: ");
						passString = scanIn.nextLine();
						System.out.println(usersInformation.get(nameString));
						if (usersInformation.get(nameString).equals(passString)) {
							System.out.println("Welcome user !");
							logedIn = true;
						} else {
							System.out.println("Wrong username/password");
						}
					}
				} else {
					break;
				}
			}
		}
	}

}
