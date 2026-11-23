public class Main {
    static void main(String[] args) {
        TestReportGenerator reportGenerator = new TestReportGenerator();
        reportGenerator.printSummary(reportGenerator.run);

        System.out.println();

        WorkWeekChecker checker = new WorkWeekChecker();
        checker.test();


    }

}
