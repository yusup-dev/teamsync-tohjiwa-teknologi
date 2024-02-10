package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface PwsAppRepository extends JpaRepository<PwsAppEntity, Long> {
    Optional<PwsAppEntity> findByPws_Id(@NonNull Long id);
}
