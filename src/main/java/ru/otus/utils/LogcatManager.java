package ru.otus.utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import ru.otus.exceptions.LogcatManagementException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Singleton
public class LogcatManager {

   @Inject
   public LogcatManager() {
   }

   public void saveLogcat(AndroidDriver driver, ExtensionContext context) {
      try {
         LogEntries logEntries = driver.manage().logs().get("logcat");

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
         throw new LogcatManagementException("Не удалось сохранить logcat", e);
      }
   }
}