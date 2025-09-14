import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver = new ChromeDriver();

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void loginExitoso() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Test
    public void loginFallido() {
        driver.findElement(By.id("username")).sendKeys("usuarioIncorrecto");
        driver.findElement(By.id("password")).sendKeys("claveIncorrecta");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
