package com.vectree.billing.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * User of the system.
 *
 * @author Egor Voronyansky
 * @version 0.1.
 */
@Entity
@Table(name = "users")
@Data
public class User {

    /**
     * Unique per user number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billingSequence")
    @SequenceGenerator(sequenceName = "billing_sequence", allocationSize = 1, name = "billingSequence")
    private Long id;

    /**
     * Name of the user.
     */
    @Column(name = "username")
    private String username;

    /**
     * Email of the user.
     */
    @Column(name = "email")
    private String email;

    /**
     * Password of the user, must be crypt before adding to the database.
     */
    @Column(name = "password")
    private String password;

    /**
     * Password confirmation of the user.
     */
    @Transient
    private String confirmPassword;

    /**
     * Wallet of the user.
     */
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", unique = true, nullable = false)
    private Account account;

    /**
     * Roles of the user.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
        this.id = 0L;
        this.username = "";
        this.email = "";
        this.password = "";
        this.roles = new HashSet<Role>();
        this.account = new Account();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        User user = (User) obj;
        return id.equals(user.getId())
                && username.equals(user.getUsername())
                && email.equals(user.getEmail())
                && password.equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return id.intValue() + username.hashCode() + email.hashCode() + password.hashCode();
    }

    @Override
    public String toString() {
        String resultRoles = "";
        for (Role role : roles) {
            resultRoles += role + " ";
        }
        if (resultRoles.length() > 1) {
            resultRoles = resultRoles.substring(0, resultRoles.length() - 1);
        }

        String result = String.format("User {id=%d username=\"%s\" email=\"%s\" password=\"%s\" confirmPassword=\"%s\"" +
                " Account {id=%d} roles {%s}}", id, username, email, password, confirmPassword, account.getId(), resultRoles);
        return result;
    }
}
