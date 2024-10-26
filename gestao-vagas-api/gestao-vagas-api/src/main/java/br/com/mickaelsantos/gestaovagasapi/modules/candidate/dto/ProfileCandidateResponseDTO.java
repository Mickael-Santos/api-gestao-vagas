package br.com.mickaelsantos.gestaovagasapi.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProfileCandidateResponseDTO 
{
    private UUID uuId;

    @Schema(example = "Pedro Alves", requiredMode = RequiredMode.REQUIRED, description = "Nome completo do usuário")
    private String name;

    @Schema(example = "Pedro", requiredMode = RequiredMode.REQUIRED, description = "Primeiro nome ou apelido")
    private String username;

    @Schema(example = "contato.geanmickael@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do usuário")
    private String email;

    @Schema(example = "Desenvolvedor Júnior e profissional da área de TI com experiências para agregar e pronto para adquirir novas experiências", requiredMode = RequiredMode.NOT_REQUIRED, 
    description = "Uma apresentação profissional resumida")
    private String description;
}
