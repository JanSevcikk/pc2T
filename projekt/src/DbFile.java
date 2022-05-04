import java.io.*;
import java.util.Map;

//ukladani do txt
public class DbFile {
    //do txt, path je cesta a nazev, radek je student a oddělene hodnoty ; a konec jsou známky
    
    public static void zapisDoTXT(Db studenti, String path)
    {
        File db = new File(path);
        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter(new FileWriter(db));
            for (Map.Entry<Integer,student> studentEntry : studenti.getPrvkyDatabaze().entrySet() )
            {
                bw.write(studentEntry.getValue().getId()+";"+studentEntry.getValue().getJmeno()+";"+studentEntry.getValue().getPrijmeni()+";");
                bw.write(studentEntry.getValue().getDatum()[0]+";"+studentEntry.getValue().getDatum()[1]+";"+studentEntry.getValue().getDatum()[2]+";");
                bw.write(studentEntry.getValue().getTypeOfStudium());
                for(var znamka: studentEntry.getValue().getZnamky())
                    bw.write(";" + znamka);
                bw.newLine();
            }
            bw.flush();
        }
        catch (IOException e)
        {
            System.out.println("Nelze otevřít");
        }
        finally {
            try {
                assert bw != null;
                bw.close();
            }
            catch (IOException e)
            {
                System.out.println("Nelze zavřít");
            }
        }
    }
    
    public static void LoadFromTXT (String path, Db studenti)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            String line;
            String[] dat;
            while((line = br.readLine()) !=null)
            {
                dat = line.split(";");
                int id = Integer.parseInt(dat[0]);
                int den = Integer.parseInt(dat[3]);
                int mesic = Integer.parseInt(dat[4]);
                int rok = Integer.parseInt(dat[5]);
                studenti.setPrvkyDatabaze(id,dat[1],dat[2],den, mesic,rok,dat[6]);
                for(int i = 7; i < dat.length;i++)
                {
                    studenti.addZnamku(id,Integer.parseInt(dat[i]));
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Nelze otevřít soubor");
        }
    }
}