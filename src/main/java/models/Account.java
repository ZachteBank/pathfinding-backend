package models;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

@MappedSuperclass
public class Account implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;
    private String passHash;

    public Account() {
    }

    public Account(String email, String passHash) {
        this.email = email;
        this.passHash = passHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String password) {
        // Hash a password for the first time
        this.passHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password) {
        // Check that an unencrypted password matches one that has
        // previously been hashed
        return (BCrypt.checkpw(password, this.passHash));
    }

    @Override
    public int getId() {
        return id;
    }
}
