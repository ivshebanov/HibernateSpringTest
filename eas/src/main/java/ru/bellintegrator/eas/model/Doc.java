package ru.bellintegrator.eas.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;

@Entity
@Table(name = "Doc_type")
public class Doc {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Column(name = "code")
    private int code;

    @Version
    private int version;

    @Basic(optional = false)
    @Column(name = "doc_name")
    private String docName;

    @OneToOne(optional = false, mappedBy = "doc")
    private User user;

    public Doc() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doc)) return false;
        Doc doc = (Doc) o;
        return getCode() == doc.getCode() &&
                version == doc.version &&
                Objects.equals(getId(), doc.getId()) &&
                Objects.equals(getDocName(), doc.getDocName()) &&
                Objects.equals(getUser(), doc.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), version, getDocName(), getUser());
    }

    @Override
    public String toString() {
        return "Doc{" +
                "id=" + id +
                ", code=" + code +
                ", version=" + version +
                ", docName='" + docName + '\'' +
                ", user=" + user +
                '}';
    }
}
