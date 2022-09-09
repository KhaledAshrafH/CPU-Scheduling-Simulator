package shortestJobFirst;

import Main.Process;
import Main.duration;

import java.util.ArrayList;
import java.util.Comparator;

public class shortestJobFirst {
    public ArrayList<duration> start(ArrayList<Process> processes, int age) {

        ArrayList<duration> durations = new ArrayList<>();
        processes.sort(new SJFComparator());
        int time = processes.get(0).arrivalTime;
        while (!processes.isEmpty()) {
            int shortest = 0;
            for (int j = 0; j < processes.size() && processes.get(j).arrivalTime <= time; j++) {
                if (time - processes.get(j).arrivalTime >= age) {
                    shortest = j;
                    break;
                } else if (processes.get(j).burstTime < processes.get(shortest).burstTime) {
                    shortest = j;
                }
            }
            durations.add(new duration(processes.get(shortest).Name, time, time += processes.get(shortest).burstTime, processes.get(shortest).getPID(), "Done",processes.get(shortest).burstTime,processes.get(shortest).arrivalTime));
            processes.remove(shortest);
        }
        return durations;
    }

    public static class SJFComparator implements Comparator<Process> {

        @Override
        public int compare(Process o1, Process o2) {
            if (o1.arrivalTime != o2.arrivalTime)
                return o1.arrivalTime - o2.arrivalTime;
            return o1.burstTime - o2.burstTime;
        }
    }
}
