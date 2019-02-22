package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "facture")
    private Set<LigneFacture> lignes;

    public double getTotal(){
        double prixTotal = 0;
        for(LigneFacture ligne : lignes){
            prixTotal += ligne.getArticle().getPrix() * ligne.getQuantite();
        }
        return prixTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<LigneFacture> getLigne() {
        return lignes;
    }

    public void setLigne(Set<LigneFacture> lignes) {
        this.lignes = lignes;
    }
}
