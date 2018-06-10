//package vv;
//
//import vv.restwebservice.client.JerseyTestClient;
//import vv.restwebservice.modells.Address;
//import vv.restwebservice.modells.Contract;
//import vv.restwebservice.modells.Customer;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//
//public class testDaten {
//    public static void main(String[] args) throws ParseException {
//        JerseyTestClient c =new JerseyTestClient();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//        c.addNewCustomer(
//                new Customer("Harry", "Hirsch", df.parse("1998.05.25"), new Address("", "", ""), "",
//                        Arrays.asList(new Contract(),new Contract()) ));
//    }
//}
