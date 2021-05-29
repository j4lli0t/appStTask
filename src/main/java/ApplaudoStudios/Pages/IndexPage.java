package ApplaudoStudios.Pages;

import ApplaudoStudios.Utils.Wrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class IndexPage
{
    private Wrappers element;
    private WebDriver driver;

    /*Elements*/
    private final By singInButton = By.linkText("Sign in");
    private final By inputUser = By.id("email");
    private final By inputPasswd = By.id("passwd");
    private final By signInButton = By.id("SubmitLogin");
    private final By womanButton = By.xpath("//a[@title='Women']");
    private final By costumerLabel = By.xpath("//a[@title='View my customer account']");
    private final By selectedDress = By.xpath("//a[@class='product-name']");
    private final By addToCartButton = By.id("add_to_cart");
    private final By continueShoppingButton = By.xpath("//span[@title='Continue shopping']");
    private final By cartProducts = By.xpath("//a[@title='View my shopping cart']");
    private final By deleteButton = By.xpath("//a[@class='cart_quantity_delete']");
    private final By storeInformationLabel = By.xpath("//h4[text()='Store information']/following::ul//li");

    /*Initialize Driver*/
    public IndexPage(WebDriver driver)
    {
        this.driver = driver;
        element = new Wrappers(driver);
    }

    /*Actions in page */
    public void clickSingInButton()
    {
        element.doClick(singInButton);
    }

    public void doLogin(String user,String passwd)
    {
        element.doSendKeys(inputUser,user);
        element.doSendKeys(inputPasswd,passwd);
        element.doClick(signInButton);
        element.waitForElementPresent(costumerLabel,10).isDisplayed();
    }

    public void doShop(String dressType) throws InterruptedException {
        element.doClick(womanButton);
        element.doSelectOptionByText(selectedDress,dressType);
        element.doClick(addToCartButton);
        Thread.sleep(5000);
        element.waitForElementPresent(continueShoppingButton,10).click();
    }


    public Boolean verifyOrder(String text)
    {
        element.doClick(cartProducts);
        WebElement article = element.getElementByLinkText(text);
        return article != null;
    }

    public Boolean deleteItem(String item) throws InterruptedException {
        element.doClick(cartProducts);
        WebElement article = element.getElementByLinkText(item);
        if(article != null)
        {
            String attribute = driver.findElement(By.xpath("//table[@id='cart_summary']//a[text()='"+item+"']/ancestor::tr")).getAttribute("id");
            System.out.println(attribute);
            attribute = attribute.replace("product_", "");
            element.doClickELementBySpecificId(deleteButton,attribute);
            Thread.sleep(5000);
            return true;
        }
        System.out.println("Element is not found!");
        return false;
    }

    public Boolean checkFooterInformation(String[] text) throws InterruptedException {
        Boolean flag = false;
        List<WebElement> elements = driver.findElements(storeInformationLabel);
        element.goToFooter();
        for(int i = 0; i < elements.size(); i++)
        {
            if(Arrays.toString(text).contains(elements.get(i).getText()))
            {
                flag = true;
            }else {
                flag = false;
                break;
            }
        }
        Thread.sleep(3000);
        return flag;
    }
}
