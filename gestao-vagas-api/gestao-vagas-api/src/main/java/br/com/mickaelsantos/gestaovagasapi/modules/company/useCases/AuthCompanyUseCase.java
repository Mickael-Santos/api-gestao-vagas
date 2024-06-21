package br.com.mickaelsantos.gestaovagasapi.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.mickaelsantos.gestaovagasapi.modules.company.dto.AuthCompanyDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.company.dto.AuthCompanyResponseDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase 
{
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    @Value("${config.issuer}")
    private String tokenIssuer;


    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException
    {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername())
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("username/password incorrect");
        });

        var passwordMatches = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(!passwordMatches)
        {
            throw new AuthenticationException("incorrect credentials");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(48));

        var token = JWT.create().withIssuer(tokenIssuer)
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2))) 
            .withClaim("roles", Arrays.asList("COMPANY"))
            .withSubject(company.getUuid().toString())
            .sign(algorithm);

        AuthCompanyResponseDTO authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
        .acessToken(token)
        .expiresIn(expiresIn.toEpochMilli())
        .build();

        return authCompanyResponseDTO;
        
    }
}
