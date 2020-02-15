package br.com.fiap.cervejariabatchtasklet;

import java.io.File;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class CervejariaBatchTaskletApplication {
    Logger logger = LoggerFactory.getLogger(CervejariaBatchTaskletApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CervejariaBatchTaskletApplication.class, args);
    }

    @Bean
    @Qualifier("deleteTasklet")
    public Tasklet deleteTasklet(@Value("${file.path}") String path) {
        return (contribution, chunkContext) -> {
            File file = Paths.get(path).toFile();

            if (file.delete()) {
                logger.info("Arquivo deletado.");
            } else {
                logger.error("Não foi possível deletar o arquivo.");
            }

            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step step(StepBuilderFactory factory, Tasklet tasklet) {
        return factory.get("Delete File Step").tasklet(tasklet).allowStartIfComplete(true).build();
    }

    @Bean
    public Job job(JobBuilderFactory factory, Step step) {
        return factory.get("Delete file Job").start(step).build();
    }
}
