package au.com.jc.nettyclient.route;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NettyProducerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:sendMessage" )
                .log("Sending message to netty producer")
                .to("netty4:tcp://127.0.0.1:8992?sync=true&clientInitializerFactory=#clientInitializerFactory&reuseChannel=true");
    }
}
