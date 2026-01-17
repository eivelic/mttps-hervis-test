import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HervisHomePage;

public class HervisTest {

    WebDriver driver;
    HervisHomePage homePage;

    // Postavljamo Firefox kao prvi/zadani izbor
    String browser = "firefox";

    @BeforeMethod
    public void setup() {
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new org.openqa.selenium.firefox.FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            driver = new org.openqa.selenium.chrome.ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new org.openqa.selenium.edge.EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.get("https://www.hervis.hr/shop/");
        homePage = new HervisHomePage(driver);
        homePage.prihvatiKolacice();
    }

    @Test(priority = 1)
    public void test01_LogoVisible() {
        Assert.assertTrue(homePage.isLogoVisible(), "Logo nije vidljiv!");
    }

    @Test(priority = 2)
    public void test02_PretragaTenisice() throws InterruptedException {
        homePage.pretrazi("tenisice");
        // Provjera URL-a nakon što je Thread.sleep u pretrazi odradio svoje
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        Assert.assertTrue(currentUrl.contains("tenisice"), "URL ne sadrži pojam. URL: " + currentUrl);
    }

    @Test(priority = 3)
    public void test03_KosaricaStranica() throws InterruptedException {
        homePage.idiUKosaricu();
        // Umjesto provjere overlay-a koji je nestabilan, provjeravamo URL košarice
        Assert.assertTrue(driver.getCurrentUrl().contains("cart") || driver.getCurrentUrl().contains("kosarica"));
    }

    @Test(priority = 4)
    public void test04_Akcije() throws InterruptedException {
        homePage.klikniAkcije();
        Assert.assertTrue(driver.getCurrentUrl().contains("sale") || driver.getCurrentUrl().contains("outlet"));
    }

    @Test(priority = 5)
    public void test05_Trgovine() throws InterruptedException {
        homePage.klikniTrgovine();
        Assert.assertTrue(driver.getCurrentUrl().contains("trgovine"));
    }

    @Test(priority = 6)
    public void test06_LoginStranica() throws InterruptedException {
        driver.get("https://www.hervis.hr/shop/login");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test(priority = 7)
    public void test07_NewsletterFooter() throws InterruptedException {
        homePage.scrollDown();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getPageSource().contains("Newsletter"));
    }

    @Test(priority = 8)
    public void test08_DrustveneMreze() throws InterruptedException {
        homePage.scrollDown();
        Thread.sleep(2000);
        boolean socialPresent = driver.getPageSource().contains("facebook.com") ||
                driver.getPageSource().contains("instagram.com");
        Assert.assertTrue(socialPresent, "Društvene mreže nisu pronađene u footeru.");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}