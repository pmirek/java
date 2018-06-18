// based on  https://caffinc.github.io/2016/03/cpu-load-generator/

public class LoadGen {

   private static int cpuNum = -1;
   private static int threadsPerCpu = -1;
   private static double load = -1;
   private static int durationSec = -1;
   private static String parameterValue = null;

   private static void printUsage (){
      System.out.println("Usage:");
      System.out.println("java LoadGen -cpuNum ... -load ... -durationSec ...");
      System.out.println("where -cpuNum is CPUs to be utilized");
      System.out.println("      -threadsPerCpu is number of threads assigned per CPU");
      System.out.println("      -load is total system load to be achieved");
      System.out.println("      -durationSec is total time to run in seconds");
      System.exit(1);
   }

   public static void main(String[] args) {
      System.out.print("Executing cmd with args: ");
      for (String arg : args) {
         System.out.print(" " + arg);
      }
      System.out.println();
      for (int i = 0; i < args.length; i++) {
         //String arg = args[i].toLowerCase();
         String arg = args[i];
         try{
            if (arg.equals("-cpuNum")) {
               if (i + 1 < args.length) {
                  parameterValue = args[++i];
                  System.out.println("cpuNum ["+ parameterValue +"]");
                  cpuNum=Integer.parseInt(parameterValue);
               } else {
                  System.out.println("not found: cpuNum, exiting");
                  return;
               }
            } else if (arg.equals("-threadsPerCpu")) {
               if (i + 1 < args.length) {
                  parameterValue = args[++i];
                  System.out.println("threadsPerCpu ["+ parameterValue +"]");
                  threadsPerCpu=Integer.parseInt(parameterValue);
               } else {
                  System.out.println("not found: threadsPerCpu, exiting");
                  return;
               }
            } else if (arg.equals("-load")) {
               if (i + 1 < args.length) {
                  parameterValue = args[++i];
                  System.out.println("load ["+ parameterValue +"]");
                  load=Double.parseDouble(parameterValue);
               } else {
                  System.out.println("not found: load, exiting");
                  return;
               }
            } else if (arg.equals("-durationSec")) {
               if (i + 1 < args.length) {
                  parameterValue = args[++i];
                  System.out.println("durationSec ["+ parameterValue +"]");
                  durationSec=Integer.parseInt(parameterValue);
               } else {
                  System.out.println("not found: durationSec, exiting");
                  return;
               }
            } else {
               System.out.println("Unknown argument: " + arg);
               printUsage();
            }
         } catch(NumberFormatException nfe){  
            System.out.println("numeric value expected or other issue");
            printUsage();  
         }  
      }

      if (    (cpuNum == -1)
           || (threadsPerCpu == -1)
           || (load == -1)
           || (durationSec == -1)
          ) {
          printUsage();
      }

      for (int thread = 0; thread < cpuNum * threadsPerCpu; thread++) {
         new BusyThread("Thread" + thread, load, durationSec).start();
      }
   }

   public static void createLoad(int cpuNum, int threadsPerCpu, double load, long durationSec) {
      for (int thread = 0; thread < cpuNum * threadsPerCpu; thread++) {
         new BusyThread("Thread" + thread, load, durationSec).start();
      }
   }

   private static class BusyThread extends Thread {
      private double load;
      private long durationSec;

      public BusyThread(String name, double load, long durationSec) {
         super(name);
         this.load = load;
         this.durationSec = durationSec;
      }
      @Override
      public void run() {
         long startTime = System.currentTimeMillis();
         try {
            // Loop for the given durationSec
            long currentTime = System.currentTimeMillis();
            while ((currentTime - startTime) < durationSec*1000) {
               // Every 100ms, sleep for the percentage of unladen time
               if ((currentTime % 100) == 0) {
                  Thread.sleep((long) Math.floor((1 - load) * 100));
               }
               currentTime = System.currentTimeMillis();
            }
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
}
