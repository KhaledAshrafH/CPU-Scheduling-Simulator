package PriorityScheduling;

import Main.Process;
import Main.duration;

import java.util.ArrayList;
import java.util.Comparator;

public class PriorityScheduling {

    public ArrayList<duration> start(ArrayList<Process> processes, int contextSwitching, int age) {
        ArrayList<duration> durations = new ArrayList<>();
        processes.sort(new PComparator());
        int time = processes.get(0).arrivalTime;
        while (!processes.isEmpty()) {
            int shortest = 0;
            for (int j = 0; j < processes.size() && processes.get(j).arrivalTime <= time; j++) {
                if (time - processes.get(j).arrivalTime >= age) {
                    shortest = j;
                    break;
                } else if (processes.get(j).priorityNumber <= processes.get(shortest).priorityNumber) {
                    shortest = j;
                }
            }
            durations.add(new duration(processes.get(shortest).Name, time, time += processes.get(shortest).burstTime, processes.get(shortest).getPID(), "Done" ,processes.get(shortest).burstTime,processes.get(shortest).arrivalTime));
            time += contextSwitching;
            processes.remove(shortest);
        }
        return durations;
    }

    public static class PComparator implements Comparator<Process> {

        @Override
        public int compare(Process o1, Process o2) {
            if (o1.arrivalTime != o2.arrivalTime)
                return o1.arrivalTime - o2.arrivalTime;
            return o1.priorityNumber - o2.priorityNumber;
        }
    }
}
