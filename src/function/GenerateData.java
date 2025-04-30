package function;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * The GenerateData class generates config files from 2^20-2^30 with integers and doubles. You MUST specify
 * in private static final String OUTPUT_DIR = "./"; where you want them to be stored before you run the class.
 * As of now, the data will be made into text files varying from 4 megabytes to 4 gigabytes for the integer files.
 * The double files are doubled, so they range from 8 megabytes to 8 gigabytes. Make sure you have storage.
 * Also, they will not be stored as arrays. They will just be random numbers with a new line between each one.
 * Author: Nickolas Chacon
 */
public class GenerateData {
    private static final Random rand = new Random();

    //TODO: Change this to the directory where you want the files to be stored at(between the " ")
    private static final String OUTPUT_DIR = "data/";

    public static void generate(int startExp, int endExp) {
        //integers
        for (int exp = startExp; exp <= endExp; exp++) {
            long size = 1L << exp;//Bit shifting 2^exp val (long stores up to 2^63)
            String filename = OUTPUT_DIR + "ints_" + exp + ".txt";

            System.out.println("Generating file: " + filename + " with " + size + " integers");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (long i = 0; i < size; i++) {
                    writer.write(rand.nextInt() + "\n");
                }
            } catch (IOException e) {
                System.err.println("Error writing file: " + filename);
            }
            System.out.println("Finished generating: " + filename);
        }
        System.out.println("All integer files generated.");

        //doubles
        for (int exp = startExp; exp <= endExp; exp++) {
            long size = 1L << exp;
            String filename = OUTPUT_DIR + "doubles_" + exp + ".txt";

            System.out.println("Generating file: " + filename + " with " + size + " doubles");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (long i = 0; i < size; i++) {
                    writer.write(rand.nextDouble() + "\n");
                }
            } catch (IOException e) {
                System.err.println("Error writing file: " + filename);
            }
            System.out.println("Finished generating: " + filename);
        }
        System.out.println("All double files generated.");
    }
}

