import enums.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


@XmlRootElement(name = "StateMachine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Machine {
    State transitonTable [][] = {};
    OutputAlphabet[][] outputAlphabet={};
//    State states = State.class;
//  /  InputSymbol inputSymbol;
//    OutputAlphabet output;
//     State[] state= new State[];
    ArrayList<State> state = new ArrayList<State>();
    HashSet<InputSymbol> inputSymbol = new HashSet<>();
    ArrayList<OutputAlphabet> output = new ArrayList<OutputAlphabet>();
    ArrayList<OutputAlphabet> transitionSymbols = new ArrayList<>();
    Machine(){
////        this.state = state;
//        this.output = output;
//        this.inputSymbol = inputSymbol;
        transitonTable = Table.transitonTable;
        outputAlphabet = Table.outputAlphabet;
        System.out.println(State.values());
        for (State s:
             State.values()) {
            System.out.println(s);
            state.add(s);
        }
//        for (InputSymbol s:
//                InputSymbol.values()) {
//            inputSymbol.add(s);
//        }
        transitionSymbols.addAll(Arrays.asList(OutputAlphabet.values()));
        for (OutputAlphabet out:
             OutputAlphabet.values()) {
           // transitionSymbols.add(new Symbol(out));
        }
    }
}
