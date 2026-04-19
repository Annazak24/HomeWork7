package ru.otus.factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
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
      emulatorProvider.takeAndGet();

      try {
         AndroidDriver driver = new AndroidDriver(
               new URL("http://android:4723"),
               capabilities
         );

         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
         return driver;
      } catch (MalformedURLException e) {
         emulatorProvider.putBack();
         throw new DriverInitializationException("Invalid Appium server URL", e);
      } catch (Exception e) {
         emulatorProvider.putBack();
         throw new DriverInitializationException("Failed to initialize Android driver", e);
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