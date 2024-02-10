package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsUserSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface PwsUserSettingRepository extends JpaRepository<PwsUserSettingEntity, Long> {
    Optional<PwsUserSettingEntity> findByUser_IdAndPws_Id(@NonNull Long id, @NonNull Long id1);
}