import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InputFileWatcher implements Runnable {


    private BlockingQueue inputBlockingQueue;
    InputFileWatcher(BlockingQueue inputQueue){
        this.inputBlockingQueue=inputQueue;
    }
    @Override
    public void run() {
        System.out.println("File Watcher active");
        try {
            Files.walk(Paths.get("./json"))
                    .filter(Files::isRegularFile)
                    .forEach(inputBlockingQueue::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // @TODO File Watcher APi
        while(true){
            java.nio.file.Path dir = Paths.get("./json");
            try {
                WatchService watcher = dir.getFileSystem().newWatchService();
                dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_MODIFY);

                WatchKey watckKey = watcher.take();

                List<WatchEvent<?>> events = watckKey.pollEvents();
                for (WatchEvent event : events) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        System.out.println("Created: " + event.context().toString());
//                        JsonToXmlMealyMachine workingConverterMachine = new JsonToXmlMealyMachine();
//                        workingConverterMachine.jsonToXmlMealyMachine(event.context().toString());

                        //JsonToXmlMealyMachine.jsonToXmlMealyMachine();
                        inputBlockingQueue.add(Paths.get("./json",event.context().toString()));
//                        Files.delete(Paths.get("./json",event.context().toString()));

                    }
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        System.out.println("Modify: " + event.context().toString());
//                        JsonToXmlMealyMachine workingConverterMachine = new JsonToXmlMealyMachine();
//                        workingConverterMachine.jsonToXmlMealyMachine(event.context().toString());
                    }
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.toString());
            }
            // @TODO
        }
    }
}
