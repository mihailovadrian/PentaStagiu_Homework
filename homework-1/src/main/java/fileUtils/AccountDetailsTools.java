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
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.AccountDetails;

public class AccountDetailsTools {
	private List<AccountDetails> resultAccountDetails;
	private String filePath = null;

	private static final Logger LOGGER = Logger.getLogger(AccountDetailsTools.class.getName());
	private static AccountDetailsTools instance = null;

	private AccountDetailsTools() {
		this.filePath = xmlFilePath();
		this.resultAccountDetails = readAccountDetailsXML();
	}

	@SuppressWarnings("unchecked")
	private List<AccountDetails> readAccountDetailsXML() {
		this.resultAccountDetails = new ArrayList<AccountDetails>();

		LOGGER.info("path xml : " + filePath);
		try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));) {

			resultAccountDetails = (List<AccountDetails>) xmlDecoder.readObject();

		} catch (FileNotFoundException e) {

			LOGGER.log(Level.SEVERE, "Exception occur ", e);

		}

		return resultAccountDetails;
	}

	public Boolean writeAccountDetailsToXML(List<AccountDetails> accountDetails) {

		LOGGER.info("path xml : " + filePath);
		try (XMLEncoder x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)));) {
			x.writeObject(accountDetails);
			return true;

		} catch (FileNotFoundException e) {

			LOGGER.log(Level.SEVERE, "Exception occur ", e);

		}

		return false;
	}

	private String xmlFilePath() {
		URI uriFileName = null;
		try {
			uriFileName = ClassLoader.getSystemResource(Constants.ACCOUNT_DETAILS_XML).toURI();
		} catch (URISyntaxException e1) {
			LOGGER.log(Level.SEVERE, "Exception occur ", e1);

		}
		return Paths.get(uriFileName).toString();
	}

	public static AccountDetailsTools getInstance() {
		if (instance == null) {
			instance = new AccountDetailsTools();
		}
		return instance;
	}

	public List<AccountDetails> getResultAccountDetails() {
		return resultAccountDetails;
	}

	public void setResultAccountDetails(List<AccountDetails> resultAccountDetails) {
		this.resultAccountDetails = resultAccountDetails;
	}

}
