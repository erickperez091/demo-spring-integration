package com.integration.aggregator;

import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.ReleaseStrategy;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonAggregator {

    @ReleaseStrategy
    public boolean release(List<Message<?>> messages) {
        MessageHeaders headers = messages.get(messages.size() - 1).getHeaders();
        if (headers.containsKey("sequenceNumber") && headers.containsKey("sequenceSize")) {
            int sequenceNumber = headers.get("sequenceNumber", Integer.class);
            int sequenceSize = headers.get("sequenceSize", Integer.class);
            return sequenceNumber == sequenceSize;
        }
        return false;
    }

    @Aggregator
    public List<Object> aggregate(List<Object> payloads) {
        return payloads;
    }
}
