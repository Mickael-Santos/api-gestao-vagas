package br.com.mickaelsantos.gestaovagasapi.modules.company.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils 
{

    public static String objectToJson(Object obj)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.writeValueAsString(obj);

        }
        catch(Exception ex)
        {
            throw new RuntimeException(ex);
        }
        
    }

    public static String generateTestToken(UUID companyUuId, String secret)
    {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var token = JWT.create()
            .withIssuer("javagas")
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2))) 
            .withClaim("roles", Arrays.asList("COMPANY"))
            .withSubject(companyUuId.toString())
            .sign(algorithm);

        return token;
    }
}
