package enums;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class Serialization {
    public static void main(String[] args) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(InputSymbol.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(InputSymbol.class, new File("InputSymobl.xml"));
    }

}
