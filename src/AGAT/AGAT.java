package AGAT;

import Main.Process;
import Main.duration;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AGAT {
    public static StringBuilder message;
    public ArrayList<Process> readyQueue = new ArrayList<>();

    public ArrayList<Process> deadlist = new ArrayList<>();
    public ArrayList<Integer> quantumleft = new ArrayList<>();
    public ArrayList<Integer> Factor = new ArrayList<>();
    public float V1;
    public float V2;

    public ArrayList<duration> start(ArrayList<Process> processes) {
        message = new StringBuilder();
        ArrayList<duration> durations = new ArrayList<>();
        sort(processes, Comparator.comparing(Process::getArrivalTime));
        setV1(processes);

        int time = 0, i = 0, lastop = 0;
        while ((i < processes.size() || !readyQueue.isEmpty())) {

            for (; i < processes.size(); i++) {
                if (processes.get(i).getArrivalTime() <= time) {
                    readyQueue.add(processes.get(i));
                    quantumleft.add(processes.get(i).getQuantum());
                } else if (processes.get(i).getArrivalTime() > time) {
                    break;
                }
            }

            if (readyQueue.isEmpty()) {
                time++;
                continue;
            }
            setV2(readyQueue);
            if (readyQueue.get(0).burstTime == 0) {

                //  finishes executing
                durations.add(new duration(readyQueue.get(0).getName(), lastop, time, readyQueue.get(0).getPID(), "finishes executing", readyQueue.get(0).burstTime, readyQueue.get(0).arrivalTime));

                lastop = time;

                readyQueue.get(0).setQuantum(0);
                deadlist.add(readyQueue.get(0));

                readyQueue.remove(0);
                quantumleft.remove(0);

            }

            if (readyQueue.isEmpty()) {
                time++;
                continue;
            }
            int Factor = getAGATFactor(readyQueue);
            if (readyQueue.get(0).getQuantum() - quantumleft.get(0) >= Math.round(readyQueue.get(0).getQuantum() * 0.4)) {
                if (readyQueue.get(0).burstTime != 0 && quantumleft.get(0) == 0) {

                    message.append(MessageFormat.format("{0} factor updated :{1}{2}{3} {4}=>{5} new quantum {6}\n", time, 10 - readyQueue.get(Factor).priorityNumber, (int) Math.ceil(readyQueue.get(Factor).arrivalTime / V1), (int) Math.ceil(readyQueue.get(Factor).burstTime / V2), readyQueue.get(0).getName(), readyQueue.get(Factor).getName(), readyQueue.get(Factor).getQuantum()));
                    durations.add(new duration(readyQueue.get(0).getName(), lastop, time, readyQueue.get(0).getPID(), "finished it’s quantum", readyQueue.get(0).burstTime, readyQueue.get(0).arrivalTime));
                    lastop = time;

                    Process p = readyQueue.get(0);

                    p.setQuantum(p.getQuantum() * 2);

                    readyQueue.remove(0);
                    quantumleft.remove(0);

                    readyQueue.add(p);
                    quantumleft.add(p.getQuantum());

                } else if (Factor != 0) {

                    message.append(MessageFormat.format("{0} factor updated :{1}{2}{3} {4}=>{5} new quantum {6}\n", time, 10 - readyQueue.get(Factor).priorityNumber, (int) Math.ceil(readyQueue.get(Factor).arrivalTime / V1), (int) Math.ceil(readyQueue.get(Factor).burstTime / V2), readyQueue.get(0).getName(), readyQueue.get(Factor).getName(), readyQueue.get(Factor).getQuantum()));
                    durations.add(new duration(readyQueue.get(0).getName(), lastop, time, readyQueue.get(0).getPID(), "swapped", readyQueue.get(0).burstTime, readyQueue.get(0).arrivalTime));
                    lastop = time;

                    readyQueue.get(0).setQuantum(readyQueue.get(0).getQuantum() + quantumleft.get(0));
                    quantumleft.set(0, readyQueue.get(0).getQuantum());

                    Collections.swap(readyQueue, 0, Factor);
                    Collections.swap(quantumleft, 0, Factor);

                }
            }
            readyQueue.get(0).burstTime--;
            quantumleft.set(0, quantumleft.get(0) - 1);
            time++;
        }
        return durations;
    }

    public void sort(ArrayList<Process> processes, Comparator x) {
        processes.sort(x);
    }

    public void setV1(ArrayList<Process> tmp) {

        if (tmp.get(tmp.size() - 1).arrivalTime > 10) {
            V1 = (float) ((tmp.get(tmp.size() - 1).arrivalTime) / 10.0);
        } else {
            V1 = 1;
        }
    }

    public void setV2(ArrayList<Process> tmp) {
        int maxBurstTime = 0;
        for (Process process : tmp) {
            if (process.burstTime > maxBurstTime) {
                maxBurstTime = process.burstTime;
            }
        }
        if (maxBurstTime > 10) {
            V2 = (float) (maxBurstTime / 10.0);
        } else {
            V2 = 1;
        }
    }

    public int getAGATFactor(ArrayList<Process> tmp) {
        Factor.clear();
        for (Process process : tmp) {
            int factor = (10 - process.priorityNumber) + (int) Math.ceil(process.arrivalTime / V1) + (int) Math.ceil(process.burstTime / V2);
            Factor.add(factor);
        }

        int minFactor = 0;
        for (int i = 0; i < Factor.size(); i++) {
            if (Factor.get(i) <= Factor.get(minFactor)) {
                minFactor = i;
            }
        }
        return minFactor;
    }
}