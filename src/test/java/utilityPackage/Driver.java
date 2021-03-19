package utilityPackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public synchronized static WebDriver getDriver(String browser) {
        // String browser ==>  it originally comes from xml file to test base class, from test base it comes here
        if (driver == null) {
            // first we check if the value from xml file is null or not
            // if the value from xml file NOT null we use
            // the value from xml file IS null, we get the browser from properties file

            browser = browser == null ? ConfigurationReader.getProperty("browser") : browser;

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "chromeHeadless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "firefoxHeadless":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    break;

                case "ie":
                    if (System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("You are operating Mac OS which doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    if (System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("You are operating Windows OS which doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;
                default:
                    throw new RuntimeException("Illegal browser type!");
            }
        }
        return driver;
    }

    public static WebDriver getDriver() {

        return getDriver(null);
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static Object executeJavaScriptFunctionOnPage(String scriptToExecute, WebElement elementToRunScriptAgainst) {
        Object res = null;

        try {
            if (Driver.getDriver() instanceof JavascriptExecutor) {
                JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
                if (elementToRunScriptAgainst != null) {
                    res = js.executeScript(scriptToExecute, new Object[]{elementToRunScriptAgainst});
                } else {
                    res = js.executeScript(scriptToExecute, new Object[0]);
                }

                if (res != null) {
                    System.out.println(" -- Javascript executed successfully: " + res.toString());
                }
            } else {
                System.out.println("The driver you passed as parameter to function executeJavaScriptFunctionOnPage cannot run JavaScript, because the executor is not instanciated. Make sur that your driver allows you and is capabale of running java script.");
            }
        } catch (StaleElementReferenceException var5) {
            System.out.println("ERROR in function executeJavaScriptFunctionOnPage: StaleElementException");
        } catch (Exception var6) {
            System.out.println("ERROR in function executeJavaScriptFunctionOnPage:: " + var6.getMessage() + "\r\n" + var6.getLocalizedMessage() + "\r\n" + var6.toString());
        }

        return res;
    }


    public static void scrollIntoMiddleView(WebElement pElement) {
        System.out.println("Scroll scroll into middle view [" + pElement.getText() + "].");
        executeJavaScriptFunctionOnPage("window.scrollTo(0,(arguments[0].getBoundingClientRect().top + window.pageYOffset) - (window.innerHeight / 2));", pElement);
    }

    public static void wait(By byField) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Integer.parseInt(ConfigurationReader.getProperty("explicitWait")));
        wait.until(ExpectedConditions.elementToBeClickable(byField));
    }


}
