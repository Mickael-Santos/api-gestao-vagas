package br.com.mickaelsantos.gestaovagasapi.exceptions.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto 
{
    private String Message;
    private String Field;
    
}
