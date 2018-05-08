package machine;

import enums.Alphabet;
import enums.State;
import enums.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

//import org.json.JSONException;
/**
 * Class for a Mealy State Machine
 *
 * */
@XmlRootElement(name = "StateMachine")
@XmlAccessorType(XmlAccessType.FIELD)
class MealyMachine {
    State transitonTable[][] = {};
    public Alphabet[][] alphabetOut = {};
    //    private ArrayList<State> state = new ArrayList<State>();
    /**
     * Here is the Alphabet of the Symbols saved */
    private ArrayList<Alphabet> transitionSymbols = new ArrayList<>();

    MealyMachine() {
////        // Needed for the basic Machine.xml File
        transitonTable = Table.transitonTable;
        alphabetOut = Table.alphabetOut;
        System.out.println(Arrays.toString(State.values()));
        /*
          Here is the Alphabet of the Symbol generated
        */
        transitionSymbols.addAll(Arrays.asList(Alphabet.values()));
    }

    /**
     * Function for the Transition
     * @see Table
     * @param symbolValue Index from the current EnumAlphabet
     * @param stateValue  Index from the current EnumConstructorState
     * @return Return a new State with the help of the Transition Table
     */
    private State transitionMachine(int symbolValue, int stateValue) {
        State next;
        try {
            next = transitonTable[symbolValue][stateValue];
        } catch (IllegalArgumentException s) {
            throw new RuntimeException(s +
                    "This entry in the Transition Table [" + symbolValue + "][" + stateValue + "] is written wrong, " +
                    "and therefore not a valid State.  Accepted  State are" + Arrays.asList(State.values()));
        }
        if (next == State.Error) {
            System.out.println("The entry in the Transiton Table [" + symbolValue + "][" + stateValue + "] is not a valid State! ERROR State!");
            System.out.println("This machine will now close itself, and suspend further actions");
        }
        return next;
    }
    /**
     * Function for the Output
     * @see Table
     * @param inputValue Index from the read EnumAlphabet
     * @param stateValue Index from the current EnumConstructorState
     * @return Output-String for the blocking Queue
     */
    private String  outputBlockingQueu( int inputValue, int stateValue) throws IOException {
        try {
            return alphabetOut[inputValue][stateValue].toString();

        }catch (IllegalArgumentException s) {
            throw new RuntimeException(s +
                    "This entry in the Alphabet Table [" + inputValue + "][" + stateValue + "] is written wrong, " +
                    "and therefore not a valid State.  Accepted  State are" + Arrays.asList(State.values()));
        }
    }

    /**
     * Logic from the MealyMachine
     * + output + and transition
     * @param blockingQueue This blocking Queue could handle the output
     * @param pathOfJsonFile Path from the input file
     * Each character of pathOfJsonFile is a new input*/
    void workingMealyMachine(String pathOfJsonFile, BlockingQueue blockingQueue) throws IOException {

        String outputPath = pathOfJsonFile.replace("\\", "/")
                .replace("./json/", "./output/").replace(".msg", ".out");

        String data = outputPath.replace("./output/", "").replace(".out", "");
//        System.out.println(alphabetOut[1].length);
        State current = State.Initial;
        String output="";
        File outputFile = new File(outputPath);
        // Mealy Machine process
        // Get the new Input
        // Each character in the filename is a input
        System.out.println("data -->>> " + data);
        for (char input :
                data.toCharArray()) {
            try {
                // Get the new State
                System.out.println("Value Inputsymbol = " + String.valueOf(input));

                Alphabet search = Alphabet.valueOf(String.valueOf(input));
                transitionSymbols.indexOf(search);
                output = output.concat(outputBlockingQueu(transitionSymbols.indexOf(search), current.getCurrentState()));
                System.out.println("INDEX -> "+ transitionSymbols.indexOf(search));
                // Transition into new State
                current = transitionMachine(transitionSymbols.indexOf(search), current.getCurrentState());
                // Break if in a illegal State
                if(current == State.Error) break;
            } catch (IllegalArgumentException e) {
                //e.printStackTrace();
                throw new RuntimeException(e +
                        "Please, only you this valid characters --> " + Arrays.asList(Alphabet.values()) + " in your Filename");
            }

        }
        System.out.println("Ausgabe --> "+ output);
        blockingQueue.add(outputFile);
        blockingQueue.add(output);
        System.out.println("Process finished");

        Files.delete(Paths.get(pathOfJsonFile));
    }

}

