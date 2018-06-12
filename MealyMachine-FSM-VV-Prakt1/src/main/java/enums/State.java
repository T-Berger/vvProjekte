package enums;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
/**
 * These Enum Entrys are the possible States of the FSM*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlEnum
public enum State {
    Initial (0),
    Final (1),
    Error (2),
    STATE0 (3),
    STATE1 (4),
    STATE2 (5),
    STATE3 (6),
    STATE4 (7);
    static public final Integer stateLength = State.values().length;
    private int currentState;
    State (int currentState) {
        this.currentState = currentState;
    }
    public int getCurrentState(){ return currentState;}
}
