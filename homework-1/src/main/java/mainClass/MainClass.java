package mainClass;

import java.util.Map;

import fileUtils.FileUtils;
import projectConstants.Constants;

public class MainClass {
	
	public static void main(String[] args) {
		Map<String, String> usersInformation = FileUtils.readUserInfoFromFile(Constants.inputFilePath);
		System.out.println(usersInformation.toString());
	}

}
