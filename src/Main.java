public class Main {
    static void main(String[] args) {
        FlakyDetector detector = new FlakyDetector();

        System.out.println("Flaky-кандидаты: " + detector.flaky(detector.getRun1(), detector.getRun2()));
        System.out.println("Прогон 1, сводка: " + detector.statusSummary(detector.getRun1()));
        System.out.println("Прогон 2, сводка: " + detector.statusSummary(detector.getRun2()));
    }
}
