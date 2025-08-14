package com.batch.config;

import com.batch.steps.ItemDescompresStep;
import com.batch.steps.ItemProcessorStep;
import com.batch.steps.ItemReaderStep;
import com.batch.steps.ItemWriterStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfiguration {

    @Bean
    public ItemDescompresStep itemDescompresStep(){
        return new ItemDescompresStep();
    }

    @Bean
    public ItemReaderStep itemReaderStep(){
        return new ItemReaderStep();
    }

    @Bean
    public ItemProcessorStep itemProcessorStep(){
        return new ItemProcessorStep();
    }

    @Bean
    public ItemWriterStep itemWriterStep(){
        return new ItemWriterStep();
    }


}
