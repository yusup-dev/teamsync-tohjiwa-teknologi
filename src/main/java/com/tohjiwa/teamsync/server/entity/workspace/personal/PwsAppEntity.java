package com.tohjiwa.teamsync.server.entity.workspace.personal;

import com.tohjiwa.teamsync.server.converter.PwsAppConverterJson;
import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import com.tohjiwa.teamsync.server.model.App;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "personal_workspace_apps")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE personal_workspace_apps SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class PwsAppEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_workspace_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_apps_pws"))
    private PwsEntity pws;

    @NotNull
    @Convert(converter = PwsAppConverterJson.class)
    @Column(columnDefinition = "json")
    private List<App> apps = new ArrayList<>();
}
