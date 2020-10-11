/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import entiteti.Korisnik;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.Registration;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
//import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author korisnik
 */

@ManagedBean
@Named(value = "registracijaKontroler")
@SessionScoped

public class RegistrationController implements Serializable {
    public static String ime;
    private String prezime;
    private String email;
    private String username;
    private String password;
    private String pol;
    private String passwordAg;
    private String message;
    private String pitanje;
    private String odgovor;
    private String tip;
    private String prihvacen;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        RegistrationController.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getPasswordAg() {
        return passwordAg;
    }

    public void setPasswordAg(String passwordAg) {
        this.passwordAg = passwordAg;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPrihvacen() {
        return prihvacen;
    }

    public void setPrihvacen(String prihvacen) {
        this.prihvacen = prihvacen;
    }
    
        public String registrujse(){
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
       cr.add(Restrictions.eq("username", username));
      
       if(cr.list().size() > 0) {
           FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Korisnik sa ovim korisnickim imenom vec postoji."));
           message = "Korisnik sa ovim korisnickim imenom vec postoji.";
           return "registracija";
          // return "index";
       } else {
           if(!password.equals(passwordAg)){
               FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinke se ne poklapaju."));
               message = "Lozinke se ne poklapaju.";
               return "registracija";
               //return "index";
           }
           else {
               
           boolean ok = false;
               int passLen = password.length();
                 if(passLen >= 8 && passLen <= 12){
                    if(password.equals(password.toLowerCase())){
                      //  registerMessage += "Lozinka mora da sadrži barem 1 veliko slovo.\n";
                      ok = true;
                         FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 1 veliko slovo"));
                    return "registracija";
                    }
                    if(!password.matches("^[A-Za-z].*$")){
                      //  registerMessage += "Lozinka mora počinjati sa slovom.\n";
                       ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora početi slovom."));
                      
                    return "registracija";
                      
                    }
                    int numOfLower = 0;
                    for (int i = 0; i < password.length(); i++) if (Character.isLowerCase(password.charAt(i))) numOfLower++;                    
                    if(numOfLower < 3){
                      //  registerMessage += "Lozinka mora da sadrži barem 3 malih slova.\n";
                      ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 3 malih slova."));
                      
                    return "registracija";
           
                    }
                    if(!password.matches(".*[.,/!?|/()].*$")){
                       // registerMessage += "Lozinka mora da sadrži barem 1 broj.\n";
                        ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 1 specijalni znak."));
                      
                    return "registracija";
                  
                    }
                    if(!password.matches(".*[0-9].*$")){
                       // registerMessage += "Lozinka mora da sadrži barem 1 specijalni znak.\n";
                       //[A-Za-z0-9 ]*
                      ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " Lozinka mora da sadrži barem 1 broj."));
                      
                    return "registracija";
                    }
                    
                } else {
                    //registerMessage += "Dužina lozinke mora biti između 6 i 12 karaktera.\n";
                     ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Dužina lozinke mora biti između 8 i 12 karaktera."));
                      
                    return "registracija";
                 }
                 
                 
                 //=?facet-redirect=true
                 if(!ok ) {
                   
                 
                  
            // String path = "C:\\Users\\korisnik\\Documents\\NetBeansProjects\\Projekat\\web\\resources\\images\\" + Slika.getFileName();
          /*     try {
                   Slika.write(path);
               } catch (Exception ex) {
                   java.util.logging.Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
               }
                */
                  String pass = kriptovanjePass(password);
               Korisnik k = new Korisnik();
               
               k.setIme(ime);
               k.setEmail(email);
               k.setPassword(pass);
               k.setPrezime(prezime);
               k.setPol(pol);
               k.setUsername(username);
               k.setPitanje(pitanje);
               k.setOdgovor(odgovor);

               k.setTip(tip);
               if (!tip.equals("K"))
                k.setPrihvacen("Z");
               else 
                   k.setPrihvacen("P");
               session.save(k); 
       
               session.getTransaction().commit(); 
               session.close();
               
               setIme(null);
               setEmail(null);
               setPassword(null);
               setPrezime(null);
               setPol(null);
               setUsername(null);
               setPitanje(null);
               setOdgovor(null);

               setTip(null);
                         
               
               message = "Korisnik je registrovan";
               
              // FacesContext.getCurrentInstance().
                 //       addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Korisnik je registrovan"));
               
               //return "index"  //return "login"
                   try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/prijava.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } }
           }
       }
        return null;
        
    }
        
            public String kriptovanjePass(String password){
         String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
            
            return generatedPassword;
    }
        
}
