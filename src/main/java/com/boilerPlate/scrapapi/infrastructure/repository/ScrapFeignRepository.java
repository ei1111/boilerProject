package com.boilerPlate.scrapapi.infrastructure.repository;

import com.boilerPlate.scrapapi.infrastructure.domain.ScrapData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapFeignRepository extends JpaRepository<ScrapData, Long> {
    Optional<ScrapData> findByName(String name);
}
