package namkybot;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm NamKyBot");
        System.out.println("What can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("____________________________________");
    }

    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showAddedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks.");
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice job, I have taken note:");
        System.out.println("  " + task);
    }

    public void showDeletedTask(Task task, int size) {
        System.out.println("Well done, you finished something.");
        System.out.println(task);
        System.out.println("Only " + size + " left to go. Letsss gooo !!!");
    }

    public void showMatchingTasks(ArrayList<Task> tasks) {
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }
}