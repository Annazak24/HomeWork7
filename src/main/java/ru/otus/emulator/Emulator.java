package ru.otus.emulator;

import lombok.Getter;

@Getter
public enum Emulator {

   ANDROID_12(4723);

   private final int port;

   Emulator(int port) {
      this.port = port;
   }

}