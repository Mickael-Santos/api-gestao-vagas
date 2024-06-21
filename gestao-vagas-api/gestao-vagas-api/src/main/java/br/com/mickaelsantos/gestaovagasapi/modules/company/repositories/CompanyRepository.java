package br.com.mickaelsantos.gestaovagasapi.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Company;
import java.util.List;


public interface CompanyRepository extends JpaRepository<Company, UUID>
{
    Optional<Company> findByUsernameOrEmail(String username, String email);

    Optional<Company> findByUsername(String username);
}
