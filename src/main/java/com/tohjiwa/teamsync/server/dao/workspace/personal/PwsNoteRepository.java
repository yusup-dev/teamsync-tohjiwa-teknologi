package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PwsNoteRepository extends JpaRepository<PwsNoteEntity, Long> {
}