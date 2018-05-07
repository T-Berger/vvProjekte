
import enums.*;
//import org.json.JSONException;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class MealyMachine extends Machine{
    /**@param symbolValue Index from the current EnumConstructorSymbol
     * @param stateValue Index from the current EnumConstructorState
     * @return Return a new State with the help of the Transition Table
     */
    private State transitionMachine (int symbolValue, int stateValue){
//        System.out.println(transitonTable);
        State next;
        try {
            next = transitonTable[symbolValue][stateValue];
        }catch (IllegalArgumentException s){
            throw new RuntimeException(s +
                    "This entry in the Transition Table [" + symbolValue+ "]["+stateValue+"] is written wrong, " +
                    "and therefore not a valid State.  Accepted  State are" + Arrays.asList(State.values()));
        }
        if (next == State.Error) {
            System.out.println("The entry in the Transiton Table [" + symbolValue+"]["+stateValue+"] is not a valid State! ERROR State!");
            System.out.println("This machine will now close itself, and suspend further actions");
        }
        return next;
    }

    private static char readNextInputChar() throws IOException, FileNotFoundException {
        InputStream in = new FileInputStream(InputPath);
        Charset encoding = Charset.defaultCharset();
        char ch = 26;
        Reader reader = new InputStreamReader(in, encoding);

        int r = 0;
        if ((r = reader.read()) != -1){
            ch = (char) reader.read();
        }

        in.close();
        reader.close();
        return ch;
    }
    /**
     * @param filepath for the output File
     * @param inputValue
     */
    private void output(File filepath, int inputValue, int stateValue) throws IOException {
        FileWriter ofstream = new FileWriter(filepath);
        try (BufferedWriter out = new BufferedWriter(ofstream)) {
            out.write(
                    outputAlphabet[inputValue][stateValue].toString()
            );
        }
    }



    private static String InputPath;
    private static String OutputPath;

    public void workingMealyMachine (String pathOfJsonFile) throws IOException {
        //InputPath = "./json/" + pathOfJsonFile;
        InputPath = pathOfJsonFile;
//        OutputPath = "./output/" + pathOfJsonFile;
        OutputPath = pathOfJsonFile.replace("./json/","./output/");
        OutputPath.replace(".msg",".out");
        System.out.println(InputPath);
        System.out.println(OutputPath);
        State current = State.Initial;
       // InputSymbol symbol = InputSymbol.a;// falsch
        File outputFile = new File(OutputPath);
        // Mealy Machine process
        while (current != State.Error){
            // Get the new Input
            char input = readNextInputChar();
            if(input == 26) break;
            // Get the new State

            try {
                OutputAlphabet search = OutputAlphabet.valueOf(String.valueOf(input));
                transitionSymbols.indexOf(search);
                output(outputFile,transitionSymbols.indexOf(search),current.getCurrentState());
                current = transitionMachine(transitionSymbols.indexOf(search),current.getCurrentState());
            } catch (IllegalArgumentException e) {
                //e.printStackTrace();
                throw new RuntimeException(e +
                        "Please, only you this valid characters --> "+ Arrays.asList(Alphabet.values()) +" in your Filename");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("New State after transition=> "+current);

//            transitionSymbols.contains(OutputAlphabet.valueOf(String.valueOf(readNextInputChar())));
//            InputSymbol input = InputSymbol.valueOf(String.valueOf(readNextInputChar()));
//
//
//            current = transitionMachine(symbol.getCurrentSymbol(),current.getCurrentState());
//            System.out.println("New State after transition=> "+current);
//            // Starts the processing Step from the current Transition
//            symbol.output();
////            System.out.println(symbol.getXml());
////            System.out.println(symbol.getNextSymbol());
////            // Save the old Symbol in previous śymbol and set the current symbol to the next
//            Symbol tmp = symbol;
//            symbol = symbol.getNextSymbol();
//            symbol.previousSymbol = tmp;
//            System.out.println(symbol.getCurrentSymbol());
        }

        System.out.println("Process finished");
//        Files.delete(Paths.get("./json/"+ pathOfJsonFile));
        Files.delete(Paths.get(pathOfJsonFile));
    }

}

