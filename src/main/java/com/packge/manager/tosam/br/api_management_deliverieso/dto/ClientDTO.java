package com.packge.manager.tosam.br.api_management_deliverieso.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ClientDTO")
public record ClientDTO(
        @Schema(name = "clientId")
        String clientId,

        @Schema(name = "clientSecrete")
        String clientSecrete,

        @Schema(name = "redirectURI")
        String redirectURI,

        @Schema(name = "scope")
        String scope
) {
}
