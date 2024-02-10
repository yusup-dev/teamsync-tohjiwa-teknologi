package com.tohjiwa.teamsync.server.entity.workspace.personal;

import com.tohjiwa.teamsync.server.converter.PwsUserConfigConverterJson;
import com.tohjiwa.teamsync.server.entity.UserEntity;
import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsUserConfig;
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
@Table(name = "personal_workspace_user_configs")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE personal_workspace_user_configs SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class PwsUserConfigEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_user_configs_users"))
    private UserEntity user;

    @NotNull
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "personal_workspace_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_user_configs_pws"))
    private PwsEntity pws;

    @NotNull
    @Convert(converter = PwsUserConfigConverterJson.class)
    @Column(nullable = false, columnDefinition = "json")
    private PwsUserConfig userConfiguration;
}
