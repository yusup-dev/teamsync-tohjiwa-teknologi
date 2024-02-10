package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PwsUserRepository extends JpaRepository<PwsUserEntity, Long> {
    List<PwsUserEntity> findByUser_Id(Long id);

}
