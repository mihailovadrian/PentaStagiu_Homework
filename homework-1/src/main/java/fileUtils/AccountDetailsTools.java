package fileUtils;

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
import java.util.logging.Logger;

import entity.AccountDetails;

public class AccountDetailsTools {
	private static List<AccountDetails> result;
	private final static Logger logger = Logger.getLogger(AccountDetailsTools.class.getName());
	private static URI uriFileName = null;

	@SuppressWarnings("unchecked")
	public static List<AccountDetails> readAccountDetailsXML() {
		result = new ArrayList<AccountDetails>();

		try {
			uriFileName = ClassLoader.getSystemResource(Constants.ACCOUNT_DETAILS_XML).toURI();
		} catch (URISyntaxException e1) {
			logger.warning(e1.getMessage());
		}
		String filePath = Paths.get(uriFileName).toString();
		logger.info("path xml : " + filePath);
		try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));) {

			result = (List<AccountDetails>) xmlDecoder.readObject();

		} catch (FileNotFoundException e) {

			logger.warning(e.getMessage());
		}

		return result;
	}

	public static Boolean writeAccountDetailsToXML(List<AccountDetails> accountDetails) {

		try {
			uriFileName = ClassLoader.getSystemResource(Constants.ACCOUNT_DETAILS_XML).toURI();
		} catch (URISyntaxException e1) {
			logger.warning(e1.getMessage());
		}
		String filePath = Paths.get(uriFileName).toString();
		logger.info("path xml : " + filePath);
		try (XMLEncoder x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)));) {
			x.writeObject(accountDetails);
			return true;

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return false;
	}

}
