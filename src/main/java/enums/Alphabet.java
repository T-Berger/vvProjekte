package enums;

import javax.xml.bind.annotation.*;
/**
 * The entrys in the Alphabet enum is the Alphabet for the Input- and Outputsymbol*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlEnum
public enum Alphabet {
    A,B,C,D,E,F,G,H,z,ERROR;
    public String printOutput (){
        return this.toString();
        }

    @XmlElement(name="outputTable")
    public static Alphabet[] alphabet ={A,B};
}
