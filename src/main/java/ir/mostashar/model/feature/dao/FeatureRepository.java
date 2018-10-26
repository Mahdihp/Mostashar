package ir.mostashar.model.feature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ir.mostashar.model.feature.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long>, JpaSpecificationExecutor<Feature> {
	Feature findByName(String name);
}
