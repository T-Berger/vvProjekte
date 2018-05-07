import enums.InputSymbol;
import enums.OutputAlphabet;
import enums.Table;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MachineSerialization {
    public static void marshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Machine.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(new Machine(), new File("Machine.xml"));

        Unmarshaller unmarshaller = context.createUnmarshaller();
        File xml = new File("Machine.xml");
        Machine root = (Machine) unmarshaller.unmarshal(xml);

        System.out.println(root +"ENDE");
        System.out.println(root.transitonTable);
    }
    public static void unmarshaller(){

    }
    public static void main(String[] args) throws JAXBException {

    }

}
