package fileUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import mainClass.Constants;

public class ReadUserInformation {
	private static ReadUserInformation instance = null;
	private static Map<String, String> usersInformation = null;

	private ReadUserInformation() {

		List<String[]> usersInformation = new ArrayList<String[]>();

		try {
			URI uriFileName = ClassLoader.getSystemResource(Constants.inputFilePath).toURI();
			String filePath = Paths.get(uriFileName).toString();

			// read file into stream, try-with-resources
			try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
				usersInformation = stream.map(ReadUserInformation::convertLineToArray).collect(Collectors.toList());
			} catch (InvalidPathException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();

			}
		} catch (URISyntaxException e1) {

			e1.printStackTrace();
		}
		// return the user information in a Map
		ReadUserInformation.usersInformation = usersInformation.stream()
				.collect(Collectors.toMap(e -> e[0], e -> e[1]));

	}

	static public ReadUserInformation getInstance() {
		if (instance == null) {
			instance = new ReadUserInformation();
		}
		return instance;
	}

	// converts each line readed from the file to array
	private static String[] convertLineToArray(String line) {
		String[] result = null;

		result = line.split(Constants.inputFileValuesSeparator);

		return result;

	}

	public static Map<String, String> getUsersInformation() {
		return usersInformation;
	}

	public static void setUsersInformation(Map<String, String> usersInformation) {
		ReadUserInformation.usersInformation = usersInformation;
	}

}