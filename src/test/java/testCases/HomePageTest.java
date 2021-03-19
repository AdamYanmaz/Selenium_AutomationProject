package testCases;



import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageObjects;
import utilityPackage.Driver;


public class HomePageTest {
    HomePageObjects homePageObject = new HomePageObjects();

    @BeforeMethod
    public void setUp() {

        String path = this.getClass().getResource("/index.html").getPath();
        Driver.getDriver().get("file://" + path);
    }

    @Test
    public void Test1() {
        //Assert that email address input is displayed.
        Assert.assertTrue( homePageObject.getEmailAddressInput().isDisplayed());
        //Assert that password input is displayed.
        Assert.assertTrue( homePageObject.getPasswordInput().isDisplayed());
        //Assert that sign in button is displayed.
        Assert.assertTrue( homePageObject.getSignInButton().isDisplayed());

        homePageObject.enterEmail("Adam.yanmaz@gmail.com");
        homePageObject.enterPassword("A@#11556b!");
        homePageObject.clickSignIn();

    }

    @Test
    public void Test2() {
        int expectedNumberOfValues = 3;
        String expectedSecondItemValue = "List Item 2";
        String expectedSecondItemBadgeValue = "6";
//Assert that there are three values in the list group
        Assert.assertEquals( expectedNumberOfValues, homePageObject.getListGroupElements().size());
        //Assert that the second list item's value is set to List Item 2.
        Assert.assertTrue( homePageObject.getListItemValues(2).contains(expectedSecondItemValue));
        //Assert that the second list item's badge value is 6.
        Assert.assertEquals( homePageObject.getListItemBadgeValues(2), expectedSecondItemBadgeValue);
    }

    @Test
    public void Test3() {
        Driver.scrollIntoMiddleView(homePageObject.getDefaultValue());
        //Assert that Option 1 is the default selected value.
        Assert.assertTrue( homePageObject.getDefaultValue().isDisplayed());
        homePageObject.clickOptionThree();
    }

    @Test
    public void Test4() {
        Driver.scrollIntoMiddleView(homePageObject.getFirstButton());
        //Assert that the first button is enabled.
        Assert.assertTrue( homePageObject.getFirstButton().isDisplayed());
        //Assert that the second button is disabled.
        Assert.assertTrue( homePageObject.getSecondButton().isDisplayed());

    }

    @Test
    public void Test5() {

        homePageObject.clickButton();
        Driver.scrollIntoMiddleView(homePageObject.getTest5Button());
        //Assert that the success message has appeared.
        Assert.assertTrue( homePageObject.getSuccessMessage().isDisplayed());
        //Assert that the button is disabled.
        Assert.assertTrue( homePageObject.verifyDisabledButton().isDisplayed());
    }

    @Test
    public void Test6() {
        Driver.scrollIntoMiddleView(homePageObject.getTest06Table().getContainer());
        String value = homePageObject.getTest06Table().getCellText(2, 2);
        String expectedValue = "Ventosanzap";
        //Assert that the value of the cell is "Ventosanzap"
        Assert.assertEquals(expectedValue, value);
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}
