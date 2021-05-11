/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Qiong
 */
public class SchedulerTest {
    
    public SchedulerTest() {
    }

    @Test
    public void testAll() {
        // input test file 
        int[] testlist = {1, 2, 3, 4, 5, 6, 7, 8};
        for (int index = 0; index < testlist.length; index++){
            String inputF = "test-" + testlist[index] + ".dat";
            String expectF = "test-" + testlist[index] + ".results";
            String expectLine = null;

            final String dir = System.getProperty("user.dir");
            System.out.println("current dir = " + dir);
            System.out.println("current class = " + this.getClass().toString());
            Path inputFPath = Paths.get(inputF);
            System.out.println("[Test File Path] : " + inputFPath.toAbsolutePath());
            String classPath = Apps.Scheduler.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            System.out.println("[Apps.Scheduler Path] : " + Apps.Scheduler.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String command = "java -cp " + dir + "/lib/DS_Library_List.jar;" + dir + "/DS_Library_List.jar;"
                    + classPath + "/;"+ dir + "/;" + dir + "/classes/;" + dir + "/build/classes/" + " Apps.Scheduler "  + inputFPath.toAbsolutePath();
            
            System.out.println("command: " + command);
            try {
                System.out.println("Output:");
                //BufferedReader testFile = new BufferedReader(
                //        new InputStreamReader(this.getClass().getResourceAsStream(dir + "./" + expectF)));
                BufferedReader testFile = Files.newBufferedReader(Paths.get(expectF).toAbsolutePath());
                Process process = Runtime.getRuntime().exec(command);

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                String line;
                int count = 0;
                while ((line = reader.readLine()) != null) {
                    expectLine = testFile.readLine();
                    if (! expectLine.equalsIgnoreCase(line)){
                        System.out.println("Output:" + line);
                        System.out.println("Expected:" + expectLine);
                        fail("Not match!");
                    }
                    count++;
                }
                System.out.println("Test " + testlist[index] + " passed. " + count + " lines are same");

                reader.close();
                testFile.close();

            } catch (IOException e) {
                e.printStackTrace();
                fail("Error");
            }
        }
    }
    
}
