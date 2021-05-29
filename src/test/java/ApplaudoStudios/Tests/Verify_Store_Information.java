package ApplaudoStudios.Tests;

import ApplaudoStudios.Base.BaseTest;
import org.testng.annotations.Test;

public class Verify_Store_Information extends BaseTest
{
    @Test(description = "Verify Store Information")
    public void verifyInformation()
    {
        String[] constraints = {properties.getProperty("street"),properties.getProperty("phone"),properties.getProperty("emailed")};
        softAssert.assertTrue(indexPage.checkFooterInformation(constraints));
        softAssert.assertAll();
    }
}
