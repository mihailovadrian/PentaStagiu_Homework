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
	private static List<AccountDetails> resultAccountDetails;
	private final static Logger logger = Logger.getLogger(AccountDetailsTools.class.getName());
	private static String filePath = null;
	private static AccountDetailsTools instance = null;

	private AccountDetailsTools() {
		filePath = xmlFilePath();
		resultAccountDetails = readAccountDetailsXML();
	}

	@SuppressWarnings("unchecked")
	private static List<AccountDetails> readAccountDetailsXML() {
		resultAccountDetails = new ArrayList<AccountDetails>();

		filePath = xmlFilePath();
		logger.info("path xml : " + filePath);
		try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));) {

			resultAccountDetails = (List<AccountDetails>) xmlDecoder.readObject();

		} catch (FileNotFoundException e) {

			logger.warning(e.getMessage());
		}

		return resultAccountDetails;
	}

	public static Boolean writeAccountDetailsToXML(List<AccountDetails> accountDetails) {

		filePath = xmlFilePath();
		logger.info("path xml : " + filePath);
		try (XMLEncoder x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)));) {
			x.writeObject(accountDetails);
			return true;

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return false;
	}

	private static String xmlFilePath() {
		URI uriFileName = null;
		try {
			uriFileName = ClassLoader.getSystemResource(Constants.ACCOUNT_DETAILS_XML).toURI();
		} catch (URISyntaxException e1) {
			logger.warning(e1.getMessage());
		}
		return Paths.get(uriFileName).toString();
	}

	public static AccountDetailsTools getInstance() {
		if (instance == null) {
			instance = new AccountDetailsTools();
		}
		return instance;
	}

	public static List<AccountDetails> getResult() {
		return resultAccountDetails;
	}

	public static void setResult(List<AccountDetails> result) {
		AccountDetailsTools.resultAccountDetails = result;
	}

}
