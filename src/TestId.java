import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// при изменении политики на SOURCE || CLASS пропадает возможность исследовать класс через рефлексию тк аннотации с политикой SOURCE, CLASS недоступны во время выполнения программы, на чем и основана рефлексия
@Retention(RetentionPolicy.RUNTIME)  // аннотация сохраняется в скомпилированном файле и доступна через рефлексию
@Target(ElementType.METHOD)          // ограничивает область применения, можно применять только над методами
public @interface TestId {
    String value();   // номер тест-кейса, например "TC-101"
    int order();      // порядок выполнения в прогоне
}