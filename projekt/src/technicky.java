public class technicky extends kombinovane {
    

    public technicky(String jmeno, String prijmeni, int ID, int den, int mesic, int rok)
    {
        super(jmeno,prijmeni,ID,den,mesic,rok);
    }
    public String getTypeOfStudium()
    {
        return "Tech";
    }
    @Override
    public void Abilita() {
        PrestupnyRok();
}
}
