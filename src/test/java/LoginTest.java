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
import com.aventstack.extentreports.*;
import utils.ExtentManager;
import java.lang.reflect.Method;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    ExtentTest test;

    @BeforeMethod
    public void setUp(Method method) {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.get("timeout")))); // ⏳ Espera implícita
        driver.get(ConfigReader.get("url"));
        loginPage = new LoginPage(driver);

        // Iniciar reporte por método
        test = ExtentManager.createTest(method.getName());
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
        try {
            loginPage.ingresarUsuario(usuario);
            loginPage.ingresarContrasena(clave);
            loginPage.hacerLogin();

            String mensaje = loginPage.obtenerMensaje();
            test.info("Mensaje recibido: " + mensaje);
            Assert.assertTrue(mensaje.contains(mensajeEsperado),
                    "El mensaje de login exitoso no es el esperado.");
            test.pass("Validación exitosa");
        } catch (Exception e) {
            test.fail("Test fallido con excepción: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String ruta = ScreenshotHelper.capturarYDevolverRuta(driver, result.getName());

            if (ruta != null) {
                test.fail("❌ Falló el test con excepción: " + result.getThrowable());
                test.addScreenCaptureFromPath(ruta);  // ✅ ADJUNTAR EN REPORTE
            }
        }
        driver.quit();
    }

    @AfterSuite
    public void cerrarReporte() {
        ExtentManager.flush();
    }
}
