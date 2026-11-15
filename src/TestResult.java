import java.util.Objects;

public record TestResult(String name, Status status, long durationMs) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TestResult other)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "  " + name + " — " + status + " (" + durationMs + " ms)";
    }
}
