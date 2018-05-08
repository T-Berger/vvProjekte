import machine.MealyThread;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import watcherThreads.InputFileWatcher;
import watcherThreads.WriteOutputBlockingQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

public class MainTest {
    File a = new File("./json/AAA.msg");
    File b = new File("./json/AA.msg");
    File c = new File("./json/A.msg");
    @Before
    public void setUp() throws Exception {

        a.createNewFile();
//        b.createNewFile();
//        c.createNewFile();

        BlockingQueue inputBlockingQueue = new LinkedBlockingQueue();
        BlockingQueue outputBlockingQueue = new LinkedBlockingQueue();
        InputFileWatcher directoryFileWatcherOnInput = new InputFileWatcher(inputBlockingQueue);
        WriteOutputBlockingQueue blockQueueing = new WriteOutputBlockingQueue(outputBlockingQueue);

        MealyThread mealyThread = new MealyThread(inputBlockingQueue, outputBlockingQueue, "MachineTEST");
        new Thread(directoryFileWatcherOnInput).start();
        new Thread(mealyThread).start();
        new Thread(blockQueueing).start();
        //Warte auf die anderen Threads
        Thread.sleep(10000);
        System.out.println("Fertig mit warten");
    }


    @Test
    public void abnahmeTestA() throws IOException, InterruptedException {
        Scanner in = new Scanner(new FileReader("./output/AAA.out"));
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        String outString = sb.toString();
        assertEquals("BBB",outString);
        b.createNewFile();
        Thread.sleep(10000);
        System.out.println("Fertig mit warten");
    }
    @Test
    public void abnahmeTestB() throws FileNotFoundException, InterruptedException {
        Scanner in = new Scanner(new FileReader("./output/AA.out"));
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        String outString = sb.toString();
        assertEquals("BB",outString);
    }
//    @Test
//    public void abnahmeTestC() throws FileNotFoundException, InterruptedException {
//        Scanner in = new Scanner(new FileReader("./output/A.out"));
//        StringBuilder sb = new StringBuilder();
//        while(in.hasNext()) {
//            sb.append(in.next());
//        }
//        in.close();
//        String outString = sb.toString();
//        assertEquals("B",outString);
//    }
    @After
    public void tearDown() throws Exception {
        new File("./output/AA.out").delete();
        new File("./output/AAA.out").delete();
//                c.delete();
    }

}