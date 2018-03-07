package ru.bellintegrator.eas.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;

@Entity
@Table(name = "Docs")
public class Docs {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
    @MapsId
    private Documentation code;

    @Basic(optional = false)
    @Column(name = "doc_name")
    private int docName;

    @Version
    private int version;

    public Docs() {
    }

    public Long getId() {
        return id;
    }

    public Documentation getCode() {
        return code;
    }

    public void setCode(Documentation code) {
        this.code = code;
    }

    public int getDocName() {
        return docName;
    }

    public void setDocName(int docName) {
        this.docName = docName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Docs)) return false;
        Docs docs = (Docs) o;
        return getDocName() == docs.getDocName() &&
                version == docs.version &&
                Objects.equals(getId(), docs.getId()) &&
                Objects.equals(getCode(), docs.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getDocName(), version);
    }
}
