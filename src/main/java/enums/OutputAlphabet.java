package enums;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum OutputAlphabet {
    A,B,C,D,E,F,G,H;
    public String printOutput (){
            return this.toString();
        }
    @XmlElement
    static OutputAlphabet outputAlphabet[][];
}
