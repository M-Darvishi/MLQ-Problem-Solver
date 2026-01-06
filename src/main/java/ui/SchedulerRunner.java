package ui;

import core.MLQ;
import core.algorithms.SJF;
import core.model.MyPriorityQueue;
import core.model.Process;
import core.model.ProcessView;
import ui.model.ProcessModel;
import ui.model.QueueConfig;

import java.util.ArrayList;
import java.util.List;

public class SchedulerRunner {

    List<MyPriorityQueue> priorityQueues= new ArrayList<>();
    List<Process> processList= new ArrayList<>();
    MLQ finalSchedule;

    public SchedulerRunner(List<QueueConfig> queues , List<ProcessModel> processes ) {
        for(QueueConfig q : queues){
            if(q.getAlgo().equals("RR")){
                priorityQueues.add(new MyPriorityQueue(q.getAlgo(),q.getTimeSlice(),q.getPriority(),q.getPolicy())); //todo : policy رو اضافه کن
            }else {
                priorityQueues.add(new MyPriorityQueue(q.getAlgo() , q.getPriority()));
            }
        }
        for(ProcessModel p : processes){
            processList.add(new Process(p.getName(), p.getArrival(), p.getService() , p.getPriority()));
        }
    }

    public List<ProcessView> doTheRun(){
        finalSchedule = new MLQ(processList,priorityQueues);
        processList = finalSchedule.getProcesslist();
        List<Process> p = finalSchedule.schedule();
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> finalGantt =  q.getView(p);
        return finalGantt;
    }

//    public List<ProcessView> runQueues(){
//
//    }

    public List<ProcessModel> setProcessModels(){
        List<ProcessModel> processModels = new ArrayList<>();
        for(Process p : this.processList){
            processModels.add(new ProcessModel(p.getProcessName(), p.getArrivalTime(),
                    p.getServiceTime() , p.getPriority(), p.getCompletionTime()));
        }
        return processModels;
    }

}
