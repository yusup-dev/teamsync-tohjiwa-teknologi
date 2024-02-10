package com.tohjiwa.teamsync.server.entity.workspace.personal;

import com.tohjiwa.teamsync.server.converter.PwsSettingConverterJson;
import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsSetting;
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
@Table(name = "personal_workspace_settings")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE personal_workspace_settings SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class PwsSettingEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_workspace_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_settings_pws"))
    private PwsEntity pws;

    @NotNull
    @Convert(converter = PwsSettingConverterJson.class)
    @Column(columnDefinition = "json")
    private PwsSetting personalWorkspaceSetting;

    @Column(nullable = false)
    @NotNull
    private Integer personalWorkspaceSettingHashCode;
}
