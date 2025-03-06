private static void readProcessData(String fileName) {
  try (BufferedReader br = new BufferedReader(New FileReader(fileName))) {
      String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 4) {
                    int pid = Integer.parseInt(parts[0]);
                    int arrival = Integer.parseInt(parts[1]);
                    int burst = Integer.parseInt(parts[2]);
                    int priority = Integer.parseInt(parts[3]);
                    processList.add(new Process(pid, arrival, burst, priority));
                } else {
                    System.err.println("Invalid format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
 private static void fcfsScheduling() {
        processList.sort(Comparator.comparingInt(p -> p.arrivalTime));
        executeScheduling();
    }

    private static void sjfScheduling() {
        processList.sort(Comparator.comparingInt(p -> p.burstTime));
        executeScheduling();
    }

    private static void priorityScheduling() {
        processList.sort(Comparator.comparingInt(p -> p.priority));
        executeScheduling();
    }
