public record TestStep(String id, String methodName, int order) {
    @Override
    public String toString() {
        return "  " + order + ". " + id + " -> " + methodName;
    }
}
