package br.com.mickaelsantos.gestaovagasapi.modules.candidate.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import br.com.mickaelsantos.gestaovagasapi.exceptions.CandidateNotFoundException;
import br.com.mickaelsantos.gestaovagasapi.exceptions.JobNotFoundException;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.Repositories.ApplyJobRepository;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.Repositories.CandidateRepository;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.models.ApplyJob;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.models.Candidate;
import br.com.mickaelsantos.gestaovagasapi.modules.candidate.useCases.ApplyJobUseCase;
import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Job;
import br.com.mickaelsantos.gestaovagasapi.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobUseCaseTest 
{
    @InjectMocks
    private ApplyJobUseCase applyJobUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found()
    {
       
        assertThatThrownBy(() -> applyJobUseCase.execute(null, null))
        .isInstanceOf(CandidateNotFoundException.class);

    }    

    @Test
    public void should_not_be_able_to_apply_job_with_job_not_found()
    {
        var idCandidate = UUID.randomUUID();

        var candidate = new Candidate();
        candidate.setUuid(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try
        {
            applyJobUseCase.execute(null, idCandidate);
        }
        catch(Exception ex)
        {
            assertThat(ex).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test

    public void should_be_able_to_create_a_new_apply_job()
    {
        var uuIdCandidate = UUID.randomUUID();

        var uuIdJob = UUID.randomUUID();

        var applyJob = ApplyJob.builder()
        .candidateUuId(uuIdCandidate)
        .jobUuId(uuIdJob)
        .build();

        var applyJobCreated = ApplyJob.builder()
        .uuid(UUID.randomUUID())
        .build();

        when(candidateRepository.findById(uuIdCandidate)).thenReturn(Optional.of(new Candidate()));
        when(jobRepository.findById(uuIdJob)).thenReturn(Optional.of(new Job()));
        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobUseCase.execute(uuIdJob, uuIdCandidate);

        assertThat(result).hasFieldOrProperty("uuid");
        assertNotNull(applyJobCreated.getUuid());
    }
}

