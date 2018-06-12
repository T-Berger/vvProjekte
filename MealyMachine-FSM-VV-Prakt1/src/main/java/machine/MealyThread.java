package machine;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
/**
 * This class handles 2 BlockingQueue (Input Output)
 * And Feeds the MealyMachine with Input
 * Acceptes a xml file as config for the mealy machine*/
public class MealyThread implements Runnable {

    private MealyMachine workingConverterMachine;

    private BlockingQueue inputQueue;
    public BlockingQueue outputQueue;
    public MealyThread(BlockingQueue inputQueue, BlockingQueue outputQueue){
        this.inputQueue=inputQueue;
        this.outputQueue= outputQueue;
        workingConverterMachine = new MealyMachine();
    }
    public MealyThread(BlockingQueue inputQueue, BlockingQueue outputQueue, String xmlConfig){
        this.inputQueue=inputQueue;
        this.outputQueue= outputQueue;
        try {
            this.workingConverterMachine = MachineSerialization.unmarshaller(xmlConfig);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        System.out.println("Mealy Thread active");
        int runtime = 0;
        while(inputQueue.isEmpty() && runtime < 120){
            System.out.println("OKAY " + inputQueue + inputQueue.isEmpty());
            try {
                String file = inputQueue.take().toString();
                if(file.endsWith(".msg")) {
                    workingConverterMachine.workingMealyMachine(file ,outputQueue);
                    System.out.println(file);
                }
                // STOP after 2 Minutes Runtime
                Thread.sleep(1000);
                runtime++;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
