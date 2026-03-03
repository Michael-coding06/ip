package namkybot;
/**
 * Handles the loading and saving of task list to a localfile (as declared as filePath)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from the storage file
     * 
     * Parses each line and reconstructs the corresponding Task objects (todo, deadline, or event)
     * Returns a list of tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String type = line.substring(1, 2);
                boolean isDone = line.substring(4, 5).equals("X");
                String description = line.substring(7);

                Task task;
                String[] parts;

                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;

                case "D":
                    parts = description.split(" \\(by: ");
                    task = new Deadline(parts[0], parts[1].replace(")", ""));
                    break;

                case "E":
                    parts = description.split(" \\(from: | to: ");
                    task = new Event(parts[0], parts[1], parts[2].replace(")", ""));
                    break;

                default:
                    continue;
                }

                if (isDone) {
                    task.mark();
                }

                tasks.add(task);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file
     * 
     * Each task is written using its string representation, 
     * with 1 task per line 
     * Example of a task would be: [D][ ] buy book (by: monday)
     */
    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);

            for (Task task : tasks) {
                writer.write(task.toString());
                writer.write(System.lineSeparator());
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}