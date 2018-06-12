package deprecated;

public enum AlphabetOLD {
        a(0),b(1),c(2),d(4),e(5),f(6);
        private final int currentInputSymbol;
        // default enum constructor
        AlphabetOLD(int currentInputSymbol){
            this.currentInputSymbol = currentInputSymbol;
        }
        // getter for the current Symbol step/ process
        public int getCurrentInputSymbol(){ return currentInputSymbol;}

        static public final Integer symbolLength= InputSymbol.values().length;
}
