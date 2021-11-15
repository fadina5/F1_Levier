package fr.dfl.f1_levier.database.entity;

public class TourValeur {

    private String nom;

    private float moyennneTours;

    private float pitStop;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getMoyennneTours() {
        return moyennneTours;
    }

    public void setMoyennneTours(float moyennneTours) {
        this.moyennneTours = moyennneTours;
    }

    public float getPitStop() {
        return pitStop;
    }

    public void setPitStop(float pitStop) {
        this.pitStop = pitStop;
    }
}
