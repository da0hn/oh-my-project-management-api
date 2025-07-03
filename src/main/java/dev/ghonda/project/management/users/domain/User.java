package dev.ghonda.project.management.users.domain;

import dev.ghonda.project.management.shared.exceptions.DomainFieldValidationException;
import dev.ghonda.project.management.shared.exceptions.DomainValidationException;
import dev.ghonda.project.management.shared.validators.annotations.UniqueBy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@UniqueBy.Fields({
    @UniqueBy(field = "username", message = "Username already exists"),
    @UniqueBy(field = "email", message = "Email already exists")
})
public class User extends Auditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 2441102039523994659L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(name = "full_name")
    private String fullName;

    @Email
    @NotBlank
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(this.id)
            .append(this.username)
            .append(this.email)
            .append(this.role)
            .toHashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final User user)) return false;

        return new EqualsBuilder()
            .append(this.id, user.id)
            .append(this.username, user.username)
            .append(this.email, user.email)
            .append(this.role, user.role)
            .isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public void changeUsername(final String username) {
        if (username == null || username.isBlank()) {
            DomainFieldValidationException.builder()
                .message("Username cannot be null or blank")
                .field("username")
                .rejectedValue(username)
                .throwException();
        }
        this.username = username;
        this.setUpdatedAt(LocalDateTime.now());
    }

    public void changeFullName(final String fullName) {
        if (fullName == null || fullName.isBlank()) {
            DomainFieldValidationException.builder()
                .message("Full name cannot be null or blank")
                .field("fullName")
                .rejectedValue(fullName)
                .throwException();
        }
        this.fullName = fullName;
        this.setUpdatedAt(LocalDateTime.now());
    }

    public void changeEmail(final String email) {
        if (email == null || email.isBlank()) {
            DomainFieldValidationException.builder()
                .message("Email cannot be null or blank")
                .field("email")
                .rejectedValue(email)
                .throwException();
        }
        this.email = email;
        this.setUpdatedAt(LocalDateTime.now());
    }

    public void changeRole(final Role role) {
        if (role == null) {
            DomainFieldValidationException.builder()
                .message("Role cannot be null")
                .field("role")
                .throwException();
        }
        this.role = role;
        this.setUpdatedAt(LocalDateTime.now());
    }

}
