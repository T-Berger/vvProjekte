package deprecated;

import enums.Alphabet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "symbol")
@XmlAccessorType(XmlAccessType.FIELD)
public class Symbol {
    private static int counter = 0;
    final private int index;
    private Symbol nextInputSymbol;
    private Symbol previousInputSymbol;
    private Alphabet inputValue;
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

    public Symbol(Alphabet inputValue){
        this.inputValue = inputValue;
        index = counter++;
    }



    public int getIndex() {
        return index;
    }
    public static int getCounter() {
        return counter;
    }
    public static void resetCounter() { counter = 0; }


    public void setNextSymbol(Symbol nextInputSymbol) {
        this.nextInputSymbol = nextInputSymbol;
    }
    public Symbol getNextInputSymbol() {
        return nextInputSymbol;
    }
    public Symbol getPreviousInputSymbol() {
        return previousInputSymbol;
    }
    public void setPreviousInputSymbol(Symbol previousInputSymbol) {
        this.previousInputSymbol = previousInputSymbol;
    }

}