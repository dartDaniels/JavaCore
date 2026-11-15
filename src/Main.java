void main() {

    List<String> lines = new ArrayList<>();

    Path path = Path.of("run.log");

    try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
        String line;
        
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    TestLogParser logParser = new TestLogParser();

    logParser.parse(lines);

    System.out.println("Результаты:");
    for (TestResult result : logParser.getResults()) {
        System.out.println(result);
    }

    System.out.println("Битые строки: " + String.join(", ", logParser.getErrors()));

    Optional<TestResult> firstFailed = logParser.firstFailed(logParser.getResults());

    System.out.println("Первый упавший: " + firstFailed.map(TestResult::toString).orElse("Падений нет").trim());

}
