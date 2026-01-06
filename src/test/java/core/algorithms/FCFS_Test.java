package core.algorithms;

import core.MLQ;
import core.model.MyPriorityQueue;
import core.model.ProcessView;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import core.model.Process;

public class FCFS_Test {

    @Test
    public void testSimpleFCFS() {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 0, 4, 1));
        processes.add(new Process("P2", 1, 3, 1));
        processes.add(new Process("P3", 2, 1, 1));

        FCFS fcfs = new FCFS(processes);
        fcfs.schedule();

        List<Process> result = fcfs.getProcesslist();

        assertEquals(4, result.get(0).getCompletionTime()); // P1
        assertEquals(7, result.get(1).getCompletionTime()); // P2
        assertEquals(8, result.get(2).getCompletionTime()); // P3
    }

    @Test
    public void testFCFSWithIdleCPU() {
        List<Process> processes = Arrays.asList(
                new Process("P1", 5, 2, 1), // میاد دیرتر
                new Process("P2", 0, 3, 1)
        );

        FCFS fcfs = new FCFS(processes);
        fcfs.schedule();

        List<Process> result = fcfs.getProcesslist();
        assertEquals(3, result.get(0).getCompletionTime());
        assertEquals(7, result.get(1).getCompletionTime());
    }


    @Test
    public void testNo1(){
        List<Process> processes = Arrays.asList(
                new Process("p2",0,7,2),
                new Process("p5",0,3,2)
        );

        FCFS fcfs = new FCFS(processes);
        MyPriorityQueue q = new MyPriorityQueue();
        List<Process> p =fcfs.schedule();
        List<ProcessView> results = q.getView(p );
        assertEquals("p2",results.get(0).getProcessName());
        assertEquals("p5",results.get(1).getProcessName());
    }



}