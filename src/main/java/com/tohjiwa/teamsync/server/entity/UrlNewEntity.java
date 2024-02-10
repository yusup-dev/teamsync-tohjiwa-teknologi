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
@Table(name = "url_news")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE url_news SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class UrlNewEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 2048)
    private String name;

    @Column(length = 2048)
    private String domain;

    @Column(length = 4096)
    private String url;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_category_id", foreignKey = @ForeignKey(name = "fk_url_news_url_categories"))
    private UrlCategoryEntity urlCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "productivity_status", nullable = false)
    private ProductivityStatus productivityStatus;

    @Column(name = "is_publish", nullable = false)
    private boolean isPublish = false;

    @Column(name = "publish_item_id")
    private Long publishItemId;
}