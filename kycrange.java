import java.io.*;
import java.text.*;
import java.util.*;
public class kycrange{
private Date signupdate;
private Date currentdate;
private final SimpleDateFormat dateform =new SimpleDateFormat("dd-MM-yyyy");
public kycrange(String sign,String curr)  {
        try {
            sign= dateform.parse(signupdate);
            curr = dateform.parse(currentdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
public boolean validity() {

        return sign.compareTo(curr) <= 0;
             
   }
public Date AnniversaryDate() {

        Calendar ani = Calendar.getInstance();
        ani.setTime(sign);

        Calendar cur = Calendar.getInstance();
        cur.setTime(curr);
        cur.add(Calendar.DATE,30);
        Date i1=cur.getTime();
        Date i2=ani.getTime();
        ani.set(Calendar.YEAR,cur.get(Calendar.YEAR));
        if(ani.after(cur)){
            ani.add(Calendar.YEAR,-1);
        }
        Date anniversary=ani.getTime();
        return anniversary;//anni is used to convert calender to Date which is return type

    }

public String generateFormDateRange(Date anniv) {

        Calendar cal = Calendar.getInstance();       
         cal.setTime(anniv);
        cal.add(Calendar.DATE, -30);
        Date startani = cal.getTime();
        cal.add(Calendar.DATE, 60);
        Date endani = cal.getTime();
        if(endani.after(curr)) {
            endani=curr;
        }
        if(startani.before(sign)){
            startani=sign;
        }
        return (dateform.format(startani)+" to "+dateform.format(endani));}}
    
 
public static void main(String [] args)
{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));Scanner input=new Scanner(System.in);
        String s=null;
        System.out.println("Enter signup date and current date in \'dd-mm-yyyy\' dateform separated by space");
        s=br.readLine();
        
            String[] dates = s.split(" ");
            kycrange form = new kycrange(dates[0], dates[1]);
            if (form.validity()) {
                Date anniv = form.AnniversaryDate();
                System.out.println("You can file your KYC for dates: " + form.generateFormDateRange(anniv));
            } else {
                System.out.println("You cannot file the KYC for the given dates");
            }
        }
        
    }


