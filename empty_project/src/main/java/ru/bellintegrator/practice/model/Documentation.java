package ru.bellintegrator.practice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Documentation")
public class Documentation {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

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

    public Long getId() {
        return id;
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
}
