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
public class Slika {
    @Id
    @Column(name = "slika")
    private String slika;
    
    
    @Column(name = "naziv")
    private String naziv;
    
    @Column(name = "umetnik")
    private String umetnik;
    

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getUmetnik() {
        return umetnik;
    }

    public void setUmetnik(String umetnik) {
        this.umetnik = umetnik;
    }

   
    
}
