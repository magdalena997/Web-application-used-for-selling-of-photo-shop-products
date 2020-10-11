/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author korisnik
 */
@Entity
public class Korpa implements Serializable {
    
    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "artikal")
    private String artikal;
    
    @Column(name = "slika")
    private String slika;
    
    @Column(name = "kupac")
    private String kupac;
    
    @Column(name = "stanje")
    private String stanje;
    
    @Column(name = "obradjeno")
    private String obradjeno;
    
    @Column(name = "cena")
    private float cena;
    
    @Column(name = "natpis")
    private String natpis;
    
    @Column(name = "boja")
    private String boja;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtikal() {
        return artikal;
    }

    public void setArtikal(String artikal) {
        this.artikal = artikal;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
        this.kupac = kupac;
    }

    public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

    public String getObradjeno() {
        return obradjeno;
    }

    public void setObradjeno(String obradjeno) {
        this.obradjeno = obradjeno;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public String getNatpis() {
        return natpis;
    }

    public void setNatpis(String natpis) {
        this.natpis = natpis;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    
    
}
