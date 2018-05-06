//import deprecated.JsonToXmlMealyMachine;
//import org.json.JSONException;
//
//import java.io.*;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class MealyMachine {
//    /**@param symbolValue Index from the current EnumConstructorSymbol
//     * @param stateValue Index from the current EnumConstructorState
//     * @return Return a new State with the help of the Transition Table
//     */
//    static public JsonToXmlMealyMachine.State transitionMachine (int symbolValue, int stateValue){
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
//                    outputAlphabet[inputValue][stateValue].toString()
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
//        JsonToXmlMealyMachine.State current = JsonToXmlMealyMachine.State.Initial;
//        Symbol symbol = Symbol.transInit;
//
//        // Mealy Machine process
//        while (current != JsonToXmlMealyMachine.State.Error){
//            // Get the new State
//
//            JsonToXmlMealyMachine.InputSymbol input = JsonToXmlMealyMachine.InputSymbol.valueOf(String.valueOf(readNextInputChar()));
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
//}
