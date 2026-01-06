package core.algorithms;

import core.model.MyPriorityQueue;
import core.model.Process;
import core.model.ProcessView;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HRRN_Test {
    @Test
    public void simpleTest(){
        List<core.model.Process> processes = Arrays.asList(
                new core.model.Process("p0", 0, 3, 1),
                new core.model.Process("p1", 2, 6, 1),
                new core.model.Process("p2", 4, 4, 1)
        );

        HRRN hrrn = new HRRN(processes);
        List<Process> p =  hrrn.schedule();
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> results = q.getView(p);

        assertEquals(3, results.get(0).getCompletionTime()); // p0
        assertEquals(9, results.get(1).getCompletionTime()); // p1
        assertEquals(13, results.get(2).getCompletionTime()); //p2
    }

    @Test
    public void sameArrival(){
        List<core.model.Process> processes = Arrays.asList(
                new core.model.Process("p0", 0, 5, 1),
                new core.model.Process("p1", 0, 3, 1),
                new core.model.Process("p2", 0, 2, 1)
        );

        HRRN hrrn = new HRRN(processes);
        List<Process> p =  hrrn.schedule();
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> results = q.getView(p);

        assertEquals(5, results.get(0).getCompletionTime()); // p2
        assertEquals(7, results.get(1).getCompletionTime()); // p1
        assertEquals(10, results.get(2).getCompletionTime()); //p0
    }

    @Test
    public void laterButShorter(){
        List<core.model.Process> processes = Arrays.asList(
                new core.model.Process("p0", 0, 7, 1),
                new core.model.Process("p1", 2, 3, 1),
                new core.model.Process("p2", 4, 2, 1)
        );

        HRRN hrrn = new HRRN(processes);
        List<Process> p =  hrrn.schedule();
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> results = q.getView(p);

        assertEquals(7, results.get(0).getCompletionTime()); // p0
        assertEquals(10, results.get(1).getCompletionTime()); // p1
        assertEquals(12, results.get(2).getCompletionTime()); //p2
    }

    @Test
    public void bigSmallService(){
        List<core.model.Process> processes = Arrays.asList(
                new core.model.Process("p0", 0, 10, 1),
                new core.model.Process("p1", 1, 1, 1),
                new core.model.Process("p2", 2, 2, 1)
        );

        HRRN hrrn = new HRRN(processes);
        List<Process> p =  hrrn.schedule();
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> results = q.getView(p);

        assertEquals(10, results.get(0).getCompletionTime()); // p0
        assertEquals(11, results.get(1).getCompletionTime()); // p2
        assertEquals(13, results.get(2).getCompletionTime()); //p1
    }

    @Test
    public void lateArrival(){
        List<core.model.Process> processes = Arrays.asList(
                new core.model.Process("p0", 0, 4, 1),
                new core.model.Process("p1", 1, 5, 1),
                new core.model.Process("p2", 10, 2, 1)
        );

        HRRN hrrn = new HRRN(processes);
        List<Process> p =  hrrn.schedule();
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> results = q.getView(p);

        assertEquals(4, results.get(0).getCompletionTime()); // p0
        assertEquals(9, results.get(1).getCompletionTime()); // p2
        assertEquals(10, results.get(2).getCompletionTime()); // idle
        assertEquals(12, results.get(3).getCompletionTime()); //p1
    }

}
