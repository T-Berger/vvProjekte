package watcherThreads;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

public class InputFileWatcherTest {
    BlockingQueue inputBlockingQueue = new LinkedBlockingQueue();
    InputFileWatcher directoryFileWatcherOnInput = new InputFileWatcher(inputBlockingQueue);
    File a = new File("./json/a.msg");
    File b = new File("./json/b.msg");
    File error = new File("./json/error");
    File txt = new File("./json/e.txt");
    @Before
    public void init() throws IOException, InterruptedException {

        new Thread(directoryFileWatcherOnInput).start();

        a.createNewFile();

    }
    @Test
    public void checkQueue() throws InterruptedException {
        inputBlockingQueue.add(a);
        System.out.println("TEST?");
        assertFalse(false);
        System.out.println(inputBlockingQueue);
        System.out.println(inputBlockingQueue.contains(a));
        boolean result1=inputBlockingQueue.contains(a);
        assertTrue(result1);
    }
    @After
    public void delete(){
       a.delete();

    }

}