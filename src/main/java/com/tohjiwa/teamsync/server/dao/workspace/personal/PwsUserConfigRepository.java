package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsUserConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PwsUserConfigRepository extends JpaRepository<PwsUserConfigEntity, Long> {
    @Query("select v from PwsUserConfigEntity v where v.user.id = ?1 and v.pws.id = ?2")
    List<PwsUserConfigEntity> findByUserIdAndWorkspacePersonalId(Long userId, Long workspacePersonalId);

    Optional<PwsUserConfigEntity> findByUser_IdAndPws_Id(Long userId, Long pwsId);

}
