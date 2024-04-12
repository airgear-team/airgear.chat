package com.airgear.service.impl;

import com.airgear.model.Goods;
import com.airgear.model.Message;
import com.airgear.dto.MessageChangeTextRequest;
import com.airgear.dto.MessageSaveRequest;
import com.airgear.dto.MessageResponse;
import com.airgear.model.User;
import com.airgear.repository.GoodsRepository;
import com.airgear.repository.MessageRepository;
import com.airgear.repository.UserRepository;
import com.airgear.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.airgear.exceptions.GoodsExceptions.goodsNotFound;
import static com.airgear.exceptions.MessageExceptions.messageNotFound;
import static com.airgear.exceptions.UserExceptions.userNotFound;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final GoodsRepository goodsRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository,
                              GoodsRepository goodsRepository,
                              UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.goodsRepository = goodsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MessageResponse create(MessageSaveRequest request) {
        return MessageResponse.fromMessage(save(request));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MessageResponse> getAllMessageByGoodsId(Pageable pageable, long goodsId) {
        return messageRepository.findAllByGoods_Id(pageable, goodsId)
                .map(MessageResponse::fromMessage);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MessageResponse> getMessageById(UUID messageId) {
        return messageRepository.findById(messageId)
                .map(MessageResponse::fromMessage);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MessageResponse> getMessageByText(String text) {
        return messageRepository.findByText(text)
                .map(MessageResponse::fromMessage);
    }

    @Override
    public MessageResponse changeTextMessage(UUID messageId, MessageChangeTextRequest request) {
        Message message = getMessage(messageId);
        message.setText(request.text());
        return MessageResponse.fromMessage(message);
    }

    @Override
    public void deleteMessageById(UUID messageId) {
        if (!messageRepository.existsById(messageId)) throw messageNotFound(messageId);
        messageRepository.deleteById(messageId);
    }

    private Message save(MessageSaveRequest request) {
        Goods goods = getGoods(request.goodsId());
        User user = getUser(request.userId());
        return new Message(
                request.text(),
                goods,
                user,
                OffsetDateTime.now()
        );
    }

    private Goods getGoods(long goodsId) {
        return goodsRepository.findById(goodsId)
                .orElseThrow(() -> goodsNotFound(goodsId));
    }

    private User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> userNotFound(userId));
    }

    private Message getMessage(UUID messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> messageNotFound(messageId));
    }
}
