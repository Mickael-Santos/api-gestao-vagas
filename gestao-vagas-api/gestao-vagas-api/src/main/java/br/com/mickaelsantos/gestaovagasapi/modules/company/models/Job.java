package br.com.mickaelsantos.gestaovagasapi.modules.company.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_JOB")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job 
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Schema(example = "Vaga para pessoa desenvolvedora júnior", requiredMode = RequiredMode.REQUIRED,
    description = "Descrição da vaga")
    private String description;

    @Schema(example = "Gympass, day-off e vale alimentação", requiredMode = RequiredMode.REQUIRED, description = "Benefícios oferecidos pela vaga")
    private String benefits;

    @NotBlank
    @Schema(example = "Júnior", requiredMode = RequiredMode.REQUIRED, description = "Descrição do nível requerido para vaga")
    private String level;

    @ManyToOne()
    @JoinColumn(name = "company_uuid", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_uuid")
    private UUID companyId;


    @CreationTimestamp
    private LocalDateTime createdAt;

}
