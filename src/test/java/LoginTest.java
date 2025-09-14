import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginExitoso() {
        loginPage.ingresarUsuario("tomsmith");
        loginPage.ingresarContrasena("SuperSecretPassword!");
        loginPage.hacerLogin();
    }

    @Test
    public void loginFallido() {
        loginPage.ingresarUsuario("usuarioIncorrecto");
        loginPage.ingresarContrasena("claveIncorrecta");
        loginPage.hacerLogin();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
