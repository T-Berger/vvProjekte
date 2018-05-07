package enums;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlEnum
public enum OutputAlphabet {
    A,B,C,D,E,F,G,H;
    public String printOutput (){
        return this.toString();
        }

    @XmlElement(name="outputTable")
    public static OutputAlphabet[] outputAlphabet={A,B};
}
