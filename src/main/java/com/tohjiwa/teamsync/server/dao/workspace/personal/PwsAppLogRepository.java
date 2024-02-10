package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsAppLogEntity;
import com.tohjiwa.teamsync.server.model.PwsAppWorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PwsAppLogRepository extends JpaRepository<PwsAppLogEntity, Long> {

    @Query("SELECT new com.tohjiwa.teamsync.server.model.PwsAppWorkingTime(" +
            "m.pws.id, m.user.id, m.name, m.process, m.productivityStatus, sum(m.sessionTotalTime) as sessionTotalTime) " +
            "FROM PwsAppLogEntity m " +
            "where m.user.id = ?1 and m.pws.id = ?2 and m.status = ?3 and m.sessionStart >= ?4 and m.sessionEnd <= ?5 " +
            "group by m.pws.id, m.user.id, m.name, m.process, m.productivityStatus " +
            "order by sessionTotalTime desc")
    List<PwsAppWorkingTime> sum(Long userId, Long pwsId, Status status, Date after, Date before);
}
