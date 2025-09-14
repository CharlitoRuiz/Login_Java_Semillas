import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // ⏳ Espera implícita
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "datosLogin")
    public Object[][] obtenerDatosLogin() {
        return new Object[][]{
                {"tomsmith", "SuperSecretPassword!", "You logged into a secure area!", true},
                {"usuarioInvalido", "SuperSecretPassword!", "Your username is invalid!", false},
                {"tomsmith", "claveIncorrecta", "Your password is invalid!", false},
                {"", "", "Your username is invalid!", false}
        };
    }

    @Test(dataProvider = "datosLogin")
    public void loginExitoso(String usuario, String clave, String mensajeEsperado, boolean loginExitoso) {
        loginPage.ingresarUsuario(usuario);
        loginPage.ingresarContrasena(clave);
        loginPage.hacerLogin();

        String mensaje = loginPage.obtenerMensaje();
        Assert.assertTrue(mensaje.contains(mensajeEsperado),
                "El mensaje de login exitoso no es el esperado.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
