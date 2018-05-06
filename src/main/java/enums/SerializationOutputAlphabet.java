package enums;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class SerializationOutputAlphabet {
    public static void main(String[] args) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(OutputAlphabet.class);
        Marshaller marshaller = context.createMarshaller();
        for (InputSymbol symbol:
                InputSymbol.values()) {
            marshaller.marshal(symbol, new File("OutputAlpha-" + symbol.toString() + ".xml"));
        }
    }

}
