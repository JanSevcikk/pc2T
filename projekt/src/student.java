
import java.util.ArrayList;
import java.util.List;

//hlavní trida studentu
public abstract class student {
    private int id;
    private String jmeno;
    private String prijmeni;
    protected int[] datum = new int[3];
    protected List<Integer> znamky = new ArrayList<>();
    private double prumer;

   //setter pro id 
    public void setID(int id) {
        this.id = id;
    }
   //setter id jmeno
    public void setJmeno(String jmeno, String prijmeni)
    {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
    }
  //setter datum
    public void setDatum (int rok, int mesic, int den)
    {
        this.datum[0] = den;
        this.datum[1] = mesic;
        this.datum[2] = rok;
    }
    //getter pro datum
    public int[] getDatum()
    {
        return this.datum;
    }
    //getter pro jmeno
    public String getJmeno()
    {
        return this.jmeno;
    }
    
    public String getPrijmeni()
    {
        return this.prijmeni;
    }
    
    public int getId()
    {
        return this.id;
    }
    //pridani znamky vyuziva arraylist
    public void addZnamka(int znamka)
    {
        //Kontroluje ci je znamka z rozsahu 1 az 5
        if(znamka >= 1 && znamka <= 5)
        {
            this.znamky.add(znamka);
            setPrumer();
        }
        else
            System.out.println("Taková známka neexistuje!");
    }
    
    public List<Integer> getZnamky() {
        return this.znamky;
    }
   
    public void setPrumer() {
        this.prumer = 0;
        for (var znamka : this.znamky)
        {
            this.prumer += znamka;
        }
        this.prumer = this.prumer / this.znamky.size();
    }
    
    public double getPrumer() {
        return this.prumer;
    }
    
    abstract void Abilita();
    //pretypovana metoda to string
    @Override
    public String toString()
    {
        String datum = getDatum()[0] +"."+getDatum()[1]+"."+getDatum()[2];
        return "ID: "+getId()+" Jmeno: " + getJmeno() + " Prijmeni: " + getPrijmeni() + " Narozeni " + datum + " Prumer: " + getPrumer();
    }
    
    abstract String getTypeOfStudium();
}