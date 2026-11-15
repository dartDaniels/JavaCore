import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleStringBuilder {
    private String state;
    private final Deque<String> snapshots;

    public SimpleStringBuilder() {
        this.state = "";
        this.snapshots = new ArrayDeque<>();
    }

    public void append(String s) {
        snapshots.push(state);
        state += s;
    }

    public void undo() {
        if (!snapshots.isEmpty()) {
            state = snapshots.pop();
        }
    }

    @Override
    public String toString() {
        return state;
    }

    static void main() {
        SimpleStringBuilder builder = new SimpleStringBuilder();

        System.out.println(builder.toString());

        builder.append("Hi");
        builder.append(".");
        builder.append("How are u?");

        System.out.println(builder.toString());

        builder.undo();
        builder.undo();
        System.out.println(builder.toString());
    }
}
