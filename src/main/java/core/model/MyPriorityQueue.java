package core.model;

import core.algorithms.*;

import java.util.ArrayList;
import java.util.List;

public class MyPriorityQueue {
    private List<Process> processList;
    private List<Process> ganttChart;
    private String algoType ;
    private int quantumTime;
    private int priority;
    private int policy;

    public MyPriorityQueue() {}

    public MyPriorityQueue(String algoType , int priority ) {
        this.algoType = algoType;
        this.quantumTime=0;
        if(algoType.equals("SRTF"))this.quantumTime = 1;
        processList = new ArrayList<>();
    }

    public MyPriorityQueue(String algoType, int quantumTime, int priority,int policy) {
        this.algoType = algoType;
        this.quantumTime = quantumTime;
        processList = new ArrayList<>();
        this.priority= priority;
        this.policy = policy;

    }

    public int getPriority() {
        return priority;
    }
    public int getQuantumTime() {
        return quantumTime;
    }
    public int getPolicy() {
        return policy;
    }
    public String getAlgoType() {
        return algoType;
    }
    public List<Process> getGanttChart() {
        return ganttChart;
    }

    public void addProcess(Process p){
        processList.add(p);
    }

    public  void  schedule(){
        switch (algoType){
            case "FCFS":
                FCFS fcfs = new FCFS(processList);
                this.ganttChart=fcfs.schedule();
                break;
            case "RR":
                RR rr = new RR(processList , quantumTime , policy );
                this.ganttChart = rr.schedule();
                break;
            case "SRTF":
                SRTF srtf = new SRTF(processList);
                this.ganttChart = srtf.schedule();
                break;
            case "SJF":
                SJF sjf = new SJF(processList);
                this.ganttChart = sjf.schedule();
                break;
            case "HRRN":
                HRRN hrrn = new HRRN(processList);
                this.ganttChart = hrrn.schedule();
                break;
            default:
                break;
        }
        for(Process p : this.ganttChart){
            p.resetRemainingTime();
        }
    }

    public List<Process> getProcessList() {
        return processList;
    }

    public boolean isGanttEmpty(){
        if(this.ganttChart==null || this.ganttChart.isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    public boolean hasProcess(int time){
        for (Process i : this.ganttChart){
            if(i.getArrivalTime()<= time){
                return true;
            }
        }
        return  false;
    }

    public ProcessView executeSlice(int now ,List<Process> processes ,boolean cheked ){
        Process executedOne = processes.get(0);

        int sliceTime =0;
        while (sliceTime < processes.size() &&
                processes.get(sliceTime).getProcessName().equals(executedOne.getProcessName()) ){
            sliceTime++;
        }

        for (int i=0 ; i<sliceTime && !processes.isEmpty() ; i++){
            processes.remove(0);
        }

        int completion = now+sliceTime;
        if(executedOne.getServiceTime()==0){
            cheked=true;
        }
        return new ProcessView(executedOne.getProcessName(), now, sliceTime , completion,executedOne.getArrivalTime(),executedOne.getServiceTime());
    }

    public List<ProcessView> getView( List<Process> processes ) {
        List<ProcessView> slices = new ArrayList<>();
        int now = 0;
        while (!processes.isEmpty()) {
            Process next = processes.get(0);
            if (next.getArrivalTime() > now) {
                int idleTime = next.getArrivalTime() - now;
                slices.add(new ProcessView("IDLE", now, idleTime, next.getArrivalTime(),now,0));
                now = next.getArrivalTime();
                continue;
            }

            boolean isProcessFinished=false;
            ProcessView slice = executeSlice(now , processes , isProcessFinished );
            slices.add(slice);
            now = slice.getCompletionTime();
        }
        return slices;
    }

    public List<Process> deepCopy(List<Process> myList){
        List<Process> copyList= myList.stream().
                map( p -> new Process(p.getProcessName(), p.getArrivalTime(), p.getServiceTime(),p.getPriority()))
                .toList();

        return new ArrayList<>(copyList);
    }
}
