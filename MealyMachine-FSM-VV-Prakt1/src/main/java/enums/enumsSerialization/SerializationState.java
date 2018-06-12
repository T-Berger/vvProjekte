package enums.enumsSerialization;

import enums.State;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class SerializationState {
    public static void main(String[] args) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(State.class);
        Marshaller marshaller = context.createMarshaller();
        for (State symbol:
                State.values()) {

            marshaller.marshal(  symbol,new File("Symbol-"+symbol.toString()+".xml"));
        }

    }

}
