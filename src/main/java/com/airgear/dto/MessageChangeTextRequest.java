package com.airgear.dto;

import javax.validation.constraints.NotBlank;

public record MessageChangeTextRequest(

        @NotBlank(message = "text must not be empty")
        String text

) {
}
