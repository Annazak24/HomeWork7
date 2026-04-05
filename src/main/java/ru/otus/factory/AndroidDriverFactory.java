package ru.otus.factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import ru.otus.emulator.Emulator;
import ru.otus.emulator.EmulatorProvider;
import ru.otus.exceptions.DriverInitializationException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Singleton
public class AndroidDriverFactory {

   private final EmulatorProvider emulatorProvider;
   private final Capabilities capabilities;

   @Inject
   public AndroidDriverFactory(EmulatorProvider emulatorProvider, Capabilities capabilities) {
      this.emulatorProvider = emulatorProvider;
      this.capabilities = capabilities;
   }

   public WebDriver create() {
      Emulator emulator = emulatorProvider.takeAndGet();

      try {
         AndroidDriver driver =
               new AndroidDriver(
                     new URL("http://127.0.0.1:%d".formatted(emulator.getPort())),
                     capabilities
               );

         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
         return driver;
      } catch (MalformedURLException e) {
         emulatorProvider.putBack();
         throw new DriverInitializationException("Invalid Appium server URL", e);
      }
   }

   public void quit(WebDriver driver) {
      try {
         if (driver != null) {
            driver.quit();
         }
      } finally {
         emulatorProvider.putBack();
      }
   }
}