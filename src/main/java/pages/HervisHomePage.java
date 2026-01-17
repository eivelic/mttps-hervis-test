package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HervisHomePage extends BasePage {

    public HervisHomePage(WebDriver driver) {
        super(driver);
    }

    // ===== LOKATORI (Poboljšani) =====
    private By cookieBtn = By.id("onetrust-accept-btn-handler");
    private By logo = By.cssSelector("a.logo, img[alt*='Hervis']");
    private By searchInput = By.xpath("//input[@data-tosca='searchBox'] | //input[@placeholder='Tražim...']");

    // Na Hervisu je košarica često unutar cx-mini-cart komponente
    private By cartIcon = By.xpath("//cx-mini-cart//a | //a[contains(@class, 'minicart')]");
    private By saleLink = By.xpath("//a[contains(@href,'sale') or contains(@href,'outlet')]");
    private By storeLink = By.xpath("//a[contains(@href,'/trgovine')] | //a[contains(.,'Trgovine')]");

    // ===== METODE =====

    public void prihvatiKolacice() {
        try {
            Thread.sleep(2000);
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cookieBtn));
            btn.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Kolačići se nisu pojavili.");
        }
    }

    public void pretrazi(String pojam) throws InterruptedException {
        WebElement s = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        s.clear();
        s.sendKeys(pojam);
        Thread.sleep(1000); // Ključno: dajemo browseru vremena
        s.sendKeys(Keys.ENTER);
        Thread.sleep(4000); // Čekamo da se rezultati učitaju
    }

    // Test 3: Idi u košaricu (Zalando stil: direktan link ako klik zapne)
    public void idiUKosaricu() throws InterruptedException {
        try {
            // Prvo pokušavamo kliknuti kao korisnik
            WebElement kosarica = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", kosarica);
            Thread.sleep(3000);
        } catch (Exception e) {
            // Ako klik ne odvede na /cart, idemo direktno (Fallback)
            driver.get("https://www.hervis.hr/shop/cart");
            Thread.sleep(3000);
        }
    }

    public void klikniAkcije() throws InterruptedException {
        siguranKlik(saleLink);
        Thread.sleep(3000);
    }

    public void klikniTrgovine() throws InterruptedException {
        try {
            WebElement trgovine = wait.until(ExpectedConditions.presenceOfElementLocated(storeLink));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trgovine);
            Thread.sleep(3000);
        } catch (Exception e) {
            driver.get("https://www.hervis.hr/shop/trgovine");
            Thread.sleep(3000);
        }
    }

    public boolean isLogoVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).isDisplayed();
        } catch (Exception e) { return false; }
    }
}