package utilities;

import com.github.javafaker.Faker;
import logger.LOG;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static utilities.CommonUtils.holdForElement;

public class DriverUtils {
    public static final int shortTimeOut = 5;
    public static final int second10TimeOut = 10;
    protected static WebDriver driver;

    public DriverUtils(WebDriver driver) {
        this.driver = driver;
    }



    public void clickOnWebElement(WebElement element) {
        waitForElement(driver,element,Duration.ofSeconds(5));
        Assert.assertTrue(iselementVisible(element, 5));
        iselementClickable(element,second10TimeOut);
        element.click();
    }

    public void sendKeys(WebElement elementLocator, String data) {
        Assert.assertTrue(iselementVisible(elementLocator, 50));
        elementLocator.sendKeys(data);
    }

    public void sendKeysWithActions(WebElement elementLocator, String data) {
        Assert.assertTrue(iselementVisible(elementLocator, 50));
        Actions actions = new Actions(driver);
        actions.sendKeys(elementLocator, data).build().perform();
    }
    public String getText(WebElement elementLocator) {
        Assert.assertTrue(iselementVisible(elementLocator, 20));
        return elementLocator.getText();
    }

    public String getAttributeValue(WebElement element) {
        Assert.assertTrue(iselementVisible(element));
        return element.getAttribute("value");
    }

    public void switchToTabByIndex(WebDriver driver, int tabIndex) {
        try {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

            if (tabIndex >= 0 && tabIndex < tabs.size()) {
                driver.switchTo().window(tabs.get(tabIndex));
            } else {
                System.out.println("Invalid tab index: " + tabIndex);
            }
        } catch (Exception e) {
            LOG.Reporter(e.getMessage());
        }
    }


    public WebElement waitForElementVisible(WebElement element, int timeout) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ex) {
            LOG.Reporter(ex.getMessage());
            throw new RuntimeException("Unable to find element" + ex.getMessage());
        }
    }

    public boolean iselementVisible(WebElement element, int timeout) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception ex) {
            LOG.INFO(ex.getMessage());
            return false;
        }

    }
    public static WebElement waitForElement(WebDriver driver, WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e) {
            return retryWaitingForElement(driver, element, timeout);
        }
    }

    private static WebElement retryWaitingForElement(WebDriver driver, WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new RuntimeException("Element could not be located even after retrying.", e);
        }
    }
    public boolean iselementEnable(WebElement element, int timeout) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.visibilityOf(element)).isEnabled();
        } catch (Exception ex) {
            LOG.INFO(ex.getMessage());
            return false;
        }

    }

    public void clickByAction(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).click().build().perform();
    }


    public void typeByAction(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).sendKeys().build().perform();
    }

    public void clickByJSE(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    public void waitForVisibility(WebElement e) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void clear(WebElement element) {
        waitForVisibility(element);
        element.clear();
    }
    public void clearWithJavaScript(WebElement element) {
        waitForVisibility(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=''", element);
    }

    public WebElement iselementClickable(WebElement element, int timeout) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception ex) {
            LOG.INFO(ex.getMessage());
            throw new RuntimeException("element not clickable" + ex.getMessage());
        }
    }

    public boolean iselementVisible(WebElement element) {
        return iselementVisible(element, shortTimeOut);
    }

    public void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
        Assert.assertTrue(iselementVisible(ele, 5));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", ele);
    }

    public void scrollByElement(WebDriver driver, WebElement ele) {
        waitForElementVisible(ele, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", ele);
        int scrollHeight = (int) js.executeScript("return document.body.scrollHeight");
        js.executeScript("window.scrollTo(0, " + scrollHeight + ")");
    }

    public void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public boolean selectByVisibleText(String visibleText, WebElement element) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(visibleText);
            LOG.Reporter("Option selected by VisibleText: " + visibleText);
            return true;
        } catch (Exception e) {
            LOG.Reporter("Option not selected by VisibleText: " + visibleText);
            return false;
        }
    }
    public static boolean selectByIndex(int index, WebElement element) {
        try {
            Select select = new Select(element);
            select.selectByIndex(index);
            LOG.Reporter("Option selected by Index: " + index);
            return true;
        } catch (Exception e) {
            LOG.Reporter("Option not selected by Index: " + index);
            return false;
        }
    }

    public void printDropdownOptions(WebElement ele) {
        Select s = new Select(ele);
        List<WebElement> options = s.getOptions();
        for (WebElement option : options) {
            LOG.Reporter("Option Are : " + option.getText());
        }
    }
    public void printDropdownOptions(List<WebElement> options) {
        for (WebElement option : options) {
            LOG.Reporter("Option Are : " + option.getText());
        }
    }


    public boolean isMultiSelectByVisibleText(String visibletext, WebElement ele) {
        boolean flag = false;
        try {
            Select s = new Select(ele);
            boolean isMultiSelect = s.isMultiple();
            if (isMultiSelect) {
                List<WebElement> options = s.getOptions();

                for (WebElement option : options) {
                    if (option.getText().equals(visibletext)) {
                        option.click();
                        flag = true;
                        break;
                    }
                }

            } else {
                s.selectByVisibleText(visibletext);
                flag = true;
            }

            return flag;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                LOG.Reporter("Option selected by VisibleText");
            } else {
                LOG.Reporter("Option not selected by VisibleText");
            }
        }
    }


    public static boolean selectByValue(String value, WebElement element) {
        try {
            Select select = new Select(element);
            select.selectByValue(value);
            LOG.Reporter("Option selected by Value: " + value);
            return true;
        } catch (Exception e) {
            LOG.Reporter("Option not selected by Value: " + value);
            return false;
        }
    }

    public void generateRandomCharacter(WebElement element) {
        String randomAlphabet = generateRandomAlphabet(4);
        sendKeys(element, randomAlphabet);
    }

    public String generateRandomAlphabet(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomAlphabet = (char) (random.nextInt(26) + 'a');
            sb.append(randomAlphabet);
        }
        return sb.toString();
    }

    public String generateRandomEmail() {
        Faker faker = new Faker();
        String username = faker.name().username();
        String domain = "appinventiv.com";
        String randomEmail = username + "@" + domain;
        return randomEmail;
    }

    public static void dragAndDropFile(WebElement target, String filePath) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(target)
                .moveToElement(target)
                .release()
                .build()
                .perform();

        String jsScript = "arguments[0].style.opacity='1'; arguments[0].style.display='block';";
        ((JavascriptExecutor) driver).executeScript(jsScript, target);

        target.sendKeys(filePath);
    }
    public void clickOutsideTheField() {
        Actions action = new Actions(driver);
        action.moveByOffset(0, 0)
                .click()
                .build()
                .perform();
    }

    public static void safeClick(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {
            handleInterceptedException(driver, element);
        }
    }

    private static void handleInterceptedException(WebDriver driver, WebElement element) {
        LOG.Reporter("Element click intercepted. Retrying after waiting...");
        holdForElement(1);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }
    public void hoverByAction(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }



}
