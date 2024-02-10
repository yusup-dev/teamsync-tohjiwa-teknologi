package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PwsActivityRepository extends JpaRepository<PwsActivityEntity, Byte> {
}