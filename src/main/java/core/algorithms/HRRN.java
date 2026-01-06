package core.algorithms;

import core.model.Process;

import java.util.Comparator;
import java.util.List;

public class HRRN extends algorithm {

    Process stratingProcess ;
    public HRRN(List<Process> p) {
        this.processlist = this.deepCopy(p);
        this.stratingProcess = this.processlist.stream()
                        .min(Comparator.comparingInt(Process::getArrivalTime)).get();
    }

    @Override
    public List<Process> schedule() { //todo : فرمول الگوریتم رو چک کن
        List<Process> readyQueue;
        int now =stratingProcess.getArrivalTime();
        while (!processlist.isEmpty()){
            int current= now;
            readyQueue = processlist.stream().filter(p -> p.getArrivalTime() <= current)
                    .toList();
            if(readyQueue.isEmpty()){
                now++;
            }else {
                stratingProcess= readyQueue.stream()
                        .max(Comparator.comparingDouble( p ->(double)p.getWaitingTime()/p.getServiceTime())).get();
                for (int i =0 ; i< stratingProcess.getServiceTime() ; i++){
                    ganttChart.add(stratingProcess);
                    now++;
                    readyQueue.stream().filter( n -> n!=stratingProcess).forEach(p -> p.setWaitingTime(p.getWaitingTime()+1));
                }
                stratingProcess.setCompletionTime(now);
                stratingProcess.setTurnaroundTime(stratingProcess.getCompletionTime() - stratingProcess.getArrivalTime());
                stratingProcess.setWaitingTime(stratingProcess.getTurnaroundTime() - stratingProcess.getServiceTime());
                processlist.remove(stratingProcess);

            }
        }
        return this.ganttChart;
    }
}
