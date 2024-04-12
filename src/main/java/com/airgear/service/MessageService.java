package com.airgear.service;

import com.airgear.dto.MessageChangeTextRequest;
import com.airgear.dto.MessageSaveRequest;
import com.airgear.dto.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface MessageService {

    MessageResponse create(MessageSaveRequest request);

    Page<MessageResponse> getAllMessageByGoodsId(Pageable pageable, long goodsId);

    Optional<MessageResponse> getMessageById(UUID messageId);

    Optional<MessageResponse> getMessageByText(String text);

    MessageResponse changeTextMessage(UUID messageId, MessageChangeTextRequest request);

    void deleteMessageById(UUID messageId);
}
