package enums;
import java.io.File;
import javax.xml.bind.*;
public class TableSerialization {


    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Table.class);

//        Unmarshaller unmarshaller = jc.createUnmarshaller();
//        File xml = new File("input.xml");
//        Table root = (Table) unmarshaller.unmarshal(xml);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(new Table(), System.out);

    }
}

