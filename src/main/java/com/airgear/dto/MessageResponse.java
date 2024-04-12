package com.airgear.dto;

import com.airgear.model.Message;

import java.util.UUID;

/**
 * Represents a response object for messages with specific fields.
 * This class is designed as a record, providing immutability and a concise syntax.
 * <p>
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public record MessageResponse(UUID id,
                              String text,
                              String username) {

    /**
     * Creates a new {@code MessageResponse} instance based on the provided {@code Message} entity.
     *
     * @param message The source message entity.
     * @return A new {@code MessageResponse} instance with data from the given message.
     */
    public static MessageResponse fromMessage(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getText(),
                message.getUser().getName()
        );
    }
}
