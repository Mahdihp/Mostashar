package ir.mostashar.model.feature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ir.mostashar.model.feature.Feature;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeatureRepo extends JpaRepository<Feature, Long> {

    Optional<Boolean> existsByName(String name);
    Optional<Feature> findByName(String name);
    Optional<Feature> findByUid(UUID uuid);

}
