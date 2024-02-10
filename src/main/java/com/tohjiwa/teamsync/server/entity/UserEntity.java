package com.tohjiwa.teamsync.server.entity;

import com.tohjiwa.teamsync.server.entity.base.UserBaseEntity;
import com.tohjiwa.teamsync.server.model.validator.ManualValidationGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "users")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE users SET is_deleted = true, deleted_at = now(), status = 'INACTIVE' WHERE id = ?")
public class UserEntity extends UserBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, length = 45)
    private String username;

    @Email(message = "Please input valid email")
    @Column(nullable = false, unique = true, length = 125)
    private String email;

    @Column(length = 8)
    private String phoneNumberCountryCode;

    @Column(length = 25)
    private String phoneNumber;

    @Length(message = "Password min 8 character and max 32 character", min = 8, max = 32, groups = ManualValidationGroup.class)
    @NotBlank(message = "Password can't be blank")
    @NotEmpty(message = "Password can't be empty")
    @NotNull(message = "Password can't be null")
    @Column(nullable = false, length = 1024)
    private String password;

    @Length(message = "First name min is 2 and max is 45", min = 2, max = 45)
    @NotBlank(message = "First name min is 2 and max is 45")
    @NotEmpty
    @NotNull
    @Column(nullable = false, length = 45)
    private String firstName;

    @Size(message = "Last name min is 1 and max is 45", min = 1, max = 45)
    @Column(length = 45)
    private String lastName;

    @Column(length = 1024)
    private String profilePictureUrl;
}
