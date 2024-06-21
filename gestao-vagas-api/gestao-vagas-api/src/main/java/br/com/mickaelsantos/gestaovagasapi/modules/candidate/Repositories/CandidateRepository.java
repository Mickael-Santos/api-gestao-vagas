package br.com.mickaelsantos.gestaovagasapi.modules.candidate.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mickaelsantos.gestaovagasapi.modules.candidate.models.Candidate;
import java.util.List;


public interface CandidateRepository extends JpaRepository<Candidate, UUID>
{
    Optional<Candidate> findByUsernameOrEmail(String username, String email);

    Optional<Candidate> findByUsername(String username);

    Optional<Candidate> findByUuid(UUID uuid);
    
} 
