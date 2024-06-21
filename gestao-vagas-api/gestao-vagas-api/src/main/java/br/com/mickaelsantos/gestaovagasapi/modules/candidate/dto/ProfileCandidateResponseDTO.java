package br.com.mickaelsantos.gestaovagasapi.modules.candidate.dto;

import java.util.UUID;

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

    private String name;

    private String username;

    private String email;

    private String description;
}
