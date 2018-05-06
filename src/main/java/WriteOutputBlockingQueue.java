import java.util.concurrent.BlockingQueue;

public class WriteOutputBlockingQueue implements Runnable  {
    private BlockingQueue outputQueue;
    WriteOutputBlockingQueue(BlockingQueue outputQueue){
        this.outputQueue=outputQueue;
    }
    @Override
    public void run() {
        System.out.printf("Writer Active");
        while(true) {
            // System.out.println(outputQueue);
        }
    }
}
