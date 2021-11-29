import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class Single_server
{

  // SHOULD BE ELIMINATED GIVEN JAVA SUPPORTS DYNAMIC STORAGE ALLOCATION
  final static int Q_LIMIT = 1000;
  final static int BUSY = 1;
  final static int IDLE = 0;
  


  public static int next_event_type,num_custs_delayed;
  public static int num_delays_required;
  public static int num_events;
  public static int num_in_q;
  public static int server_status;
  public static float area_num_in_q, area_server_status;
  public static float mean_interarrival;
  public static float mean_service;
  public static float sim_time;
  public static float time_last_event;
  public static float total_of_delays;
  public static  float [] time_arrival =new float [Q_LIMIT+1]; 
  public static float [] time_next_event = new float [3];



  public static void main(String[] args) {
    num_events = 2;
  
    try {
      FileWriter myWriter = new FileWriter("mm1.out");
      myWriter.write("Single-server queueing system\n\n");
      myWriter.write("Mean interarrival time "+ mean_interarrival +" minutes\n\n" );
      myWriter.write("Mean service time "+ mean_service +" minutes\n\n");
      myWriter.write("Number of customers "+ num_delays_required +"\n\n");
      myWriter.close();
      // System.out.println("Successfully wrote to the file.");
      }
      catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();  
      }
  
       initialize();
  
      while (num_custs_delayed < num_delays_required) {
        timing();
        update_time_avg_stats();
        switch (next_event_type) {
          case 1:
              arrive();
              break;
          case 2:
              depart();
              break;
        }
      }
    report();
    return;
  
  }

   static void initialize() {
    // fetch file config
    try {
        Scanner txtIn = new Scanner(new File("mm1.in"));
        while (txtIn.hasNext()){
             mean_interarrival=txtIn.nextFloat();
             mean_service=txtIn.nextFloat();
             num_delays_required=txtIn.nextInt();
        //    TESTED, WORKS
            // System.out.println(mean_interarrival);
            // System.out.println(mean_service);
            // System.out.println(num_delays_required);
        }
    }
    catch (FileNotFoundException e) {}




    /* Initialize the simulation clock. */
    sim_time = (float) 0.0;
    /* Initialize the state variables. */
    server_status= IDLE;    
    num_in_q= 0;
    time_last_event = (float) 0.0;
    /* Initialize the statistical counters. */
    num_custs_delayed  = 0;
    total_of_delays    = (float) 0.0;
    area_num_in_q      = (float) 0.0;
    area_server_status = (float) 0.0;
    /* Initialize event list.  Since no customers are present,the departure (service completion) event is eliminated from consideration. */
    time_next_event[1] = sim_time + expon(mean_interarrival);
    time_next_event[2] =  (float) 1.0e+30;
  }



   static void timing() {
    int   i;
    float min_time_next_event = (float) 1.0e+29;
    next_event_type = 0;
    /* Determine the event type of the next event to occur. */
    for (i = 1; i <= num_events; ++i)        
        if (time_next_event[i] < min_time_next_event) {
            min_time_next_event = time_next_event[i];
            next_event_type     = i;
        }
    /* Check to see whether the event list is empty. */
    if (next_event_type == 0) {
        /* The event list is empty, so stop the simulation. */
        // TODO::CHECKED
        try {
          FileWriter myWriter = new FileWriter("mm1.out");
          myWriter.write("\nEvent list empty at time "+sim_time);
          myWriter.close();
        } catch (Exception e) {
          System.out.println("An error occurred.");
          e.printStackTrace();  
        }
    }
    sim_time = min_time_next_event;
  }



   static void arrive() {
    float delay;
    /* Schedule next arrival. */
    time_next_event[1] = sim_time + expon(mean_interarrival);
    /* Check to see whether server is busy. */
    if (server_status == BUSY) {
    /* Server is busy, so increment number of customers in queue. */
    ++num_in_q;
    /* Check to see whether an overflow condition exists. */
    if (num_in_q > Q_LIMIT) {
    /* The queue has overflowed, so stop the simulation. */
    
    try {
          FileWriter myWriter = new FileWriter("mm1.out");
          myWriter.write("\nOverflow of the array time_arrival at:");
          myWriter.write(" time :"+ sim_time);
          myWriter.close();
        } catch (Exception e) {
          System.out.println("An error occurred.");
          e.printStackTrace();  
        }
 
    }
    /* There is still room in the queue, so store the time of arrival of the arriving customer at the (new) end of time_arrival. */
    time_arrival[num_in_q] = sim_time;    }
    else {
        /* Server is idle, so arriving customer has a delay of zero.  (The following two statements are for program clarity and do not affect
        the results of the simulation.) */
        delay= (float) 0.0;
        total_of_delays += delay;
        /* Increment the number of customers delayed, and make server busy. */
        ++num_custs_delayed;
        server_status = BUSY;
        /* Schedule a departure (service completion). */
        time_next_event[2] = sim_time + expon(mean_service);
    }
  }



   static void depart() {
    int   i;
    float delay;
    /* Check to see whether the queue is empty. */
    if (num_in_q == 0) {
        /* The queue is empty so make the server idle and eliminate the departure (service completion) event from consideration. */
        server_status= IDLE;       
        time_next_event[2] = (float)1.0e+30;
    }
    else {
        /* The queue is nonempty, so decrement the number of customers in queue. */
        --num_in_q;
        /* Compute the delay of the customer who is beginning service and update           the total delay accumulator. */
        delay= sim_time - time_arrival[1];
        total_of_delays += delay;
        /* Increment the number of customers delayed, and schedule departure. */
        ++num_custs_delayed;
        time_next_event[2] = sim_time + expon(mean_service);
        /* Move each customer in queue (if any) up one place. */
        for (i = 1; i <= num_in_q; ++i) 
            time_arrival[i] = time_arrival[i + 1];
    }
  }


   static void report() {

    try {
      FileWriter myWriter = new FileWriter("mm1.out");
      myWriter.write("\n\nAverage delay in queue"+(total_of_delays / num_custs_delayed)+" minutes\n\n");
      myWriter.write("Average number in queue"+(area_num_in_q / sim_time)+"\n\n");
      myWriter.write("Server utilization"+(area_server_status / sim_time)+"\n\n");
      myWriter.write("Time simulation ended"+(sim_time)+" minutes");
      myWriter.close();
      // System.out.println("Successfully wrote to the file.");
      }
      catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();  
      }
  }


   static void update_time_avg_stats() {
    float time_since_last_event;
    /* Compute time since last event, and update last-event-time marker. */
    time_since_last_event = sim_time - time_last_event;
    time_last_event = sim_time;
    /* Update area under number-in-queue function. */
    area_num_in_q += num_in_q * time_since_last_event;
    /* Update area under server-busy indicator function. */
    area_server_status += server_status * time_since_last_event;
  }


  static float expon(float mean) {
    Random randomno = new Random();

    double ans = Math.log(1-randomno.nextDouble())/(-mean);
    return (float) ans;
}
 
  


}