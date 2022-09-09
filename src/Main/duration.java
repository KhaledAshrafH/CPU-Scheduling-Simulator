package Main;

public class duration {
    public String name;
    public int start, end, id , arrivalTime,burstTime;
    public String description;

    public duration(String name, int start, int end, int id, String description , int burstTime , int arrivalTime) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.description = description;
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime ;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
