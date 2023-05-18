package main.java.persistencia;

public class Excepciones {

    public static class ConfigFileException extends RuntimeException {
        public ConfigFileException(String message) {
            super(message);
        }
     
    }
    public static class TypeUserException extends RuntimeException {
        public TypeUserException(String message) {
            super(message);
        }
    }
    public class SelectException extends Exception {
        public SelectException(String message) {
            super(message);
        }
    }
    public class SQLException extends Exception {
        public SQLException(String message) {
            super(message);
        }
    }
}
