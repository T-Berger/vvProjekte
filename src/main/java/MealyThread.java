//import deprecated.JsonToXmlMealyMachine;
//import org.json.JSONException;
//
//import java.io.IOException;
//import java.util.concurrent.BlockingQueue;
//
//public class MealyThread implements Runnable {
//
//    JsonToXmlMealyMachine workingConverterMachine = new JsonToXmlMealyMachine();
//
//
//    private BlockingQueue inputQueue;
//    public BlockingQueue outputQueue;
//    MealyThread(BlockingQueue inputQueue, BlockingQueue outputQueue){
//        this.inputQueue=inputQueue;
//        this.outputQueue= outputQueue;
//    }
//    @Override
//    public void run(){
//        System.out.println("Mealy Thread active");
//        while(inputQueue.isEmpty()){
//
//        }
//        System.out.println("OKAY " + inputQueue + inputQueue.isEmpty());
//        try {
//            workingConverterMachine.jsonToXmlMealyMachine(inputQueue.take().toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //JsonToXmlMealyMachine.jsonToXmlMealyMachine();
//
////        inputBlockingQueue.add(Paths.get("./json",event.context().toString()));
////        Files.delete(Paths.get("./json",event.context().toString()));
//    }
//
//
//}
