package ir.mostashar.model.settingType.repository;

import ir.mostashar.model.settingType.SettingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SettingTypesRepo extends JpaRepository<SettingType, Long> {

    Optional<Boolean> existsByName(String name);

    Optional<SettingType> findByUid(UUID uuid);

    Optional<SettingType> findByName(String name);

    Optional<SettingType> findByType(int type);

}
