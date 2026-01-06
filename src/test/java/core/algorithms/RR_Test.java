package core.algorithms;

import core.model.MyPriorityQueue;
import core.model.Process;
import core.model.ProcessView;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RR_Test {

    @Test
    public void simpleTest(){
        List<core.model.Process> processes = Arrays.asList(
                new core.model.Process("p0", 0, 4, 1),
                new core.model.Process("p1", 3, 3, 1),
                new core.model.Process("p2", 5, 2, 1)
        );

        RR rr = new RR(processes , 3 , 1 );
        List<Process> p =  rr.schedule();
        for(Process pro : p){
            pro.resetRemainingTime();
        }
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> results = q.getView(p);

        assertEquals("p0", results.get(0).getProcessName());
        assertEquals("p1", results.get(1).getProcessName());
        assertEquals("p0", results.get(2).getProcessName());
        assertEquals("p2", results.get(3).getProcessName());

        rr = new RR(processes , 3 , 2 );
        p =  rr.schedule();
        for(Process pro : p){
            pro.resetRemainingTime();
        }
        results = q.getView(p);

        assertEquals("p0", results.get(0).getProcessName());
        assertEquals("p1", results.get(1).getProcessName());
        assertEquals("p2", results.get(2).getProcessName());
    }


    @Test
    public void sameArrival(){
        List<core.model.Process> processes = Arrays.asList(
                new core.model.Process("p0", 0, 2, 1),
                new core.model.Process("p1", 0, 3, 1),
                new core.model.Process("p2", 0, 4, 1)
        );

        RR rr = new RR(processes , 2 , 1 );
        List<Process> p =  rr.schedule();
        for(Process pro : p){
            pro.resetRemainingTime();
        }
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> results = q.getView(p);
        assertEquals("p0", results.get(0).getProcessName());
        assertEquals("p1", results.get(1).getProcessName());
        assertEquals("p2", results.get(2).getProcessName());
        assertEquals("p1", results.get(3).getProcessName());
        assertEquals("p2", results.get(4).getProcessName());

        rr = new RR(processes , 2 , 2 );
        p =  rr.schedule();
        for(Process pro : p){
            pro.resetRemainingTime();
        }
        results = q.getView(p);
        assertEquals("p0", results.get(0).getProcessName());
        assertEquals("p1", results.get(1).getProcessName());
        assertEquals("p2", results.get(2).getProcessName());
        assertEquals("p1", results.get(3).getProcessName());
        assertEquals("p2", results.get(4).getProcessName());
    }


    @Test
    public void shortAndLongProcesses() {
        List<core.model.Process> processes = Arrays.asList(
                new core.model.Process("p0", 0, 7, 1),
                new core.model.Process("p1", 1, 2, 1),
                new core.model.Process("p2", 2, 5, 1),
                new core.model.Process("p3", 3, 1, 1)
        );

        RR rr = new RR(processes, 2, 1);
        List<Process> p = rr.schedule();
        for (Process pro : p) pro.resetRemainingTime();
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> results = q.getView(p);
        assertEquals("p0", results.get(0).getProcessName());
        assertEquals("p1", results.get(1).getProcessName());
        assertEquals("p2", results.get(2).getProcessName());
        assertEquals("p0", results.get(3).getProcessName());
        assertEquals("p3", results.get(4).getProcessName());
        assertEquals("p2", results.get(5).getProcessName());
        assertEquals("p0", results.get(6).getProcessName());
        assertEquals("p2", results.get(7).getProcessName());
        assertEquals("p0", results.get(8).getProcessName());

        rr = new RR(processes, 2, 2); // پالیسی ۲
        p = rr.schedule();
        for (Process pro : p) pro.resetRemainingTime();
        results = q.getView(p);
        assertEquals("p0", results.get(0).getProcessName());
        assertEquals("p1", results.get(1).getProcessName());
        assertEquals("p0", results.get(2).getProcessName());
        assertEquals("p2", results.get(3).getProcessName());
        assertEquals("p3", results.get(4).getProcessName());
        assertEquals("p0", results.get(5).getProcessName());
        assertEquals("p2", results.get(6).getProcessName());
        assertEquals("p0", results.get(7).getProcessName());
        assertEquals("p2", results.get(8).getProcessName());
    }

    @Test
    public void lateArrival() {
        List<core.model.Process> processes = Arrays.asList(
                new core.model.Process("p0", 0, 3, 1),
                new core.model.Process("p1", 1, 4, 1),
                new core.model.Process("p2", 2, 5, 1),
                new core.model.Process("p3", 10, 2, 1)
        );

        RR rr = new RR(processes, 2, 1);
        List<Process> p = rr.schedule();
        for (Process pro : p) pro.resetRemainingTime();
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> results = q.getView(p);
        assertEquals("p0", results.get(0).getProcessName());
        assertEquals("p1", results.get(1).getProcessName());
        assertEquals("p2", results.get(2).getProcessName());
        assertEquals("p0", results.get(3).getProcessName());
        assertEquals("p1", results.get(4).getProcessName());
        assertEquals("p2", results.get(5).getProcessName());
        assertEquals("p3", results.get(6).getProcessName());
        assertEquals("p2", results.get(7).getProcessName());

        rr = new RR(processes, 2, 2);
        p = rr.schedule();
        for (Process pro : p) pro.resetRemainingTime();
        results = q.getView(p);
        assertEquals("p0", results.get(0).getProcessName());
        assertEquals("p1", results.get(1).getProcessName());
        assertEquals("p0", results.get(2).getProcessName());
        assertEquals("p2", results.get(3).getProcessName());
        assertEquals("p1", results.get(4).getProcessName());
        assertEquals("p2", results.get(5).getProcessName());
        assertEquals("p3", results.get(6).getProcessName());
        assertEquals("p2", results.get(7).getProcessName());
    }


    @Test
    public void mixedServiceTimes() {
        List<core.model.Process> processes = Arrays.asList(
                new core.model.Process("p0", 0, 7, 1),
                new core.model.Process("p1", 5, 2, 1),
                new core.model.Process("p2", 6, 6, 1),
                new core.model.Process("p3", 7, 1, 1),
                new core.model.Process("p4", 8, 3, 1)
        );

        RR rr = new RR(processes, 3, 1);
        List<Process> p = rr.schedule();
        for (Process pro : p) pro.resetRemainingTime();
        MyPriorityQueue q = new MyPriorityQueue();
        List<ProcessView> results = q.getView(p);
        assertEquals("p0", results.get(0).getProcessName());
        assertEquals("p1", results.get(1).getProcessName());
        assertEquals("p2", results.get(2).getProcessName());
        assertEquals("p0", results.get(3).getProcessName());
        assertEquals("p3", results.get(4).getProcessName());
        assertEquals("p4", results.get(5).getProcessName());
        assertEquals("p2", results.get(6).getProcessName());

        rr = new RR(processes, 3, 2);
        p = rr.schedule();
        for (Process pro : p) pro.resetRemainingTime();
        results = q.getView(p);
        assertEquals("p0", results.get(0).getProcessName());
        assertEquals("p1", results.get(1).getProcessName());
        assertEquals("p0", results.get(2).getProcessName());
        assertEquals("p2", results.get(3).getProcessName());
        assertEquals("p3", results.get(4).getProcessName());
        assertEquals("p4", results.get(5).getProcessName());
        assertEquals("p2", results.get(6).getProcessName());
    }

}
