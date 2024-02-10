package com.tohjiwa.teamsync.server.entity;

import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "url_categories")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE url_categories SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class UrlCategoryEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 128)
    private String name;

    @Column(length = 512)
    private String description;

    @Column(length = 256)
    private String externalLink;
}