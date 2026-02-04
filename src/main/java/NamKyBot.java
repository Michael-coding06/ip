import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NamKyBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm NamKyBot");
        System.out.println("What can I do for you?");

        while (true) {
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (command.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                continue;
            }


            if (command.startsWith("mark")) {
                String[] parts = command.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                Task markedTask = tasks.get(index);
                markedTask.mark();
                System.out.println(
                    "Nice job, I have taken note: \n"
                    + markedTask.toString()
                );
                continue;
            }
            
            if (command.startsWith("unmark")) {
                String[] parts = command.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                Task markedTask = tasks.get(index);
                markedTask.unmark();
                System.out.println(
                    "Nice job, I have taken note: \n"
                    + markedTask.toString()
                );
                continue;
            }

            if (command.startsWith("todo")) {
                String description = command.substring(5);

                Task task = new Todo(description);
                tasks.add(task);

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                continue;
            }

            if (command.startsWith("deadline")) {
                String[] parts = command.split("by ");

                String description = parts[0].substring(9);
                String by = parts[1];

                Task task = new Deadline(description, by);
                tasks.add(task);

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                continue;
            }


           if (command.startsWith("event")) {
                String[] parts = command.split(" from | to ");
                String description = parts[0].substring(6);
                String from = parts[1];
                String to = parts[2];

                Task task = new Event(description, from, to);
                tasks.add(task);

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                continue;
            }

        }
    }
}