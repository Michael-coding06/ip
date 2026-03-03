package namkybot;

public class Parser {

    public static String getKeyword(String command) {
        String[] parts = command.split(" ", 2);
        return parts[0].toLowerCase();
    }

    public static String parseFindKeyword(String command) throws NamKyBotException {
        String[] parts = command.trim().split(" ", 2);

        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new NamKyBotException("Please provide a keyword to search");
        }

        return parts[1].trim();
    }

    public static int parseIndex(String command) throws NamKyBotException {
        String[] parts = command.trim().split(" ");

        if (parts.length < 2) {
            throw new NamKyBotException("Please specify the task number.");
        }

        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0) {
                throw new NamKyBotException("Task number must be positive.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new NamKyBotException("Task number must be a number.");
        }
    }

    public static String parseTodoDescription(String command) throws NamKyBotException {
        if (command.length() <= 5) {
            throw new NamKyBotException("Please add description.");
        }
        String description = command.substring(5).trim();
        if (description.isEmpty()) {
            throw new NamKyBotException("The description cannot be empty");
        }
        return description;
    }

    public static String[] parseDeadline(String command) throws NamKyBotException {
        if (!command.contains("by")) {
            throw new NamKyBotException("A deadline must have a 'by' time.");
        }

        String[] parts = command.split("by", 2);
        String description = parts[0].substring(9).trim();
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new NamKyBotException("Deadline description or time cannot be empty.");
        }

        return new String[]{description, by};
    }
    
    public static String[] parseEvent(String command) throws NamKyBotException {
        if (!command.contains(" from ") || !command.contains(" to ")) {
            throw new NamKyBotException("An event must have 'from' and 'to'.");
        }

        String[] parts = command.split(" from | to ");
        if (parts.length < 3) {
            throw new NamKyBotException("Event description, start, or end is missing.");
        }

        String description = parts[0].substring(6).trim();
        String from = parts[1].trim();
        String to = parts[2].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new NamKyBotException("Event fields cannot be empty.");
        }

        return new String[]{description, from, to};
    }
}