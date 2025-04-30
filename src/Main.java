import function.Benchmarker;
import function.GenerateData;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Invalid arguments");
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
            case "-benchmark" -> {
                if (args.length < 4) {
                    System.out.println("Need range & trials");
                    return;
                }
                int startExp = Integer.parseInt(args[1]);
                int endExp = Integer.parseInt(args[2]);
                int trials = Integer.parseInt(args[3]);
                boolean verbose = args.length > 4 && args[4].equals("-V");
                Benchmarker.benchmark(startExp, endExp, trials, verbose);
            }
            default -> System.out.println("Invalid mode");
        }
    }
}