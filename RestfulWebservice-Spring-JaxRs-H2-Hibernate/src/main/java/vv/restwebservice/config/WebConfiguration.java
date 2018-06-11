package vv.restwebservice.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;

    }
//    @Bean
//    public UndertowServletWebServerFactory embeddedServletContainerFactory() {
//        UndertowServletWebServerFactory factory =
//                new UndertowServletWebServerFactory();
//
//        factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {
//            @Override
//            public void customize(io.undertow.Undertow.Builder builder) {
//                builder.addHttpListener(8080, "127.0.0.0");
//            }
//        });
//
//        return factory;
//    }

}