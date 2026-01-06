package core;

import core.algorithms.algorithm;
import core.model.MyPriorityQueue;
import core.model.Process;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MLQ extends algorithm {
    private List<MyPriorityQueue> queues;

    public MLQ(List<Process> processlist ,List<MyPriorityQueue> queues) {
        this.ganttChart = new ArrayList<>();
        this.queues = queues;
//        this.queues.sort((q1, q2) -> Integer.compare(q1.getPriority(), q2.getPriority()));
        allocation(processlist,this.queues);
        for (MyPriorityQueue q : queues){
            q.schedule();
        }
    }

    protected void allocation(List<Process> pList ,List<MyPriorityQueue> pQueues){
        for (Process p : pList){
            pQueues.get(p.getPriority()).addProcess(p);
        }
    }

    @Override
    public List<Process> schedule() {
        int now = 0;
        MyPriorityQueue pq=null;

        while (!isAllEmpty()) {
            boolean flag=false;
            for (int k=0; k<queues.size();k++) {
                pq = queues.get(k);
                if (!pq.hasProcess(now) || pq.isGanttEmpty()) {
                    continue;
                }
                flag=true;

                Process executedOne = pq.getGanttChart().get(0);
                int remainingTime = executedOne.getRemainingTime();
                int sliceTime = Math.min(pq.getQuantumTime(), remainingTime);
                if (pq.getQuantumTime() == 0) {
                    sliceTime = remainingTime;
                }

                for (int j=0; j<sliceTime;j++){
                    pq.getGanttChart().remove(0);
                    this.ganttChart.add(executedOne);
                    now++;
                }
                executedOne.setRemainingTime(remainingTime - sliceTime);
                if (executedOne.getRemainingTime() == 0) {
                    executedOne.setCompletionTime(now);
                    this.processlist.add(executedOne);
                }

                if(flag)break;
            }

            if(!flag){
                now++;
            }

        }
        for(Process p : this.ganttChart){
            p.resetRemainingTime();
        }
        return this.ganttChart;

    }

    private  boolean isAllEmpty(){
        for(MyPriorityQueue q: queues){
            if(!q.isGanttEmpty()){
                return false;
            }
        }
        return true;
    }

}
