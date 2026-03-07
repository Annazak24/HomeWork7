package ru.otus.extensions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jspecify.annotations.NullMarked;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.openqa.selenium.WebDriver;
import ru.otus.factory.AndroidDriverFactory;
import ru.otus.factory.AndroidDriverModule;

@NullMarked
public class AndroidExtension
        implements TestInstancePostProcessor,
        BeforeEachCallback,
        AfterTestExecutionCallback,
        AfterEachCallback {

    private final Injector injector =
            Guice.createInjector(new AndroidDriverModule());

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        injector.injectMembers(testInstance);;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        WebDriver driver = injector.getInstance(WebDriver.class);
        WebDriverRunner.setWebDriver(driver);
        Selenide.open();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        try {
            Runtime.getRuntime().exec("cmd /c adb logcat -d > logcat.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterEach(ExtensionContext context) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        injector.getInstance(AndroidDriverFactory.class).quit(driver);
    }
}
