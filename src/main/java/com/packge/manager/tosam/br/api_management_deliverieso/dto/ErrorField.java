package com.packge.manager.tosam.br.api_management_deliverieso.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ErrorField")
public record ErrorField(
        @Schema(name = "message")
        String message,
        
        @Schema(name = "field")
        String field) {
}
