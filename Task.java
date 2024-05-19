public class Task {
    private String name;
    private int priority;
    private int startNumber;
    private int totalBurstTime;
    private int remainingBurstTime;
    private int waitTime = 0;

    public Task(String name, int priority, int startNumber, int totalBurstTime) {
        this.name = name;
        this.priority = priority;
        this.startNumber = startNumber;
        this.totalBurstTime = totalBurstTime;
        this.remainingBurstTime = totalBurstTime;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public int getTotalBurstTime() {
        return totalBurstTime;
    }

    public int getRemainingBurstTime() {
        return remainingBurstTime;
    }

    public void decreaseRemainingBurstTime() {
        remainingBurstTime--;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public String toString() {
        return name;
    }
}
