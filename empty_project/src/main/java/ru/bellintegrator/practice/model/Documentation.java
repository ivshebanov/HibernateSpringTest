package ru.bellintegrator.practice.model;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name = "Documentation")
public class Documentation {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private int userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_code")
    private int docCode;

    @Basic(optional = false)
    @Column(name = "doc_number")
    private int docNumber;

    @Basic(optional = false)
    @JoinColumn(name = "doc_date")
    private Date docDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_code")
    private int citizenshipCode;

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

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDocCode() {
        return docCode;
    }

    public void setDocCode(int docCode) {
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

    public int getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(int citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
