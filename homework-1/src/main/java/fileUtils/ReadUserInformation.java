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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadUserInformation {
	private static final Logger LOGGER = Logger.getLogger(ReadUserInformation.class.getName());

	private static ReadUserInformation instance = null;
	private Map<String, String> usersInformation = null;

	private ReadUserInformation() {

		List<String[]> informationFromFile = new ArrayList<String[]>();

		try {
			URI uriFileName = ClassLoader.getSystemResource(Constants.INPUT_USER_INFORMATION_FILE).toURI();
			String filePath = Paths.get(uriFileName).toString();

			// read file into stream, try-with-resources
			try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
				informationFromFile = stream.map(ReadUserInformation::convertLineToArray).collect(Collectors.toList());
			} catch (InvalidPathException e) {
				LOGGER.log(Level.SEVERE, "Exception occur ", e);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Exception occur ", e);

			}
		} catch (URISyntaxException e1) {

			LOGGER.log(Level.SEVERE, "Exception occur ", e1);
		}
		// return the user information in a Map
		this.setUsersInformation(informationFromFile.stream().collect(Collectors.toMap(e -> e[0], e -> e[1])));

	}

	public static ReadUserInformation getInstance() {
		if (instance == null) {
			instance = new ReadUserInformation();
		}
		return instance;
	}

	// converts each line from the file to array
	private static String[] convertLineToArray(String line) {
		String[] result = null;

		result = line.split(Constants.INPUT_FILE_SEPARATOR);

		return result;

	}

	public Map<String, String> getUsersInformation() {
		return usersInformation;
	}

	public void setUsersInformation(Map<String, String> usersInformation) {
		this.usersInformation = usersInformation;
	}

}
