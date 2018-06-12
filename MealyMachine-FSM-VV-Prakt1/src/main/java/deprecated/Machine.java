package deprecated;

import enums.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


@XmlRootElement(name = "StateMachine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Machine {
    State transitonTable [][] = {};
    Alphabet[][] alphabet ={};
//    State states = State.class;
//  /  InputSymbol inputSymbol;
//    Alphabet output;
//     State[] state= new State[];
    ArrayList<State> state = new ArrayList<State>();
    HashSet<InputSymbol> inputSymbol = new HashSet<>();
    ArrayList<Alphabet> output = new ArrayList<Alphabet>();
    ArrayList<Alphabet> transitionSymbols = new ArrayList<>();
    Machine(){
////        this.state = state;
//        this.output = output;
//        this.inputSymbol = inputSymbol;
        transitonTable = Table.transitonTable;
        alphabet = Table.alphabetOut;
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
        transitionSymbols.addAll(Arrays.asList(Alphabet.values()));
        for (Alphabet out:
             Alphabet.values()) {
           // transitionSymbols.add(new Symbol(out));
        }
    }
}
