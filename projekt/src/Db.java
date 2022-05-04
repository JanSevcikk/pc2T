import java.sql.SQLException;
import java.util.*;



 //Celá hlavní metoda, založena na TreeMap 

public class Db {
    private TreeMap<Integer, student> prvkyDatabaze;
    private Scanner sc;
    public Db()
    {
        prvkyDatabaze = new TreeMap<>();
        sc = new Scanner(System.in);
    }
    
     //Getter pro DB
     
    public TreeMap<Integer, student> getPrvkyDatabaze() {
        return prvkyDatabaze;
    }
   
     //Setter pro db, využívá se při načítání do db z .txt bo sqlka

    public void setPrvkyDatabaze(int id, String jmeno, String prijmeni, int den, int mesic, int rok, String typ) {
        if(typ.equals("Tech"))
            this.prvkyDatabaze.put(id,new technicky(jmeno,prijmeni,id,den,mesic,rok));
        else if(typ.equals("Huma"))
            this.prvkyDatabaze.put(id,new humanitni(jmeno,prijmeni,id,den,mesic,rok));
        else
            this.prvkyDatabaze.put(id,new kombinovane(jmeno,prijmeni,id,den,mesic,rok));
    }

    //settr pro nového studenta 
    public void setStudent()
    {
        System.out.println("Zadejte jméno:");
        String jmeno = sc.next();
        System.out.println("Zadejte příjmení:");
        String prijmeni = sc.next();
        System.out.println("Zadejte datum narození v formátu |DEN.MĚSÍC.ROK|");
        int[] datum = validation.ValidDatum(sc);
        System.out.println("Vyberte obor:\n 1 - Technický obor\n 2 - Humanitní obor\n 3 - Kombinovany obor");
        int volba = validation.OnlyInteger(sc);
        int id ;
        //id pro db (začínáme od 1 pokud je prázdná a pak +1)
        if(prvkyDatabaze.isEmpty())
            id = 1; 
        else
            id = prvkyDatabaze.lastKey()+1;
        switch(volba)
        {
            case 1:
                this.prvkyDatabaze.put(id,new technicky(jmeno,prijmeni,id,datum[0],datum[1],datum[2]) );
                break;
            case 2:
                this.prvkyDatabaze.put(id,new humanitni(jmeno,prijmeni,id,datum[0],datum[1],datum[2]) );
                break;
            case 3:
                this.prvkyDatabaze.put(id,new kombinovane(jmeno,prijmeni,id,datum[0],datum[1],datum[2]) );
                break;
        }
    }
   //getter pro studenta řídící se dle jeho ID pak vyhození studenta a vymazani dle ID
    public student getStudent(int ID)
    {
        for(student student : prvkyDatabaze.values())
        {
            if(prvkyDatabaze.get(ID).getId() == ID)
                return student;
        }
        return null;
    }
  
    public boolean VyhodStudenta(int ID) {
        if(prvkyDatabaze.get(ID) == null)
            return false;
        this.prvkyDatabaze.remove(ID);
        return true;
    }

    public boolean addZnamku(int ID, int znamka)
    {
        if(prvkyDatabaze.get(ID) == null)
            return false;
        this.prvkyDatabaze.get(ID).addZnamka(znamka);
        return true;
    }
//pridani znamky
//vypis studenta
    
    public String VypisStudenta(int ID)
    {
        if(this.prvkyDatabaze.containsKey(ID))
        {
            return this.prvkyDatabaze.get(ID).toString();
        }
        else
            return "Zadany student neexistuje";
    }
    
    public void seradStud()
    {//vytvorime arraylist z entrysedu z db a nasledne vypisujeme díky compare to values
        
        List<Map.Entry<Integer, student>> entryList = new ArrayList<Map.Entry<Integer, student>>(this.prvkyDatabaze.entrySet());
      
        entryList.sort((o1, o2) -> o1.getValue().getPrijmeni().compareTo(o2.getValue().getPrijmeni()));
        //Vypis dle odboru
        System.out.println("Studenti technického oboru:");
        for (var value : entryList)
        {
            if(value.getValue().getTypeOfStudium().equals("Tech"))
                System.out.println(value.getValue());
        }
        System.out.println("Studenti humanitního oboru:");
        for (var value : entryList)
        {
            if(value.getValue().getTypeOfStudium().equals("Huma"))
                System.out.println(value.getValue());
        }
        System.out.println("Studenti kombinovaneho oboru:");
        for (var value : entryList)
        {
            if(value.getValue().getTypeOfStudium().equals("Kombi"))
                System.out.println(value.getValue());
        }

    }
    
    public int[] pocStud()
    {
        //keyset na prechadzanie prvkov v TreeMap
        Set <Integer> identifikace = this.prvkyDatabaze.keySet();
        int[] pocty = new int[3];
        for(int ident:identifikace)
        {
            if(prvkyDatabaze.get(ident).getTypeOfStudium().equals("Tech"))
                pocty[0]++;
            else if(prvkyDatabaze.get(ident).getTypeOfStudium().equals("Huma"))
                pocty[1]++;
            else
                pocty[2]++;
        }
        return pocty;
    }

    /**
     * Metoda na vypocet obecneho priemeru pre jednotlive odbory
     */
    public void ObecnyPrumer()
    {
        Set <Integer> keys = prvkyDatabaze.keySet();
        double techPrumer = 0; double humPrumer = 0;
        for(int key:keys)
        {
            if(prvkyDatabaze.get(key).getTypeOfStudium().equals("Tech"))
            techPrumer+=prvkyDatabaze.get(key).getPrumer();
            else if(prvkyDatabaze.get(key).getTypeOfStudium().equals("Huma"))
            humPrumer+=prvkyDatabaze.get(key).getPrumer();
        }
        techPrumer = techPrumer / pocStud()[0];
        humPrumer = humPrumer / pocStud()[1];
        System.out.println("Technicky obor: "+techPrumer+"\nHumanitní obor: "+humPrumer);
    }
//zapsani z db do sql 
    public void addDb(DbSql db) throws SQLException {
        db.Connect();
        db.CreateTable();
        Set <Integer> keydb = this.prvkyDatabaze.keySet();
        int i = 0;
        
        for(int key:keydb)
        {
            int id = prvkyDatabaze.get(key).getId();
            String name = prvkyDatabaze.get(key).getJmeno();
            String surname = prvkyDatabaze.get(key).getPrijmeni();
            int[] datum = prvkyDatabaze.get(key).getDatum();
            String type = prvkyDatabaze.get(key).getTypeOfStudium();
            db.AddStudent(id,name,surname,datum,type);
            
            for(var value:prvkyDatabaze.get(key).getZnamky())
            {
                db.AddGrades(id,value,i);
                i++;
            }
        }
        db.Disconnect();
    }

   //nacitani z sql
    public void loadDb(DbSql db, Db dat) throws SQLException {
        db.Connect();
        db.LoadStudent(dat);
        Set <Integer> keydb = this.prvkyDatabaze.keySet();
        for(int key:keydb)
        {
            db.LoadGrades(key,dat);
        }
        db.Disconnect();
    }
}