package enums;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class InputSymbolTest {
    private JAXBContext context;

    @Before
    public void init() throws JAXBException {
        this.context = JAXBContext.newInstance(InputSymbol.class);
    }

    @org.junit.Test
    public void serialization() throws JAXBException {
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(InputSymbol.class, new File("InputSymobl.xml"));

    }
    @org.junit.Test
    public void unmarshall() throws JAXBException {

        Unmarshaller unmarshaller = this.context.createUnmarshaller();
        Object unmarshalled = unmarshaller.unmarshal(new File("InputSymobl.xml"));
        System.out.println("unmarshalled =" + unmarshalled);

    }
    @Test
    void setNextSymbol() {
    }
}