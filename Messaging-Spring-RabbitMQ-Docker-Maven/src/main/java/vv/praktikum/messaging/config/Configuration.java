package vv.praktikum.messaging.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import vv.praktikum.messaging.receiver.InputFilter;
import vv.praktikum.messaging.receiver.OutputFilter;
import vv.praktikum.messaging.sender.TelematicUnit;
import vv.praktikum.messaging.topicDistributer.Logbook;

//@Profile({"receiver", "sender", "topicDistributer", "config"})
@org.springframework.context.annotation.Configuration
public class Configuration {
    public static final String EXCHANGE_NAME = "appExchange";
    public static final String QUEUE_UNFILTERED_DRIVINGQUEUE = "unfilteredDrivingQueue";
    public static final String QUEUE_FILTERED_DRIVINGDATA = "appSpecificQueue";




    /**
     * ERSTE QUEUE
     * *******************************************************************************************************
     *
     * */

    @Bean
    public Queue unfiltered_DrivingQueue() {
        return new Queue(QUEUE_UNFILTERED_DRIVINGQUEUE);
    }

    @Bean
    public TelematicUnit ioT_sender() {
        return new TelematicUnit();
    }

    @Bean
    public InputFilter receiver() {
        return new InputFilter();
    }

    /**
     * Topic QUEUE
     * *******************************************************************************************************
     *
     * */
//    @Bean
//    public Alarm alarm() {
//        return new Alarm();
//    }
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }
    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public OutputFilter sender() {
        return new OutputFilter();
    }

    @Bean
    public Queue filteredDrivingData() {
        return new AnonymousQueue();
    }
    @Bean
    public Logbook receiverFilteredData() {
        return new Logbook();
    }
    @Bean
    public Binding alarmPriorityBinding() {
        return BindingBuilder.bind(filteredDrivingData())
                .to(appExchange())
                .with("alarm");
    }

    @Bean
    public Binding normalPriorityBinding() {
        return BindingBuilder.bind(filteredDrivingData())
                .to(appExchange())
                .with("normal");
    }
    //    @Profile("filter")

    //    public TopicExchange topic() {

    //        return new TopicExchange("receiver");
    //    private class ReceiverConfig {
//    @Bean

//    }

//    @Profile("telematic-unit")

    /////////////////////////////////////////

//    @Profile("receiver")



//    }
//    @Profile("sender")
}
