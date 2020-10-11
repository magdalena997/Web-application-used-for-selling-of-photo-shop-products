/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

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
public class Dostava {
    @Id
    @Column(name = "sifra")
    private int sifra;
    
    @Column(name = "nacin")
    private String nacin;
    
    @Column(name = "ulica")
    private String ulica;
    
    @Column(name = "broj")
    private String broj;
    
    @Column(name = "grad")
    private String grad;
    
    @Column(name = "telefon")
    private int telefon;
    
    @Column(name = "cena")
    private float cena;

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getNacin() {
        return nacin;
    }

    public void setNacin(String nacin) {
        this.nacin = nacin;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }
    
    
}
