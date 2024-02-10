package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsUserUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface PwsUserUrlRepository extends JpaRepository<PwsUserUrlEntity, Long> {
    Optional<PwsUserUrlEntity> findByUser_IdAndPws_Id(@NonNull Long userId, @NonNull Long pwsId);
}
