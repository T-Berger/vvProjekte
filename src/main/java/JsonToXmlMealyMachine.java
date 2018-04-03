import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.StandardWatchEventKinds;

import java.util.List;

import javafx.scene.shape.Path;
import org.json.JSONException;
import static java.nio.file.StandardWatchEventKinds.*;


public class JsonToXmlMealyMachine {

    enum State {
        Initial (0),
        Final (1),
        Error (2);

        static public final Integer stateLength = State.values().length;

        private int currentState;

        State (int currentState) {
            this.currentState = currentState;
        }
        public int getCurrentState(){ return currentState;}
    }


    enum Symbol{
        transInit (0){
            @Override
            String output(String input) throws IOException, JSONException {
                System.out.println("Verarbeite Eingabe");
                //Read JSON File
                long startTime = System.currentTimeMillis();
                String json = readFile(InputPath);//Read File
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                System.out.println("Read File Duration: "+duration);

                //Convert JSON to XML
                startTime = System.currentTimeMillis();
                setXml( convertJsonToXML(json, "root"));//State name of root element tag
                endTime = System.currentTimeMillis();
                duration = endTime - startTime;
                System.out.println("Process Data Duration: "+duration);


                setNextSymbol(Symbol.transFinal);
                return null;
            }
        },
        transFinal (1){
            @Override
            String output(String input) throws IOException {
                System.out.println("XML:");
                System.out.println(getPreviousSymbol().getXml());
//                System.out.println(this.xml);

                //Write XML File
                long startTime = System.currentTimeMillis();
                writeXmlFile(OutputPath, getPreviousSymbol().getXml());
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                System.out.println("Write File Duration: "+duration);
                System.out.println("Verarbeite Ausgabe");
                setNextSymbol(Symbol.transError);
                return null;
            }
        },
        transError (2){
            @Override
            String output(String input) {
                setNextSymbol(Symbol.transError);
                return null;
            }
        };

        private final int currentSymbol;
        private Symbol nextSymbol;
        private Symbol previousSymbol;
        Symbol (int currentSymbol){
            this.currentSymbol = currentSymbol;
        }

        public int getCurrentSymbol(){ return currentSymbol;}

        static public final Integer symbolLength= Symbol.values().length;

        abstract String output(String input) throws IOException, JSONException;

        public void setNextSymbol(Symbol nextSymbol) {
            this.nextSymbol = nextSymbol;
        }
        public Symbol getNextSymbol() {
            return nextSymbol;
        }
        private String xml;
        public void setXml(String xml) {
            this.xml = xml;
        }

        public Symbol getPreviousSymbol() {
            return previousSymbol;
        }
        public void setPreviousSymbol(Symbol previousSymbol) {
            this.previousSymbol = previousSymbol;
        }

        public String getXml() {
            return xml;
        }
    }
    static State transitonTable [][] = {
            //       transInit       transFinal       transError
            {
                    State.Final,  State.Final,    State.Error  //  Initial
            }, {
            State.Initial,    State.Error,  State.Error // Final
    }, {
            State.Error,    State.Error,    State.Error // Error
    }

    };
    static public State transitionMachine (int symbolValue, int stateValue){
        System.out.println(transitonTable);
        return transitonTable[symbolValue][stateValue];
    }
    //    public State transitionMachine (int symbolValue, int stateValue){
//        return transitonTable[symbolValue][stateValue];
//    }
//    private class TranisitonMachine < T extends State, V extends Symbol> {
//        TranisitonMachine ( T symbol, V state, T[][] transTable, T init){
//            if(transTable.length != T.stateLength || transTable[0].length != V.symbolLength) {
//                throw ArrayIndexOutOfBoundsException;
//            }
//           transitonTable[0][1]
//
//        }
//
//    }
    public String xml;
    public static String convertJsonToXML(String json, String root) throws JSONException {
        org.json.JSONObject jsonFileObject = new org.json.JSONObject(json);
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n<"+root+">"
                + org.json.XML.toString(jsonFileObject) + "</"+root+">";
        return xml;
    }
    public static String readFile(String filepath) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(InputPath);
        Charset encoding = Charset.defaultCharset();

        Reader reader = new InputStreamReader(in, encoding);

        int r = 0;
        while ((r = reader.read()) != -1){
            char ch = (char) r;
            sb.append(ch);
        }

        in.close();
        reader.close();

        return sb.toString();
    }
    public static void writeXmlFile(String filepath, String output) throws FileNotFoundException, IOException {
        FileWriter ofstream = new FileWriter(filepath);
        try (BufferedWriter out = new BufferedWriter(ofstream)) {
            out.write(output);
        }
    }

    private static String InputPath = "input.json";
    private static String OutputPath = "./output.xml";

    public static void main(String[] args) throws IOException, JSONException {
        State current = State.Initial;
        Symbol symbol = Symbol.transInit;

        //InputStream input = main.class.getResourceAsStream("input.json");


        OutputStream output = System.out;





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

                }
                if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println("Modify: " + event.context().toString());
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }






        // System.out.println(symbol.getCurrentSymbol()+ " kkkk " + current.currentState);
        // current = transitionMachine(0,2);
        //System.out.println(transitionMachine(symbol.getCurrentSymbol(),current.getCurrentState()));
//                current = transitionMachine(symbol.getCurrentSymbol(),current.getCurrentState());
//                System.out.println(current);
//                symbol.output(symbol.currentSymbol);
//                System.out.println(symbol.getNextSymbol());
//                symbol = symbol.getNextSymbol();
//                System.out.println(symbol.getCurrentSymbol());
        while (current != State.Error){
//            System.out.println("Get in there");
////            System.out.println(symbol.getCurrentSymbol()+ " kkkk " + current.currentState);
//            current = transitionMachine(symbol.getCurrentSymbol(),current.getCurrentState());
//            symbol.currentSymbol.output
//            symbol = symbol.getNextSymbol();
            current = transitionMachine(symbol.getCurrentSymbol(),current.getCurrentState());
            System.out.println(current);
            symbol.output("LEER");
            System.out.println(symbol.getXml());
            System.out.println(symbol.getNextSymbol());
            Symbol tmp = symbol;
            symbol = symbol.getNextSymbol();
            symbol.previousSymbol = tmp;
            System.out.println(symbol.getCurrentSymbol());
//            if(current == State.Final){
//                System.out.println("DEBUG");
//              //  symbol.output("LEER");
//                break;
//            }
        }
    }
    // https://github.com/beckchr/staxon/wiki/Converting-JSON-to-XML


}
