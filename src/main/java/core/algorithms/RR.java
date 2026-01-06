package core.algorithms;

import core.model.Process;
import java.util.*;

public class RR extends algorithm{

    private int quantumTime;
    private int policy;

    public RR(List<Process> p, int quantumTime , int policy) {
        this.processlist = this.deepCopy(p);
        this.quantumTime = quantumTime;
        this.policy =policy;
    }

    @Override
    public List<Process> schedule(){
        Queue<Process> readyQueue = new LinkedList<>();
        List<Process> remainingProcesses = new ArrayList<>(this.processlist);
        int now = 0 ;

        while (!remainingProcesses.isEmpty() || !readyQueue.isEmpty()){
            Iterator<Process> iterator = remainingProcesses.iterator();
            while (iterator.hasNext()) {
                Process p = iterator.next();
                if(p.getArrivalTime() <= now){
                    readyQueue.add(p);
                    iterator.remove();
                }
            }
            if (readyQueue.isEmpty()) {
                now++;
                continue;
            }
            Process currentProcess = readyQueue.poll();
            int time = Math.min(quantumTime , currentProcess.getRemainingTime());
            List<Process> lastOnes = new ArrayList<>();
            for (int i = 0; i< time; i++) {
                ganttChart.add(currentProcess);
                now++;
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);
                List<Process> justArrivedNow = new ArrayList<>();
                for (Process p : new ArrayList<>(remainingProcesses)) {
                    if (p.getArrivalTime() <= now) {
                        readyQueue.add(p);
                        remainingProcesses.remove(p);
                        if (p.getArrivalTime() == now) {
                            justArrivedNow.add(p);
                        }
                    }
                }
                if (!justArrivedNow.isEmpty()) {
                    lastOnes = justArrivedNow;
                }
            }
            if (currentProcess.getRemainingTime() > 0) {
                if(!lastOnes.isEmpty() && lastOnes.get(0).getArrivalTime()==now){
                    readyQueue.removeAll(lastOnes);
                    if (policy == 1) {
                        readyQueue.addAll(lastOnes);
                        readyQueue.add(currentProcess);
                    } else {
                        readyQueue.add(currentProcess);
                        readyQueue.addAll(lastOnes);
                    }
                }else {
                    readyQueue.add(currentProcess);
                }
            } else {
                currentProcess.setCompletionTime(now);
                currentProcess.setTurnaroundTime(currentProcess.getCompletionTime() - currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getServiceTime());
            }
        }

        return ganttChart;
    }
}