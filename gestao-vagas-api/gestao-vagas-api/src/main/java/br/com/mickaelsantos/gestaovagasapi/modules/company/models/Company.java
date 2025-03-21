package br.com.mickaelsantos.gestaovagasapi.modules.company.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity(name = "TB_COMPANY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Company 
{
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;

    @Pattern(regexp = "^(?!\\s*$).+", message = "O campo [username] não deve conter espaços!")
    private String cnpj;
    
    private String username;

    @Email(message = "O campo deve conter um e-mail válido!")
    private String email;

    @Length(min = 10, max = 100, message = "O campo [password] deve conter no mínimo 10 e no máximo 100 caracteres!")
    private String password;

    private String website;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "companyId")
    private List<Job> jobs;


}
