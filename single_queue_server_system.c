#include "stdio.h"
#include "math.h"
// header for random no. generator
#include "lcgrand.h"    
#include <stdio.h>


// limit on queue length
#define Q_LIMIT 100
// mnemonic for server being busy
#define BUSY    1
// and idle
#define IDLE    0
 

 int next_event_type,num_custs_delayed, num_delays_required, num_events, num_in_q, server_status;

 float area_num_in_q, area_server_status, mean_interarrival, mean_service,sim_time, time_arrival[Q_LIMIT+1],time_last_event,time_next_event[3],total_delays;

 FILE *infile ,*outfile;


 void initialize(void);
 void timing(void);
 void arrive(void);
 void depart(void);
 void report(void);
 void update_time_avg_stats(void);
 float expon(float mean);