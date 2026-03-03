package namkybot;
/**
 * Represents a custom exception specific to NamKyBot.
 * 
 * The exception is thrown when invalid commands or improper inputs are seen.
 */
class NamKyBotException extends Exception {
    public NamKyBotException(String message) {
        super(message);
    }
}