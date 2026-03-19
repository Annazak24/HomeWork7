package ru.otus.extensions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.appium.java_client.android.AndroidDriver;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import ru.otus.factory.AndroidDriverFactory;
import ru.otus.factory.AndroidDriverModule;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@NullMarked
public class AndroidExtension
        implements TestInstancePostProcessor,
        BeforeEachCallback,
        AfterEachCallback {

    private final Injector injector =
            Guice.createInjector(new AndroidDriverModule());

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
    public void afterEach(ExtensionContext context) {
        WebDriver driver = WebDriverRunner.getWebDriver();

        try {
            saveLogcat(driver, context);
        } finally {
            injector.getInstance(AndroidDriverFactory.class).quit(driver);
        }
    }

    private void saveLogcat(WebDriver driver, ExtensionContext context) {
        if (!(driver instanceof AndroidDriver androidDriver)) {
            return;
        }

        try {
            LogEntries logEntries = androidDriver.manage().logs().get("logcat");

            Path logsDir = Path.of("logs");
            Files.createDirectories(logsDir);

            String testName = context.getRequiredTestMethod().getName();
            Path logFile = logsDir.resolve(testName + ".log");

            StringBuilder content = new StringBuilder();
            for (LogEntry entry : logEntries) {
                content.append(entry.getTimestamp())
                        .append(" ")
                        .append(entry.getLevel())
                        .append(" ")
                        .append(entry.getMessage())
                        .append(System.lineSeparator());
            }

            Files.writeString(logFile, content.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить logcat", e);
        }
    }
}
