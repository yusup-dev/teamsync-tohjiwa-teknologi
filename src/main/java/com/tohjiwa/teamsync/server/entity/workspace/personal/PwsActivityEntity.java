package com.tohjiwa.teamsync.server.entity.workspace.personal;

import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "personal_workspace_activities")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE personal_workspace_activities SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class PwsActivityEntity extends DataBaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Byte id;

    @NotNull
    @Column(name = "activity", nullable = false, length = 125)
    private String activity;

    @NotNull
    @Column(name = "name", nullable = false, length = 125)
    private String name;

    @Column(name = "description", length = 350)
    private String description;
}