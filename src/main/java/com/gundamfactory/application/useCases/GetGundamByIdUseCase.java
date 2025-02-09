package com.gundamfactory.application.useCases;

import com.gundamfactory.domain.entities.Gundam;

import java.util.Optional;
import java.util.function.Function;

public interface GetGundamByIdUseCase extends Function<Long, Optional<Gundam>> {
}
