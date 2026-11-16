import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestPlanScanner {
    private final List<TestStep> testSteps = new ArrayList<>();
    private String className;

    public String getClassName() {
        return className;
    }

    public List<TestStep> getTestSteps() {
        return testSteps;
    }

    public void buildPlan(Class<?> clazz) {
        testSteps.clear();
        className = clazz.getSimpleName();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(TestId.class)) {

                TestId testId = method.getAnnotation(TestId.class);
                String methodName = method.getName();
                TestStep testStep = new TestStep(testId.value(), methodName, testId.order());
                testSteps.add(testStep);
            }
        }
        testSteps.sort(Comparator.comparingInt(TestStep::order));
    }
}
