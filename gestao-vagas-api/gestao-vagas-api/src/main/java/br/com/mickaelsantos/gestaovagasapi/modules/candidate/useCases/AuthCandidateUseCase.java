package br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.mickaelsantos.gestaovagasapi.modules.candidate.Repositories.CandidateRepository;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.models.Candidate;
import ch.qos.logback.core.util.Duration;

@Service
public class AuthCandidateUseCase 
{
    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Value("${config.issuer}")
    private String issuer;
    @Autowired

    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder PasswordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authRequestDto) throws AuthenticationException
    {
        var candidate = candidateRepository.findByUsername(authRequestDto.username())
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("username/password incorrect");
        });

        var passwordMatches = PasswordEncoder.matches(authRequestDto.password(), candidate.getPassword());

        if(!passwordMatches)
        {
            throw new AuthenticationException("password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var ExpiresIn = (Instant.now().plus(java.time.Duration.ofHours(5)));
        var token = JWT.create()
        .withIssuer(issuer)
        .withSubject(candidate.getUuid().toString())
        .withExpiresAt(ExpiresIn)
        .withClaim("roles", Arrays.asList("CANDIDATE"))
        .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
        .access_token(token)
        .expiresIn(ExpiresIn.toEpochMilli())
        .build();

        return authCandidateResponse;
    }
}
