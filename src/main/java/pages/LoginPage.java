package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    WebDriver driver;

    // Selectores
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By flashMessage = By.id("flash");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
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

    public String obtenerMensaje() {
        return driver.findElement(flashMessage).getText();
    }
}
