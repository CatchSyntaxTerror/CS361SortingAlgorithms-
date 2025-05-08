import function.Benchmarker;
import function.GenerateData;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Invalid arguments");
            return;
        } else if (args[0].equals("INFO")) {
            System.out.println("BENCHMARKER INFO:");
            Runtime r = Runtime.getRuntime();
            System.out.println("Max memory: " + r.maxMemory());
            System.out.println("Free memory: " + r.freeMemory());
            System.out.println("Total memory: " + r.totalMemory());
            return;
        }

        String mode = args[0];
        switch(mode) {
            case "-generate" ->{
                if (args.length != 3) {
                    System.out.println("Need range");
                    return;
                }
                int startExp = Integer.parseInt(args[1]);
                int endExp = Integer.parseInt(args[2]);
                GenerateData.generate(startExp, endExp);
            }
            case "-benchsingle" -> {
                if (args.length != 4) {
                    System.out.println("Provide algorithm acronym & data file" +
                            " name as arguments");
                    return;
                }
                Benchmarker.benchmarkSingle(args[1], args[2],
                        Integer.parseInt(args[3]));
            }
            default -> System.out.println("Invalid mode");
        }
    }
}