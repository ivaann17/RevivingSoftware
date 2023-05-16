package main.java.persistencia;

public class Excepciones {

    public static class ConfigFileException extends RuntimeException {
        public ConfigFileException(String message) {
            super(message);
        }
    }

}
