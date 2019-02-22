package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Classe permettant d'insérer des données dans l'application.
 */
@Service
@Transactional
public class InitData implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private EntityManager em;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertTestData();
    }

    private void insertTestData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Client client1 = newClient("PETRILLO", "Alexandre", LocalDate.parse("22/02/1983", formatter));
        em.persist(client1);

        Client client2 = newClient("Dupont", "Jérome", LocalDate.parse("24/12/1976", formatter));
        em.persist(client2);

        Client client3 = newClient("Leveque", "Martin", LocalDate.parse("27/06/1997", formatter));
        em.persist(client3);

        Article a1 = new Article();
        a1.setLibelle("Balayette");
        a1.setPrix(3.99);
        em.persist(a1);

        Article a2 = new Article();
        a2.setLibelle("Stylo espion");
        a2.setPrix(130);
        em.persist(a2);

        Facture f1 = new Facture();
        f1.setClient(client1);
        em.persist(f1);

        Facture f2 = new Facture();
        f2.setClient(client2);
        em.persist(f2);

        em.persist(newLigneFacture(a1, 2, f1));
        em.persist(newLigneFacture(a2, 1, f1));
        em.persist(newLigneFacture(a1, 10,f2));
    }

    private LigneFacture newLigneFacture (Article a1, int quantite, Facture f1){
        LigneFacture lf1 = new LigneFacture();
        lf1.setArticle(a1);
        lf1.setQuantite(quantite);
        lf1.setFacture(f1);

        return lf1;
    }

    private Client newClient(String nom, String prenom, LocalDate date) {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setDateNaissance(date);
        return client;
    }
}
