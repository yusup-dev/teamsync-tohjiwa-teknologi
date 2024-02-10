package com.tohjiwa.teamsync.server.entity.plan;

import com.tohjiwa.teamsync.server.converter.ListStringConverterJson;
import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "personal_workspace_plan_manages")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE personal_workspace_plan_manages SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class PwsPlanManageEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 640)
    private String name;

    @NotNull
    @Convert(converter = ListStringConverterJson.class)
    @Column(columnDefinition = "json")
    private List<String> featureCodes = new ArrayList<>();

    @NotNull
    @Column(nullable = false, length = 350)
    private String description;

    @Column(length = 512)
    private String externalLink;

    @NotNull
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "currency_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_plan_manages_currencies"))
    private CurrencyEntity currency;

    @Column(name = "price", nullable = false)
    private float price;
}