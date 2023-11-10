import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager; // Import WebDriverManager
import com.qa.util.Utilclass;

public class BaseTestClass {
    public static WebDriver driver;
    public static Properties prop;

    public BaseTestClass() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream(Utilclass.config_path);
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        String browsername = prop.getProperty("browser");
        if (browsername.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup(); // Use WebDriverManager to set up ChromeDriver
            driver = new ChromeDriver();
        } else if (browsername.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup(); // Use WebDriverManager to set up FirefoxDriver
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Utilclass.Page_Load_TimeOut, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Utilclass.Implicit_Wait, TimeUnit.SECONDS);
        driver.get("https://www.demoblaze.com/index.html"); // Update the URL here
    }
}
