import enums.InputSymbol;
import enums.OutputAlphabet;
import enums.State;
import enums.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Array;
import java.util.ArrayList;


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
    ArrayList<InputSymbol> inputSymbol = new ArrayList<InputSymbol>();
    ArrayList<OutputAlphabet> output = new ArrayList<OutputAlphabet>();
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
        for (InputSymbol s:
                InputSymbol.values()) {
            inputSymbol.add(s);
        }
        for (OutputAlphabet out:
             OutputAlphabet.values()) {
            output.add(out);
        }
    }
}
