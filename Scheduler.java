import java.util.ArrayList;
import java.util.TreeMap;

public abstract class Scheduler {
    protected ArrayList<Task> readyTasks = new ArrayList<Task>();
    protected ArrayList<Task> finishedTasks = new ArrayList<Task>();
    protected Task activeTask;
    protected static int elapsedTime = 0;

    public int getReadyTaskCount() {
        return readyTasks.size();
    }

    public String getActiveTaskName() {
        return activeTask.getName();
    }

    public abstract void addNewTask(Task t);

    public abstract Task getNextTask();

    protected abstract void switchToNextTask();

    public abstract void run();

    public abstract boolean isResourceNeeded();

    protected int getWaitTime(Task t) {
        return elapsedTime - t.getStartNumber() - t.getTotalBurstTime();
    }

    public TreeMap<String, Integer> getWaitTimes() {
        TreeMap<String, Integer> waitTimes = new TreeMap<String, Integer>();
        for (Task t : finishedTasks) {
            String taskName = t.getName();
            if (!waitTimes.containsKey(taskName)) {
                int waitTime = t.getWaitTime();
                waitTimes.put(taskName, waitTime);
            }
        }
        return waitTimes;
    }
}