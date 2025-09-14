import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginExitoso() {
        loginPage.ingresarUsuario("tomsmith");
        loginPage.ingresarContrasena("SuperSecretPassword!");
        loginPage.hacerLogin();

        String mensaje = loginPage.obtenerMensaje();
        Assert.assertTrue(mensaje.contains("You logged into a secure area!"),
                "El mensaje de login exitoso no es el esperado.");
        Assert.assertTrue(mensaje.toLowerCase().contains("logged into"), "Mensaje inesperado");
    }

    @Test
    public void loginFallido() {
        loginPage.ingresarUsuario("usuarioIncorrecto");
        loginPage.ingresarContrasena("claveIncorrecta");
        loginPage.hacerLogin();

        String mensaje = loginPage.obtenerMensaje();
        Assert.assertTrue(mensaje.contains("Your username is invalid!"),
                "El mensaje de error no es el esperado.");

        //boolean mensajeExitoVisible = driver.findElement(By.cssSelector(".flash.success")).isDisplayed();
        //Assert.assertFalse(mensajeExitoVisible, "El mensaje de login exitoso no debería mostrarse");

        boolean mensajeExitoPresente = driver.findElements(By.cssSelector(".flash.success")).size() > 0;
        Assert.assertFalse(mensajeExitoPresente, "El mensaje de éxito no debería estar presente tras login fallido.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
