package ru.otus.extensions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.appium.java_client.android.AndroidDriver;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import ru.otus.factory.AndroidDriverFactory;
import ru.otus.factory.AndroidDriverModule;
import ru.otus.utils.LogcatManager;

@NullMarked
public class AndroidExtension
      implements TestInstancePostProcessor,
      BeforeEachCallback,
      AfterTestExecutionCallback,
      AfterEachCallback {

   private final Injector injector = Guice.createInjector(new AndroidDriverModule());
   private final LogcatManager logcatManager = injector.getInstance(LogcatManager.class);

   @Override
   public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
      injector.injectMembers(testInstance);
   }

   @Override
   public void beforeEach(ExtensionContext context) {
      WebDriver driver = injector.getInstance(WebDriver.class);
      WebDriverRunner.setWebDriver(driver);
      Selenide.open();
   }

   @Override
   public void afterTestExecution(ExtensionContext context) {
      AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
      logcatManager.saveLogcat(driver, context);
   }

   @Override
   public void afterEach(ExtensionContext context) {
      WebDriver driver = WebDriverRunner.getWebDriver();
      injector.getInstance(AndroidDriverFactory.class).quit(driver);
   }
}