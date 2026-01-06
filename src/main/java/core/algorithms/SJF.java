package core.algorithms;

import core.model.Process;

import java.util.Comparator;
import java.util.List;

public class SJF extends algorithm{

    public SJF(List<Process> p) {
        this.processlist = this.deepCopy(p);
    }

    @Override
    public List<Process> schedule() {
        List<Process> readyQueue;
        int now = 0 ;
        while (!processlist.isEmpty()){
            int current= now;
            readyQueue = processlist.stream().filter(p -> p.getArrivalTime() <= current)
                    .toList();
            if(readyQueue.isEmpty()){
                now++;
            }else {
                Process nextProcess= readyQueue.stream()
                        .min(Comparator.comparingInt(Process::getServiceTime)).get();
                for (int i =0 ; i< nextProcess.getServiceTime() ; i++){
                    this.ganttChart.add(nextProcess);
                    now++;
                    int currentTime = now;
                    processlist.stream()
                            .filter(p -> p != nextProcess && p.getArrivalTime() <= currentTime)
                            .forEach(p -> p.setWaitingTime(p.getWaitingTime() + 1));
                }
                nextProcess.setCompletionTime(now);
                nextProcess.setTurnaroundTime(nextProcess.getCompletionTime() - nextProcess.getArrivalTime());
                nextProcess.setWaitingTime(nextProcess.getTurnaroundTime() - nextProcess.getServiceTime());
                processlist.remove(nextProcess);
            }
        }
        return this.ganttChart;
    }
}
