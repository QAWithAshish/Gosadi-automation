package browserFactory;

import logger.LOG;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverInit {

    public static String browserType;
    private static WebDriver driver;
    public static String applicationUrl = System.getProperty("app.url");

    public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver(String browserType) {
        if (DriverInit.browserType == null) {
            DriverInit.browserType = browserType;
        }
        if (driver == null) {
            switch (browserType) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    HashMap<String, Object> chromePref = new HashMap<String, Object>();
                    chromePref.put("profile.default_content_settings.popups", 0);
                    chromePref.put("credentials_enable_service", false);
                    chromePref.put("profile.password_manager_enabled", false);
                    chromePref.put("download.default_directory", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources");
                    chromePref.put("browser.set_download_behavior", "{ behavior: 'allow' , downloadPath: '" + System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + "'}");
                    options.addArguments("--headless=new");
                    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                    options.addArguments("--disable-notifications");
                    options.addArguments("window-size=1200x600");
                    options.addArguments("--disable-popup-blocking");
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--disable-web-security");
                    options.addArguments("--allow-running-insecure-content");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--no-sandbox", "--disable-gpu", "--disable-software-rasterizer");
                    options.addArguments("--ignore-ssl-errors=yes");
                    options.addArguments("--ignore-certificate-errors");
                    options.setExperimentalOption("prefs", chromePref);
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                    LOG.INFO("Launching browser");
                    driver.get(applicationUrl);
                    LOG.INFO("Running URl :: " + applicationUrl);
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    LOG.INFO("Launching browser");
                    driver.get(applicationUrl);
                    LOG.INFO("Running URl :: " + applicationUrl);
                    break;
                case "safari":
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                    LOG.INFO("Launching browser");
                    driver.get(applicationUrl);
                    LOG.INFO("Running URl :: " + applicationUrl);

                    break;
                case "edge":
                    break;
            }
        }
        tDriver.set(driver);
        return driver;
    }

    public static void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                tDriver.remove();
                LOG.INFO("Quit Driver");
            }
        } catch (Exception e) {
            LOG.INFO("An error occurred during teardown: " + e.getMessage());
        } finally {
            driver = null;
        }
    }

    public static synchronized WebDriver getDriver() {
        return tDriver.get();
    }
}
