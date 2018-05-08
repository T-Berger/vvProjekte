
import watcherThreads.InputFileWatcher;
import machine.MealyThread;
import watcherThreads.WriteOutputBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * Example and default Application
 * With a the default MealyMachine*/
public class Main {
    public static void main (String[] args){
        BlockingQueue inputBlockingQueue = new LinkedBlockingQueue();
        BlockingQueue outputBlockingQueue = new LinkedBlockingQueue();
        InputFileWatcher directoryFileWatcherOnInput = new InputFileWatcher(inputBlockingQueue);
        WriteOutputBlockingQueue blockQueueing = new WriteOutputBlockingQueue(outputBlockingQueue);

        MealyThread mealyThread = new MealyThread(inputBlockingQueue, outputBlockingQueue, "MachineTEST");
        new Thread(directoryFileWatcherOnInput).start();
        new Thread(mealyThread).start();
        new Thread(blockQueueing).start();
    }
}

