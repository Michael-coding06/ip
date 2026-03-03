package namkybot;

import java.util.ArrayList;

public class NamKyBot {

    private static Storage storage = new Storage("NamKyBot.txt");
    private static Ui ui = new Ui();
    private static TaskList tasks;

    public static void main(String[] args) {
        tasks = new TaskList(storage.load());
        ui.showWelcome();

        while (true) {
            try {
                String command = ui.readCommand();

                if (command.isEmpty()) {
                    throw new NamKyBotException("Please enter a command.");
                }

                String keyword = Parser.getKeyword(command);

                switch (keyword) {
                case "bye":
                    ui.showGoodbye();
                    return;

                case "list":
                    ui.showTaskList(tasks.getAll());
                    break;

                case "mark": {
                    int index = Parser.parseIndex(command);
                    if (index >= tasks.size()) {
                        throw new NamKyBotException("That task number does not exist.");
                    }
                    Task task = tasks.get(index);
                    task.mark();
                    ui.showMarkedTask(task);
                    storage.save(tasks.getAll());
                    break;
                }

                case "unmark": {
                    int index = Parser.parseIndex(command);
                    if (index >= tasks.size()) {
                        throw new NamKyBotException("That task number does not exist.");
                    }
                    Task task = tasks.get(index);
                    task.unmark();
                    ui.showMarkedTask(task);
                    storage.save(tasks.getAll());
                    break;
                }

                case "delete": {
                    int index = Parser.parseIndex(command);
                    if (index >= tasks.size()) {
                        throw new NamKyBotException("That task number does not exist.");
                    }
                    Task task = tasks.get(index);
                    tasks.remove(index);
                    ui.showDeletedTask(task, tasks.size());
                    storage.save(tasks.getAll());
                    break;
                }

                case "todo": {
                    String description = Parser.parseTodoDescription(command);
                    Task task = new Todo(description);
                    addTask(task);
                    storage.save(tasks.getAll());
                    break;
                }

                case "deadline": {
                    String[] parts = Parser.parseDeadline(command);
                    Task task = new Deadline(parts[0], parts[1]);
                    addTask(task);
                    storage.save(tasks.getAll());
                    break;
                }

                case "event": {
                    String[] parts = Parser.parseEvent(command);
                    Task task = new Event(parts[0], parts[1], parts[2]);
                    addTask(task);
                    storage.save(tasks.getAll());
                    break;
                }

                case "find": {
                    String findString = Parser.parseFindKeyword(command);
                    ArrayList<Task> matches = tasks.findTask(findString);
                    ui.showMatchingTasks(matches);
                    break;
                }

                default:
                    throw new NamKyBotException("What's thatttt?. I dont understand");
                }

            } catch (NamKyBotException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
    }
}