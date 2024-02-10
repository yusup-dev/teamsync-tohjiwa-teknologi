package com.tohjiwa.teamsync.server.dao.workspace;

import com.tohjiwa.teamsync.server.entity.workspace.WorkspaceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceTypeRepository extends JpaRepository<WorkspaceTypeEntity, Byte> {
}