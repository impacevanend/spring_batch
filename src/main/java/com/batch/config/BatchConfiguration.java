package com.batch.config;

import com.batch.steps.ItemDescompresStep;
import com.batch.steps.ItemProcessorStep;
import com.batch.steps.ItemReaderStep;
import com.batch.steps.ItemWriterStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @JobScope
    public ItemDescompresStep itemDescompresStep(){
        return new ItemDescompresStep();
    }

    @Bean
    @JobScope
    public ItemReaderStep itemReaderStep(){
        return new ItemReaderStep();
    }

    @Bean
    @JobScope
    public ItemProcessorStep itemProcessorStep(){
        return new ItemProcessorStep();
    }

    @Bean
    @JobScope
    public ItemWriterStep itemWriterStep(){
        return new ItemWriterStep();
    }


    @Bean
    public Step descompresFileStep(){
        return stepBuilderFactory.get("itemDescompresStep")
                .tasklet(itemDescompresStep())
                .build();
    }

    @Bean
    public Step readFileStep(){
        return stepBuilderFactory.get("itemReaderStep")
                .tasklet(itemReaderStep())
                .build();
    }

    @Bean
    public  Step processDataStep(){
        return stepBuilderFactory.get("itemProcessorStep")
                .tasklet(itemProcessorStep())
                .build();
    }

    @Bean
    public Step writerDataStep(){
        return stepBuilderFactory.get("itemWriterStep")
                .tasklet(itemWriterStep())
                .build();
    }

    public Job readCSVJob(){
        return jobBuilderFactory.get("readCSVJob")
                .start(descompresFileStep())
                .next(readFileStep())
                .next(processDataStep())
                .next(writerDataStep())
                .build();
    }

}
