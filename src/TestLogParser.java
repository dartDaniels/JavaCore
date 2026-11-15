import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestLogParser {

    private final List<TestResult> results = new ArrayList<>();
    private final List<String> errors = new ArrayList<>();

    public List<TestResult> getResults() {
        return results;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void parse(List<String> lines) {

        for (String line : lines) {
            String[] parts = line.split("\\|", -1);

            if (parts.length != 3) {
                errors.add(line + " - неверное количество полей");
                continue;
            }

            String methodName = parts[0].trim();

            if (methodName.isBlank()) {
                errors.add(line + " - нет названия теста");
                continue;
            }

            Status status;
            long durationMs;
            try {
                status = Status.valueOf(parts[1].trim());

            } catch (IllegalArgumentException e) {
                errors.add(line + " - неверный статус");
                continue;
            }

            try {
                durationMs = Long.parseLong(parts[2].trim());
            } catch (IllegalArgumentException e) {
                errors.add(line + " - не число");
                continue;
            }

            if (durationMs < 0) {
                errors.add(line + " - отрицательное время");
                continue;
            }

            TestResult result = new TestResult(methodName, status, durationMs);

            int index = results.indexOf(result);  // в данной реализации стандартный equals определенный автоматически у record никак не помогал тк сравнивал по всем 3м полям record
            if (index != -1) {                    // поэтому был переопределен для сравнения только по name
                results.set(index, result);
            } else {
                results.add(result);
            }

        }
    }

    public Optional<TestResult> firstFailed(List<TestResult> results) {
        Optional<TestResult> firstFailed = results.stream()
                .filter(result -> result.status().equals(Status.FAILED))
                .findFirst();

        return firstFailed;
    }
}
