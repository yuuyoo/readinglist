package cn.technotes.readinglist;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Reader implements UserDetails {

    @Id
    private String username;
    private String fullname;
    private String password;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 不过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 不加锁
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 凭证不过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 不禁用
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("READER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
