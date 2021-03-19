package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilityPackage.Driver;
import utilityPackage.TableHelper;

import java.util.List;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class HomePageObjects {
    private static final By EMAIL_ADDRESS_INPUT = id("inputEmail");
    private static final By PASSWORD_INPUT = id("inputPassword");
    private static final By SIGN_IN_BUTTON = xpath("//button[@type='submit']");
    private static final By LIST_GROUP = xpath("//ul[@class='list-group']/li");
    private static final By LIST_GROUP_BADGE = xpath("./span");
    private static final By DROPDOWN_LIST = xpath("//*[@id='dropdownMenuButton']");
    private static final By OPTION_THREE = xpath("//a[text()='Option 3']");
    private static final By BUTTON_ENABLED = xpath("//button[@class='btn btn-lg btn-primary']");
    private static final By BUTTON_DISABLED = xpath("//button[@class='btn btn-lg btn-secondary']");
    private static final By BUTTON = xpath("//*[@id='test5-button']");
    private static final By SUCCESS_MESSAGE = xpath("//*[@id='test5-alert']");
    private static final By TABLE_CLASS_NAME = xpath("//*[@id='test-6-div']/div/table");


    public void enterEmail(String email) {
        Driver.getDriver().findElement(EMAIL_ADDRESS_INPUT).sendKeys(email);
    }

    public void enterPassword(String password) {
        Driver.getDriver().findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clickSignIn() {
        Driver.getDriver().findElement(SIGN_IN_BUTTON).click();
    }

    public WebElement getEmailAddressInput() {
        return Driver.getDriver().findElement(EMAIL_ADDRESS_INPUT);
    }

    public WebElement getPasswordInput() {
        return Driver.getDriver().findElement(PASSWORD_INPUT);
    }

    public WebElement getSignInButton() {
        return Driver.getDriver().findElement(SIGN_IN_BUTTON);
    }

    public List<WebElement> getListGroupElements() {
        return Driver.getDriver().findElements(LIST_GROUP);
    }

    public String getListItemValues(int whichList) {
        return getListGroupElements().get(whichList - 1).getText();
    }

    public String getListItemBadgeValues(int whichList) {
        return getListGroupElements().get(whichList - 1).findElement(LIST_GROUP_BADGE).getText();
    }

    public WebElement getDefaultValue() {
        return Driver.getDriver().findElement(DROPDOWN_LIST);
    }

    public void clickOptionThree() {
        Driver.getDriver().findElement(DROPDOWN_LIST).click();
        Driver.getDriver().findElement(OPTION_THREE).click();
    }

    public WebElement getFirstButton() {
        return Driver.getDriver().findElement(BUTTON_ENABLED);
    }

    public WebElement getSecondButton() {
        return Driver.getDriver().findElement(BUTTON_DISABLED);
    }

    public WebElement getTest5Button() {
        return Driver.getDriver().findElement(BUTTON);
    }

    public void clickButton() {
        Driver.wait(BUTTON);
        Driver.getDriver().findElement(BUTTON).click();
    }

    public WebElement getSuccessMessage() {
        return Driver.getDriver().findElement(SUCCESS_MESSAGE);
    }

    public WebElement verifyDisabledButton() {
        return Driver.getDriver().findElement(BUTTON);
    }

    public TableHelper getTest06Table() {
        return new TableHelper(Driver.getDriver().findElement(TABLE_CLASS_NAME));
    }
}
