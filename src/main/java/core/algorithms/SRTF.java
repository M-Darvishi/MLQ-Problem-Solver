package core.algorithms;

import core.model.Process;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class SRTF extends algorithm{

    public SRTF(List<Process> p) {
        this.processlist = this.deepCopy(p);
    }

    @Override
    public List<Process> schedule(){
        List<Process> readyQueue = new ArrayList<>();
        List<Process> remainingProcesses = new ArrayList<>(this.processlist);
        int now = 0 ;

        while (!remainingProcesses.isEmpty() || !readyQueue.isEmpty()){
            for( Process p : new ArrayList<>(remainingProcesses)){
                if(p.getArrivalTime()<= now){
                    readyQueue.add(p);
                    remainingProcesses.remove(p);
                }
            }
            if (!readyQueue.isEmpty()){
                Process nextProcess = readyQueue.stream()
                        .min(Comparator.comparingInt(Process::getRemainingTime)).get();
                this.ganttChart.add(nextProcess);
                nextProcess.setRemainingTime(nextProcess.getRemainingTime()-1);
                now++;
                if(nextProcess.getRemainingTime()==0){
                    readyQueue.remove(nextProcess);
                    nextProcess.setCompletionTime(now);
                    nextProcess.setTurnaroundTime(nextProcess.getCompletionTime() - nextProcess.getArrivalTime());
                    nextProcess.setWaitingTime(nextProcess.getTurnaroundTime() - nextProcess.getServiceTime());
                }
            }else {
                now++;
            }
        }
        return this.ganttChart;
    }
}
