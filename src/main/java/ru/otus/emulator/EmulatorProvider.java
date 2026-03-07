package ru.otus.emulator;

import com.google.inject.Singleton;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;


@Singleton
public class EmulatorProvider {

    private final BlockingQueue<Emulator> emulators =
            new ArrayBlockingQueue<>(
                    Emulator.values().length,
                    false,
                    Arrays.asList(Emulator.values()));

    private final ThreadLocal<Emulator> currentEmulators = new ThreadLocal<>();

    @SneakyThrows
    public Emulator takeAndGet() {
        Emulator emulator = emulators.poll(2, TimeUnit.MINUTES);
        currentEmulators.set(emulator);
        return emulator;
    }

    public Emulator get() {
        return currentEmulators.get();
    }

    @SneakyThrows
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void putBack() {
        emulators.offer(get(), 2, TimeUnit.MINUTES);
    }
}
