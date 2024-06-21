package br.com.mickaelsantos.gestaovagasapi.modules.candidate.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Entity(name = "TB_CANDIDATE")
@Data

public class Candidate 
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;
    @Pattern(regexp = "^(?!\\s*$).+", message = "O campo [username] não deve conter espaços!")
    private String username;

    @Email(message = "O campo deve conter um e-mail válido!")
    private String email;

    @Length(min = 10, max = 100, message = "O campo [password] deve conter no mínimo 10 e no máximo 100 caracteres!")
    private String password;
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
