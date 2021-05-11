package Apps;
import DataStructures.CircularDoublyLinkedList;
import java.io.*;

public class Scheduler {
    private static final int QUANTUM = 20;

    public static void main(String[] args) {
        // make sure the right number of arguments were passed in
        if(args.length != 1) {
            System.err.println("Error: usage - Scheduler <testFile>");
            System.exit(1);
        }

        // open the test file for reading
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
        } catch(FileNotFoundException e) {
            System.err.println("Error: file " + args[0] + " not found.");
            System.exit(1);
        }

        // now set up the events object
        Events events = new Events(in, QUANTUM);

        // start the main loop
        CircularDoublyLinkedList<Job> dcList = new CircularDoublyLinkedList<>();
        
        mainLoop(events, dcList);
    }

    /*
     * mainLoop
     *
     * Processes every event until there are no more to process. This
     * occurs when the list is empty and there are no more entries in the
     * test file.
     */
    private static void mainLoop(Events events, CircularDoublyLinkedList<Job> list) {
        Job j;
        int lastEventTime = 0;
        int e;
        try{
            while((e = events.getNextEvent((Job)list.current())) >= 0) {
                switch(e) {
                case Events.JOB_ENTER:
                    j = events.getJob();
                    System.out.println("Job entering(time = " +
                        events.getTime() + "): " + j.toString());

                    // may need to reset the quantum or update current run time
                    if(list.current()== null)
                        events.newQuantum();
                    else
                        ((Job)list.current()).updateRunTime(events.getTime() - lastEventTime);

                    list.addLast(j);
                    printCurrentState(list);
                    break;

                case Events.JOB_FINISH:
                    j = (Job)list.removeFirst();
                    j.updateRunTime(j.getRemaining());
                    System.out.println("Job finished(time = " +
                                                                     events.getTime() + "): " + j.toString());
                    //j = null;
                    printCurrentState(list);
                    break;

                case Events.QUANTUM_EXPIRE:
                    j = (Job)list.current();
                    if(j != null) {
                        j.updateRunTime(events.getTime() - lastEventTime);
                        System.out.println("Job being replaced(time = " +
                                    events.getTime() + "): " +
                                    j.toString());
                        list.next();
                        j = (Job)list.current();
                        System.out.println("Job being restarted(time = " +
                               events.getTime() + "): " + j.toString());
                        printCurrentState(list);
                    }
                    break;
                }
                lastEventTime = events.getTime();
                System.out.println();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void printCurrentState(CircularDoublyLinkedList<Job> list) {
        System.out.println("Current state of system:");
        if(list.isEmpty())
            System.out.println("  No jobs in the system.");
        list.printList();
    }
}
