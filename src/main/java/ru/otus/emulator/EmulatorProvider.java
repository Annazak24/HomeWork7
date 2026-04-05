package ru.otus.emulator;

import com.google.inject.Singleton;
import ru.otus.exceptions.EmulatorProviderException;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Singleton
public class EmulatorProvider {

   private final BlockingQueue<Emulator> emulators =
         new ArrayBlockingQueue<>(
               Emulator.values().length,
               false,
               Arrays.asList(Emulator.values()));

   private final ThreadLocal<Emulator> currentEmulators = new ThreadLocal<>();

   public Emulator takeAndGet() {
      try {
         Emulator emulator = emulators.poll(2, TimeUnit.MINUTES);
         currentEmulators.set(emulator);
         return emulator;
      } catch (InterruptedException e) {
         throw new EmulatorProviderException("Failed to get emulator from queue", e);
      }
   }

   public Emulator get() {
      return currentEmulators.get();
   }

   @SuppressWarnings("ResultOfMethodCallIgnored")
   public void putBack() {
      try {
         emulators.offer(get(), 2, TimeUnit.MINUTES);
      } catch (InterruptedException e) {
         throw new EmulatorProviderException("Failed to get emulator from queue", e);
      }
   }
}