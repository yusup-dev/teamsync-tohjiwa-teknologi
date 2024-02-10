package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface PwsSettingRepository extends JpaRepository<PwsSettingEntity, Long> {
    Optional<PwsSettingEntity> findByPws_Id(@NonNull Long id);
}
