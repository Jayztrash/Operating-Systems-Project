import java.io.*;
import java.util.*;

public class ProcessReader {
    public static List<Process> readProcesses(String filename) {
        List<Process> processes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine(); // Skip header
            while (scanner.hasNextInt()) {
                processes.add(new Process(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return processes;
    }
}
