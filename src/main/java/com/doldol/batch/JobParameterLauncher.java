package com.doldol.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobParameterLauncher implements ApplicationRunner {

    private JobLauncher jobLauncher;
    private Job paramJob;

    public JobParameterLauncher(JobLauncher jobLauncher, Job paramJob) {
        this.jobLauncher = jobLauncher;
        this.paramJob = paramJob;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addString("name", "user11")
                                                                .addLong("seq",2L)
                                                                .addDate("date", new Date())
                                                                .addDouble("age", 16.5)
                                                                .toJobParameters();

        jobLauncher.run(paramJob, jobParameters);
    }
}
