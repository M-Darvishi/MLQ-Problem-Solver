package core.model;


import javafx.scene.paint.Color;

public class ProcessView {
    protected String processName;
    protected int startTime;
    protected int duration;
    protected int completionTime;
    protected int arrivalTime;
    protected int serviceTime ;
    protected Color color = Color.WHITE;


    public ProcessView(String processName, int startTime, int duration, int completionTime, int arrivalTime, int serviceTime) {
        this.processName = processName;
        this.startTime = startTime;
        this.duration = duration;
        this.completionTime = completionTime;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public String getProcessName() {
        return processName;
    }
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getStartTime() {
        return startTime;
    }
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public int getCompletionTime() {
        return completionTime;
    }
    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }
}
