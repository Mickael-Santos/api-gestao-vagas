package br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.gestaovagasapi.exceptions.UserFoundException;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.Repositories.CandidateRepository;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.models.Candidate;

@Service
public class CreateCandidate
{
    @Autowired
    private CandidateRepository CandidateRep;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Candidate execute(Candidate candidate)
    {
        CandidateRep.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail()).
        ifPresent((user) -> {
            throw new UserFoundException();
        });

        var password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);

        return CandidateRep.save(candidate);
    }
}
