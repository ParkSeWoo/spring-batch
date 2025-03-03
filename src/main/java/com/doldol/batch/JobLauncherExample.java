package com.doldol.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
//public class JobLauncherExample implements ApplicationRunner {
//
//    private JobLauncher jobLauncher;
//    private Job sampleJob;
//
//    public JobLauncherExample(JobLauncher jobLauncher, Job sampleJob) {
//        this.jobLauncher = jobLauncher;
//        this.sampleJob = sampleJob;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("name", "userA")  // 실행 시 전달할 파라미터
//                .toJobParameters();
//        JobExecution execution = jobLauncher.run(sampleJob, jobParameters);
//    }
//}
