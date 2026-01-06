package core.algorithms;

import core.MLQ;
import core.model.MyPriorityQueue;
import core.model.Process;
import core.model.ProcessView;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MLQ_Test {

    @Test
    public void simpleTest(){

        List<MyPriorityQueue> queues= Arrays.asList(
                new MyPriorityQueue("FCFS" , 0),
                new MyPriorityQueue("RR",3,1,1),
                new MyPriorityQueue("SRTF",2)
        );
        List<Process> processes = Arrays.asList(
                new Process("p0",2,5,0),
                new Process("p1",5,1,0),
                new Process("p2",0,4,1),
                new Process("p3",6,2,1),
                new Process("p4",13,6,2),
                new Process("p5",15,2,2),
                new Process("p6",14,1,2)
        );
        MLQ mlq=new MLQ(processes,queues);
        MyPriorityQueue q = new MyPriorityQueue();
        List<Process> p = mlq.schedule();
        List<ProcessView> results = q.getView(p);
        assertEquals("p2",results.get(0).getProcessName());
        assertEquals("p0",results.get(1).getProcessName());
        assertEquals("p1",results.get(2).getProcessName());
        assertEquals("p2",results.get(3).getProcessName());
        assertEquals("p3",results.get(4).getProcessName());
        assertEquals("p4",results.get(6).getProcessName());
        assertEquals("p6",results.get(7).getProcessName());
        assertEquals("p5",results.get(8).getProcessName());
        assertEquals("p4",results.get(9).getProcessName());
    }

    @Test
    public void testNo1(){
        List<MyPriorityQueue> queues= Arrays.asList(
                new MyPriorityQueue("SJF" , 0),
                new MyPriorityQueue("SRTF",1),
                new MyPriorityQueue("SJF",2)
        );
        List<Process> processes = Arrays.asList(
                new Process("p1",0,2,0),
                new Process("p2",0,7,2),
                new Process("p3",0,1,1),
                new Process("p4",0,12,1),
                new Process("p5",0,3,2),
                new Process("p6",0,8,0),
                new Process("p7",11,3,0)
        );
        MLQ mlq=new MLQ(processes,queues);
        MyPriorityQueue q = new MyPriorityQueue();
        List<Process> p = mlq.schedule();
        List<ProcessView> results = q.getView(p );
        assertEquals("p1",results.get(0).getProcessName());
        assertEquals("p6",results.get(1).getProcessName());
        assertEquals("p3",results.get(2).getProcessName());
        assertEquals("p7",results.get(3).getProcessName());
        assertEquals("p4",results.get(4).getProcessName());
        assertEquals("p5",results.get(5).getProcessName());
        assertEquals("p2",results.get(6).getProcessName());
    }

    @Test
    public void testNo2(){
        List<MyPriorityQueue> queues= Arrays.asList(
                new MyPriorityQueue("RR" , 4,0,1),
                new MyPriorityQueue("FCFS",1),
                new MyPriorityQueue("SRTF",2)
        );
        List<Process> processes = Arrays.asList(
                new Process("p0",0,12,0),
                new Process("p1",5,14,0),
                new Process("p2",0,4,1),
                new Process("p3",0,6,1),
                new Process("p4",0,4,2),
                new Process("p5",0,6,2)
        );
        MLQ mlq=new MLQ(processes,queues);
        MyPriorityQueue q = new MyPriorityQueue();
        List<Process> p = mlq.schedule();
        List<ProcessView> results = q.getView(p );
        assertEquals("p0",results.get(0).getProcessName());
        assertEquals("p1",results.get(1).getProcessName());
        assertEquals("p0",results.get(2).getProcessName());
        assertEquals("p1",results.get(3).getProcessName());
        assertEquals("p2",results.get(4).getProcessName());
        assertEquals("p3",results.get(5).getProcessName());
        assertEquals("p4",results.get(6).getProcessName());
        assertEquals("p5",results.get(7).getProcessName());
    }
}
