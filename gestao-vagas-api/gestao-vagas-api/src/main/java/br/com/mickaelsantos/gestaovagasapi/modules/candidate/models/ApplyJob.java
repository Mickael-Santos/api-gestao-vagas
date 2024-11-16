package br.com.mickaelsantos.gestaovagasapi.modules.candidate.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Job;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_APPLYJOB")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ApplyJob 
{
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "candidate_uuid", insertable = false, updatable = false)
    private Candidate candidate;

    @Column(name = "candidate_uuid")
    private UUID candidateUuId;

    @ManyToOne
    @JoinColumn(name = "job_uuid", insertable = false, updatable = false)
    private Job job;

    @Column(name = "job_uuid")
    private UUID jobUuId;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}
