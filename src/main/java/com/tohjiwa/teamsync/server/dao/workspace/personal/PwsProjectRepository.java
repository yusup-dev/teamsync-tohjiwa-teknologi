package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PwsProjectRepository extends JpaRepository<PwsProjectEntity, Long> {
    @Query(value = "SELECT * FROM personal_workspace_projects WHERE personal_workspace_id = :wpsId AND user_id = :userId", nativeQuery = true)
    List<PwsProjectEntity> findByUser_IdAndPws_Id_Sync(Long userId, Long wpsId);
    @Query(value = "SELECT * FROM personal_workspace_projects WHERE user_id = :userId AND personal_workspace_id = :wpsId AND last_modified_date > :lastModifiedDate", nativeQuery = true)
    List<PwsProjectEntity> findByUser_IdAndPws_IdAndLastModifiedDateGreaterThan_Sync(Long userId, Long wpsId, Date lastModifiedDate);
}
