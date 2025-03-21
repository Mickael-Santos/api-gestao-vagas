package br.com.mickaelsantos.gestaovagasapi.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class CreateJobDTO 
{
    private String description;
    private String benefits;
    private String level;
}
