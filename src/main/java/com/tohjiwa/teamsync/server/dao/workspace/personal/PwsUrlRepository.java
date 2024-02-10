package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface PwsUrlRepository extends JpaRepository<PwsUrlEntity, Long> {
    Optional<PwsUrlEntity> findByPws_Id(@NonNull Long id);
}
