
 //dedí z tridy student, ale jedná se o hlavní typ studenta (umí oboje ability a vše )
public class kombinovane extends student{
    public kombinovane(String jmeno, String prijmeni, int ID, int den, int mesic, int rok)
    {
        setJmeno(jmeno,prijmeni);
        setDatum(rok, mesic, den);
        setID(ID);
    }
    
    public void PrestupnyRok() {
        if(datum[2] % 4 == 0)
        {
            System.out.println("Jeho rok narození je přestupný");
        }
        else
        {
            System.out.println("Jeho rok narození není přestupný");
        }
    }
    
    public void Zverokruh() {
        if(((datum[0] >= 21) && (datum[1] == 3)) || ((datum[0] <= 20) && (datum[1] == 4)))
            System.out.println("Znamení: Beran");
        else if(((datum[0] >= 21) && (datum[1] == 4)) || ((datum[0] <= 20) && (datum[1] == 5)))
            System.out.println("Znamení: Byk");
        else if(((datum[0] >= 21) && (datum[1] == 5)) || ((datum[0] <= 21) && (datum[1] == 6)))
            System.out.println("Znamení: Blizenci");
        else if(((datum[0] >= 22) && (datum[1] == 6)) || ((datum[0] <= 22) && (datum[1] == 7)))
            System.out.println("Znamení: Rak");
        else if(((datum[0] >= 23) && (datum[1] == 7)) || ((datum[0] <= 22) && (datum[1] == 8)))
            System.out.println("Znamení: Lev");
        else if(((datum[0] >= 23) && (datum[1] == 8)) || ((datum[0] <= 22) && (datum[1] == 9)))
            System.out.println("Znamení: Panna");
        else if(((datum[0] >= 23) && (datum[1] == 9)) || ((datum[0] <= 22) && (datum[1] == 10)))
            System.out.println("Znamení: Vahy");
        else if(((datum[0] >= 23) && (datum[1] == 10)) || ((datum[0] <= 22) && (datum[1] == 11)))
            System.out.println("Znamení: Skorpion");
        else if(((datum[0] >= 23) && (datum[1] == 11)) || ((datum[0] <= 21) && (datum[1] == 12)))
            System.out.println("Znamení: Strelec");
        else if(((datum[0] >= 22) && (datum[1] == 12)) || ((datum[0] <= 20) && (datum[1] == 1)))
            System.out.println("Znamení: Kozoroh");
        else if(((datum[0] >= 21) && (datum[1] == 1)) || ((datum[0] <= 19) && (datum[1] == 2)))
            System.out.println("Znamení: Vodnar");
        else if(((datum[0] >= 20) && (datum[1] == 2)) || ((datum[0] <= 20) && (datum[1] == 3)))
            System.out.println("Znamení: Byk");
    }
    @Override
    public void Abilita() {
        PrestupnyRok();
        Zverokruh();
    }
    @Override
    String getTypeOfStudium() {
        return "Kombi";
    }
}