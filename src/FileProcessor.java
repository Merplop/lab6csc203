// Lab 6 File Processor, CSC-203
// Miro Haapalainen

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class FileProcessor {

    public static void processFile(String filePath) {
        try (Stream<String> lines = new BufferedReader(new FileReader(filePath)).lines()) {
            lines.map(line -> line.split(", "))// maps lines to arrays using split method
                    .filter(s -> Double.parseDouble(s[2]) <= 2.0)
                    .map(s -> new double[] { Double.parseDouble(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]) })
                    .map(s -> new double[] { s[0] * 0.5 - 150, s[1] * 0.5 - 37, s[2] * 0.5 })
                    // Hard-coded locale to US; my computer is in Finnish causing some decimals to be printed out with a comma instead of a period
                    .map(s -> String.format(Locale.US, "%.2f, %.2f, %.2f", s[0], s[1], s[2]))
                    .forEach(line -> {
                        try (PrintWriter writer = new PrintWriter(new FileWriter("drawMe.txt", true))) { // PrintWriter for more concise write code
                            writer.println(line);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
