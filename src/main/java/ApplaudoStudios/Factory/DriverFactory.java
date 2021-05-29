package ApplaudoStudios.Factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory
{
    private Properties properties;
    private OptionsManager optionsManager;
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public WebDriver initDriver(Properties properties)
    {
        optionsManager = new OptionsManager(properties);
        String typeOfBrowser = properties.getProperty("browser").trim();
        if(typeOfBrowser.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        }else if (typeOfBrowser.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        }else System.out.println("Invalid name for browser" + typeOfBrowser);
        return getDriver();
    }


    public WebDriver getDriver()
    {
        return driver.get();
    }

    public Properties initProperties() throws FileNotFoundException {
        FileInputStream file = new FileInputStream("src/test/java/ApplaudoStudios/Resources/config.properties");
        properties = new Properties();
        try
        {
            properties.load(file);
        }catch (FileNotFoundException error)
        {
            System.out.println("File not found");
            error.printStackTrace();
        }catch (IOException error)
        {
            error.printStackTrace();
        }
        return properties;
    }

}
