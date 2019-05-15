package se.thinkcode.cukenfest.steps.adapters;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import se.thinkcode.cukenfest.todolist.Database;
import se.thinkcode.cukenfest.todolist.InMemoryDatabase;
import se.thinkcode.cukenfest.todolist.SqlDatabase;

import java.net.URL;

public class AdapterFactory {

    public TodoAdapter getAdapter() {
        Database database = getDatabase();

        Seam seam = getSeam();

        if (seam.equals(new Seam("model"))) {
            return new ModelAdapter(database);
        }

        if (seam.equals(new Seam("rest"))) {
            return new RestAdapter(database);
        }

        if (seam.equals(new Seam("web"))) {
            WebDriver browser = getBrowser();
            return new WebAdapter(browser, database);
        }

        System.err.println("No seam, database, browser, device, or client specified. Will default to model in memory");
        return new ModelAdapter(database);
    }

    private Database getDatabase() {
        DatabaseSeam database = getDatabaseName();

        if (database.equals(new DatabaseSeam("in-memory"))) {
            return new InMemoryDatabase();
        }

        if (database.equals(new DatabaseSeam("sql"))) {
            return new SqlDatabase();
        }

        return new InMemoryDatabase();
    }

    private WebDriver getBrowser() {
        BrowserSeam browserName = getBrowserName();

        if (browserName.equals(new BrowserSeam("headless"))) {
            return createHeadlessDriver();
        }

        if (browserName.equals(new BrowserSeam("firefox"))) {
            return createFirefoxDriver();
        }

        if (browserName.equals(new BrowserSeam("chrome"))) {
            return createChromeDriver();
        }

        throw new RuntimeException("Unknown browser: <" + browserName + ">");
    }

    private Seam getSeam() {
        if (System.getProperty("seam") != null) {
            String seam = System.getProperty("seam");
            return new Seam(seam);
        }

        String defaultSeam = "model";
        System.err.println("No seam specified. Specify with -Dseam=<your seam name> Will default to " + defaultSeam);
        return new Seam(defaultSeam);
    }

    private DatabaseSeam getDatabaseName() {
        if (System.getProperty("database") != null) {
            String database = System.getProperty("database");
            return new DatabaseSeam(database);
        }

        String defaultDatabase = "in-memory";
        System.err.println("No database specified. Specify with -Ddatabase=<your database name> Will default to " + defaultDatabase);
        return new DatabaseSeam(defaultDatabase);
    }

    private BrowserSeam getBrowserName() {
        if (System.getProperty("browser") != null) {
            String browser = System.getProperty("browser");
            return new BrowserSeam(browser);
        }

        String defaultBrowser = "html-unit";
        System.err.println("No browser specified. Specify with -Dbrowser=<your database name> Will default to " + defaultBrowser);

        return new BrowserSeam(defaultBrowser);
    }

    private WebDriver createHeadlessDriver() {
        return new HtmlUnitDriver(true);
    }

    private WebDriver createFirefoxDriver() {
        String osName = SystemUtils.OS_NAME;
        URL url = getClass().getResource("/" + "osx" + "/geckodriver");
        String geckoDriverPath = url.getFile();
        System.setProperty("webdriver.gecko.driver", geckoDriverPath);

        return new FirefoxDriver();
    }

    private WebDriver createChromeDriver() {
        URL url = getClass().getResource("/" + "osx" + "/chromedriver");
        String chromedDriverPath = url.getFile();
        System.setProperty("webdriver.chrome.driver", chromedDriverPath);

        return new ChromeDriver();
    }
}
