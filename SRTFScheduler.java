public class SRTFScheduler extends Scheduler {
    public void addNewTask(Task t) {
        int burstTime = t.getRemainingBurstTime();
        int readyTaskCount = getReadyTaskCount();

        for (int i = 0; i < readyTaskCount; i++) {
            Task otherTask = readyTasks.get(i);
            int otherBurstTime = otherTask.getRemainingBurstTime();
            if (burstTime < otherBurstTime) {
                readyTasks.add(i, t);
                return;
            }
        }

        readyTasks.add(t);
    }

    public Task getNextTask() {
        int readyTaskCount = getReadyTaskCount();
        if (activeTask == null) {
            if (readyTaskCount > 0) {
                Task firstReadyTask = readyTasks.get(0);
                return firstReadyTask;
            } else {
                return null;
            }
        }

        if (readyTaskCount == 0) {
            if (activeTask.getRemainingBurstTime() > 0) {
                return activeTask;
            } else {
                return null;
            }
        }

        Task firstReadyTask = readyTasks.get(0);
        if (activeTask.getRemainingBurstTime() <= firstReadyTask.getRemainingBurstTime()
                && activeTask.getRemainingBurstTime() > 0) {
            return activeTask;
        }

        return firstReadyTask;
    }

    public void switchToNextTask() {
        activeTask = getNextTask();
        readyTasks.remove(activeTask);
    }

    // Ütemezés
    public void run() {
        elapsedTime++;

        if (activeTask != null) {
            int readyTaskCount = getReadyTaskCount();
            if (readyTaskCount > 0 && activeTask.getRemainingBurstTime() > 0) {
                Task firstReadyTask = readyTasks.get(0);
                if (firstReadyTask.getRemainingBurstTime() < activeTask.getRemainingBurstTime()) {
                    addNewTask(activeTask);
                }
            }
        }

        switchToNextTask();

        // Taszk lefutása
        activeTask.decreaseRemainingBurstTime();
        if (activeTask.getRemainingBurstTime() == 0) {
            int waitTime = getWaitTime(activeTask);
            activeTask.setWaitTime(waitTime);
            finishedTasks.add(activeTask);
        }
    }

    public boolean isResourceNeeded() {
        Task nextTask = getNextTask();
        return nextTask != null;
    }
}