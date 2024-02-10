package com.tohjiwa.teamsync.server.entity.plan;

import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import jakarta.persistence.*;
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
@Table(name = "currencies")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE currencies SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class CurrencyEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Byte id;

    @Column(name = "currency_code", nullable = false, unique = true, length = 50)
    private String currency_code;
}