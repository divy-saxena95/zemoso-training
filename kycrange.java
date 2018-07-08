import java.io.*;
import java.text.*;
import java.util.*;
 class kyc{
    private Date signup;
    private Date current;
    private final SimpleDateFormat dateform =new SimpleDateFormat("dd-MM-yyyy");
    public kyc(String signupdate,String currentdate)  {
        try {
            signup= dateform.parse(signupdate);
            current = dateform.parse(currentdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public boolean validity() {

        return signup.compareTo(current) <= 0;

    }
    public Date AnniversaryDate() {

        Calendar ani = Calendar.getInstance();
        ani.setTime(signup);

        Calendar cur = Calendar.getInstance();
        cur.setTime(current);
        cur.add(Calendar.DATE,30);
        ani.set(Calendar.YEAR,cur.get(Calendar.YEAR));
        if(ani.after(cur)){
            ani.add(Calendar.YEAR,-1);
        }
        Date anniversary=ani.getTime();
        return anniversary;

    }

    public String generateRange(Date anniv) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(anniv);
        cal.add(Calendar.DATE, -30);
        Date startani = cal.getTime();
        cal.add(Calendar.DATE, 60);
        Date endani = cal.getTime();
        if(endani.after(current)) {
            endani=current;
        }
        if(startani.before(signup)){
            startani=signup;
        }
        return (dateform.format(startani)+" to "+dateform.format(endani));}}

public class kycrange{
public static void main(String [] args)throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Scanner input = new Scanner(System.in);
    String s = null;int i,n;
System.out.println("Enter the number of inputs");
n = Integer.parseInt(input.next());
for(i=1;i<=n;i++){
     
    System.out.println("Enter signup date and current date in \'dd-mm-yyyy\' dateform separated by space");
    if (args.length == 2)
        s = args[0] + " " + args[1];
    else
        s = br.readLine();
    
        String[] dates = s.split(" ");
       
        kyc form = new kyc(dates[0], dates[1]);
        if (form.validity()) {
            Date anniv = form.AnniversaryDate();
            System.out.println("You can file your KYC for dates: " + form.generateRange(anniv));
        } else {
            System.out.println("No Range");
        }
}
}}




