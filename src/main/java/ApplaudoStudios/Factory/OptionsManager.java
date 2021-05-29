package ApplaudoStudios.Factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager
{
    private Properties properties;
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;

    public OptionsManager(Properties properties)
    {
        this.properties = properties;
    }

    public ChromeOptions getChromeOptions()
    {
        chromeOptions = new ChromeOptions();
        if(Boolean.parseBoolean(properties.getProperty("incognito").trim())) chromeOptions.addArguments("--incognito");
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions()
    {
        firefoxOptions = new FirefoxOptions();
        if(Boolean.parseBoolean(properties.getProperty("incognito").trim())) firefoxOptions.addArguments("--incognito");
        return firefoxOptions;
    }
}
