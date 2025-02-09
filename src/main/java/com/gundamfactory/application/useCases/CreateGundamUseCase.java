package com.gundamfactory.application.useCases;

import com.gundamfactory.domain.entities.Gundam;

import java.util.function.Function;

public interface CreateGundamUseCase extends Function<Gundam, Gundam> {
}
