package com.example.mini_project.entity;

import com.example.mini_project.util.domain.Address;
import com.example.mini_project.util.user.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한 생성자 방지
@AllArgsConstructor
@Builder
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;
    private String userId;
    private String password;
    private String phoneNumber;
    private String email;

    @Embedded // @Embedded : 값 타입을 사용하는곳에 표시
    private Address address;

    // @Enumerated : default 값은 ORDINAL 이며, 순서의 숫자를 저장합니다, String 즉 문자열 그대로 저장합니다.
    @Enumerated(EnumType.STRING)
    private Role role;

    // cascade : 연관된 Entity에도 상태 변화를 전이 시킨다.
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<SalesItemEntity> itemList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
