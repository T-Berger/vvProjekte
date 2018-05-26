//package deprecated;
//
//import java.io.*;
//import java.nio.charset.Charset;
//import java.nio.file.*;
//
//import java.util.List;
//
//// import javafx.scene.shape.Path;
//import org.json.JSONException; //needs the json-20090211.jar to run saved at ./json-20090211.jar saved
//
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlEnum;
//import javax.xml.bind.annotation.XmlEnumValue;
//import javax.xml.bind.annotation.XmlRootElement;
//// import static java.nio.file.StandardWatchEventKinds.*;
//
//// TODO: 05.04.18  1 more State/ Symbol for the Conversion
//// TODO: 05.04.18  Polling and thread communication
//// TODO: Tabelle für Ausgabefunktion?
//// Wahrscheinlich keine Änderung mehr bis zur nächsten Woche da Teilnahme an Exkursion
//
//@XmlRootElement
//public class JsonToXmlMealyMachine implements Serializable{
//    @XmlEnum
//    enum State {
//        Initial (0),
//        Final (1),
//        Error (2);
//        static public final Integer stateLength = State.values().length;
//
//        private int currentState;
//
//        State (int currentState) {
//            this.currentState = currentState;
//        }
//        public int getCurrentState(){ return currentState;}
//    }
//
//    @XmlEnum
//    enum InputSymbol {
//        a(0),b(1),c(2);
//        private final int currentInputSymbol;
//        private InputSymbol nextInputSymbol;
//        private InputSymbol previousInputSymbol;
//        // default enum constructor
//        InputSymbol (int currentInputSymbol){
//            this.currentInputSymbol = currentInputSymbol;
//        }
//        // getter for the current Symbol step/ process
//        public int getCurrentInputSymbol(){ return currentInputSymbol;}
//
//        static public final Integer symbolLength= InputSymbol.values().length;
//
//        // Get & Setters for working and modifying the enum Symobl
//        public void setNextSymbol(InputSymbol nextInputSymbol) {
//            this.nextInputSymbol = nextInputSymbol;
//        }
//        public InputSymbol getNextInputSymbol() {
//            return nextInputSymbol;
//        }
//        public InputSymbol getPreviousInputSymbol() {
//            return previousInputSymbol;
//        }
//        public void setPreviousInputSymbol(InputSymbol previousInputSymbol) {
//            this.previousInputSymbol = previousInputSymbol;
//        }
//    }
//    @XmlEnum
//    enum OutputAlphabet {
//        A,B,C,D,E,F,G,H;
//        public String printOutput (){
//             return this.toString();
//        }
//    }
//    // the Transition Table for the Mealy Machine; CurrentStates + CurrentTransiton -transitionTable-> new State
//    @XmlElement
//    static State transitonTable [][] = {
//            //       transInit       transFinal       transError
//            {
//                    State.Final,  State.Final,    State.Error   //  Initial
//            }, {
//                    State.Initial,    State.Error,  State.Error // Final
//            }, {
//                    State.Error,    State.Error,    State.Error // Error
//    }
//
//    };
//    @XmlElement
//    static OutputAlphabet alphabetOut[][];
//
//    /**@param symbolValue Index from the current EnumConstructorSymbol
//     * @param stateValue Index from the current EnumConstructorState
//     * @return Return a new State with the help of the Transition Table
//    */
//    static public State transitionMachine (int symbolValue, int stateValue){
//        System.out.println(transitonTable);
//        return transitonTable[symbolValue][stateValue];
//    }
//
//    public static char readNextInputChar() throws IOException, FileNotFoundException {
//        InputStream in = new FileInputStream(InputPath);
//        Charset encoding = Charset.defaultCharset();
//        char ch = 26;
//        Reader reader = new InputStreamReader(in, encoding);
//
//        int r = 0;
//        if ((r = reader.read()) != -1){
//            ch = (char) reader.read();
//        }
//
//        in.close();
//        reader.close();
//        return ch;
//    }
//    /**
//     * @param filepath for the output File
//     * @param inputValue
//     */
//    public static void output(String filepath, int inputValue, int stateValue) throws IOException {
//        FileWriter ofstream = new FileWriter(filepath);
//        try (BufferedWriter out = new BufferedWriter(ofstream)) {
//            out.write(
//                    alphabetOut[inputValue][stateValue].toString()
//            );
//        }
//    }
//
//
//
//    public static String InputPath;
//    private static String OutputPath;
//
//    public static void jsonToXmlMealyMachine (String pathOfJsonFile) throws IOException, JSONException {
//        //InputPath = "./json/" + pathOfJsonFile;
//        InputPath = pathOfJsonFile;
////        OutputPath = "./output/" + pathOfJsonFile;
//        OutputPath = pathOfJsonFile.replace("./json/","./output/");
//        OutputPath.replace(".msg",".out");
//        System.out.println(InputPath);
//        System.out.println(OutputPath);
//        State current = State.Initial;
//        Symbol symbol = Symbol.transInit;
//
//        // Mealy Machine process
//        while (current != State.Error){
//            // Get the new State
//
//            InputSymbol input = InputSymbol.valueOf(String.valueOf(readNextInputChar()));
//            current = transitionMachine(symbol.getCurrentSymbol(),current.getCurrentState());
//            System.out.println("New State after transition=> "+current);
//            // Starts the processing Step from the current Transition
//            symbol.output("ROOT");
//            System.out.println(symbol.getXml());
//            System.out.println(symbol.getNextSymbol());
//            // Save the old Symbol in previous śymbol and set the current symbol to the next
//            Symbol tmp = symbol;
//            symbol = symbol.getNextSymbol();
//            symbol.previousSymbol = tmp;
//            System.out.println(symbol.getCurrentSymbol());
//        }
//
//        System.out.println("Process finished");
////        Files.delete(Paths.get("./json/"+ pathOfJsonFile));
//        Files.delete(Paths.get(pathOfJsonFile));
//    }
//
//}
