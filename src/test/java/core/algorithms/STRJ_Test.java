package core.algorithms;

import core.MLQ;
import core.model.MyPriorityQueue;
import core.model.ProcessView;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import core.model.Process;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class STRJ_Test {

    @Test
    public void testSRTFThreeProcesses() {
        List<Process> processes = Arrays.asList(
                new Process("P1", 0, 8, 1),
                new Process("P2", 1, 4, 1),
                new Process("P3", 2, 2, 1)
        );

        SRTF srtf = new SRTF(processes);
        srtf.schedule();

        List<Process> result = srtf.getProcesslist();

        assertEquals(14, result.get(0).getCompletionTime());
        assertEquals(7, result.get(1).getCompletionTime());
        assertEquals(4, result.get(2).getCompletionTime());
    }

    @Test
    public void testSRTFLateButShort() {
        List<Process> processes = Arrays.asList(
                new Process("P1", 0, 7, 1),
                new Process("P2", 1, 5, 1),
                new Process("P3", 3, 2, 1),
                new Process("P4", 4, 4, 1)
        );

        SRTF srtf = new SRTF(processes);
        srtf.schedule();

        List<Process> result = srtf.getProcesslist();

        assertEquals(18, result.get(0).getCompletionTime()); // P1 //16
        assertEquals(8, result.get(1).getCompletionTime());  // P2 //6
        assertEquals(5, result.get(2).getCompletionTime());  // P3 //5
        assertEquals(12, result.get(3).getCompletionTime()); // P4 //10
    }

    @Test
    public void testSRTFSameTime() {
        List<Process> processes = Arrays.asList(
                new Process("P1", 0, 6, 1),
                new Process("P2", 0, 3, 1),
                new Process("P3", 0, 5, 1),
                new Process("P4", 0, 2, 1),
                new Process("P5", 0, 1, 1)
        );

        SRTF srtf = new SRTF(processes);
        srtf.schedule();

        List<Process> result = srtf.getProcesslist();

        assertEquals(17, result.get(0).getCompletionTime()); // P1
        assertEquals(6, result.get(1).getCompletionTime());  // P2
        assertEquals(11, result.get(2).getCompletionTime()); // P3
        assertEquals(3, result.get(3).getCompletionTime());  // P4
        assertEquals(1, result.get(4).getCompletionTime());  // P5
    }

    @Test
    public void namesTest() {
        List<Process> processes = Arrays.asList(
                new Process("p4",13,6,2),
                new Process("p5",15,2,2),
                new Process("p6",14,1,2)
        );

        SRTF srtf = new SRTF(processes);
        List<Process> result =  srtf.schedule();

        assertEquals("p4", result.get(0).getProcessName());
        assertEquals("p6", result.get(1).getProcessName());
        assertEquals("p5", result.get(2).getProcessName());
        assertEquals("p5", result.get(3).getProcessName());
        assertEquals("p4", result.get(4).getProcessName());
        assertEquals("p4", result.get(5).getProcessName());
        assertEquals("p4", result.get(6).getProcessName());
        assertEquals("p4", result.get(7).getProcessName());
        assertEquals("p4", result.get(8).getProcessName());
    }

    @Test
    public void testNo1(){
        List<Process> processes = Arrays.asList(
                new Process("p3",0,1,1),
                new Process("p4",0,12,1)
        );
        SRTF srtf = new SRTF(processes);
        MyPriorityQueue q = new MyPriorityQueue();
        List<Process> p = srtf.schedule();
        List<ProcessView> results = q.getView(p );
        assertEquals("p3",results.get(0).getProcessName());
        assertEquals("p4",results.get(1).getProcessName());

    }

}
