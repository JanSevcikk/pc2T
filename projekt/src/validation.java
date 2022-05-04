import java.util.Scanner;
//odchyt chyb co se vyskytly
public class validation {
    
    public static int OnlyInteger(Scanner sc)
    {//pouze cele cislo
        int number;
        try
        {
            number = sc.nextInt();
        }
        catch (Exception e)
        {
            System.out.println("Zadejte prosím celé číslo");
            sc.nextLine();
            number = OnlyInteger(sc);
        }
        return number;
    }
   //format datumu
    public static int[] ValidDatum(Scanner sc)
    {
        int[] datum;
        try
        {
            String[] dat =sc.next().split("\\.");
            datum = validation.CorrectLenght(dat);
        }
        catch (DateExceptions e) {
            System.out.println(e.getMessage());
            System.out.println("Opravte datum prosím");
            sc.nextLine();
            datum = ValidDatum(sc);
        }
        return datum;
    }
   
    public static int[] CorrectLenght(String [] dat) throws DateExceptions
    {
        int[] datum = new int[3];
        if(dat.length != 3)
            throw new DateExceptions("Některou část datumu jste zadali špatně");
        if(dat[2].length() > 4)
            throw new DateExceptions("Neplatny rok");
        for(int i = 0; i < 3 ; i++)
        {
            datum[i] = Integer.parseInt(dat[i]);
        }
        if(datum[0] < 1 || datum[0] > 31)
            throw new DateExceptions("Neplatny den");
        else if(datum[1] < 1 || datum[1] > 12)
            throw new DateExceptions("Neplatny mesíc");
        return datum; //pokud se vse splní vrati int
    }
    
    public static class DateExceptions extends java.lang.Exception
    {
        public DateExceptions(String msg)
        {
            super(msg);
        }
    }
}