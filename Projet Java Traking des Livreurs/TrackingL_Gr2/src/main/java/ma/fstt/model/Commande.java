package ma.fstt.model;


import java.sql.Date;

// java bean
public class Commande {
    private Long id_commande ;

    private String nom_produit ;

    private String nom_livreur ;

    private String datedebut ;

    private String datefin ;


    public Commande(Long id_commande, String nom_produit, String nom_livreur , String datedebut , String datefin) {
        this.id_commande = id_commande;
        this.nom_produit = nom_produit;
        this.nom_livreur = nom_livreur;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public Commande() {

    }

    public Long getId_commande() {
        return id_commande;
    }

    public void setId_commande(Long id_livreur) {
        this.id_commande = id_commande;
    }

    public String getNom_produit() {
        return nom_produit;
    }
    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getNom_livreur() {
        return nom_livreur;
    }

    public void setNom_livreur( String nom_livreur) {
        this.nom_livreur = nom_livreur;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut( String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin( String datefin) {
        this.datefin = datefin;
    }




    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", nom_produit='" + nom_produit + '\'' +
                ", nom_livreur ='" + nom_livreur + '\'' +
                ", datedebut ='" + datedebut + '\'' +
                ", datefin ='" + datefin + '\'' +
                '}';
    }
}

