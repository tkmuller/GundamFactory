package com.gundamfactory.application.useCases;

import com.gundamfactory.domain.entities.Gundam;

import java.util.Optional;
import java.util.function.BiFunction;

public interface UpdateGundamUseCase extends BiFunction<Long, Gundam, Optional<Gundam>> {
}
