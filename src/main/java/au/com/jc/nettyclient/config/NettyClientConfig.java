package au.com.jc.nettyclient.config;


import org.apache.camel.component.netty4.ClientInitializerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyClientConfig {

    @Bean
    public ClientInitializerFactory clientInitializerFactory() {
        return new NettyClientInitializer(null);
    }
}
