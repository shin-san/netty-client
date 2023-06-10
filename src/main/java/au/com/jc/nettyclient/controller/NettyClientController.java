package au.com.jc.nettyclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/")
public class NettyClientController {


    @Autowired
    CamelContext context;

    @Autowired
    ProducerTemplate producerTemplate;
    Exchange exchange;

    @PostMapping(path = "/sendMessage", consumes = "text/plain")
    public ResponseEntity<String> sendMessage(@RequestBody String payload) {
        log.info("Received payload: {}", payload);

        exchange = new DefaultExchange(context);

        exchange.getIn().setBody(payload, String.class);
//        exchange.setPattern(ExchangePattern.InOnly);

        producerTemplate.send("direct:sendMessage", exchange);

        String response = exchange.getIn().getBody(String.class);

        // hopefully get a response
        return ResponseEntity.ok().body(response);
    }
}
