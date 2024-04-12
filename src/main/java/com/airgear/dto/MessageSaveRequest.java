package com.airgear.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record MessageSaveRequest(

        @NotBlank(message = "text must not be empty")
        @Size(min = 2, max = 2048, message = "message of text should be between 2 and 2048 characters")
        String text,

        @NotNull(message = "goods id must not be null")
        long goodsId,

        @NotNull(message = "user id must not be null")
        long userId

) implements Serializable {
}
