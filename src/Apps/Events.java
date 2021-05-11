package Apps;

import java.io.*;

public class Events {
    // a few constants
    public static final int JOB_ENTER = 0;
    public static final int JOB_FINISH = 1;
    public static final int QUANTUM_EXPIRE = 2;
    public static final int NUM_EVENTS = 3;

    // array to hold the next time each event will occur
    private int[] eventTime;

    // file to read from
    private BufferedReader testFile;

    // quantum for this scheduler
    private int quantum;

    // job that will be the next one to start - null means no more jobs
    private Job nextJob;

    // time - virtual units
    private int time;

    /*
     * constructor
     */
    public Events(BufferedReader file, int quantum) {
        // set up some basics
        testFile = file;
        this.quantum = quantum;
        time = 0;

        // figure out the first job
        nextJob = buildNextJob();
        if(nextJob == null) {
            System.out.println("Error: empty test file.");
            System.exit(1);
        }

        // set-up the array of event times
        eventTime = new int[NUM_EVENTS];
        eventTime[JOB_ENTER] = nextJob.getStart();
        eventTime[JOB_FINISH] = nextJob.getStart() + nextJob.getRemaining();
        eventTime[QUANTUM_EXPIRE] = nextJob.getStart() + quantum;
    }

    /*
     * getNextEvent
     *
     * This method is the guts of the whole program. It determines what
     * event should happen next, updates the time to that event, and then
     * makes it happen (by returning the appropriate value). The job 
     * parameter passed in contains a reference to the currently running
     * job. If the next event is to start a new job, this reference is set
     * to that new job by the end of the method.
     */
    public int getNextEvent(Job job) {
        // calculate the time the current job would finish (w/o a quantum)
        if(job != null)
            eventTime[JOB_FINISH] = time + job.getRemaining();
        else if(eventTime[JOB_ENTER] == Integer.MAX_VALUE)
            return -1;  // no more jobs to do
        else
            eventTime[JOB_FINISH] = Integer.MAX_VALUE;

        // initially, the first event is winning
        int nextEvent = 0;
        int when = eventTime[0];

        // find the next event
        for(int i=1; i<NUM_EVENTS; i++) {
            if(eventTime[i] < when) {
                nextEvent = i;
                when = eventTime[i];
            }
        }

        // are there any events to process
        if(when == Integer.MAX_VALUE)
            return -1;  // all done

        if(nextEvent == QUANTUM_EXPIRE)
            eventTime[QUANTUM_EXPIRE] += quantum;

        // update the current time and return
        time = when;
        return nextEvent;

        /* 
       * NOTE: if the nextEvent is a JOB_ENTER, the main scheduling
       * loop must immediately call getJob(). This will return the
       * nextJob and then get the next one from the test file.
       */
    }

    /*
     * getJob
     *
     * Returns the next job to run and then gets another one from
     * the test file. It also updates the time when the next job
     * will enter the system.
     */
    public Job getJob() {
        // this should never happen, but just in case
        if(nextJob == null)
            return null;

        // make a copy of the job to run
        Job job = (Job)nextJob.clone();

        // now get the next job to run and set the time it starts
        nextJob = buildNextJob();
        if(nextJob != null)
            eventTime[JOB_ENTER] = nextJob.getStart();
        else
            eventTime[JOB_ENTER] = Integer.MAX_VALUE;

        return job;
    }

    /*
     * buildNextJob
     *
     * Reads the next line in the file and creates a new job. If there
     * is no more data in the file, just return null.
     */
    public Job buildNextJob() {
        String line = null;

        // read the next line
        try {
            line = testFile.readLine();
        } catch(IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        // see if the file is empty
        if(line == null)
        return null;

        // build the next job
        return new Job(line);
    }

    /*
     * newQuantum
     *
     * Increments the quantum. Only need to call this if there is
     * currently no active job.
     */
    public void newQuantum() {
        eventTime[QUANTUM_EXPIRE] = time + quantum;
    }

    /*
     * getTime
     */
    public int getTime() {
        return time;
    }
}
