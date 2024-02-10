package com.tohjiwa.teamsync.server.entity;

import com.tohjiwa.teamsync.server.entity.base.DataBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "user_details")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE user_details SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class UserDetailEntity extends DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_details_users"))
    private UserEntity user;

    @Column(length = 1024)
    private String skills;

    @Column(length = 128)
    private String company;

    @Column(length = 45)
    private String designation;

    @Column(length = 1024)
    private String url;

    @Column(length = 512)
    private String country;

    @Column(length = 256)
    private String city;

    @Column(length = 45)
    private String zipCode;

    @Column(length = 1024)
    private String bio;

    @Column(length = 1024)
    private String socialUrlLinkedin;

    @Column(length = 1024)
    private String socialUrlGithub;

    @Column(length = 1024)
    private String socialUrlPinterest;

    @Column(length = 1024)
    private String socialUrlDribble;

    @Column(length = 1024)
    private String socialUrlTwitter;

    @Column(length = 1024)
    private String socialUrlFacebook;

    @Column(length = 1024)
    private String socialUrlInstagram;
}
