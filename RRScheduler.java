public class RRScheduler extends Scheduler {
    private int timeSlice; // RR időegység
    private int remainingTimeSlice; // F taszk hátralévő időszeletei

    public RRScheduler(int timeSlice) {
        this.timeSlice = timeSlice;
    }

    public void addNewTask(Task t) {
        readyTasks.add(t);
    }

    public Task getNextTask() {
        int readyTaskCount = getReadyTaskCount();
        if (readyTaskCount == 0) { // ha nincs futásra kész taszk
            return null;
        }

        return readyTasks.get(0);
    }

    public void switchToNextTask() {
        activeTask = getNextTask();
        readyTasks.remove(activeTask);
        remainingTimeSlice = timeSlice;
    }

    // Ütemezés
    public void run() {
        elapsedTime++;

        if (activeTask == null) {
            switchToNextTask();
        }

        // Taszk lefutása előtt
        int remainingBurstTime = activeTask.getRemainingBurstTime();
        if (remainingTimeSlice == 0 && remainingBurstTime > 0) { // ha elfogyott az időszelet, de futna még
            addNewTask(activeTask);
            switchToNextTask();
        }

        if (remainingBurstTime == 0) { // ha a taszk nem futna már tovább
            switchToNextTask();
        }

        // Taszk lefutása
        remainingTimeSlice--;
        activeTask.decreaseRemainingBurstTime();
        if (activeTask.getRemainingBurstTime() == 0) {
            int waitTime = getWaitTime(activeTask);
            activeTask.setWaitTime(waitTime);
            finishedTasks.add(activeTask);
        }
    }

    public boolean isResourceNeeded() {
        if (activeTask != null && activeTask.getRemainingBurstTime() > 0) {
            return true;
        }

        int readyTaskCount = getReadyTaskCount();
        return readyTaskCount > 0;
    }

}
