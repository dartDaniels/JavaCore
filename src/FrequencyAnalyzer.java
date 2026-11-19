import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class FrequencyAnalyzer {
    String[] words = {"up", "down", "up", "up", "left"};
    Integer[] numbers = {3, 1, 3, 3, 7, 1};

    public <T> Map<T, Long> frequency(T[] items) {
        Map<T, Long> frequency = new LinkedHashMap<>();

        for (T item : items) {
            frequency.merge(item, 1L, Long::sum);
        }

        return Collections.unmodifiableMap(frequency);
    }

    static void main() {
        FrequencyAnalyzer analyzer = new FrequencyAnalyzer();

        System.out.println(analyzer.frequency(analyzer.words));
        System.out.println(analyzer.frequency(analyzer.numbers));
    }
}
