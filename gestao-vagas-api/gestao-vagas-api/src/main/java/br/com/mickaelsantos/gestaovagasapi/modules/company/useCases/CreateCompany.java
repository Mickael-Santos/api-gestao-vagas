package br.com.mickaelsantos.gestaovagasapi.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.gestaovagasapi.exceptions.CompanyFoundException;
import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Company;
import br.com.mickaelsantos.gestaovagasapi.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompany 
{
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Company create(Company company)
    {
        companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
        .ifPresent((item) ->{
            throw new CompanyFoundException();
        });
        var password = passwordEncoder.encode(company.getPassword());
        company.setPassword(password);
      
        return companyRepository.save(company);
    }
}
