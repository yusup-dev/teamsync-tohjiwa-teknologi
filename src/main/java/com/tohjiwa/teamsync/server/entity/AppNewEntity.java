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
@Table(name = "app_news")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE app_news SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class AppNewEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 2048)
    private String name;

    @Column(length = 4096)
    private String process;

    @Column(length = 4096)
    private String term;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_category_id", foreignKey = @ForeignKey(name = "fk_app_news_app_categories"))
    private AppCategoryEntity appCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "productivity_status", nullable = false)
    private ProductivityStatus productivityStatus;

    @Column(name = "is_publish", nullable = false)
    private boolean isPublish = false;

    @Column(name = "publish_item_id")
    private Long publishItemId;
}