package ru.bellintegrator.practice.model;

import javax.persistence.*;

@Entity
@Table(name = "Docs")
public class Docs {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Column(name = "code")
    private int code;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDocName() {
        return docName;
    }

    public void setDocName(int docName) {
        this.docName = docName;
    }
}
