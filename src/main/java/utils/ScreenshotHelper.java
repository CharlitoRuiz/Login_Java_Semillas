package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {

    public static void capturarPantalla(WebDriver driver, String nombreBase) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File origen = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreArchivo = "screenshot_" + nombreBase + "_" + timestamp + ".png";

        File destino = new File("screenshots/" + nombreArchivo);
        destino.getParentFile().mkdirs(); // crea la carpeta si no existe

        try {
            Files.copy(origen.toPath(), destino.toPath());
            System.out.println("📸 Captura de pantalla guardada: " + destino.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("❌ No se pudo guardar la captura: " + e.getMessage());
        }
    }
}
