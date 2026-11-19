import java.util.*;

public class FlakyDetector {
    private final List<String> run1 = List.of("LoginTest|PASSED", "CreateTaskTest|FAILED", "CommentTest|FAILED", "LogoutTest|PASSED");
    private final List<String> run2 = List.of("LoginTest|PASSED", "CreateTaskTest|PASSED", "CommentTest|FAILED", "StatsTest|PASSED");

    public List<String> getRun1() {
        return run1;
    }

    public List<String> getRun2() {
        return run2;
    }

    public Set<String> failed(List<String> run) {
        Set<String> failedTests = new HashSet<>();

        for (String test : run) {
            String[] parts = test.split("\\|");
            if (parts[1].equals("FAILED")) {
                failedTests.add(parts[0]);
            }
        }
        return Set.copyOf(failedTests);
    }

    public Set<String> passed(List<String> run) {
        Set<String> passedTests = new HashSet<>();

        for (String test : run) {
            String[] parts = test.split("\\|");
            if (parts[1].equals("PASSED")) {
                passedTests.add(parts[0]);
            }
        }
        return Set.copyOf(passedTests);
    }

    public List<String> flaky(List<String> run1, List<String> run2) {
        List<String> flakyTests = new ArrayList<>(failed(run1));
        flakyTests.retainAll(passed(run2));
        return List.copyOf(flakyTests);
    }

    public Map<String, Long> statusSummary(List<String> run) {
        Map<String, Long> statusSum = new HashMap<>();

        for (String test : run) {
            String[] parts = test.split("\\|");
            statusSum.merge(parts[1], 1L, Long::sum);
        }
        return Map.copyOf(statusSum);
    }

}
