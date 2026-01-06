package core.model;

import java.util.ArrayList;
import java.util.List;

public class Process {
    protected String ProcessName;
    protected int arrivalTime;
    protected int serviceTime;
    protected int priority;
    protected int remainingTime;
    protected int completionTime;
    protected int waitingTime ;
    protected  int turnaroundTime;

    public Process(String processName, int arrivalTime, int serviceTime, int priority) {
        ProcessName = processName;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.priority = priority;
        this.remainingTime = serviceTime;
        this.completionTime = 0;
        this.waitingTime=0;
        this.turnaroundTime=0;
    }

    public String getProcessName() {
        return ProcessName;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getServiceTime() {
        return serviceTime;
    }
    public int getPriority() {
        return priority;
    }

    public int getRemainingTime() {
        return remainingTime;
    }
    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }
    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }
    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }
    public void setTurnaroundTime(int turnarroundTime) {
        this.turnaroundTime = turnarroundTime;
    }

    public void resetRemainingTime() {
        this.remainingTime = this.serviceTime;
    }
}
