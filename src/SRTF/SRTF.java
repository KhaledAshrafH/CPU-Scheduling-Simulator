package SRTF;

import Main.duration;

import java.util.ArrayList;

public class SRTF {
    static class Process implements Comparable<Process> {

        @Override
        public int compareTo(Process obj) {
            return getname().compareTo(obj.getname());
        }

        private final String name;
        private final int ArrivalTime;
        private int BurstTime;
        private int completeTime;
        private int StartTime;
        private int EndTime;
        int PID;

        public int getStartTime() {
            return StartTime;
        }

        public int getEndTime() {
            return EndTime;
        }

        Process(String name, int ArrivalTime, int burst, int startTime, int endTime, int PID) {
            this.name = name;
            this.ArrivalTime = ArrivalTime;
            this.BurstTime = burst;
            this.completeTime = 0;
            this.StartTime = startTime;
            this.EndTime = endTime;
            this.PID = PID;
        }

        Process(String name, int ArrivalTime, int burst, int PID) {
            this.name = name;
            this.ArrivalTime = ArrivalTime;
            this.BurstTime = burst;
            this.completeTime = 0;
            this.PID = PID;
        }

        public String getname() {
            return this.name;
        }

        public int getArrivalTime() {
            return this.ArrivalTime;
        }

        public int getBurst() {
            return this.BurstTime;
        }

        public void setCompleteTime(int time) {
            this.completeTime = time;
        }

        public boolean isCompleted() {
            return this.completeTime == 0;
        }
    }

    public ArrayList<Process> processes = new ArrayList<>();

    private boolean checkCompleted(Process[] pros) {
        for (Process p : pros) {
            if (p.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<Process> Gantt_Chart(Process[] pros, int Age) {

        int Time = 0;
        while (!checkCompleted(pros)) {
            Process obj = null;
            int minBurstTime = Integer.MAX_VALUE;

            for (Process p : pros) {
                if (Time - p.ArrivalTime > Age) {
                    obj = p;
                    break;
                }
                if (p.isCompleted() && p.getArrivalTime() <= Time && p.getBurst() < minBurstTime) {
                    minBurstTime = p.getBurst();
                    obj = p;
                }
            }

            Time++;

            if (obj != null) {
                int arribetime = --Time;
                Process p = new Process(obj.name, arribetime, obj.BurstTime, arribetime, ++Time, obj.PID);
                processes.add(p);
                obj.BurstTime--;

                if (obj.getBurst() == 0) {
                    obj.setCompleteTime(Time);
                }
            }
        }
        return processes;
    }

    public ArrayList<duration> start(ArrayList<Main.Process> processes1, int contextSwitching, int Age) {
        ArrayList<duration> durations = new ArrayList<>();
        Process[] pros = new Process[processes1.size()];

        for (int i = 0; i < processes1.size(); i++) {
            pros[i] = new Process(processes1.get(i).Name, processes1.get(i).arrivalTime, processes1.get(i).burstTime, processes1.get(i).getPID());
        }
        ArrayList<Process> AllProcess;
        AllProcess = Gantt_Chart(pros, Age);

        for (int j = 0; j < AllProcess.size(); j++) {
            duration tmp;

            tmp = new duration(AllProcess.get(j).name, AllProcess.get(j).getStartTime(), AllProcess.get(j).getEndTime(), AllProcess.get(j).PID, "Working", AllProcess.get(j).BurstTime, AllProcess.get(j).ArrivalTime);
            while (j < AllProcess.size() && tmp.id == AllProcess.get(j).PID)
                j++;

            j--;
            tmp.setEnd(AllProcess.get(j).getEndTime());
            durations.add(tmp);
        }

        // add contextSwitching
        for (int i = 1; i < durations.size(); i++) {
            if (durations.get(i).start == durations.get(i - 1).end) {
                for (int j = i; j < durations.size(); j++) {
                    durations.get(j).start += contextSwitching;
                    durations.get(j).end += contextSwitching;
                }
            }
        }

        return durations;
    }
}