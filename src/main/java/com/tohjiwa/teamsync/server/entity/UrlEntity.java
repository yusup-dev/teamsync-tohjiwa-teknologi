package com.tohjiwa.teamsync.server.entity;

import com.tohjiwa.teamsync.server.constant.ProductivityStatus;
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
@Table(name = "urls")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE urls SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class UrlEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 2048)
    private String name;

    @NotNull
    @Column(nullable = false, length = 2048)
    private String domain;

    @NotNull
    @Column(nullable = false, length = 4096)
    private String url;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "url_category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_urls_url_categories"))
    private UrlCategoryEntity urlCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "productivity_status", nullable = false)
    private ProductivityStatus productivityStatus;
}