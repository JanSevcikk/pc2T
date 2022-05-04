public class humanitni extends kombinovane{
    public humanitni(String jmeno, String prijmeni, int ID, int den, int mesic, int rok)
    {
        super(jmeno,prijmeni,ID,den,mesic,rok);
    }
    @Override
    String getTypeOfStudium() {
        return "Huma";
    }
    @Override
    public void Abilita() {
        Zverokruh();
    }
}