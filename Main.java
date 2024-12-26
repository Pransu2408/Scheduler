import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        Scanner sc = new Scanner(System.in);

        try{
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();
        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Process ID, Arrival Time, Burst Time, Priority:");
            int id = sc.nextInt();
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            int priority = sc.nextInt();
            processes.add(new Process(id, arrivalTime, burstTime, priority));
        }

        System.out.println("Choose Scheduling Algorithm:");
        System.out.println("1. FIFO");
        System.out.println("2. SJF");
        System.out.println("3. Round Robin");
        System.out.println("4. Uni-programming");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                scheduler.fifoScheduling(processes);
                break;
            case 2:
                scheduler.sjfScheduling(processes);
                break;
            case 3:
                System.out.println("Enter Quantum Time:");
                int quantum = sc.nextInt();
                scheduler.roundRobinScheduling(processes, quantum);
                break;
            case 4:
                scheduler.uniProgrammingScheduler(processes);
                break;
            default:
                System.out.println("Invalid Choice!");
                return;
        }

        scheduler.printProcesses(processes);
        scheduler.calculateAverages(processes);
    }finally{
        sc.close();
    }
    }
}
