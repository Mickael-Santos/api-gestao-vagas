package br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.gestaovagasapi.modules.candidate.Repositories.CandidateRepository;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase 
{
    @Autowired
    private CandidateRepository candidateRepository;

   public ProfileCandidateResponseDTO execute(UUID uuIdCandidate)
   {
        var candidate = candidateRepository.findByUuid(uuIdCandidate)
        .orElseThrow(() ->{
            throw new UsernameNotFoundException("User does not exist or incorrect uuid");
        });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
        .uuId(candidate.getUuid())
        .name(candidate.getName())
        .username(candidate.getUsername())
        .email(candidate.getEmail())
        .description(candidate.getDescription())
        .build();

        return candidateDTO;
   }
}
