/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entiteti.Korisnik;
import java.io.IOException;
import java.io.Serializable;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author korisnik
 */
@ManagedBean
@RequestScoped
public class LoginController  implements Serializable{
    public static String username;
    private String password;
    private String tip;
    
    public static boolean prijavljen = false;
    public static boolean odjavljen = true;
    
    public static boolean admin = false;
    public static boolean umetnik = false;
    
    public static Korisnik kor1;
    
    public static boolean poruka1 = true;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isUmetnik() {
        return umetnik;
    }

    public void setUmetnik(boolean umetnik) {
        this.umetnik = umetnik;
    }
    
    

    public Korisnik getKor1() {
        return kor1;
    }

    public void setKor1(Korisnik kor1) {
        LoginController.kor1 = kor1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isPrijavljen() {
        return prijavljen;
    }

    public void setPrijavljen(boolean prijavljen) {
        LoginController.prijavljen = prijavljen;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOdjavljen() {
        return odjavljen;
    }

    public void setOdjavljen(boolean odjavljen) {
        LoginController.odjavljen = odjavljen;
    }

    public static boolean isPoruka1() {
        return poruka1;
    }

  

    public static void setPoruka1(boolean poruka1) {
        LoginController.poruka1 = poruka1;
    }
    
    
      public String login(){
          
              
          
        poruka1 = true;
    //    LozinkaController.pork1 = false;
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
        
        String pass = kriptovanjePass(password);
        
        
        Korisnik k = (Korisnik) cr.add(Restrictions.eq("username", username)).add(Restrictions.eq("password", pass)).uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        
       
        if(k != null){
            if(k.getPrihvacen().equals("P")) {
            if(k.getTip().equals(tip)){
               
            HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            sesija.setAttribute("user", k);
            poruka1 = false;
            if(k.getTip().equals("K")){
               // HttpSession sesija1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            //sesija1.setAttribute("takmicar", k);
            kor1 = k;
            prijavljen = true;
            odjavljen = false;
            admin = false;
            umetnik = false;
            try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/prodavnica.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
            }
            else {
                if(k.getTip().equals("A")){
                    kor1 = k;
                    prijavljen = true;
                    odjavljen = false;
                    admin = true;
                    umetnik = false;
                    try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/admin.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
                }
                else {
                    kor1 = k;
                    prijavljen = true;
                    odjavljen = false;
                    admin = false;
                    umetnik = true;
                    SupervizorController.pregledslika();
                    try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/umetnik.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
                }
            }
            }
            else {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Tip koji ste uneli je pogresan!"));
                return null;
            } } else {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ovaj korisnik nije prihvacen od strane admina!"));
                return null;
            }
        } else {
        
        SessionFactory SessionFactory1 = DB.HibernateUtil.getSessionFactory();
        Session session1 = SessionFactory.openSession();
        session1.beginTransaction();
        
        Criteria cr1 = session1.createCriteria(Korisnik.class);
         Korisnik k1 = (Korisnik) cr1.add(Restrictions.eq("username", username)).uniqueResult();
          session1.getTransaction().commit();
        session1.close();
        if(k1 != null && !k1.getPassword().equals(password)) {
            FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka koju ste uneli je pogresna!"));
            
        }
        else {
            FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Korisnicko ime ne postoji!"));
            
        }
        
          }
        return null; 
    }
      
     public void logout1(){
        prijavljen = false;
        odjavljen = true;
        admin = false;
        umetnik = false;
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        s.invalidate();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     
     public String logout(){
         HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        //    sesija.removeValue("user");
            sesija.invalidate();
          return "index";
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
     
         public void zaboravljenaLozinka1(){
        LozinkaController.username = "";
        LozinkaController.odgovor = "";
        LozinkaController.zabLozinka = ""; 
        try {
                
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/lozinkaZab.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
     }
     
         public void promenaLozinke(){
        LozinkaController.username = "";
        LozinkaController.password = "";
        LozinkaController.noviPass = "";
        LozinkaController.ponoviPass = "";
           try {
                
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/lozinka.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
         
         public void registracija(){
        RegistrationController.ime = "";
            try {
                
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/registracija.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
