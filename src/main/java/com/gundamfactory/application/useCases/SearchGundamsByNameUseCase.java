package com.gundamfactory.application.useCases;

import com.gundamfactory.domain.entities.Gundam;

import java.util.List;
import java.util.function.Function;

public interface SearchGundamsByNameUseCase extends Function<String, List<Gundam>> {
}
