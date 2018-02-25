package ru.bellintegrator.practice.model;

import javax.persistence.*;

//@Entity
//@Table(name = "Docs")
public class Docs {

    @Id
    @GeneratedValue
    @Column(name = "code")
    @OneToOne(optional = false, mappedBy = "docCode")
    private Long code;

    @Basic(optional = false)
    @Column(name = "doc_name")
    private int docName;

    @Version
    private int version;

    public Docs() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public int getDocName() {
        return docName;
    }

    public void setDocName(int docName) {
        this.docName = docName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
