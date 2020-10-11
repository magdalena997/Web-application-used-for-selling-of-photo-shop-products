/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author korisnik
 */
@Entity
public class Narudzbina {
     @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "sifra")
    private int sifra;
    
    @Column(name = "korisnik")
    private String korisnik;
    
    @Column(name = "suma")
    private float suma;
    
    @Column(name = "artikal")
    private String artikal;
    
     @Column(name = "slika")
    private String slika;
    
    @Column(name = "natpis")
    private String natpis;
    
    @Column(name = "datum")
    private Date datum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public float getSuma() {
        return suma;
    }

    public void setSuma(float suma) {
        this.suma = suma;
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

    public String getNatpis() {
        return natpis;
    }

    public void setNatpis(String natpis) {
        this.natpis = natpis;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
    

    
}
