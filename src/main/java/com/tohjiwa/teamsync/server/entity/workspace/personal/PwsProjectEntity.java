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

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "personal_workspace_projects")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE personal_workspace_projects SET is_deleted = true, deleted_at = now(), status = 'INACTIVE', last_operation = 'DELETE' WHERE id = ?")
public class PwsProjectEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_projects_users"))
    private UserEntity user;

    @NotNull
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "personal_workspace_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_projects_pws"))
    private PwsEntity pws;

    @Length(message = "Project name min is 3 and max is 125", min = 3, max = 125)
    @NotBlank(message = "Project name can't be blank")
    @NotEmpty(message = "Project name can't be empty")
    @NotNull(message = "Project name can't be null")
    @Column(nullable = false, length = 125)
    private String name;

    @Column(length = 350)
    private String description;

    @Convert(converter = ListLongConverterJson.class)
    @Column(columnDefinition = "json")
    private List<Long> tagIds = new java.util.ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "last_operation", nullable = false)
    private LastOperation lastOperation = LastOperation.UNDEFINED;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false)
    private Source source;
}
