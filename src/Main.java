void main() {
    TestPlanScanner scanner = new TestPlanScanner();

    scanner.buildPlan(Checklist.class);

    if (scanner.getTestSteps().isEmpty()) {
        System.out.println("Кейсов нет");
    } else {
        System.out.println("План прогона для " + scanner.getClassName() + ":");
        for (TestStep step : scanner.getTestSteps()) {
            System.out.println(step);
        }
    }
}
