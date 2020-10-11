/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import entiteti.Artikal;
import entiteti.Korisnik;
import entiteti.Slika;
import entiteti.Korpa;
import entiteti.Narudzbina;
import entiteti.Dostava;
import java.util.ArrayList;
import java.util.Arrays;
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

//import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedBean;
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
import java.util.Date;

/**
 *
 * @author korisnik
 */
@ManagedBean
@Named(value = "supervizorKontroler")
@SessionScoped
public class SupervizorController implements Serializable{
    private Korisnik selectedKor;
    private Artikal selectedArt;
    private Slika selectedSlika;
    private UploadedFile Slika;
    private String naziv;
    private String kategorija;
    private int kolicina;
    private String ime;
    private String prezime;
    private float cena;
    private Korpa korpa1;
    private String natpis;
    private String bojaSlike;
    private boolean korpaPoruka;
    
    private int sifra;
    private float suma;
    private  String dostava; //K-kuca, P-prodavnica
    
    private String ulica;
    private String broj;
    private String grad;
    private int telefon;
    private boolean tel1;
    private ArrayList<Narudzbina> narudzbine;
    
    private String dostava1;
    

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public ArrayList<Narudzbina> getNarudzbine() {
        return narudzbine;
    }

    public void setNarudzbine(ArrayList<Narudzbina> narudzbine) {
        this.narudzbine = narudzbine;
    }

    public String getDostava() {
        return dostava;
    }

    public void setDostava(String dostava) {
        this.dostava = dostava;
    }

    public String getDostava1() {
        return dostava1;
    }

    public void setDostava1(String dostava1) {
        this.dostava1 = dostava1;
    }

    public boolean isTel1() {
        return tel1;
    }

    public void setTel1(boolean tel1) {
        this.tel1 = tel1;
    }

    public float getSuma() {
        return suma;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }

    public String getNatpis() {
        return natpis;
    }

    public void setNatpis(String natpis) {
        this.natpis = natpis;
    }

    
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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

    public String getBojaSlike() {
        return bojaSlike;
    }

    public void setBojaSlike(String bojaSlike) {
        this.bojaSlike = bojaSlike;
    }

    public Korpa getKorpa1() {
        return korpa1;
    }

    public void setKorpa1(Korpa korpa1) {
        this.korpa1 = korpa1;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public boolean isKorpaPoruka() {
        return korpaPoruka;
    }

    public void setKorpaPoruka(boolean korpaPoruka) {
        this.korpaPoruka = korpaPoruka;
    }

    public Slika getSelectedSlika() {
        return selectedSlika;
    }

    public void setSelectedSlika(Slika selectedSlika) {
        this.selectedSlika = selectedSlika;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
        System.out.println(kolicina);
    }

    public Artikal getSelectedArt() {
        return selectedArt;
    }

    public void setSelectedArt(Artikal selectedArt) {
        this.selectedArt = selectedArt;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public void setSlika(UploadedFile Slika) {
        this.Slika = Slika;
    } 

    public UploadedFile getSlika() {
        return Slika;
    } 
    
    private static Korisnik kor;
  /*  static{
      //  System.out.println(LoginController.username);
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
        int n = cr.list().size();
        
       //   HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
       //    Korisnik k = (Korisnik) sesija.getAttribute("user");
        
         for (int i = 0; i < n; i++){
            Korisnik kor1 = (Korisnik) cr.list().get(i);
            if((kor1.getUsername()).equals(LoginController.kor1.getUsername())) {
                kor = kor1;
            }
        }
        
         
         
        session.getTransaction().commit();
        session.close();
    }
*/
    public Korisnik getKor() {
        return kor;
    }

    public void setKor(Korisnik kor) {
        SupervizorController.kor = kor;
    }
    
    
    private static ArrayList<Korisnik> korisnici;
    static {
        korisnici = new ArrayList<Korisnik>();
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
        int n = cr.list().size();
        
         for (int i = 0; i < n; i++){
            Korisnik kor = (Korisnik) cr.list().get(i);
            if((kor.getPrihvacen()).equals("Z")) {
         
            korisnici.add(kor);}
        }
        
         
         
        session.getTransaction().commit();
        session.close();
    }
    private static ArrayList<Artikal> soljeDostupne;
    static {
        soljeDostupne = new ArrayList<Artikal>();
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Artikal.class);
        
        int n = cr.list().size();
        
         for (int i = 0; i < n; i++){
            Artikal solja = (Artikal) cr.list().get(i);
           if(solja.getKolicina() > 0) {
         
            soljeDostupne.add(solja);
           
         }
        }
        
         
         
        session.getTransaction().commit();
        session.close();
    }
        private static ArrayList<Artikal> solje;
    static {
        solje = new ArrayList<Artikal>();
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Artikal.class);
        
        int n = cr.list().size();
        
         for (int i = 0; i < n; i++){
            Artikal solja = (Artikal) cr.list().get(i);
          //  if((solja.getKategorija()).equals("solje")) {
         
            solje.add(solja);
           
         //}
        }
        
         
         
        session.getTransaction().commit();
        session.close();
    }
    
    private static  ArrayList<Slika> slike;
    
    private static  ArrayList<Slika> slikeKupiti;
    static {
        slikeKupiti = new ArrayList<Slika>();
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Slika.class);
        
       // HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
       //  Korisnik k = (Korisnik) sesija.getAttribute("user");
        
        int n = cr.list().size();
        
         for (int i = 0; i < n; i++){
            Slika s = (Slika) cr.list().get(i);
        //    if((s.getUmetnik()).equals(LoginController.kor1.getUsername())) {
                
                slikeKupiti.add(s);
            
           
        // }
        }
        
         
         
        session.getTransaction().commit();
        session.close();
    } 


    public ArrayList<Slika> getSlike() {
        return slike;
    }

    public void setSlike(ArrayList<Slika> slike) {
        this.slike = slike;
    }

    public ArrayList<Slika> getSlikeKupiti() {
        return slikeKupiti;
    }

    public void setSlikeKupiti(ArrayList<Slika> slikeKupiti) {
        SupervizorController.slikeKupiti = slikeKupiti;
    }

    public ArrayList<Artikal> getSoljeDostupne() {
        return soljeDostupne;
    }

    public void setSoljeDostupne(ArrayList<Artikal> soljeDostupne) {
        SupervizorController.soljeDostupne = soljeDostupne;
    }

    public ArrayList<Artikal> getSolje() {
        return solje;
    }

    public void setSolje(ArrayList<Artikal> solje) {
        SupervizorController.solje = solje;
    }
    

    public Korisnik getSelectedKor() {
        return selectedKor;
    }

    public void setSelectedKor(Korisnik selectedKor) {
        this.selectedKor = selectedKor;
    }

    public  ArrayList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ArrayList<Korisnik> korisnici) {
        SupervizorController.korisnici = korisnici;
    }
    
    
    
    public static boolean adminKorNaloga = false;

    public boolean isAdminKorNaloga() {
        return adminKorNaloga;
    }

    public void setAdminKorNaloga(boolean adminKorNaloga) {
        this.adminKorNaloga = adminKorNaloga;
    }
    
    public void adminKorNaloga1(){
        if(korisnici.size() == 0)
           FacesContext.getCurrentInstance().addMessage("mesii", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Svi korisnicki nalozi su obradjeni!"));
        
    }
    
    public void adminKorNaloga(){
        if(adminKorNaloga) {
            adminKorNaloga = false;
        }
        if(korisnici.size() > 0){
        adminKorNaloga = true;} 
        else {
            FacesContext.getCurrentInstance().addMessage("mesii", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Svi korisnicki nalozi su obradjeni!"));
        }
    }
    
        public String prihvati(Korisnik kor) {
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
        Korisnik k = (Korisnik) cr.add(Restrictions.eq("username", kor.getUsername())).uniqueResult();
        
        k.setPrihvacen("P");
        
        session.save(k);
        
        session.getTransaction().commit();
        session.close();
        
        
             FacesContext.getCurrentInstance().addMessage("mesii", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Baza je uspesno azurirana!"));
       korisnici.remove(kor);
       
        if(korisnici.size() <=0){
           adminKorNaloga = false;
            FacesContext.getCurrentInstance().addMessage("mesii", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Svi zahtevi su obradjeni!"));
       }
       
             return "admin";
          // return "proba";
        
    }
        
        public String odbij (Korisnik kor) {
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
        Korisnik k = (Korisnik) cr.add(Restrictions.eq("username", kor.getUsername())).uniqueResult();
        
        k.setPrihvacen("O");
        
        session.save(k);
        
        session.getTransaction().commit();
        session.close();

           FacesContext.getCurrentInstance().addMessage("mesii", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Baza je uspesno azurirana!"));
       korisnici.remove(kor);
       
       if(korisnici.size() <=0){
           adminKorNaloga = false;
            FacesContext.getCurrentInstance().addMessage("mesii", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Svi zahtevi su obradjeni!"));
       }
       
           return "admin";
        // return "proba";
    }
        
            public void setProfilnaSlika(UploadedFile Slika) {
        this.Slika = Slika;
    }
          public void handleFileUpload(FileUploadEvent event){
       Slika = event.getFile();
       // FacesContext.getCurrentInstance().
                  //      addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Usli u funkciju!"));
       }
     
          public String dodajArikal(){
                SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Artikal.class);
        
       cr.add(Restrictions.eq("slika", Slika.getFileName()));
      
       if(cr.list().size() > 0) {
           FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ova slika vec postoji u bazi!"));
           
           return "admin";
          // return "index";
       } else {
               String slikaBaza = Slika.getFileName();
                     Path dst =  Paths.get("C:\\Users\\korisnik\\Documents\\NetBeansProjects\\DiplomskiProdavnica\\web\\resources\\images\\" + Slika.getFileName());
                    
                   InputStream input;
               try {
                   input = Slika.getInputstream();
                   Files.copy(input, dst, StandardCopyOption.REPLACE_EXISTING);
               } catch (IOException ex) {
                   java.util.logging.Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               Artikal a = new Artikal();
               
               a.setNaziv(naziv);
               a.setKategorija(kategorija);
               a.setKolicina(kolicina);
               a.setSlika(slikaBaza);
               a.setCena(cena);
               
               session.save(a); 
       
               session.getTransaction().commit(); 
               session.close();
               
               solje.add(a);
               
               setSlika(null);
               setNaziv(null);
               setKolicina(0);
               setKategorija(null);
               setCena(0);
               FacesContext.getCurrentInstance().addMessage("messages3", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Uspesno ste dodali novi artikal!"));
               
               return "admin";
          }
       
      
          }
          
        public String azurirajKolicinuAdmin(Artikal artikal) {
                   System.out.println("Metoda");
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Artikal.class);
        
        Artikal a = (Artikal) cr.add(Restrictions.eq("slika", artikal.getSlika())).uniqueResult();
        
        a.setCena(selectedArt.getCena());
        a.setKolicina(selectedArt.getKolicina());
        
        session.save(a); 
        
        session.getTransaction().commit();
        session.close();
        
        
        return "adminAzuriraj";
        }
        
        public void obrisiArtikalAdmin (Artikal artikal) {
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Artikal.class);
        
        Artikal a = (Artikal) cr.add(Restrictions.eq("slika", artikal.getSlika())).uniqueResult();
        
        session.delete(a);
        
        
        session.getTransaction().commit();
        session.close();

           FacesContext.getCurrentInstance().addMessage("mes2", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Baza je uspesno azurirana!"));
       solje.remove(artikal);
       
       if(solje.size() <=0){
           adminKorNaloga = false;
            FacesContext.getCurrentInstance().addMessage("mes2", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Svi zahtevi su obradjeni!"));
       }
       
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/adminAzuriraj.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        // return "proba";
    }
        
       public String logout1(){
           System.out.println("Metoda");
           FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }
       
     public String dodajSliku() {
    SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Slika.class);
        
       cr.add(Restrictions.eq("slika", Slika.getFileName()));
       
       
      
       if(cr.list().size() > 0) {
           FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ova slika vec postoji u bazi!"));
           
           return "umetnik";
          // return "index";
       } else {
               String slikaBaza = Slika.getFileName();
                     Path dst =  Paths.get("C:\\Users\\korisnik\\Documents\\NetBeansProjects\\DiplomskiProdavnica\\web\\resources\\images\\" + Slika.getFileName());
                    
                   InputStream input;
               try {
                   input = Slika.getInputstream();
                   Files.copy(input, dst, StandardCopyOption.REPLACE_EXISTING);
               } catch (IOException ex) {
                   java.util.logging.Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
               }
             //  HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        // Korisnik k = (Korisnik) sesija.getAttribute("user");
               
               Slika s = new Slika();
               
               s.setNaziv(naziv);
               s.setSlika(slikaBaza);
               s.setUmetnik(LoginController.kor1.getUsername());
               slikeKupiti.add(s);
               session.save(s); 
       
               session.getTransaction().commit(); 
               session.close();
               
               setSlika(null);
               setNaziv(null);
               FacesContext.getCurrentInstance().addMessage("messages3", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Uspesno ste dodali novu sliku!"));
               
               slike.add(s);
               
               return "umetnik";
          }
    }
     
        public static void pregledslika(){
        
        slike = new ArrayList<Slika>();
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Slika.class);
        
      //  HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
      //   Korisnik k = (Korisnik) sesija.getAttribute("user");
        
        int n = cr.list().size();
        
         for (int i = 0; i < n; i++){
            Slika s = (Slika) cr.list().get(i);
            if((s.getUmetnik()).equals(LoginController.kor1.getUsername())) {
                
                slike.add(s);
            
           
         }
        }
        
         
         
        session.getTransaction().commit();
        session.close();
        System.out.println(slike.size());
        
        //return "umetnik";
        }
     
        public String obrisiSlikuUmetnik (Slika slika) {
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Slika.class);
        
        Slika s = (Slika) cr.add(Restrictions.eq("slika", slika.getSlika())).uniqueResult();
        
        session.delete(s);
        
        
        session.getTransaction().commit();
        session.close();

           FacesContext.getCurrentInstance().addMessage("mes2", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Baza je uspesno azurirana!"));
       slike.remove(slika);
       
       if(slike.size() <=0){
           adminKorNaloga = false;
            FacesContext.getCurrentInstance().addMessage("mes2", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Nema slika za prikazivanje!"));
       }
       
           return "umetnik";
        // return "proba";
    }
        
     public void dodajArtikalKorpa(Artikal artikal) {
         FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Uspesno ste dodali novi artikal u korpu!"));
       // return "prodavnica";
    }
     
     public void izaberiSliku(Artikal artikal){
          SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
          
          Session session1 = SessionFactory.openSession();
        session1.beginTransaction();
        
        Criteria cr1 = session1.createCriteria(Artikal.class);
        Artikal a = (Artikal) cr1.add(Restrictions.eq("slika", selectedArt.getSlika())).uniqueResult();
          session1.getTransaction().commit();
        session1.close();
        if(a.getKolicina() == 0) {
             FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(null, "Ovaj artikal nije na stanju"));
             try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/prodavnica.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
          
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korpa.class);
        
        int n = cr.list().size();
        
        int id = n+1;
      
      
               String slikaBaza = selectedArt.getSlika();
               
               
               Korpa korpa = new Korpa();
               
               korpa.setId(id);
               korpa.setKupac(LoginController.kor1.getUsername());
               korpa.setArtikal(slikaBaza);
               korpa.setStanje("D"); //D-ima na lageru, N-nema
               korpa.setObradjeno("N");//N-nije kupljeno, O-obradjeno
               korpa.setCena(selectedArt.getCena());
               korpa.setSlika(null);
               korpa.setNatpis(null);
               
               session.save(korpa); 
       
               session.getTransaction().commit(); 
               session.close();
               
               korpa1 = korpa;
               
               FacesContext.getCurrentInstance().addMessage("growll", new FacesMessage(null, "Uspesno ste dodali novi artikal!"));
               
      
           
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/slike.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void dodajSlikuKorpa(Slika slika){
         SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
         Criteria cr = session.createCriteria(Korpa.class);
        
        Korpa korpa = (Korpa) cr.add(Restrictions.eq("id", (korpa1.getId()))).uniqueResult();
        System.out.println(korpa1.getId());
        float cena = korpa.getCena();
        if(bojaSlike.equals("boja")){
            korpa.setCena(cena+200);
            korpa.setBoja("uBoji");
        }
        else {
           korpa.setCena(cena+100); 
           korpa.setBoja("bezBoje");
        }
        korpa.setSlika(selectedSlika.getSlika());
           session.save(korpa); 
       
               session.getTransaction().commit(); 
               session.close();
               
               korpa1 = korpa;
         try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/prodavnica.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void dodajArtikalBezPrint(){
         SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        
        
          
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korpa.class);
        
        int n = cr.list().size();
        
        int id = n+1;
      
      
               String slikaBaza = selectedArt.getSlika();
               
               
               Korpa korpa = new Korpa();
               
               korpa.setId(id);
               korpa.setKupac(LoginController.kor1.getUsername());
               korpa.setArtikal(slikaBaza);
               korpa.setStanje("D"); //D-ima na lageru, N-nema
               korpa.setObradjeno("N");//N-nije kupljeno, O-obradjeno
               korpa.setCena(selectedArt.getCena());
               korpa.setSlika(null);
               korpa.setNatpis(null);
               korpa.setBoja(null);
               
               session.save(korpa); 
       
               session.getTransaction().commit(); 
               session.close();
               
               korpa1 = korpa;
               
               FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Azurirana korpa", "Uspesno ste dodali novi artikal!"));
               
      
           
       /* try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/prodavnica.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } */
     }
     
     public void dodajNatpisArtikal (Artikal artikal){
             SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();

        
     
          
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korpa.class);
        
        int n = cr.list().size();
        
        int id = n+1;
      
      
               String slikaBaza = selectedArt.getSlika();
               
               
               Korpa korpa = new Korpa();
               
                if(bojaSlike.equals("boja")){
            korpa.setCena(selectedArt.getCena()+100);
            korpa.setBoja("uBoji");
        }
        else {
           korpa.setCena(selectedArt.getCena()+50); 
           korpa.setBoja("bezBoje");
        }
               
               korpa.setId(id);
               korpa.setKupac(LoginController.kor1.getUsername());
               korpa.setArtikal(slikaBaza);
               korpa.setStanje("D"); //D-ima na lageru, N-nema
               korpa.setObradjeno("N");//N-nije kupljeno, O-obradjeno
            //   korpa.setCena(selectedArt.getCena());
               korpa.setSlika(null);
               korpa.setNatpis(natpis);
               
               session.save(korpa); 
       
               session.getTransaction().commit(); 
               session.close();
               
               korpa1 = korpa;
               
               FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Azurirana korpa", "Uspesno ste dodali novi artikal!"));
               
      
  
     
     }
     
     
     public void zavrsiKupvinu(Slika slika){
        
         SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
         Criteria cr = session.createCriteria(Korpa.class);
        
        Korpa korpa = (Korpa) cr.add(Restrictions.eq("id", (korpa1.getId()))).uniqueResult();
        System.out.println(korpa1.getId());
        float cena = korpa.getCena();
        if(bojaSlike.equals("boja")){
            korpa.setCena(cena+200);
            korpa.setBoja("uBoji");
        }
        else {
           korpa.setCena(cena+100); 
           korpa.setBoja("bezBoje");
        }
        korpa.setSlika(selectedSlika.getSlika());
           session.save(korpa); 
       
               session.getTransaction().commit(); 
               session.close();
               
               korpa1 = korpa;
               pregledKorpe();
        /* try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/korpa.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
           */ 
     }
     
     private  ArrayList<Korpa> korpaSlike;
     private ArrayList<Korpa> korpaNatpis;
     private  ArrayList<Korpa> korpaBezSlike;

    public ArrayList<Korpa> getKorpaSlike() {
        return korpaSlike;
    }

    public void setKorpaSlike(ArrayList<Korpa> korpaSlike) {
        this.korpaSlike = korpaSlike;
    }

    public ArrayList<Korpa> getKorpaNatpis() {
        return korpaNatpis;
    }

    public void setKorpaNatpis(ArrayList<Korpa> korpaNatpis) {
        this.korpaNatpis = korpaNatpis;
    }

    public ArrayList<Korpa> getKorpaBezSlike() {
        return korpaBezSlike;
    }

    public void setKorpaBezSlike(ArrayList<Korpa> korpaBezSlike) {
        this.korpaBezSlike = korpaBezSlike;
    }
   
    
    
   private int nUkupnoKorpa;

    public int getnUkupnoKorpa() {
        return nUkupnoKorpa;
    }

    public void setnUkupnoKorpa(int nUkupnoKorpa) {
        this.nUkupnoKorpa = nUkupnoKorpa;
    }

   
     
     public void pregledKorpe(){
         korpaSlike = new ArrayList<Korpa>();
         korpaNatpis = new ArrayList<Korpa>();
         korpaBezSlike = new ArrayList<Korpa>();
                 
         
          SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
         Criteria cr = session.createCriteria(Korpa.class);
        
     //   Korpa korpa = (Korpa) cr.add(Restrictions.eq("kupac", (LoginController.kor1.getUsername()))).uniqueResult();
        
        int n = cr.list().size();
        
         for (int i = 0; i < n; i++){
            Korpa korpa = (Korpa) cr.list().get(i);
            if((korpa.getKupac()).equals(LoginController.kor1.getUsername()) && (korpa.getObradjeno().equals("N"))) {
                 Session session1 = SessionFactory.openSession();
                 session1.beginTransaction();
        
                Criteria cr1 = session1.createCriteria(Artikal.class);
                int n1 = cr1.list().size();
                int kolicina1 = 0;
                for (int i1 = 0; i1 < n1; i1++){
                    Artikal artikal = (Artikal) cr1.list().get(i1);
                    if((artikal.getSlika()).equals(korpa.getArtikal())) {
                        kolicina1 = artikal.getKolicina();
                    }
                    
                }
                session1.getTransaction().commit(); 
               session1.close();
               
               if(kolicina1 == 0) {
                   korpa.setStanje(null);
                   session.save(korpa);
                   korpaPoruka = true;
                    
               } else {
                    korpaPoruka = false;
                }
                   if(korpa.getSlika() != null) {
                       korpaSlike.add(korpa);
                   } else {
                       if(korpa.getNatpis() != null) {
                           korpaNatpis.add(korpa);
                       } else {
                           korpaBezSlike.add(korpa);
                       }
                   }
                
               
                
         }
        }
         nUkupnoKorpa = korpaSlike.size() + korpaNatpis.size() + korpaBezSlike.size();
        
        
        session.getTransaction().commit(); 
               session.close();
               
               
          try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/korpa.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void potvrdi() {
         boolean brisi = false;
       //  FacesContext.getCurrentInstance().addMessage("porukaPotvrdi", new FacesMessage("Neki artikli nisu na stanju", "Proizvode koji nisu na stanju obrisite iz korpe!"));
         if(korpaSlike.size() > 0){
             for(int i = 0; i<korpaSlike.size(); i++ ){
                 if((korpaSlike.get(i)).getObradjeno().equals("N") && korpaSlike.get(i).getStanje() == null){
                     if(!brisi) {
                     FacesContext.getCurrentInstance().addMessage("porukaPotvrdi", new FacesMessage("Neki artikli nisu na stanju", "Proizvode koji nisu na stanju obrisite iz korpe!"));
                     brisi = true;
                     }
                 }
             }
         }
         if(korpaBezSlike.size() > 0){
             for(int ii = 0; ii<korpaBezSlike.size(); ii++ ){
               if((korpaBezSlike.get(ii)).getObradjeno().equals("N") && korpaBezSlike.get(ii).getStanje() == null){
                   if(!brisi) { 
                   FacesContext.getCurrentInstance().addMessage("porukaPotvrdi", new FacesMessage("Neki artikli nisu na stanju", "Proizvode koji nisu na stanju obrisite iz korpe!"));
                   brisi = true;
                    }
                 }
             }
         }
        if(korpaNatpis.size() > 0){
             for(int i1 = 0; i1<korpaNatpis.size(); i1++ ){
                 if((korpaNatpis.get(i1)).getObradjeno().equals("N") && korpaNatpis.get(i1).getStanje() == null){
                     if(!brisi) {
                     FacesContext.getCurrentInstance().addMessage("porukaPotvrdi", new FacesMessage("Neki artikli nisu na stanju", "Proizvode koji nisu na stanju obrisite iz korpe!"));
                     brisi = true;
                     }
                 }
             }
         }
        
        if(!brisi) {
              SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
         Session session = SessionFactory.openSession();
         session.beginTransaction();
        
        Criteria cr = session.createCriteria(Artikal.class);
                    
        int n = cr.list().size();
        
         Session sessionn = SessionFactory.openSession();
         sessionn.beginTransaction();
        
        Criteria crn = session.createCriteria(Narudzbina.class);
                    
        int nn = crn.list().size();
        
        
        
        if(nn > 0) {
           Narudzbina narudz = (Narudzbina) crn.list().get(nn-1);
           sifra = narudz.getSifra()+1;
           System.out.println(sifra);
        } else {
            sifra = 0;
            System.out.println(sifra);
        }
        sessionn.getTransaction().commit(); 
        sessionn.close();
        
        for (int i = 0; i < n; i++){
           Artikal a = (Artikal) cr.list().get(i);
           if(korpaSlike.size() > 0){
             for(int i3 = 0; i3<korpaSlike.size(); i3++ ){
                 if(a.getSlika().equals(korpaSlike.get(i3).getArtikal())){
                     int kolicina2 = a.getKolicina();
                     a.setKolicina(kolicina2-1);
                     session.save(a);
                 }
             }
         }
         if(korpaBezSlike.size() > 0){
                for(int i3 = 0; i3<korpaBezSlike.size(); i3++ ){
                 if(a.getSlika().equals(korpaBezSlike.get(i3).getArtikal())){
                     int kolicina2 = a.getKolicina();
                     a.setKolicina(kolicina2-1);
                     session.save(a);
                 }
             }
         }
        if(korpaNatpis.size() > 0){
             for(int i3 = 0; i3<korpaNatpis.size(); i3++ ){
                 if(a.getSlika().equals(korpaNatpis.get(i3).getArtikal())){
                     int kolicina2 = a.getKolicina();
                     a.setKolicina(kolicina2-1);
                     session.save(a);
                 }
             }
         } 
         }
         session.getTransaction().commit(); 
         session.close();    
  
         Session session2 = SessionFactory.openSession();
         session2.beginTransaction();
        
        Criteria cr2 = session2.createCriteria(Korpa.class);
                    
        int n3 = cr2.list().size();
        
        for (int i = 0; i < n3; i++){
           Korpa korpa = (Korpa) cr2.list().get(i);
           if(korpaSlike.size() > 0){
             for(int i3 = 0; i3<korpaSlike.size(); i3++ ){
                 if(korpa.getId() == korpaSlike.get(i3).getId()){
                     korpa.setObradjeno("D");
                     session2.save(korpa);
                 }
             }
         }
         if(korpaBezSlike.size() > 0){
                 for(int i3 = 0; i3<korpaBezSlike.size(); i3++ ){
                 if(korpa.getId() == korpaBezSlike.get(i3).getId()){
                     korpa.setObradjeno("D");
                     session2.save(korpa);
                 }
             }
         }
        if(korpaNatpis.size() > 0){
            for(int i3 = 0; i3<korpaNatpis.size(); i3++ ){
                 if(korpa.getId() == korpaNatpis.get(i3).getId()){
                     korpa.setObradjeno("D");
                     session2.save(korpa);
                 }
             }
         } 
         }
         session2.getTransaction().commit(); 
         session2.close();    
        
        
          
        Session session1 = SessionFactory.openSession();
        session1.beginTransaction();
        
        Criteria cr1 = session1.createCriteria(Narudzbina.class);
        
        int n1 = cr1.list().size();
        
        int id = n1;
         suma = 0;
         Date date = new Date();
       java.sql.Date sqlDanasnji = new java.sql.Date(date.getTime());
        
         if(korpaSlike.size() > 0){
             for(int i3 = 0; i3<korpaSlike.size(); i3++ ){
                suma += korpaSlike.get(i3).getCena();
                Narudzbina nar = new Narudzbina();
                id = id+1;
                nar.setArtikal(korpaSlike.get(i3).getArtikal());
                nar.setSlika(korpaSlike.get(i3).getSlika());
                nar.setKorisnik(LoginController.kor1.getUsername());
                nar.setId(id);
                nar.setSifra(sifra);//sifra
                nar.setNatpis(null);
                nar.setSuma(korpaSlike.get(i3).getCena());
                nar.setDatum(sqlDanasnji);
                session1.save(nar);
               
             }
         }
         if(korpaBezSlike.size() > 0){
                for(int i3 = 0; i3<korpaBezSlike.size(); i3++ ){
                 suma += korpaBezSlike.get(i3).getCena();
                 Narudzbina nar = new Narudzbina();
                id = id+1;
                nar.setArtikal(korpaBezSlike.get(i3).getArtikal());
                nar.setSlika(korpaBezSlike.get(i3).getSlika());
                nar.setKorisnik(LoginController.kor1.getUsername());
                nar.setId(id);
                nar.setSifra(sifra);//sifra
                nar.setNatpis(null);
                nar.setSuma(korpaBezSlike.get(i3).getCena());
                nar.setDatum(sqlDanasnji);
                session1.save(nar);
              
             }
         }
        if(korpaNatpis.size() > 0){
             for(int i3 = 0; i3<korpaNatpis.size(); i3++ ){
                 suma += korpaNatpis.get(i3).getCena();
                 Narudzbina nar = new Narudzbina();
                id = id+1;
                nar.setArtikal(korpaNatpis.get(i3).getArtikal());
                nar.setSlika(korpaNatpis.get(i3).getSlika());
                nar.setKorisnik(LoginController.kor1.getUsername());
                nar.setId(id);
                nar.setSifra(sifra);//sifra
                nar.setNatpis(korpaNatpis.get(i3).getNatpis());
                nar.setSuma(korpaNatpis.get(i3).getCena());
                nar.setDatum(sqlDanasnji);
                session1.save(nar);
                
             }
         } 
        
        
        
        
        session1.getTransaction().commit(); 
         session1.close();
         
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/dostava.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
        
        
     }
     
     public void obrisi(Korpa korpa){
         System.out.println(korpa.getId());
         int id = korpa.getId();
         SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
         Session session = SessionFactory.openSession();
         session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korpa.class);
                    
        int n = cr.list().size();
        
        for (int i = 0; i < n; i++){
           Korpa k = (Korpa) cr.list().get(i);
           if(k.getId() == id){
              k.setObradjeno("D");
              session.save(k);
           }
         }
         session.getTransaction().commit(); 
         session.close();    
         if(korpaSlike.size() > 0){
             for(int i = 0; i<korpaSlike.size(); i++ ){
                 if((korpaSlike.get(i)).getId() == id){
                     korpaSlike.remove(i);

                 }
             }
         }
         if(korpaBezSlike.size() > 0){
             for(int ii = 0; ii<korpaBezSlike.size(); ii++ ){
                 if((korpaBezSlike.get(ii)).getId() == id){
                     korpaBezSlike.remove(ii);
                 }
             }
         }
        if(korpaNatpis.size() > 0){
             for(int i1 = 0; i1<korpaNatpis.size(); i1++ ){
                 if((korpaNatpis.get(i1)).getId() == id){
                     korpaNatpis.remove(i1);
                 }
             }
         } 
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/korpa.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
     
     public void dostavaNacin(String nacinDostave){
         if (nacinDostave.equals("P")) {
             
             dostava1 = "Preuzimanje u radnji";
             ulica = null;
             broj = null;
             grad = null;
             tel1 = false;
             
             SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
         Session session = SessionFactory.openSession();
         session.beginTransaction();
         
         Dostava d = new Dostava();
         
         d.setSifra(sifra);
         d.setNacin("P");
         d.setUlica(null);
         d.setBroj(null);
         d.setGrad(null);
         d.setTelefon(0);
         d.setCena(0);
         
         session.save(d);
         session.getTransaction().commit(); 
         session.close();
             
            try {
                pregledStavki();
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/pregledInfo.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         } else{
             if(suma < 500) {
                 suma += 500;
             }
            try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/adresa.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        }              
         }
     }
     
     public void dodajAdresu(){
          if (dostava.equals("K")) {
              
              dostava1 = "Dostava na kucnu adresu";
              
             SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
         Session session = SessionFactory.openSession();
         session.beginTransaction();
         
         Dostava d = new Dostava();
         
         d.setSifra(sifra);
         d.setNacin("K");
         d.setUlica(ulica);
         d.setBroj(broj);
         d.setGrad(grad);
         d.setTelefon(telefon);
         d.setCena(500);
         
         tel1 = true;
         
         session.save(d);
         session.getTransaction().commit(); 
         session.close();
         pregledStavki();    
            try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/pregledInfo.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
          }
     }
     
     public void pregledStavki(){
         narudzbine = new ArrayList<Narudzbina>();
         
         SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
         Session session = SessionFactory.openSession();
         session.beginTransaction();
         
         Criteria cr = session.createCriteria(Narudzbina.class);
                    
        int n = cr.list().size();
        
        for (int i = 0; i < n; i++){
           Narudzbina nar = (Narudzbina) cr.list().get(i);
           if(nar.getSifra() == sifra) {
               narudzbine.add(nar);
           }
        }
        session.getTransaction().commit(); 
         session.close();
     }
     
     public void kraj(){
         try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/zavrsiKupovinu.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
          
     }
     
     public void naslovna(){
         try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
     }
     
     public void nalog(){
         try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/nalog.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
     }
     
     public void umetnik(){
         try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/umetnik.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
     }
     public void admin(){
         try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/admin.xhtml");
            
            //  return "slike";
        } catch (IOException ex) {
            Logger.getLogger(SupervizorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
     }
}
