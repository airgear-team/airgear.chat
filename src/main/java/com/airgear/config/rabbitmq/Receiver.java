package com.airgear.config.rabbitmq;

import com.airgear.dto.MessageSaveRequest;
import com.airgear.service.MessageService;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private final MessageService messageService;

    public Receiver(MessageService messageService) {
        this.messageService = messageService;
    }

    public void receiveMessage(MessageSaveRequest request) {
        messageService.create(request);
    }

    public String getMethodName() {
        return "receiveMessage";
    }
}
