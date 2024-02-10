package com.tohjiwa.teamsync.server.entity.workspace.personal;

import com.tohjiwa.teamsync.server.entity.UserEntity;
import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "personal_workspace_time_Logs")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE personal_workspace_time_Logs SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class PwsTimeLogEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_time_logs_users"))
    private UserEntity user;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_workspace_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_time_logs_pws"))
    private PwsEntity pws;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_workspace_project_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_time_logs_pws_projects"))
    private PwsProjectEntity pwsProject;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_workspace_task_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_time_logs_pws_tasks"))
    private PwsTaskEntity pwsTask;

    @NotNull
    @Column(nullable = false)
    private Date sessionStart;

    @NotNull
    @Column(nullable = false)
    private Date sessionEnd;

    @PositiveOrZero
    @NotNull
    @Column(nullable = false)
    private Long sessionTotalTime;
}
