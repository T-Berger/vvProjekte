//package vv.restwebservice.config;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.connector.Connector;
//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryCustomizer;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TomcatConfig {
//
//    @Value("${http.port}")
//    private int httpPort;
//
//
//
//
////    @Bean
////    public ServletWebServerFactoryCustomizer containerCustomizer() {
////
////        return new ServletWebServerFactoryCustomizer(ServerProperties.Tomcat c) {
////            @Override
////            public void customize(ConfigurableServletWebServerFactory container) {
////                if (container instanceof TomcatServletWebServerFactory) {
////                    TomcatServletWebServerFactory containerFactory  =
////                            (TomcatServletWebServerFactory) container;
////
////                    Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
////                    connector.setPort(httpPort);
////                    containerFactory.addAdditionalTomcatConnectors(connector);
////                }
////            }
////        };
////
////    }
////    @Bean
////    public EmbeddedServletContainerCustomizer containerCustomizer() {
////        return new EmbeddedServletContainerCustomizer() {
////            @Override
////            public void customize(ConfigurableEmbeddedServletContainer container) {
////                if (container instanceof TomcatEmbeddedServletContainerFactory) {
////                    TomcatEmbeddedServletContainerFactory containerFactory =
////                            (TomcatEmbeddedServletContainerFactory) container;
////
////                    Connector connector = new Connector(TomcatEmbeddedServletContainerFactory.DEFAULT_PROTOCOL);
////                    connector.setPort(httpPort);
////                    containerFactory.addAdditionalTomcatConnectors(connector);
////                }
////            }
////        };
////    }
//
//    @Configuration
//    public class TomcatRedirectHttpToHttpsConfig {
//
//        @Bean
//        public ConfigurableServletWebServerFactory servletContainer() {
//            TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//                @Override
//                protected void postProcessContext(Context context) {
//                    SecurityConstraint securityConstraint = new SecurityConstraint();
//                    securityConstraint.setUserConstraint("CONFIDENTIAL");
//                    SecurityCollection collection = new SecurityCollection();
//                    collection.addPattern("/*");
//                    securityConstraint.addCollection(collection);
//                    context.addConstraint(securityConstraint);
//                }
//            };
//            tomcat.addAdditionalTomcatConnectors(redirectConnector());
//            return tomcat;
//        }
//
//        private Connector redirectConnector() {
//            Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//            connector.setScheme("http");
//            connector.setPort(8080);
//            connector.setSecure(false);
//            connector.setRedirectPort(4334);
//            return connector;
//        }
//    }
//
//}
