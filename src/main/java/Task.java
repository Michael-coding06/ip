public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        // this.type = type;
        this.description = description;
        this.isDone = false;
    }

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
        return "[" + getStatus() + "] " + description + "(";
    }
}
