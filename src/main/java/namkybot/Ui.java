package namkybot;
/**
 * Handles user interaction through the command-line interface.
 * 
 * Responsible for displaying formatted messages and information.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    // Constructs Ui instance and intialize the input scanner.
    public Ui() {
        scanner = new Scanner(System.in);
    }

    // Reads and retruns a command entered by the user.
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    // Displays the welcome message when the application starts.
    public void showWelcome() {
        System.out.println("Hello! I'm NamKyBot");
        System.out.println("What can I do for you?");
    }

    // Displays the farewell message when the application user types "bye".
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // Displays an error message to the user.
    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
    }

    // Displays all the tasks currently stored in the list.
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }  

    // Displays confirmation that a task has been added.
    public void showAddedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks.");
    }

    // Displays confirmation that a task has been marked/unmarked.
    public void showMarkedTask(Task task) {
        System.out.println("Nice job, I have taken note:");
        System.out.println("  " + task);
    }

    // Displays confirmation that a task has been deleted.
    public void showDeletedTask(Task task, int size) {
        System.out.println("Well done, you finished something.");
        System.out.println(task);
        System.out.println("Only " + size + " left to go. Letsss gooo !!!");
    }   

    // Displays list of tasks that match search keyword from user.
    public void showMatchingTasks(ArrayList<Task> tasks) {
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }
}