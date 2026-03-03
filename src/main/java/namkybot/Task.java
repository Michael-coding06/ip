package namkybot;
/**
 * This represents a taks in NamKyBot.
 * A task basically has a description, a type and a marked/unmarked status
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Display the mark status
    public String getStatus() {
        return this.isDone ? "X" : " ";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
