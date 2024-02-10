package com.tohjiwa.teamsync.server.entity.plan;

import com.tohjiwa.teamsync.server.converter.MapStringObjectConverterJson;
import com.tohjiwa.teamsync.server.entity.UserEntity;
import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users_payment_methods")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE users_payment_methods SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class UserPaymentMethodEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_users_payment_methods_users"))
    private UserEntity user;

    @NotNull
    @Convert(converter = MapStringObjectConverterJson.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> paymentMethod;
}