package core.algorithms;

import core.MLQ;
import core.model.MyPriorityQueue;
import core.model.ProcessView;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import core.model.Process;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SJF_Test {
    @Test
    public void sameArrival(){
        List<Process>processes = Arrays.asList(
                new Process("p0", 0, 6, 1),
                new Process("p1", 0, 4, 1),
                new Process("p2", 0, 2, 1)
        );

        SJF sjf = new SJF(processes);
        List<Process> p =  sjf.schedule();
        MyPriorityQueue q = new MyPriorityQueue("FCFS",1);
        List<ProcessView> results = q.getView(p);

        assertEquals(2, results.get(0).getCompletionTime()); //p2
        assertEquals(6, results.get(1).getCompletionTime()); //p1
        assertEquals(12, results.get(2).getCompletionTime()); //p0
    }

    @Test
    public void laterButShorter(){
        List<Process>processes = Arrays.asList(
                new Process("p0", 0, 7, 1),
                new Process("p1", 2, 3, 1),
                new Process("p2", 4, 2, 1)
        );

        SJF sjf = new SJF(processes);
        List<Process> p =  sjf.schedule();
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> results = q.getView(p);

        assertEquals(7, results.get(0).getCompletionTime()); // p0
        assertEquals(9, results.get(1).getCompletionTime()); // p2
        assertEquals(12, results.get(2).getCompletionTime()); //p1
    }

    @Test
    public void differentArrivals(){
        List<Process>processes = Arrays.asList(
                new Process("p0", 0, 8, 1),
                new Process("p1", 1, 4, 1),
                new Process("p2", 2, 2, 1),
                new Process("p3", 3, 1, 1),
                new Process("p4", 4, 3, 1)
        );

        SJF sjf = new SJF(processes);
        List<Process> p =  sjf.schedule();
        MyPriorityQueue q = new MyPriorityQueue("FCFS",1);
        List<ProcessView> results = q.getView(p);

        assertEquals(8, results.get(0).getCompletionTime()); // p0
        assertEquals(9, results.get(1).getCompletionTime()); // p3
        assertEquals(11, results.get(2).getCompletionTime()); //p2
        assertEquals(14, results.get(3).getCompletionTime()); // p4
        assertEquals(18, results.get(4).getCompletionTime()); //p1
    }

    @Test
    public void testNo1(){
        List<Process> processes = Arrays.asList(
                new Process("p1",0,2,0),
                new Process("p6",0,8,0),
                new Process("p7",11,3,0)
        );
        SJF sjf = new SJF(processes);
        List<Process> p =  sjf.schedule();
        MyPriorityQueue q = new MyPriorityQueue("FCFS",1);
        List<ProcessView> results = q.getView(p);
        assertEquals("p1",results.get(0).getProcessName());
        assertEquals("p6",results.get(1).getProcessName());
        assertEquals("p7",results.get(3).getProcessName());

    }
}
