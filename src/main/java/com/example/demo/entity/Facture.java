package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //????
    private Set<LigneFacture> ligne;

    public double getTotal(){
        //TODO
        return 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<LigneFacture> getLigne() {
        return ligne;
    }

    public void setLigne(Set<LigneFacture> ligne) {
        this.ligne = ligne;
    }
}
