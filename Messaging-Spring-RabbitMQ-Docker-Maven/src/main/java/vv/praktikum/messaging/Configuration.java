package vv.praktikum.messaging;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import vv.praktikum.messaging.receiver.InputFilter;
import vv.praktikum.messaging.receiver.OutputFilter;
import vv.praktikum.messaging.sender.TelematicUnit;
import vv.praktikum.messaging.topicDistributer.Logbook;

@Profile({"receiver", "sender", "topicDistributer"})
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public TopicExchange topic() {
        return new TopicExchange("tut.topic");
    }


    @Bean
    public Queue unfiltered_DrivingQueue() {
        return new Queue("unfiltered_DrivingQueue");
    }
    @Profile("filter")
    @Bean
    public InputFilter receiver() {
        return new InputFilter();
    }

    @Profile("telematic-unit")
    @Bean
    public TelematicUnit ioT_sender() {
        return new TelematicUnit();
    }

    /////////////////////////////////////////

    @Profile("receiver")
    private static class ReceiverConfig {
        @Bean
        public Logbook receiverFilteredData() {
            return new Logbook();
        }

        @Bean
        public Queue filteredDrivingData() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding normalPriorityBinding(TopicExchange topic,
                                             Queue filteredDrivingData) {
            return BindingBuilder.bind(filteredDrivingData)
                    .to(topic)
                    .with("normal.*");
        }

        @Bean
        public Binding alarmPriorityBinding(TopicExchange topic,
                                            Queue filteredDrivingData) {
            return BindingBuilder.bind(filteredDrivingData)
                    .to(topic)
                    .with("alarm.*");
        }



    }

    @Profile("sender")
    @Bean
    public OutputFilter sender() {
        return new OutputFilter();
    }
}
