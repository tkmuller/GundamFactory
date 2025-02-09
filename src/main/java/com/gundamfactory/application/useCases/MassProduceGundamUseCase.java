package com.gundamfactory.application.useCases;

import com.gundamfactory.domain.entities.Gundam;

import java.util.function.BiConsumer;

public interface MassProduceGundamUseCase extends BiConsumer<Gundam, Integer> {
}
