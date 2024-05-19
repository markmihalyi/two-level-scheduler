import java.util.Map;
import java.util.TreeMap;

public class TwoLevelScheduler {
    private Scheduler levelOneScheduler;
    private Scheduler levelTwoScheduler;

    private String taskExecutionOrder = "";

    public TwoLevelScheduler(Scheduler l1, Scheduler l2) {
        levelOneScheduler = l1;
        levelTwoScheduler = l2;
    }

    public void addNewTask(Task t) {
        int priority = t.getPriority();
        if (priority == 1) {
            levelTwoScheduler.addNewTask(t);
        } else if (priority == 0) {
            levelOneScheduler.addNewTask(t);
        }
    }

    public void run() {
        if (levelTwoScheduler.isResourceNeeded()) {
            levelTwoScheduler.run();
            taskExecutionOrder += levelTwoScheduler.getActiveTaskName();
            return;
        }

        if (levelOneScheduler.isResourceNeeded()) {
            levelOneScheduler.run();
            taskExecutionOrder += levelOneScheduler.getActiveTaskName();
        }
    }

    public String getTaskExecutionResult() {
        String result = "";

        for (int i = 0; i < taskExecutionOrder.length(); i++) {
            String taskName = String.valueOf(taskExecutionOrder.charAt(i));
            if (result.equals("")) {
                result += taskName;
                continue;
            }

            int resultLastIndex = result.length() - 1;
            String lastTaskName = String.valueOf(result.charAt(resultLastIndex));
            if (!lastTaskName.equals(taskName)) {
                result += taskName;
            }
        }

        return result;
    }

    public String getTaskWaitTimes() {
        TreeMap<String, Integer> waitTimes = new TreeMap<String, Integer>();
        waitTimes.putAll(levelOneScheduler.getWaitTimes());
        waitTimes.putAll(levelTwoScheduler.getWaitTimes());

        String result = "";
        int index = 0;
        for (Map.Entry<String, Integer> taskWaitTime : waitTimes.entrySet()) {
            String taskName = taskWaitTime.getKey();
            int waitTime = taskWaitTime.getValue();
            result += taskName + ":" + waitTime;
            if (index < waitTimes.size() - 1) {
                result += ",";
            }
            index++;
        }
        return result;
    }
}
