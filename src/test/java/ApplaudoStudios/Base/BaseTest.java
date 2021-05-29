package ApplaudoStudios.Base;

import ApplaudoStudios.Factory.DriverFactory;
import ApplaudoStudios.Pages.IndexPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseTest
{
    /* Pages instances */

    DriverFactory driverFactory;
    public WebDriver driver;
    public Properties properties;
    protected IndexPage indexPage;
    protected SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void setUpDriver() throws FileNotFoundException
    {

        driverFactory = new DriverFactory();
        properties = driverFactory.initProperties();
        properties.setProperty("browser",properties.getProperty("browser").trim());
        driver = driverFactory.initDriver(properties);
        driver.manage().window().maximize();
        driver.get(properties.getProperty("url"));
        System.out.println(properties.getProperty("browser"));


        /*Init pages*/
        indexPage = new IndexPage(driver);

    }

    @AfterClass
    public void closeDriver()
    {
        driver.quit();
    }
}
