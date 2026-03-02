package namkybot;

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