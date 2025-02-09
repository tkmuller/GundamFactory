package com.gundamfactory.application.useCases;

import com.gundamfactory.domain.entities.Gundam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.function.Function;

public interface GetAllGundamsUseCase extends Function<Pageable, Page<Gundam>> {
}
