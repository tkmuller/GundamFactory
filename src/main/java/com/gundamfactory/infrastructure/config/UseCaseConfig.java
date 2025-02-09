package com.gundamfactory.infrastructure.config;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.application.useCases.CreateGundamUseCase;
import com.gundamfactory.application.useCases.DeleteGundamUseCase;
import com.gundamfactory.application.useCases.GetAllGundamsUseCase;
import com.gundamfactory.application.useCases.GetGundamByIdUseCase;
import com.gundamfactory.application.useCases.MassProduceGundamUseCase;
import com.gundamfactory.application.useCases.SearchGundamsByNameUseCase;
import com.gundamfactory.application.useCases.UpdateGundamUseCase;
import com.gundamfactory.application.useCases.impl.CreateGundamUseCaseImpl;
import com.gundamfactory.application.useCases.impl.DeleteGundamUseCaseImpl;
import com.gundamfactory.application.useCases.impl.GetAllGundamsUseCaseImpl;
import com.gundamfactory.application.useCases.impl.GetGundamByIdUseCaseImpl;
import com.gundamfactory.application.useCases.impl.MassProduceGundamUseCaseImpl;
import com.gundamfactory.application.useCases.impl.SearchGundamsByNameUseCaseImpl;
import com.gundamfactory.application.useCases.impl.UpdateGundamUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public GetAllGundamsUseCase getAllGundamsUseCase(GundamRepositoryPort gundamRepositoryPort) {
        return new GetAllGundamsUseCaseImpl(gundamRepositoryPort);
    }

    @Bean
    public GetGundamByIdUseCase getGundamByIdUseCase(GundamRepositoryPort gundamRepositoryPort) {
        return new GetGundamByIdUseCaseImpl(gundamRepositoryPort);
    }

    @Bean
    public CreateGundamUseCase createGundamUseCase(GundamRepositoryPort gundamRepositoryPort) {
        return new CreateGundamUseCaseImpl(gundamRepositoryPort);
    }

    @Bean
    public UpdateGundamUseCase updateGundamUseCase(GundamRepositoryPort gundamRepositoryPort) {
        return new UpdateGundamUseCaseImpl(gundamRepositoryPort);
    }

    @Bean
    public DeleteGundamUseCase deleteGundamUseCase(GundamRepositoryPort gundamRepositoryPort) {
        return new DeleteGundamUseCaseImpl(gundamRepositoryPort);
    }

    @Bean
    public SearchGundamsByNameUseCase SearchGundamsByNameUseCase(GundamRepositoryPort gundamRepositoryPort) {
        return new SearchGundamsByNameUseCaseImpl(gundamRepositoryPort);
    }

    @Bean
    public MassProduceGundamUseCase produceGundamUseCase(GundamRepositoryPort gundamRepositoryPort) {
        return new MassProduceGundamUseCaseImpl(gundamRepositoryPort);
    }
}
