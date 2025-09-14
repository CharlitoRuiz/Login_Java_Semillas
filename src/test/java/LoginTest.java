import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import java.time.Duration;
import utils.ScreenshotHelper;
import org.testng.ITestResult;
import utils.ConfigReader;


public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.get("timeout")))); // ⏳ Espera implícita
        driver.get(ConfigReader.get("url"));
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "datosLogin")
    public Object[][] obtenerDatosLogin() {
        return new Object[][]{
                {ConfigReader.get("usuarioValido"), ConfigReader.get("claveValida"), "You logged into a secure area!", true},
                {ConfigReader.get("usuarioInvalido"), ConfigReader.get("claveValida"), "Your username is invalid!", false},
                {ConfigReader.get("usuarioValido"), ConfigReader.get("claveInvalida"), "Your password is invalid!", false},
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
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotHelper.capturarPantalla(driver, result.getName());
        }
        driver.quit();
    }
}
