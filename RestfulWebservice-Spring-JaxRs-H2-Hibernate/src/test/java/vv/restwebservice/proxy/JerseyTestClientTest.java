package vv.restwebservice.proxy;

import org.glassfish.jersey.client.JerseyClient;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.test.annotation.DirtiesContext;
import vv.restwebservice.modells.Address;
import vv.restwebservice.modells.Contract;
import vv.restwebservice.modells.Customer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JerseyTestClientTest {
    JerseyTestClient jerseyClient = new JerseyTestClient();;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
    Address address = new Address("ZWERGENSTADT","12212","TOONSWORLD");

    Customer margred = new Customer("MARGRED", "TEST", df.parse("1998.05.25"),"");
    Contract testContract =new Contract("TEST","QWERTY",0,margred);
    Contract auto = new Contract("Auto","KFH A",85264,margred);
    Contract brandschutz = new Contract("Brandschutz","KFH A",85264,margred);
    List<Contract> contracts = new ArrayList<>();

    Customer mordred = new Customer("Mordred", "Mordred", df.parse("2000.00.00"),"");
    Address address1 = new Address("foo","1","bar");
    List<Contract> contracts1 = new ArrayList<>();
    public JerseyTestClientTest() throws ParseException {
        contracts.add(auto);
        contracts.add(brandschutz);
        margred.setContracts(contracts);
        margred.setAddress(address);
        contracts1.add(new Contract("ASD","ZDF",85264,mordred));
        contracts1.add(new Contract("YMCA","QWERTZ",10000,mordred));
        mordred.setContracts(contracts1);
        mordred.setAddress(address1);
        jerseyClient.addCustomer(margred);
        jerseyClient.addCustomer(mordred);
        jerseyClient.addContract(testContract);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


//    @BeforeClass
//
//   public static void setUp() throws Exception {
//        JerseyTestClientTest test =new JerseyTestClientTest();
//
//
//    }
    @Test
    public void a() throws ParseException {
        JerseyTestClientTest test = new JerseyTestClientTest();

        assertNotEquals("", outContent.toString());
        assertEquals("", errContent.toString());

        test.jerseyClient.getContractById(1);
        test.jerseyClient.getCustomerById(2);
        assertNotEquals("", outContent.toString());
        assertEquals("", errContent.toString());

        test.mordred.setSurname("Paladin");
        jerseyClient.updateCustomer(test.mordred);
        assertEquals(test.mordred.getSurname(),jerseyClient.getContractById(2).getSurname());
        assertNotEquals("", outContent.toString());
        assertEquals("", errContent.toString());


        jerseyClient.updateContract(new Contract("123","123",2,mordred,5));
        assertEquals(jerseyClient.getContractById(5).getPrice(),2,0);
        assertNotEquals("", outContent.toString());
        assertEquals("", errContent.toString());

        test.jerseyClient.deleteCustomer(1);
        test.jerseyClient.deleteContract(3);

        assertNotEquals("", outContent.toString());
        assertEquals("", errContent.toString());

    }


//
//    @Before
//    public void setForEveryTest() throws ParseException {
//        jerseyClient = new JerseyTestClient();
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
//
//
//        JerseyTestClient jerseyClient = new JerseyTestClient();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//        margred = new Customer("MARGRED", "TEST", df.parse("1998.05.25"),"");
//        Address address = new Address("ZWERGENSTADT","12212","TOONSWORLD");
//        List<Contract> contracts = new ArrayList<>();
//        contracts.add(new Contract("Auto","KFH A",85264,margred));
//        contracts.add(new Contract("Brandschutz","KFH A",85264,margred));
//        margred.setContracts(contracts);
//        margred.setAddress(address);
//        //        jerseyClient.addCustomer(margred);
//        //        jerseyClient.updateContract(new Contract("123","123",2,margred,9));
//
//        Customer mordred = new Customer("Mordred", "Mordred", df.parse("2000.00.00"),"");
//        Address address1 = new Address("foo","1","bar");
//        List<Contract> contracts1 = new ArrayList<>();
//        contracts1.add(new Contract("ASD","ZDF",85264,mordred));
//        contracts1.add(new Contract("YMCA","QWERTZ",10000,mordred));
//        mordred.setContracts(contracts1);
//        mordred.setAddress(address1);
//
//            jerseyClient.addCustomer(margred);
//            jerseyClient.addCustomer(mordred);
//        Contract testContract =new Contract("TEST","QWERTY",0,margred);
//            jerseyClient.addContract(testContract);// do the setup
////            jerseyClient.basicProxyFunctions.close();
//    }
////    @Test
////    public void A_addCustomer1() throws InterruptedException, ParseException {
////        assertNotEquals("", outContent.toString());
////        assertEquals("", errContent.toString());
////    }
////    @Test
////    public void A_addCustomer2() throws InterruptedException {
////
//////        jerseyClient.addCustomer(mordred);
////        testContract =new Contract("TEST","QWERTY",0,margred);
////        assertNotEquals("", outContent.toString());
////        assertEquals("", errContent.toString());
//////        Thread.sleep(5000);
////    }
////    @Test
////    public void A_addCustomer3() throws InterruptedException{
//////        testContract =new Contract("TEST","QWERTY",0,margred);
////        assertNotEquals("", outContent.toString());
////        assertEquals("", errContent.toString());
//////        Thread.sleep(5000);
////    }
////    @Test
////    public void B_addContract() {
//////        jerseyClient.addContract(testContract);
////        assertNotEquals("", outContent.toString());
////        assertEquals("", errContent.toString());
//////        Thread.sleep(10000);
////    }
//    @Test
//    public void C_getCustomerById() {
//        Customer c = jerseyClient.getCustomerById(1);
//        assertEquals(c.getForename(),mordred.getForename());
//        assertNotEquals("", outContent.toString());
//        assertEquals("", errContent.toString());
//    }
//    @Test
//    public void D_getContractById() {
//        Contract c = jerseyClient.getContractById(2);
//        assertEquals(c.getType(),testContract.getType());
//        assertNotEquals("", outContent.toString());
//        assertEquals("", errContent.toString());
//    }
//    @Test
//    public void E_getCustomerDetails() {
//        assertNotEquals("", outContent.toString());
//        assertEquals("", errContent.toString());
//    }
//    @Test
//    public void F_updateCustomer() {
//        mordred.setSurname("Paladin");
//        jerseyClient.updateCustomer(mordred);
//        assertEquals(mordred.getSurname(),jerseyClient.getContractById(2).getSurname());
//        assertNotEquals("", outContent.toString());
//        assertEquals("", errContent.toString());
//    }
//
//    @Test
//    public void G_deleteCustomer() {
//        jerseyClient.deleteCustomer(1);
//        assertNotEquals("", outContent.toString());
//        assertEquals("", errContent.toString());
//    }
//    @Test
//    public void H_updateContract() {
//        jerseyClient.updateContract(new Contract("123","123",2,mordred,5));
//        assertEquals(jerseyClient.getContractById(5).getPrice(),2,0);
//        assertNotEquals("", outContent.toString());
//        assertEquals("", errContent.toString());
//    }
//
//    @Test
//    public void I_deleteContract() {
//        jerseyClient.deleteContract(1);
//        assertNotEquals("", outContent.toString());
//        assertEquals("", errContent.toString());
//    }
//    @Test
//    public void J_getContractDetails() {
//        assertNotEquals("", outContent.toString());
//        assertEquals("", errContent.toString());
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        jerseyClient.basicProxyFunctions.close();
//    }
//
//
//
//
//
//
//
//
//
//
//
//
}