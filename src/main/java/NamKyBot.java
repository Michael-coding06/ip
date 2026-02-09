import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NamKyBot {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();

        while (true) {
            try {
                String command = scanner.nextLine().trim();

                if (command.isEmpty()) {
                    throw new NamKyBotException("Please enter a command.");
                }

                String[] parts = command.split(" ", 2);
                String keyword = parts[0].toLowerCase();

                switch(keyword) {
                case "bye":
                    sayGoodbye();
                    return;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    markTask(command, true);
                    break;
                case "unmark":
                    markTask(command, false);
                    break;
                case "todo":
                    addTodo(command);
                    break;
                case "deadline":
                    addDeadline(command);
                    break;
                case "event":
                    addEvent(command);
                    break;
                default:
                    throw new NamKyBotException("What's thatttt?. I dont understand");
                }
            } catch (NamKyBotException e) {
                System.out.println(" OOPS!!! " + e.getMessage());
            }
        }
    }

    private static void greet() {
        System.out.println("Hello! I'm NamKyBot");
        System.out.println("What can I do for you?");
    }

    private static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void markTask(String command, boolean mark) throws  NamKyBotException{
        String[] parts = command.split(" ");

        if (parts.length < 2) {
            throw new NamKyBotException("Please specify the task number.");
        }

        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new NamKyBotException("Task number must be a number.");
        }
        if (index < 1 || index >= tasks.size()) {
            throw new NamKyBotException("That task number does not exist.");
        }

        Task task = tasks.get(index);
        if (mark) {
            task.mark();
        } else {
            task.unmark();
        }

        System.out.println("Nice job, I have taken note:");
        System.out.println("  " + task);
    }

    private static void addTodo(String command) throws NamKyBotException{
        if (command.length() <= 5) {
            throw new NamKyBotException("Please add description");
        }

        String description = command.substring(5);

        if (description.isEmpty()) {
            throw new NamKyBotException("The description cannot be empty");
        }

        Task task = new Todo(description);
        addTask(task);
    }

    private static void addDeadline(String command) throws NamKyBotException {
        if (!command.contains("by")) {
            throw new NamKyBotException("A deadline must have a 'by' time.");
        }

        String[] parts = command.split("by ", 2);

        if (parts[0].length() <= 9 || parts[1].trim().isEmpty()) {
            throw new NamKyBotException("Deadline description or time cannot be empty.");
        }

        String description = parts[0].substring(9).trim();
        String by = parts[1].trim();

        Task task = new Deadline(description, by);
        addTask(task);
    }

    private static void addEvent(String command) throws NamKyBotException {
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

        Task task = new Event(description, from, to);
        addTask(task);
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
    }
}
