package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitHelper;


public class LoginPage {
    WebDriver driver;
    WaitHelper wait;

    // Selectores
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By flashMessage = By.id("flash");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);
    }


    // Acciones
    public void ingresarUsuario(String usuario) {
        driver.findElement(usernameField).sendKeys(usuario);
    }

    public void ingresarContrasena(String contrasena) {
        driver.findElement(passwordField).sendKeys(contrasena);
    }

    public void hacerLogin() {
        driver.findElement(loginButton).click();
    }

    // Metodo nuevo con espera explicita
    public String obtenerMensaje() {
        return wait.esperarElementoVisible(flashMessage).getText();
    }
}
