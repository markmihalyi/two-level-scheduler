import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TwoLevelScheduler tls = new TwoLevelScheduler(new SRTFScheduler(), new RRScheduler(2));
        Scanner input = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<Task>();
        int totalBurstTime = 0;

        while (true) {
            String line = input.nextLine();
            if (line == "")
                break;

            String[] data = line.split(",");
            if (data.length != 4) {
                System.out.println("Hibás bemenet.\n");
                continue;
            }

            String name = data[0];
            int priority;
            int startNumber;
            int burstTime;

            try {
                priority = Integer.parseInt(data[1]);
                startNumber = Integer.parseInt(data[2]);
                burstTime = Integer.parseInt(data[3]);
            } catch (NumberFormatException e) {
                System.out.println("Hibás bemenet.\n");
                continue;
            }

            Task t = new Task(name, priority, startNumber, burstTime);
            tasks.add(t);
            totalBurstTime += burstTime;
        }

        input.close();

        // Ötlet:
        // külön adja hozzá az összes bemenet megadása után a taszkokat a FK listához
        // ciklus iterációkat kell számolgatni (iteráció = run = startNumber)
        // i = időegység

        for (int i = 0; i <= totalBurstTime; i++) {
            // Megfelelő taszkok hozzáadása a kétszintű ütemezőhöz
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                Task t = iterator.next();
                if (t.getStartNumber() == i) {
                    tls.addNewTask(t);
                    iterator.remove();
                }
            }

            // Ütemezés
            tls.run();
        }

        System.out.println(tls.getTaskExecutionResult());
        System.out.println(tls.getTaskWaitTimes());
    }
}