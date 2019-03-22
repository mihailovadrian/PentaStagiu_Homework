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

public class FileUtils {

	public static Map<String, String> readUserInfoFromFile(String fileName) {

		List<String[]> usersInformation = new ArrayList<String[]>();

		try {
			URI uriFileName = ClassLoader.getSystemResource(fileName).toURI();
			String filePath = Paths.get(uriFileName).toString();

			// read file into stream, try-with-resources
			try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
				usersInformation = stream.map(FileUtils::convertLineToArray).collect(Collectors.toList());
			} catch (InvalidPathException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();

			}
		} catch (URISyntaxException e1) {

			e1.printStackTrace();
		}
		// return the user information in a Map
		return usersInformation.stream().collect(Collectors.toMap(e -> e[0], e -> e[1]));

	}

	// converts each line readed from the file to array
	private static String[] convertLineToArray(String line) {
		String[] result = null;

		result = line.split(Constants.inputFileValuesSeparator);

		return result;

	}

}
