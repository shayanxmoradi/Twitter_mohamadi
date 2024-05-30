package menu.util;

public class Message {
    private static final String inputMessage = "pls enter %S";
    private static final String existMessage = "%S is already exist";
    private static final String invalidInputMessage = "your input values are invalid";

    public static String getInputMessage(String message) {
        return String.format(inputMessage, message);
    }
    public static String getExistMessage(String message) {
      return String.format(existMessage, message);
    }
    public static String getInvalidInputMessage() {
        return invalidInputMessage;
    }
}
