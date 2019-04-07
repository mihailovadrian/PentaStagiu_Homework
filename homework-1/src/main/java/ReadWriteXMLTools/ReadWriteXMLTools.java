package ReadWriteXMLTools;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadWriteXMLTools<T> {
	private List<T> result;
	private String filePath = null;

	private static final Logger LOGGER = Logger.getLogger(ReadWriteXMLTools.class.getName());

	public ReadWriteXMLTools(String filePath) {
		this.filePath = xmlFilePath(filePath);
		this.result = readInformationFromXML();
	}

	@SuppressWarnings("unchecked")
	private List<T> readInformationFromXML() {
		this.result = new ArrayList<>();

		LOGGER.info("path xml : " + filePath);
		try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));) {

			result = (List<T>) xmlDecoder.readObject();

		} catch (FileNotFoundException e) {

			LOGGER.log(Level.SEVERE, "Exception occur ", e);

		}
		return result;
	}

	public Boolean writeAccountDetailsToXML(List<T> informationToSave) {

		LOGGER.info("path xml : " + filePath);
		try (XMLEncoder x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)));) {
			x.writeObject(informationToSave);
			return true;

		} catch (FileNotFoundException e) {

			LOGGER.log(Level.SEVERE, "Exception occur ", e);

		}

		return false;
	}

	private String xmlFilePath(String filePath) {
		URI uriFileName = null;
		try {
			uriFileName = ClassLoader.getSystemResource(filePath).toURI();
		} catch (URISyntaxException e1) {
			LOGGER.log(Level.SEVERE, "Exception occur ", e1);
		}

		return Paths.get(uriFileName).toString();
	}

	public List<T> getInformationResult() {
		return result;
	}

	public void setInformationResult(List<T> informationList) {
		this.result = informationList;
	}

}
