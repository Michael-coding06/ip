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
                if (tasks.isEmpty()) {
                    System.out.println("No commands recorded yet.");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " 
                        + "[" + tasks.get(i).getStatus() + "]" + " "
                        + tasks.get(i));
                    }
                }
                continue;
            }

            if (command.startsWith("mark")) {
                String[] parts = command.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                Task markedTask = tasks.get(index);
                // tasks.get(index).mark();
                markedTask.mark();
                System.out.println(
                    "Nice job, I have taken note: \n"
                    + "[" + markedTask.getStatus() + "] " + markedTask.toString()
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
                    + "[" + markedTask.getStatus() + "] " + markedTask.toString()
                );
                continue;
            }

            Task task = new Task(command);
            tasks.add(task);
            System.out.println("added: " + command);
        }
    }
}