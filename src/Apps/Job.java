package Apps;
import java.util.*;

/**
 * Job class which manages job id, start time, total time,
 * and actual run time
 * @author ITSC 2214 Q.
 * @version 1.0
 */
public class Job implements Cloneable, Comparable<Job> {
    // jobs id number
    private int id;

    // time remaining for the job
    private int startTime;
    private int totalTime;
    private int runTime;

    /**
     * constructor 
     * initialize a job by parsing a line which includes
     * job id number, arrival time, and total run time needed
     * @param line 
     */
    public Job(String line) {
        // parse the string
        StringTokenizer tok = new StringTokenizer(line);

        // simple check for correctness
        if (tok.countTokens() != 3) {
            System.err.println("Error: invalid test file format.");
            System.exit(1);
        }

        /*
        * first token - job id number
        * second token - arrival time
        * third token - total run time needed
        * NOTE: no token format verification performed - not real robust :(
        */

        // get the job's id number
        id = Integer.parseInt(tok.nextToken());

        // get the start time
        startTime = Integer.parseInt(tok.nextToken());

        // get the total run time
        totalTime = Integer.parseInt(tok.nextToken());

        // set the time it has run so far
        runTime = 0;
    }

    /**
     * retrieve job's start time 
     * @return startTime
     */
    public int getStart() {
        return startTime;
    }

    /**
     * retrieve the required remaining time to run the job
     * @return remaining time
     */
    public int getRemaining() {
        return totalTime - runTime;
    }

    /**
     * increment the runtime time
     * @param lastRunTime 
     */
    public void updateRunTime(int lastRunTime) {
        runTime += lastRunTime;
    }

    /**
     * clone the object itself and return a new object
     * @return cloned object
     */
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } 
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    /**
     * retrieve the id
     * @return job id
     */
    public int getID() {
        return this.id;
    }

    /**
     * text representation of the job itself
     * @return text representation
     */
    public String toString() {
        String s = new String("  Job " + id
                + ": start = " + startTime
                + "; total needed = " + totalTime
                + "; running time = " + runTime);
        return s;
    }

    /**
     * implement the compareable interface
     * compare the object itself with another job in 
     * the parameter list
     * 
     * @param another
     * @return 
     *     0: object itself is same as another job
     *     other: object itself is greater or less than another job
     */
    @Override
    public int compareTo(Job another) {
        if (this.id == another.getID())
            return 0;
        else if (this.id > another.getID())
            return 1;
        else
            return -1;
    }
}
