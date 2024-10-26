package br.com.mickaelsantos.gestaovagasapi.modules.candidate.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
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

    @Schema(example = "João Farias", requiredMode = RequiredMode.REQUIRED, description = "Nome completo do usuário")
    private String name;

    @Pattern(regexp = "^(?!\\s*$).+", message = "O campo [username] não deve conter espaços!")
    @Schema(example = "João", requiredMode = RequiredMode.REQUIRED, description = "Primeiro nome do usuário ou apelido")
    private String username;

    @Email(message = "O campo deve conter um e-mail válido!")
    @Schema(example = "contato.joaofarias@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do usuário")
    private String email;

    @Length(min = 10, max = 100, message = "O campo [password] deve conter no mínimo 10 e no máximo 100 caracteres!")
    @Schema(example = "Xo8dkaj5ef*",minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Senha do usuário")
    private String password;

    @Schema(example = "Sou joão farias, sou desenvolvedor Senior a 10 anos, além de grande entusiamo possuo comprometimento com as oportunidades que me são dadas", requiredMode = RequiredMode.NOT_REQUIRED,
    description = "Uma apresentação profissional resumida")
    private String description;
    
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
