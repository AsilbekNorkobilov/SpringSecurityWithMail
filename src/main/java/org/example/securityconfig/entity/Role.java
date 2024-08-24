package org.example.securityconfig.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.securityconfig.entity.enums.RoleName;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return roleName.name();
    }
}