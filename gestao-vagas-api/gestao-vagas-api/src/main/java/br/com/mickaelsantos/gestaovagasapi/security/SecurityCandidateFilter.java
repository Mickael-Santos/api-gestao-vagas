package br.com.mickaelsantos.gestaovagasapi.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.mickaelsantos.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter   
{

    @Autowired
    private JWTCandidateProvider candidateProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException 
    {
        
        //SecurityContextHolder.getContext().setAuthentication(null);

        String headerToken = request.getHeader("Authorization");

        if(request.getRequestURI().startsWith("/candidate"))
        {
            if(headerToken != null)
            {
                var token = candidateProvider.validateToken(headerToken);

                if(token == null)
                {
                    
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                
                request.setAttribute("candidate_id", token.getSubject());

                var roles = token.getClaim("roles").asList(Object.class);

                var granteds = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
                .toList();
                System.out.println();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    token.getSubject(),
                     null,
                      granteds);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }
        

        filterChain.doFilter(request, response);
    }
    
}
