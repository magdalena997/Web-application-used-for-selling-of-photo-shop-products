/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import entiteti.Korisnik;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author korisnik
 */

@ManagedBean
@Named(value = "lozinkaKontroler")
@SessionScoped

public class LozinkaController implements Serializable {
    public static String username;
    public static String password;
    public static String noviPass;
    public static String ponoviPass;
    private String poruka;
    public static boolean pork1 = true;
    
    private boolean pitanje1 = false;
    
    private boolean isValid = false;
    
   
    
   private String pitanje;
   
   
   public static String odgovor;

  public static  String zabLozinka;

    public boolean isPitanje1() {
        return pitanje1;
    }

    public void setPitanje1(boolean pitanje1) {
        this.pitanje1 = pitanje1;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public String getZabLozinka() {
        return zabLozinka;
    }

    public void setZabLozinka(String zabLozinka) {
        this.zabLozinka = zabLozinka;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public boolean isPork1() {
        return pork1;
    }

    public void setPork1(boolean pork1) {
        this.pork1 = pork1;
    }
    
    
    


    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
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

    public String getNoviPass() {
        return noviPass;
    }

    public void setNoviPass(String noviPass) {
        this.noviPass = noviPass;
    }

    public String getPonoviPass() {
        return ponoviPass;
    }

    public void setPonoviPass(String ponoviPass) {
        this.ponoviPass = ponoviPass;
    }
   
    
    public String promeniPass(){
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        Criteria cr1 = session.createCriteria(Korisnik.class);
        
        String pass1 = kriptovanjePass(password);
        
        cr.add(Restrictions.eq("username", username));
        if(cr.list().size() > 0){
            cr1.add(Restrictions.eq("username", username)).add(Restrictions.eq("password", pass1));
            if(cr1.list().size() > 0){
                if(noviPass.equals(ponoviPass)) {
                    Korisnik korisnik = (Korisnik) cr1.add(Restrictions.eq("username", username)).add(Restrictions.eq("password", pass1)).uniqueResult();
                    
                      boolean ok = false;
               int passLen = noviPass.length();
                 if(passLen >= 8 && passLen <= 12){
                    if(noviPass.equals(noviPass.toLowerCase())){
                      //  registerMessage += "Lozinka mora da sadrži barem 1 veliko slovo.\n";
                      ok = true;
                         FacesContext.getCurrentInstance().
                        addMessage("messages5", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 1 veliko slovo"));
                    return "lozinka";
                    }
                    if(!noviPass.matches("^[A-Za-z].*$")){
                      //  registerMessage += "Lozinka mora počinjati sa slovom.\n";
                       ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("messages5", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora počinjati sa slovom."));
                      
                    return "lozinka";
                      
                    }
                    int numOfLower = 0;
                    for (int i = 0; i < noviPass.length(); i++) if (Character.isLowerCase(noviPass.charAt(i))) numOfLower++;                    
                    if(numOfLower < 3){
                      //  registerMessage += "Lozinka mora da sadrži barem 3 malih slova.\n";
                      ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("messages5", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 3 malih slova."));
                      
                    return "lozinka";
           
                    }
                    if(!noviPass.matches(".*[.,/!?|/()].*$")){
                       // registerMessage += "Lozinka mora da sadrži barem 1 broj.\n";
                        ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("messages5", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 1 specijalni znak."));
                      
                    return "lozinka";
                  
                    }
                    if(!noviPass.matches(".*[0-9].*$")){
                       // registerMessage += "Lozinka mora da sadrži barem 1 specijalni znak.\n";
                       //[A-Za-z0-9 ]*
                      ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("messages5", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " Lozinka mora da sadrži barem 1 broj."));
                      
                    return "lozinka";
                    }
                    
                } else {
                    //registerMessage += "Dužina lozinke mora biti između 6 i 12 karaktera.\n";
                     ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("messages5", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Dužina lozinke mora biti između 8 i 12 karaktera."));
                      
                    return "lozinka";
                 }
                 
                    if(!ok){
                        
                        String pass = kriptovanjePass(noviPass);
                        
                    korisnik.setPassword(pass);
                    session.save(korisnik);
                    session.getTransaction().commit();
                    session.close();
                   
                    poruka = "Uspesno je promenjena lozinka"; }
                    try {
                 FacesContext.getCurrentInstance().addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Uspesno je promenjena lozinka!"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/prijava.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
                }  else {
                    FacesContext.getCurrentInstance().
                        addMessage("messages5", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinke se ne poklapaju."));
                    poruka = "Lozinke se ne poklapaju";
                    return "lozinka";
                }
            } else {
                FacesContext.getCurrentInstance().
                        addMessage("messages5", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka koju ste uneli je pogresna. Pokusajte ponovo."));
                poruka = "Lozinka koju ste uneli je pogresna. Pokusajte ponovo.";
                return "lozinka";
            }
        } else {
            FacesContext.getCurrentInstance().
                        addMessage("messages5", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Korisnicko ime koje ste uneli ne postoji."));
            poruka = "Korisnicko ime koje ste uneli ne postoji";
            return "lozinka";
        }
        return null;
    }
    
    public void show(){
       if(isValid)
           isValid = false;
       else 
           isValid = true;
    }
    
    public String zaboravljenaLozinka(){
        pitanje1 = false;
        pork1 = true;
        LoginController.poruka1 = false;
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        Criteria cr1 = session.createCriteria(Korisnik.class);
      //  if(username.equals(""))
 
        
        cr.add(Restrictions.eq("username", username));
        if(cr.list().size() > 0){
         Korisnik k1 = (Korisnik)  cr1.add(Restrictions.eq("username", username)).uniqueResult();
            if(k1 != null){
                    session.getTransaction().commit();
                    session.close();
                    pitanje = k1.getPitanje();
                    HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            sesija.setAttribute("promenaUser", k1);
                    /*FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Uspesno je promenjena lozinka!")); */
                    pork1 = false;
                    isValid = false;
                    pitanje1 = true;
                    return "pitanje"; }

             else {
                FacesContext.getCurrentInstance().
                        addMessage("messages15", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "JMBG koji ste uneli je pogresan. Pokusajte ponovo."));
               pork1 = false;
                return "lozinkaZab";
            }
        } else {
            pork1 = false;
            FacesContext.getCurrentInstance().
                        addMessage("messages11", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Korisnicko ime koje ste uneli ne postoji."));
            
            return "lozinkaZab";
        }
        
        
    }
    int cnt = 2;
    public String novaLozinka(){
     /*   SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
         
      Korisnik kor = (Korisnik) cr.add(Restrictions.eq("username", username)).add(Restrictions.eq("JMBG", JMBG)).uniqueResult();
        session.getTransaction().commit();
        session.close(); 
      if(kor != null){
          
          */
     
     HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
     Korisnik kor = (Korisnik) sesija.getAttribute("promenaUser");
          if(kor.getOdgovor().equals(odgovor)){
               //HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
           // sesija.setAttribute("promenaUser", kor);
             return "zaboravljenaLoz"; 
          }
          else {
              if(cnt == 2) {
                   FacesContext.getCurrentInstance().
                        addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Odgovor nije ispravan. Ostao Vam je jos jedan pokusaj!"));
                   cnt--;
                   return "pitanje";
              }
              FacesContext.getCurrentInstance().
                        addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Nazalost nismo uspeli da Vas identifikujemo."));
              cnt = 2;
              return "lozinkaZab";
          }
    //  } else 
        
       // return "index";
    }
    
    public String dodajLozinku() {
      
    
     SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
        Korisnik korisnik = (Korisnik) cr.add(Restrictions.eq("username", username)).uniqueResult();
            if(korisnik != null)     {   
        boolean ok = false;
               int passLen = zabLozinka.length();
                 if(passLen >= 8 && passLen <= 12){
                    if(zabLozinka.equals(zabLozinka.toLowerCase())){
                      //  registerMessage += "Lozinka mora da sadrži barem 1 veliko slovo.\n";
                      ok = true;
                         FacesContext.getCurrentInstance().
                        addMessage("messages4", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 1 veliko slovo"));
                    return "zaboravljenaLoz";
                    }
                    if(!zabLozinka.matches("^[A-Za-z].*$")){
                      //  registerMessage += "Lozinka mora počinjati sa slovom.\n";
                       ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("messages4", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora počinjati sa slovom."));
                      
                    return "zaboravljenaLoz";
                      
                    }
                    int numOfLower = 0;
                    for (int i = 0; i < zabLozinka.length(); i++) if (Character.isLowerCase(zabLozinka.charAt(i))) numOfLower++;                    
                    if(numOfLower < 3){
                      //  registerMessage += "Lozinka mora da sadrži barem 3 malih slova.\n";
                      ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("messages4", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 3 malih slova."));
                      
                    return "zaboravljenaLoz";
           
                    }
                    if(!zabLozinka.matches(".*[.,/!?|/()].*$")){
                       // registerMessage += "Lozinka mora da sadrži barem 1 broj.\n";
                        ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("messages4", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 1 specijalni znak."));
                      
                    return "zaboravljenaLoz";
                  
                    }
                    if(!zabLozinka.matches(".*[0-9].*$")){
                       // registerMessage += "Lozinka mora da sadrži barem 1 specijalni znak.\n";
                       //[A-Za-z0-9 ]*
                      ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("messages4", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " Lozinka mora da sadrži barem 1 broj."));
                      
                    return "zaboravljenaLoz";
                    }
                    
                } else {
                    //registerMessage += "Dužina lozinke mora biti između 6 i 12 karaktera.\n";
                     ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("messages4", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Dužina lozinke mora biti između 8 i 12 karaktera."));
                      
                    return "zaboravljenaLoz";
                 }
                 
                 if(!ok){
                     String pass = kriptovanjePass(zabLozinka);
                     
                    korisnik.setPassword(pass);
                    HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    sesija.setAttribute("promenaUser", korisnik);
                    session.save(korisnik);
                    session.getTransaction().commit();
                    session.close();}
        
     
          try {
                 FacesContext.getCurrentInstance().addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Uspesno je promenjena lozinka!"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/prijava.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return "index";
            } 
            
            else {FacesContext.getCurrentInstance().
                        addMessage("messages4", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Pokusajte ponovo." + username));
                return "novaZabLozinka";}
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
    
   public String prethodnaStrana(){
       return "lozinkaJMBG";
   }
    public String prethodnaStrana1(){
       return "zaboravljenaLozinka";
   }
    
}