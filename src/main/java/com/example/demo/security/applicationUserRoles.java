package com.example.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.applicationUserPermissions.*;

public enum applicationUserRoles {
    admin(Sets.newHashSet(course_read,course_write,student_read,student_write)),
    student(Sets.newHashSet()),
    adminTraine(Sets.newHashSet(course_read,student_read));

    private final Set<applicationUserPermissions> permissions;

    applicationUserRoles(Set<applicationUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<applicationUserPermissions> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permission = getPermissions().stream().map(
                permissions -> new SimpleGrantedAuthority(permissions.getPermissions()))
                .collect(Collectors.toSet());
        permission.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permission;
    }
}
