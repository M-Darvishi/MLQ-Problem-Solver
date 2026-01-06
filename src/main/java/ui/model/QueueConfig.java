package ui.model;

public class QueueConfig {

    private static Integer priorityCnt= 0;
    private  Integer priority;
    private final String algorithm;
    private final Integer timeSlice;
    private final Integer policy;

    public QueueConfig( String algorithm) {
        this.priority=++priorityCnt;
        this.algorithm = algorithm;
        this.timeSlice = 0;
        this.policy = 0;
    }

    public QueueConfig( String algorithm, Integer timeSlice, Integer policy) {
        this.priority=++priorityCnt;
        this.algorithm = algorithm;
        this.timeSlice = timeSlice;
        this.policy = policy;
    }

    public Integer getPriority() { return priority; }
    public String getAlgo() { return algorithm; }
    public Integer getTimeSlice() { return timeSlice; }
    public int getPolicy() {
        return policy;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
