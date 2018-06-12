package watcherThreads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
/**Not necessary*/
public class WriteOutputBlockingQueue implements Runnable  {
    private BlockingQueue outputQueue;
    public WriteOutputBlockingQueue(BlockingQueue outputQueue){
        this.outputQueue=outputQueue;
    }
    @Override
    public void run() {
        System.out.printf("Writer Active");
        while(true) {
            // System.out.println(outputQueue);
            try {
                Object filepath =  outputQueue.take();
                Object output = outputQueue.take();
                FileWriter ofstream = new FileWriter(filepath.toString());
                try (BufferedWriter out = new BufferedWriter(ofstream)) {
                    out.write( output.toString() );
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
