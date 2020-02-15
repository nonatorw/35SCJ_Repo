package br.com.fiap.cervejariabatchtasklet;

import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.fiap.cervejariabatchtasklet.config.BatchTestConfig;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CervejariaBatchChunkApplication.class, BatchTestConfig.class})
class CervejariaBatchTaskletApplicationTests {

    @Autowired
    private JobLauncherTestUtils jobLauncher;

    @Autowired
    private DataSource datasource;

    @Autowired
    private Job job;

    @Test
    public void testJob() throws
                          Exception {
        JobExecution jobExecution = jobLauncher.getJobLauncher().run(job, jobLauncher.getUniqueJobParameters());

        Assertions.assertNotNull(jobExecution);
        Assertions.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

        ResultSet resultSet = datasource.getConnection()
                                        .prepareStatement("SELECT count(*) FROM tb_pessoa")
                                        .executeQuery();

        await().atMost(15, TimeUnit.SECONDS).until(() -> {
            resultSet.last();
            return resultSet.getInt(1) == 10;
        });

        Assertions.assertEquals(10, resultSet.getInt(1));
    }
}
