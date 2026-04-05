package ru.otus.emulator;

public enum Emulator {

   ANDROID_12(4723);

   private final int port;

   Emulator(int port) {
      this.port = port;
   }

   public int getPort() {
      return port;
   }
}