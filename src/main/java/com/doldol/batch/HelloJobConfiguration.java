package com.doldol.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class HelloJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public HelloJobConfiguration(JobRepository jobRepository,
                                 PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job helloJob() {
        return new JobBuilder("myJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(myStep())
                .next(myStep2())
                .build();
    }

    @Bean
    public Step myStep() {
        return new StepBuilder("myStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("=================");
                    System.out.println(" >> Hello Spring Batch!!");
                    System.out.println("=================");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step myStep2() {
        return new StepBuilder("myStep2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("=================");
                    System.out.println(" >> STEP2 was executed !!");
                    System.out.println("=================");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}

/*
(Spring Batch 4.x 이하)
@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job myJob() {
        return jobBuilderFactory.get("myJob")
                .start(myStep())
                .build();
    }

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step 실행");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}

*/