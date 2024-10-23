package br.com.mickaelsantos.gestaovagasapi.modules.company.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Job;

public interface JobRepository extends JpaRepository<Job, UUID>
{
    Optional<Job> findById(UUID uuid);

    List<Job> findByDescriptionContaining(String filter);
}
