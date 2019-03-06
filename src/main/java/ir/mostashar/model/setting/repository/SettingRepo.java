package ir.mostashar.model.setting.repository;

import ir.mostashar.model.setting.Setting;
import ir.mostashar.model.settingType.SettingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import sun.plugin.util.UIUtil;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SettingRepo extends JpaRepository<Setting, Long> {


    Optional<Boolean> existsBySettingType(SettingType settingType);
    Optional<Setting> findByUid(UUID uuid);
}
