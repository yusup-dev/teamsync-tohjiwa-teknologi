package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsUserAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface PwsUserAppRepository extends JpaRepository<PwsUserAppEntity, Long> {
    Optional<PwsUserAppEntity> findByUser_IdAndPws_Id(@NonNull Long userId, @NonNull Long pwsId);
}