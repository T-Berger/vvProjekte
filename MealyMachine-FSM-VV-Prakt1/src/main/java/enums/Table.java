package enums;


import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * Here you find the Arrays for the transition and output*/
public class Table {
    /**
     * Array Alphabet[][] alphabetOut is the table for the output
     * First Index value of the Alphabet entry
     * Second Index value of the next State
     * Returns a OutputSymbol - Alphabet Enum Value- */
    @XmlElement(name="outputTable")
    public static Alphabet[][] alphabetOut = new Alphabet[Alphabet.values().length][State.stateLength];
//    public static Alphabet[][] alphabetOut={
//
//
//            {Alphabet.A,Alphabet.A},
//            {Alphabet.A,Alphabet.A},
//            {Alphabet.A,Alphabet.A}
//    };
    /**
     * Array State[][] transitonTable  is the table for the transitions
     * First Index value of the Alphabet entry
     * Second Index value of the next State
     * Returns the next State Enum Value */
    @XmlElement(name="transitonTable")
    public static State transitonTable [][] = new State[Alphabet.values().length][State.stateLength];
    /**
     * This static initializer fills both Tables with the basic configuration */
    static {
    for (int i=0; i < transitonTable.length; i++){
        Arrays.fill(transitonTable[i], State.Error);
        Arrays.fill(alphabetOut[i], Alphabet.ERROR);
        }
    }
}
