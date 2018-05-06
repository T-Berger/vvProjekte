package enums;


import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {

    @XmlElement(name="outputTable")
    public static OutputAlphabet[][] outputAlphabet={
            {OutputAlphabet.A,OutputAlphabet.A},
            {OutputAlphabet.A,OutputAlphabet.A},
            {OutputAlphabet.A,OutputAlphabet.A}
    };
    @XmlElement(name="transitonTable")
    public static State transitonTable [][] = {
            //       transInit       transFinal       transError
            {
                    State.Final,  State.Final,    State.Error   //  Initial
            }, {
                    State.Initial,    State.Error,  State.Error // Final
            }, {
                    State.Error,    State.Error,    State.Error // Error
    }
    };
    Table(){
       out = OutputAlphabet.values();
    }
    @XmlElement
    private OutputAlphabet[] out;
    @XmlEnum
    enum card{
        @XmlEnumValue("Pike")
        pike,@XmlEnumValue("ass")ass,@XmlEnumValue("heart")heart;
    }

    @XmlEnum(String.class)
    public enum Cards { CLUBS, DIAMONDS, HEARTS, SPADES }
    @XmlEnum
    public enum MyEnum {

        MY_ENUM_1,
        MY_ENUM_2;

        public String value() {
            return name();
        }

        public static MyEnum fromValue(String v) {
            return valueOf(v);
        }

    }
}

//            //       transInit       transFinal       transError
//            {
//                    State.Final,  State.Final,    State.Error   //  Initial
//            }, {
//                    State.Initial,    State.Error,  State.Error // Final
//            }, {
//                    State.Error,    State.Error,    State.Error // Error
//    }