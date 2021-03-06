package com.jason.module.security.dto;

import com.jason.module.security.entity.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDto extends User implements UserDetails {

    private List<UserAuthority> userAuthorityList;

    private UserGroup userGroup;

    private Long groupId;

    private String token;

    private Long roleId;

    private String npass;

    public void setUserAuthorityList(List<UserAuthority> userAuthorityList) {
        this.userAuthorityList = userAuthorityList;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userAuthorityList;
    }

    @Override
    public String getUsername() {
        return this.getUserName();
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
        return this.getEnable();
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getNpass() {
        return npass;
    }

    public void setNpass(String npass) {
        this.npass = npass;
    }
}
