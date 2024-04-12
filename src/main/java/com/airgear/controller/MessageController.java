package com.airgear.controller;

import com.airgear.util.Routes;
import com.airgear.config.rabbitmq.RabbitmqProperties;
import com.airgear.exceptions.MessageExceptions;
import com.airgear.dto.MessageChangeTextRequest;
import com.airgear.dto.MessageSaveRequest;
import com.airgear.dto.MessageResponse;
import com.airgear.service.MessageService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

import static com.airgear.exceptions.MessageExceptions.messageNotFound;

@RestController
@RequestMapping(Routes.MESSAGE)
public class MessageController {

    private final MessageService messageService;
    private final RabbitmqProperties rabbitmqProperties;
    private final AmqpTemplate amqpTemplate;

    public MessageController(MessageService messageService,
                             RabbitmqProperties rabbitmqProperties,
                             AmqpTemplate amqpTemplate) {
        this.messageService = messageService;
        this.rabbitmqProperties = rabbitmqProperties;
        this.amqpTemplate = amqpTemplate;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MessageResponse> create(@RequestBody @Valid MessageSaveRequest request,
                                                  UriComponentsBuilder ucb) {
        amqpTemplate.convertAndSend(rabbitmqProperties.getExchange(), rabbitmqProperties.getKey(), request);
        MessageResponse response = messageService.getMessageByText(request.text())
                .orElseThrow(() -> MessageExceptions.messageNotFound(request.text()));

        return ResponseEntity
                .created(ucb.path("/{id}").build(response.id()))
                .body(response);
    }

    @GetMapping(
            value = "/{goodsId}/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PageableAsQueryParam
    public Page<MessageResponse> listMessages(@Parameter(hidden = true) Pageable pageable,
                                              @PathVariable long goodsId) {
        return messageService.getAllMessageByGoodsId(pageable, goodsId);
    }

    @GetMapping(
            value = "/{messageId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MessageResponse getMessageById(@PathVariable UUID messageId) {
        return messageService.getMessageById(messageId)
                .orElseThrow(() -> messageNotFound(messageId));
    }

    @PatchMapping(
            value = "/{messageId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MessageResponse changeText(@PathVariable UUID messageId,
                                      @RequestBody @Valid MessageChangeTextRequest request) {
        return messageService.changeTextMessage(messageId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{messageId}")
    public void deleteMessageById(@PathVariable UUID messageId) {
        messageService.deleteMessageById(messageId);
    }
}
