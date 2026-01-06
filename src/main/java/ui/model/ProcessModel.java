package ui.model;

public class ProcessModel {

    private final String name;
    private final Integer arrivalTime;
    private final Integer serviceTime;
    private final Integer priority;
    private final Integer completionTime;

    public ProcessModel(String name, Integer arrivalTime, Integer serviceTime, Integer priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.priority = priority;
        this.completionTime = 0;
    }
    public ProcessModel(String name, Integer arrivalTime, Integer serviceTime, Integer priority , Integer completionTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.priority = priority;
        this.completionTime = completionTime;
    }

    public String getName() { return name; }
    public Integer getArrival() { return arrivalTime; }
    public Integer getService() { return serviceTime; }
    public Integer getPriority() { return priority; }
    public Integer getCompletionTime() {
        return completionTime;
    }
}
