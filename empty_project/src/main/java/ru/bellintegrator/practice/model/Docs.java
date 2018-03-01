package ru.bellintegrator.practice.model;

import javax.persistence.*;

@Entity
@Table(name = "Docs")
public class Docs {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
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
}
