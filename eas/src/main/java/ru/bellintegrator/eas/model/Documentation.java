package ru.bellintegrator.eas.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Documentation")
public class Documentation {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToOne(mappedBy = "code", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Docs docCode;

    @Basic(optional = false)
    @Column(name = "doc_number")
    private int docNumber;

    @Basic(optional = false)
    @Column(name = "doc_date")
    private Date docDate;

    @OneToOne(mappedBy = "code", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Countries countryCode;

    @Basic(optional = false)
    @Column(name = "is_identified")
    private boolean isIdentified;

    @Version
    private int version;

    public Documentation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Docs getDocCode() {
        return docCode;
    }

    public void setDocCode(Docs docCode) {
        this.docCode = docCode;
    }

    public int getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(int docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Countries getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Countries countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Documentation)) return false;
        Documentation that = (Documentation) o;
        return getDocNumber() == that.getDocNumber() &&
                isIdentified() == that.isIdentified() &&
                version == that.version &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getDocCode(), that.getDocCode()) &&
                Objects.equals(getDocDate(), that.getDocDate()) &&
                Objects.equals(getCountryCode(), that.getCountryCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getDocCode(), getDocNumber(),
                getDocDate(), getCountryCode(), isIdentified(), version);
    }
}
