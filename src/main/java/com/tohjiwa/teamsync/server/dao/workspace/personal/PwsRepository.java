package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PwsRepository extends JpaRepository<PwsEntity, Long> {
}
