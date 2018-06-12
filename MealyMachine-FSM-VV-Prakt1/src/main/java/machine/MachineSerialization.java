package machine;

import deprecated.Machine;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MachineSerialization {

    /**
     * Creates a XML from Java Class --> MealyMachine
     * @param pathname "xml/"+pathname+".xml" is the dir where the xml file is stored*/
    public static void marshaller(String pathname) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(MealyMachine.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(new MealyMachine(), new File("xml/"+pathname+".xml"));
    }
    /**
     * Creates a MealyMachine instance from the xml file, with pathname  *
     *@param pathname relative path from ./xml/ without ending */
    public static MealyMachine unmarshaller(String pathname) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(MealyMachine.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File xml = new File("xml/"+pathname+".xml");
        MealyMachine root = (MealyMachine) unmarshaller.unmarshal(xml);

        System.out.println(root +"ENDE");
        System.out.println(root.transitonTable);
        return root;
    }


    public static void main(String[] args) throws JAXBException {
        marshaller("Machine");
        unmarshaller("Machine");
    }

}
