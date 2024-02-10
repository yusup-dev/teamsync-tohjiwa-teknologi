package com.tohjiwa.teamsync.server.entity.plan;

import com.tohjiwa.teamsync.server.constant.PlanSubscriptionStatus;
import com.tohjiwa.teamsync.server.converter.ListLongConverterJson;
import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsEntity;
import com.tohjiwa.teamsync.server.converter.ListStringConverterJson;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "personal_workspace_plan_subscriptions")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE personal_workspace_plan_subscriptions SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class PwsPlanSubscriptionEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 640)
    private String name;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_workspace_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pws_plan_subscriptions_pws"))
    private PwsEntity pws;

    @Column(name = "is_free_plan", nullable = false)
    private boolean isFreePlan;

    @NotNull
    @Convert(converter = ListStringConverterJson.class)
    @Column(columnDefinition = "json")
    private List<String> featureCodes = new ArrayList<>();

    @Min(message = "Minimal users in subscription is 1", value = 1)
    @Column(name = "number_of_users", nullable = false)
    private int numberOfUsers;

    @NotNull
    @Column(nullable = false, length = 350)
    private String description;

    @Column(length = 1024)
    private String note;

    @Convert(converter = ListLongConverterJson.class)
    @Column(columnDefinition = "json")
    private List<Long> tagIds = new ArrayList<>();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "active_from", nullable = false)
    private Date activeFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "active_until")
    private Date activeUntil;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_payment_method_id", foreignKey = @ForeignKey(name = "fk_pws_plan_subscriptions_user_payment_methods"))
    private UserPaymentMethodEntity userPaymentMethodEntity;

    @Column(name = "subscription_period", nullable = false)
    private int subscriptionPeriod;

    @Column(name = "auto_renew", nullable = false)
    private boolean autoRenew = false;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "plan_subscription_status", nullable = false)
    private PlanSubscriptionStatus planSubscriptionStatus;
}