package Main;

public class Process {
    public String Name;
    public int arrivalTime;
    public int burstTime;
    public int priorityNumber;
    public int quantum;
    public int PID;

    public Process(String name, int arrivalTime, int burstTime, int priorityNumber, int quantum, int PID) {
        Name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityNumber = priorityNumber;
        this.quantum = quantum;
        this.PID = PID;
    }

    public String[] getData() {
        return new String[]{String.valueOf(PID), Name, String.valueOf(arrivalTime), String.valueOf(burstTime), String.valueOf(priorityNumber), String.valueOf(quantum)};
    }

    public String getName() {
        return Name;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getPID() {
        return PID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public Process clone() {
        return new Process(Name,
                this.arrivalTime,
                this.burstTime,
                this.priorityNumber,
                this.quantum,
                this.PID);
    }
}
