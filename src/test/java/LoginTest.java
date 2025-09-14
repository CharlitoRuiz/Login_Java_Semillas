import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        driver.quit();

        // Segundo intento - login fallido
        WebDriver driver2 = new ChromeDriver();
        driver2.get("https://the-internet.herokuapp.com/login");

        driver2.findElement(By.id("username")).sendKeys("usuarioIncorrecto");
        driver2.findElement(By.id("password")).sendKeys("claveIncorrecta");
        driver2.findElement(By.cssSelector("button[type='submit']")).click();

        driver2.quit();
    }
}
