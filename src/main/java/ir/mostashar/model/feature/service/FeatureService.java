package ir.mostashar.model.feature.service;

import ir.mostashar.model.feature.Feature;
import ir.mostashar.model.feature.dto.FeatureForm;
import ir.mostashar.model.feature.repository.FeatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeatureService {

    @Autowired
    FeatureRepo featureRepo;

    public boolean createFeature(FeatureForm featureForm) {
        Optional<Boolean> byName = featureRepo.existsByName(featureForm.getName());
        if (byName.isPresent())
            if (!byName.get()) {
                Feature feature = new Feature();
                feature.setUid(UUID.randomUUID());
                feature.setName(featureForm.getName());
                feature.setDescription(featureForm.getDescription());
                feature.setGroupKey(featureForm.getGroupKey());
                featureRepo.save(feature);
                return true;
            }
        return true;
    }

    public boolean updateFeature(FeatureForm featureForm) {
        Optional<Feature> feature = featureRepo.findByUid(UUID.fromString(featureForm.getUid()));
        if (feature.isPresent()) {
            feature.get().setName(featureForm.getName());
            feature.get().setDescription(featureForm.getDescription());
            feature.get().setGroupKey(featureForm.getGroupKey());
            featureRepo.save(feature.get());
            return true;
        }
        return true;
    }

    public boolean deleteFeature(String uid) {
        Optional<Feature> feature = featureRepo.findByUid(UUID.fromString(uid));
        if (feature.isPresent()) {
            featureRepo.delete(feature.get());
            return true;
        }
        return true;
    }

    public Optional<Feature> findFeaturebyUid(String uid) {
        Optional<Feature> feature = featureRepo.findByUid(UUID.fromString(uid));
        if (feature.isPresent())
            return Optional.ofNullable(feature.get());
        else
            return Optional.empty();
    }

    public Optional<List<Feature>> findAllFeature() {
        List<Feature> features = featureRepo.findAll();
        if (features != null)
            return Optional.ofNullable(features);
        else
            return Optional.empty();
    }
}
