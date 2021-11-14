package tests;

import static org.testng.Assert.assertEquals;

import com.utility.ConfigReader;
import com.utility.Reader;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.myPages.HomePage;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;

public class FreeCarCheckTest extends BaseTest{

	private Reader reader = new Reader();


	@Test()
	public void validateCarDetails() throws IOException, InterruptedException {
		SoftAssert softAssert= new SoftAssert();
		HomePage homePage = page.getInstance(HomePage.class);
		ArrayList<String> getCarInput = reader.getCarInputDetails();

		for (int i = 0; i < getCarInput.size(); i++) {
			String registrationNum = getCarInput.get(i).replaceAll(" ", "");
			ArrayList<String> getCarDetailsGUI = homePage.getCarDetailsGUI(ConfigReader.pros.getProperty("url"), registrationNum);
			ArrayList<String> getCarDetailsOutput = reader.getCarOutPutDetails(registrationNum);
			if (getCarDetailsGUI.get(0).equals("")) {
				try {
					softAssert.assertTrue(false, registrationNum + " : registration number is not available in output");
					softAssert.assertAll();
				}catch(AssertionError e)
				{
					System.out.println(e.getMessage());
				}
			}

			for (int j = 0; j < getCarDetailsOutput.size(); j++) {
				try{
					String detailsOnGUI = getCarDetailsGUI.get(j);
					String detailsOnOutput = getCarDetailsOutput.get(j);
					softAssert.assertEquals(detailsOnGUI, detailsOnOutput);
//					System.out.println(detailsOnGUI +" is available in output");
				   softAssert.assertAll();
				}catch(AssertionError e)
				{
					System.out.println(e.getMessage());
				}
			}

		}


	}
}
