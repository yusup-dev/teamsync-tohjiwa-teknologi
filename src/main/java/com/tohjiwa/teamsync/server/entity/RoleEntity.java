package com.tohjiwa.teamsync.server.entity;

import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "roles")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE roles SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class RoleEntity extends DataBaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Byte id;

    @NotNull
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull
    @Column(nullable = false, length = 50)
    private String role;

    @Column(length = 350)
    private String description;
}
