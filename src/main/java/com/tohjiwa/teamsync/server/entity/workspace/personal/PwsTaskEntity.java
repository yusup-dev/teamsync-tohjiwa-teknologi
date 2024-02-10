package com.tohjiwa.teamsync.server.entity.workspace.personal;

import com.tohjiwa.teamsync.server.constant.LastOperation;
import com.tohjiwa.teamsync.server.constant.Source;
import com.tohjiwa.teamsync.server.converter.ListLongConverterJson;
import com.tohjiwa.teamsync.server.entity.UserEntity;
import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "personal_workspace_tasks")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE personal_workspace_tasks SET is_deleted = true, deleted_at = now(), status = 'INACTIVE', last_operation = 'DELETE' WHERE id = ?")
public class PwsTaskEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_tasks_users"))
    private UserEntity user;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_workspace_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_tasks_pws"))
    private PwsEntity pws;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_workspace_project_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_tasks_projects"))
    private PwsProjectEntity pwsProject;

    @Length(message = "Task name min is 3 and max is 125", min = 3, max = 125)
    @NotBlank(message = "Task name can't be blank")
    @NotEmpty(message = "Task name can't be empty")
    @NotNull(message = "Task name can't be null")
    @Column(nullable = false, length = 125)
    private String name;

    @Column(length = 350)
    private String description;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_user_id", foreignKey = @ForeignKey(name = "fk_pws_tasks_reporter_users"))
    private UserEntity reporterUser;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_user_id", foreignKey = @ForeignKey(name = "fk_pws_tasks_assignee_users"))
    private UserEntity assigneeUser;

    @Column(name = "deadline")
    private Date deadline;

    @Convert(converter = ListLongConverterJson.class)
    @Column(columnDefinition = "json")
    private List<Long> tagIds = new ArrayList<>();

    @Column(name = "is_flagged", nullable = false)
    private boolean isFlagged = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "last_operation", nullable = false)
    private LastOperation lastOperation = LastOperation.UNDEFINED;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false)
    private Source source;
}
