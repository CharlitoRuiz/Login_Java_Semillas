package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitHelper {

    private WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        // ⏳ Espera explícita de 10 segundos
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement esperarElementoVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement esperarElementoClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean esperarTextoPresente(By locator, String texto) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, texto));
    }
}
