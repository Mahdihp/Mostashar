package ir.mostashar.model.feature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ir.mostashar.model.feature.Feature;

@Repository
public interface FeatureRepo extends JpaRepository<Feature, Long> {
	Feature findByName(String name);
}
