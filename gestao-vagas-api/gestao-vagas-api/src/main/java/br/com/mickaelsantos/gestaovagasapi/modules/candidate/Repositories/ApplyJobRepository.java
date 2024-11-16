package br.com.mickaelsantos.gestaovagasapi.modules.candidate.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mickaelsantos.gestaovagasapi.modules.candidate.models.ApplyJob;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, UUID >
{
    
}
