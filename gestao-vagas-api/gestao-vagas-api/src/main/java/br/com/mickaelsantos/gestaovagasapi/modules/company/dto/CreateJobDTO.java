package br.com.mickaelsantos.gestaovagasapi.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO 
{
    private String description;
    private String benefits;
    private String level;
}
