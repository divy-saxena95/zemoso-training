import java.io.*;
import java.util.*;
public class HostPinging {
    private String comm;
    private int noofping;
    public boolean ping(String ip, int num){
       noofping=num;
        comm= String.format("ping -c %d %s", num, ip);
        try{
            Process exec = Runtime.getRuntime().exec(comm);
            System.out.printf("Command: %s%n", comm);
              exec.waitFor();
            BufferedReader r = new BufferedReader(new InputStreamReader(exec.getInputStream()));
             List<Float> time = new ArrayList<>();
            int c = 0;
            String line;
            try{
                while((line=r.readLine()) != null) {

                    String[] tokens = line.split(" ");

                    if ((c > 0) && (c <= noofping)) 
                        time.add(Float.valueOf(tokens[7].substring(5)));
                    

                    c++;
                }
            }catch(NumberFormatException e){
                System.out.println("Host not responding to ping");
                return false;
            }
            exec.destroy();
            r.close();
            System.out.printf("Number of pings = %d%n", noofping);
            if(time.size()==0)
                return false;
            System.out.printf("The median of time taken to ping is %sms%n", MedianTime(time));

        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    private float MedianTime(List<Float> list){
        if(list.size()==0)return 0;
        Collections.sort(list);
        int num=list.size();
        if(num%2==0)
            return (list.get(num/2-1)+list.get(num/2))/2;
        
        return list.get(num / 2);
    }

 

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Host address");
        String ip=input.next();
        System.out.println("Enter number of times to ping");
        int n;
        n = Integer.parseInt(input.next());
        System.out.printf("Ping %s%n", ip);
        HostPinging p=new HostPinging();
        p.ping(ip,n);
    }
} 

