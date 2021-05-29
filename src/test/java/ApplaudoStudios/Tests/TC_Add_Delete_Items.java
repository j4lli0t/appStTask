package ApplaudoStudios.Tests;

import ApplaudoStudios.Base.BaseTest;
import org.testng.annotations.Test;

public class TC_Add_Delete_Items extends BaseTest
{

    @Test(description = "Add item to shopping cart")
    public void addItemToCart() throws Exception
    {
        indexPage.clickSingInButton();
        indexPage.doLogin(properties.getProperty("username"),properties.getProperty("passwd"));
        indexPage.doShop(properties.getProperty("dress-type"));
        softAssert.assertTrue(indexPage.verifyOrder(properties.getProperty("dress-type")));
        softAssert.assertAll();
    }

    @Test(description = "Delete item from shopping cart")
    public void deleteItemFromCart() throws InterruptedException {
        softAssert.assertTrue(indexPage.deleteItem(properties.getProperty("dress-type")));
        softAssert.assertAll();
    }
}
