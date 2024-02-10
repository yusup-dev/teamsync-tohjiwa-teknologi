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
@Table(name = "plan_features")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE plan_features SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class PlanFeatureEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 64)
    private String featureCode;

    @NotNull
    @Column(nullable = false, length = 128)
    private String name;

    @NotNull
    @Column(nullable = false, length = 384)
    private String description;

    @Column(length = 512)
    private String externalLink;

    @NotNull
    @Convert(converter = ListStringConverterJson.class)
    @Column(columnDefinition = "json")
    private List<String> requirementFeatureCodes = new ArrayList<>();

    @NotNull
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "currency_id", nullable = false, foreignKey = @ForeignKey(name = "fk_plan_features_currencies"))
    private CurrencyEntity currencyEntity;

    @Column(name = "price", nullable = false)
    private float price;
}