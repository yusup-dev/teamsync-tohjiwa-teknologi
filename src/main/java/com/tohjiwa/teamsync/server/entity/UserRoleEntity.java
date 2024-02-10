package com.tohjiwa.teamsync.server.entity;

import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import jakarta.persistence.*;
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
@Table(name = "users_roles")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE users_roles SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class UserRoleEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_users_roles_users"))
    private UserEntity user;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "fk_users_roles_roles"))
    private RoleEntity role;
}
