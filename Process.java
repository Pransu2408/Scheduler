public class Process {
    private int processId;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int waitingTime;
    private int turnaroundTime;

    public Process(int processId, int arrivalTime, int burstTime, int priority) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    public int getProcessId() { return processId; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public void setBurstTime(int burstTime) { this.burstTime = burstTime; }
    public int getPriority() { return priority; }
    public int getWaitingTime() { return waitingTime; }
    public void setWaitingTime(int waitingTime) { this.waitingTime = waitingTime; }
    public int getTurnaroundTime() { return turnaroundTime; }
    public void setTurnaroundTime(int turnaroundTime) { this.turnaroundTime = turnaroundTime; }
}
