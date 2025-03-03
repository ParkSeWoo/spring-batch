package com.doldol.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobParameterConfiguration extends DefaultBatchConfiguration {
    @Bean
    public Job paramJob(JobRepository jobRepository, Step paramStep, Step paramStep2) {
        return new JobBuilder("paramJob", jobRepository)
                .start(paramStep)
                .next(paramStep2)
                .build();
    }

    @Bean
    public Step paramStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("paramStep", jobRepository)
                    .tasklet((contribution, chunkContext) -> {
                    JobParameters jobParameters = chunkContext.getStepContext()
                            .getStepExecution()
                            .getJobExecution()
                            .getJobParameters();

                    String name = jobParameters.getString("name", "defaultUser");

                    System.out.println("Step 실행 중... name: " + name);

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step paramStep2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("paramStep2", jobRepository)
                    .tasklet(new CustomTasklet(), transactionManager)
                    .build();
    }
}
