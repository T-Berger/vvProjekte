package deprecated;

import deprecated.InputSymbol;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class SerializationInputSymbol {
    public static void main(String[] args) throws JAXBException {
        InputSymbol D;
        System.out.println(  );

        JAXBContext context = JAXBContext.newInstance(InputSymbol.class);
        Marshaller marshaller = context.createMarshaller();
        for (InputSymbol symbol:
             InputSymbol.values()) {

            marshaller.marshal(  symbol,new File("Symbol-"+symbol.toString()+".xml"));
        }

    }

}
