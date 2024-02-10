package com.tohjiwa.teamsync.server.entity.workspace.personal;

import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import com.tohjiwa.teamsync.server.entity.workspace.WorkspaceTypeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "personal_workspaces")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE personal_workspaces SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class PwsEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Length(message = "Workspace name min is 2 and max is 128", min = 2, max = 128)
    @NotBlank(message = "Workspace name can't be blank")
    @NotEmpty(message = "Workspace name can't be empty")
    @NotNull(message = "Workspace name can't be null")
    @Column(nullable = false, length = 128)
    private String name;

    @Column(length = 384)
    private String description;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workspace_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_workspace_types"))
    private WorkspaceTypeEntity workspaceType;
}
