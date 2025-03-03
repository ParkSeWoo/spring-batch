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

//@Configuration
//public class JobInstanceExample extends DefaultBatchConfiguration {
//    @Bean
//    public Job sampleJob(JobRepository jobRepository, Step sampleStep) {
//        return new JobBuilder("sampleJob", jobRepository)
//                .start(sampleStep)
//                .build();
//    }
//
//    @Bean
//    public Step sampleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("sampleStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    JobParameters jobParameters = chunkContext.getStepContext()
//                            .getStepExecution()
//                            .getJobExecution()
//                            .getJobParameters();
//
//                    String name = jobParameters.getString("name", "defaultUser");
//
//                    System.out.println("Step 실행 중... name: " + name);
//
//                    return RepeatStatus.FINISHED;
//                }, transactionManager)
//                .build();
//    }
//}
