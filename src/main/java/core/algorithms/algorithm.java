package core.algorithms;

import java.util.ArrayList;
import java.util.List;
import core.model.Process;

public abstract class algorithm {
    protected List<Process> processlist = new ArrayList<>();
    protected List<Process> ganttChart= new ArrayList<>();

    public  abstract List<Process> schedule();

    public List<Process> deepCopy(List<Process> myList){
        List<Process> copyList= myList.stream().
                map( p -> new Process(p.getProcessName(), p.getArrivalTime(), p.getServiceTime(),p.getPriority()))
                .toList();

        return new ArrayList<>(copyList);
    }

    public List<Process> getProcesslist() {
        return processlist;
    }
    public void setProcesslist(List<Process> processlist) {
        this.processlist = processlist;
    }

    public List<Process> getGanttChart() {
        return ganttChart;
    }
    public void setGanttChart(List<Process> ganttChart) {
        this.ganttChart = ganttChart;
    }
}
