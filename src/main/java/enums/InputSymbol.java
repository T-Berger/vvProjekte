package enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="inputSymbol")
@XmlEnum
@XmlRootElement(name = "InputSymbol")
public enum InputSymbol {
    t0(0, OutputAlphabet.A),t1(1, OutputAlphabet.A),t3(3, OutputAlphabet.B),t4(4, OutputAlphabet.B),t5(5, OutputAlphabet.B),t6(6, OutputAlphabet.B);
    private final int currentInputSymbol;
    private InputSymbol nextInputSymbol;
    private InputSymbol previousInputSymbol;
    public OutputAlphabet value;
    // default enum constructor
    InputSymbol (int currentInputSymbol, OutputAlphabet value){
        this.currentInputSymbol = currentInputSymbol;
        this.value = value;
    }
    // getter for the current Symbol step/ process
    public int getCurrentInputSymbol(){ return currentInputSymbol;}

    static public final Integer symbolLength= InputSymbol.values().length;

    // Get & Setters for working and modifying the enum Symobl
    public void setNextSymbol(InputSymbol nextInputSymbol) {
        this.nextInputSymbol = nextInputSymbol;
    }
    public InputSymbol getNextInputSymbol() {
        return nextInputSymbol;
    }
    public InputSymbol getPreviousInputSymbol() {
        return previousInputSymbol;
    }
    public void setPreviousInputSymbol(InputSymbol previousInputSymbol) {
        this.previousInputSymbol = previousInputSymbol;
    }


}
