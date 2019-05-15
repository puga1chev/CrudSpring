package ru.puga1chev.crudspring.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

// TODO Analyze whole proj
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rolename", unique = true, updatable = true)
    private String rolename;

/*    @ManyToMany(mappedBy = "roles")
    private List<User> users;*/

    public Role(Long id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }
    public Role() { }

    public Long getId() {
        return id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getRolename();
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + getRolename();
    }
}
