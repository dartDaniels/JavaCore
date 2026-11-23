import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestReportGenerator {
    List<TestResult> run = List.of(
            new TestResult("LoginTest",        Status.PASSED, 120),
            new TestResult("CreateTaskTest",   Status.PASSED, 940),
            new TestResult("UpdateStatusTest", Status.FAILED, 2100),
            new TestResult("CommentTest",      Status.PASSED, 380),
            new TestResult("DeleteTaskTest",   Status.FAILED, 760),
            new TestResult("StatsTest",        Status.SKIPPED, 0),
            new TestResult("LogoutTest",       Status.PASSED, 90)
    );

    public Map<Status, List<String>> printSummary(List<TestResult> run) {
        Map<Status, List<String>> tests = run.stream()
                .collect(Collectors.groupingBy(TestResult::status,
                        Collectors.mapping(TestResult::name, Collectors.toList())));


        List<String> longestTests = run.stream()
                .sorted(Comparator.comparingLong(TestResult::durationMs).reversed())
                .limit(3)
                .map(TestResult::name)
                .toList();

        double averageTime = run.stream()
                .filter(s -> s.status() == Status.PASSED)
                .mapToLong(TestResult::durationMs)
                .average()
                .orElse(0.0);


        System.out.println("=== Сводка прогона ===");

        tests.entrySet().stream()
                .forEach(element -> System.out.println(element.getKey() + " "
                        + "(" + element.getValue().size() + "): "
                        + String.join(", ", element.getValue())));


        System.out.print("Топ 3 долгих: ");
        System.out.println(String.join(", ", longestTests));
        System.out.println("Среднее время прошедших: " + averageTime + " ms");
        return tests;
    }
}
