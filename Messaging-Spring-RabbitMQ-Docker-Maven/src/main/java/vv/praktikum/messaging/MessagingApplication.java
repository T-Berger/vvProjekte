package vv.praktikum.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import vv.praktikum.messaging.sender.Controller;
import vv.praktikum.messaging.sender.VehicleMessage;
import vv.praktikum.messaging.topicDistributer.Logbook;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@EnableScheduling
@SpringBootApplication
@EnableRabbit
@EnableConfigurationProperties
public class MessagingApplication {


    public static void main(String[] args) {
//        SpringApplication.run(MessagingApplication.class, args);
        SpringApplication springApplication = new SpringApplication(MessagingApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setLogStartupInfo(false);
        ConfigurableApplicationContext context = springApplication.run(args);
        VehicleMessage bean = context.getBean(VehicleMessage.class);
//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + bean);
    }
//
//    private static final Logger logger = LoggerFactory.getLogger(VehicleMessage.class);
//
//    @Autowired
//    private VehicleMessage properties;
//
    @PreDestroy
    private void close(){
//        Logbook.getInstance().getS();
//        System.out.println("HHHHHHHHHH"+ Logbook.getInstance().getS());
    }
    @PostConstruct
    private void init(){
        Controller.getInstance();
        System.out.println(Controller.getInstance().getVehicles());
//        logger.info("Spring Boot - @ConfigurationProperties annotation example");
//        logger.info(properties.toString());
    }
//
//    public static final String EXCHANGE_NAME = "appExchange";
//    public static final String QUEUE_UNFILTERED_DRIVINGQUEUE = "unfilteredDrivingQueue";
//    @Bean
//    public TopicExchange appExchange() {
//        return new TopicExchange(EXCHANGE_NAME);
//    }
////    @Bean
////    public TopicExchange topic() {
////        return new TopicExchange("receiver");
////    }
//
//    @Bean
//    public Queue unfiltered_DrivingQueue() {
//        return new Queue(QUEUE_UNFILTERED_DRIVINGQUEUE);
//    }
//    //    @Profile("filter")
//    @Bean
//    public InputFilter receiver() {
//        return new InputFilter();
//    }
//
//    //    @Profile("telematic-unit")
//    @Bean
//    public TelematicUnit ioT_sender() {
//        return new TelematicUnit();
//    }
//
//    /////////////////////////////////////////
//
//    //    @Profile("receiver")
////    private class ReceiverConfig {
//    @Bean
//    public Logbook receiverFilteredData() {
//        return new Logbook();
//    }
//
//    @Bean
//    public Queue filteredDrivingData() {
//        return new AnonymousQueue();
//    }
//
//    @Bean
//    public Binding normalPriorityBinding(TopicExchange topic,
//                                         Queue filteredDrivingData) {
//        return BindingBuilder.bind(filteredDrivingData)
//                .to(appExchange())
//                .with("normal.*");
//    }
//
//    @Bean
//    public Binding alarmPriorityBinding(TopicExchange topic,
//                                        Queue filteredDrivingData) {
//        return BindingBuilder.bind(filteredDrivingData)
//                .to(appExchange())
//                .with("alarm.*");
//    }
//
//
//
////    }
//
//    //    @Profile("sender")
//    @Bean
//    public OutputFilter sender() {
//        return new OutputFilter();
//    }
//
//
////    @Profile("usage_message")
////    @Bean
////    public CommandLineRunner usage() {
////        return new CommandLineRunner() {
////
////            @Override
////            public void run(String... arg0) throws Exception {
////                System.out.println("This app uses Spring Profiles to control its behavior.\n");
////                        System.out.println("Sample usage: java -jarrabbit-tutorials.jarspring.profiles.active=hello-world,sender");
////            }
////        };
////    }
////
////    @Profile("!usage_message")
////    @Bean
////    public CommandLineRunner tutorial() {
////        return new RabbitRunner();
////    }
//
//// ////////////////////////////////////////


}
