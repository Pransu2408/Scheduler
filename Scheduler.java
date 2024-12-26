import java.util.*;

public class Scheduler {

    // FIFO Scheduling
    public void fifoScheduling(List<Process> processes) {
        int currentTime = 0;
        for (Process p : processes) {
            if (currentTime < p.getArrivalTime()) {
                currentTime = p.getArrivalTime();
            }
            p.setWaitingTime(currentTime - p.getArrivalTime());
            currentTime += p.getBurstTime();
            p.setTurnaroundTime(p.getWaitingTime() + p.getBurstTime());
        }
    }

    // SJF Scheduling
    public void sjfScheduling(List<Process> processes) {
        PriorityQueue<Process> pq = new PriorityQueue<>(Comparator.comparingInt(Process::getBurstTime));
        int currentTime = 0;
        int completedProcesses = 0;
        int n = processes.size();
    
        while (completedProcesses < n) { // Ensure all processes are handled
            // Add processes that have arrived by currentTime to the priority queue
            Iterator<Process> iterator = processes.iterator();
            while (iterator.hasNext()) {
                Process p = iterator.next();
                if (p.getArrivalTime() <= currentTime) {
                    pq.add(p);
                    iterator.remove();
                }
            }
    
            // If the priority queue is empty, move currentTime to the next process's arrival time
            if (pq.isEmpty() && !processes.isEmpty()) {
                currentTime = processes.get(0).getArrivalTime();
                continue;
            }
    
            // Process the shortest job
            if (!pq.isEmpty()) {
                Process p = pq.poll();
                currentTime = Math.max(currentTime, p.getArrivalTime()); // Account for idle CPU
                p.setWaitingTime(currentTime - p.getArrivalTime());
                currentTime += p.getBurstTime();
                p.setTurnaroundTime(p.getWaitingTime() + p.getBurstTime());
                completedProcesses++;
            }
        }
    }
    

    // Round Robin Scheduling
    public void roundRobinScheduling(List<Process> processes, int quantum) {
        Queue<Process> queue = new LinkedList<>();
        queue.addAll(processes);
        int currentTime = 0;

        while (!queue.isEmpty()) {
            Process p = queue.poll();
            if (p.getBurstTime() > quantum) {
                currentTime += quantum;
                p.setBurstTime(p.getBurstTime() - quantum);
                queue.add(p);
            } else {
                currentTime += p.getBurstTime();
                p.setWaitingTime(currentTime - p.getArrivalTime() - p.getBurstTime());
                p.setTurnaroundTime(p.getWaitingTime() + p.getBurstTime());
            }
        }
    }

    // Uni-programming Scheduler
    public void uniProgrammingScheduler(List<Process> processes) {
        int currentTime = 0;
        for (Process p : processes) {
            if (currentTime < p.getArrivalTime()) {
                currentTime = p.getArrivalTime();
            }
            currentTime += p.getBurstTime();
            p.setWaitingTime(currentTime - p.getArrivalTime() - p.getBurstTime());
            p.setTurnaroundTime(p.getWaitingTime() + p.getBurstTime());
        }
    }

    // Helper to calculate averages
    public void calculateAverages(List<Process> processes) {
        int totalWaitingTime = 0, totalTurnaroundTime = 0;
        for (Process p : processes) {
            totalWaitingTime += p.getWaitingTime();
            totalTurnaroundTime += p.getTurnaroundTime();
        }
        System.out.println("Average Waiting Time: " + (totalWaitingTime / (float) processes.size()));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / (float) processes.size()));
    }

    // Helper to print process details
    public void printProcesses(List<Process> processes) {
        System.out.println("Process ID | Waiting Time | Turnaround Time");
        for (Process p : processes) {
            System.out.printf("%10d | %12d | %15d\n", p.getProcessId(), p.getWaitingTime(), p.getTurnaroundTime());
        }
    }
}
