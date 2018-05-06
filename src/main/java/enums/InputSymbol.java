package enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="inputSymbol")
@XmlEnum
public enum InputSymbol {
    a(0),b(1),c(2);
    private final int currentInputSymbol;
    private InputSymbol nextInputSymbol;
    private InputSymbol previousInputSymbol;
    // default enum constructor
    InputSymbol (int currentInputSymbol){
        this.currentInputSymbol = currentInputSymbol;
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
