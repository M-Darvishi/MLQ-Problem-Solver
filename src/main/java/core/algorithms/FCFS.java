package core.algorithms;

import core.model.Process;
import java.util.Comparator;
import java.util.List;

public class FCFS  extends algorithm{

    public FCFS( List<Process> p ) {
        this.processlist = this.deepCopy(p);
    }

    @Override
    public List<Process> schedule(){
        processlist.sort(Comparator.comparingInt(p -> p.getArrivalTime())); //todo: چک کن چرا کامنت کرده بودی
        int now =0 ;
        for(Process p : processlist){
            if (now < p.getArrivalTime()) {
                now = p.getArrivalTime();
            }
            for (int i = 0; i < p.getServiceTime(); i++) {
                ganttChart.add(p);
                now++;
            }

            p.setCompletionTime(now);
            p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
            p.setWaitingTime(p.getTurnaroundTime() - p.getServiceTime());
        }
        return ganttChart;
    }
}
