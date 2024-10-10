package in.co.ad.springboot.ai.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import in.co.ad.springboot.ai.sample.ClaudeRecords.*;

@HttpExchange("/v1")
public interface ClaudeInterface {

    @PostExchange("/complete")
    ClaudeResponse getCompletion(@RequestBody ClaudeRequest request);

    @PostExchange("/messages")
    ClaudeMessageResponse getMessageResponse(@RequestBody ClaudeMessageRequest request);
}