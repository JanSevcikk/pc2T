import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        boolean run = true;
        int volba;
        int typ;
        int ID;
        int grade;
        Db studenti = new Db();
        DbSql db = new DbSql();
        Scanner sc = new Scanner(System.in);
        while(run)
        {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("MAIN MENU:");
            System.out.println("1 - Novy student   || 2 - Nova znamka   ||  3 - Vyhodit studenta ||  4 - Student vypis");
            System.out.println("5 - Schopnosti     || 6 - Vypis         ||  7 - Prumer skupin    ||  8 - Pocty studentu");
            System.out.println("9 - Uložit do souboru || 10 - Nacist ze souboru");
            System.out.println("11 - Ulozit do DB     || 12 - Nacist z DB");
            System.out.println("13 - KILL ME");
            System.out.println("---------------------------------------------------------------------------------------------");
            volba = validation.OnlyInteger(sc);
            switch(volba)
            {
                case 1:
                    studenti.setStudent();
                    break;
                case 2:
                    System.out.println("Zvolte ID studenta");
                    ID = validation.OnlyInteger(sc);
                    System.out.println("Zadejte novou známku");
                    grade = validation.OnlyInteger(sc);
                    if(studenti.addZnamku(ID,grade))
                        System.out.println("Přidání úspěšné");
                    else
                        System.out.println("Špatné ID studenta");
                    break;
                case 3:
                    System.out.println("Zvolte ID studenta");
                    ID = validation.OnlyInteger(sc);
                    if(studenti.VyhodStudenta(ID))
                        System.out.println("TAK TEN NEVIDĚL CO HO TREFILO");
                    else
                        System.out.println("Špatné ID studenta");
                    if(db.Connect())
                    {
                        try {
                            db.DeleteStudent(ID);
                            db.DeleteGrades(ID);
                            db.Disconnect();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Zvolte ID studenta");
                    ID = validation.OnlyInteger(sc);
                    System.out.println(studenti.VypisStudenta(ID));
                    break;
                case 5:
                    /* System.out.println("Zvolte ID studenta");
                    ID = validation.OnlyInteger(sc);
                    try {
                        studenti.getStudent(ID).Abilita();
                        
                    }catch(Exception e){

                        System.out.println("Student neexistuje");

                    } */
                    
                    System.out.println("Zvolte ID studenta");
                    ID = validation.OnlyInteger(sc);
                    //try {
                        studenti.getStudent(ID).Abilita();
                        if(studenti.getStudent(ID) instanceof technicky)
                        {
                            System.out.println("Technicky");
                        }    
                        else if (studenti.getStudent(ID) instanceof humanitni)
                        {
                            System.out.println("Humanitni");
                        }
                        else if (studenti.getStudent(ID) instanceof kombinovane)
                        {
                            System.out.println("Kombinovane");
                        }
                    //}catch(Exception e){

                       // System.out.println("Student neexistuje");

                   // }
                                       
                    break;
                case 6:
                    studenti.seradStud();
                    break;
                case 7:
                    studenti.ObecnyPrumer();
                    break;
                case 8:
                    System.out.println("Technický obor: " + studenti.pocStud()[0] + " studentů\nHumanitní obor: " + studenti.pocStud()[1] +
                            " studentů\nKombinovaný obor: " + studenti.pocStud()[2] );
                    break;

                case 9:
                    DbFile.zapisDoTXT(studenti,"db.txt");
                    System.out.println("Soubor úspěšně uložen");
                    break;

                case 10:
                    DbFile.LoadFromTXT("db.txt",studenti);
                    System.out.println("Databáze ze souboru uspěšně načtena");
                    
                    break;
                case 11:
                    try
                    {
                            studenti.addDb(db);
                            System.out.println("SQL databaze uložena");
                    } catch (SQLException e) {
                            System.out.println(e.getMessage());
                    }
                    
                    break;

                case 12:
                    try
                    {
                        studenti.loadDb(db,studenti);
                        System.out.println("SQL databaze uspěšně načtena");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 13:
                    System.out.println("Opravdu chcete ukončit program? Ukončení = smazání dat která nejsou uložena v souboru\n 1- ANO \n 2-NE, TAKE ME HOME");
                    typ = validation.OnlyInteger(sc);
                    if(typ == 1)
                        run = false;
                    else                 
                    {
                    
                    System.out.println("➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖"); 
                    System.out.println("➖➖➖➖➖➖➖➖⬛⬛⬛➖➖➖➖➖➖➖➖➖");
                    System.out.println("➖➖➖➖➖➖➖⬛⬛⬛⬛⬛➖➖➖➖➖➖➖➖");
                    System.out.println("➖➖➖➖➖➖⬛⬛⬜⬜⬜⬛⬛➖➖➖➖➖➖➖");
                    System.out.println("➖➖➖➖➖➖⬜⬜⬜⬜⬜⬜⬜⬛➖➖➖➖➖➖");
                    System.out.println("➖➖➖➖➖⬛⬜⬜⬜⬜⬜⬜⬜⬛⬛➖➖➖➖➖");
                    System.out.println("➖➖➖➖➖⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛➖➖➖➖➖");
                    System.out.println("➖➖➖➖⬛⬜⬜⬛⬛⬛⬜⬜⬛⬛⬛➖➖➖➖➖");
                    System.out.println("➖➖➖➖⬛⬛⬛⬛⬜⬛⬜⬛⬛⬛⬛⬛➖➖➖➖");
                    System.out.println("➖➖➖➖⬛⬛⬛⬜⬜⬜⬜⬛⬛⬛⬛⬛➖➖➖➖"); 
                    System.out.println("➖➖➖⬜⬛⬛⬛⬛⬛⬜⬜⬛⬛⬛⬛⬛➖➖➖➖"); 
                    System.out.println("➖➖⬜⬜⬜⬜⬜⬜⬜⬛⬜⬛⬛⬛⬛⬛➖➖➖➖"); 
                    System.out.println("➖➖⬛⬛⬛⬜⬜⬜⬛⬜⬜⬛⬛⬛⬛⬛⬛➖➖➖"); 
                    System.out.println("➖➖⬜⬛⬛⬜⬜⬛⬜⬜⬛⬛⬛⬛⬛⬛⬛➖➖➖"); 
                    System.out.println("➖➖➖⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬜⬛⬛➖➖➖"); 
                    System.out.println("➖➖➖➖⬜⬜⬜⬛⬛⬛⬛⬛⬜⬜⬜⬛⬛➖➖➖"); 
                    System.out.println("➖➖➖➖⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬛⬛➖➖"); 
                    System.out.println("➖➖➖➖⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬛⬛➖"); 
                    System.out.println("➖➖➖⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬛⬛➖"); 
                    System.out.println("➖➖⬛⬛⬛⬛⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬛⬛⬛➖"); 
                    System.out.println("➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖"); 
                    System.out.println("➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖"); 
                    System.out.println("➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖"); 
                                      }
                    break;
            }
        }
    }
}




