/* 
 * File:   riders.h
 * Author: Craig
 *
 * Created on 20 November 2012, 20:39
 */

#define LOCATE_COMPETITOR 1
#define NOT_STARTED 2
#define ON_COURSE 3
#define HAVE_FINISHED 4
#define MANUALLY_SUPPLY 5
#define CHECKPOINT 6
#define RESULTS_LIST 7
#define QUIT 8

#define MAX_TYPE_LENGTH 2

struct Event {
    char event [79];
    char date [79];
    char time [5];
};

struct Node {
    int node;
    char type[MAX_TYPE_LENGTH];
};

struct Track {
    int track_ID;
    int node1;
    int node2;
    int allo_time;
};

struct Course {
    char course_ID [1];
    int size;
    int nodes_array[20];
    int num;
    int last_node;
};

struct Record {
    int ID;
    char name [20];
    char course [1];
    int current_time;
    int finished;
    int cp_count;
    char cp_type[2];
    int excluded;
    int cp;
    char time_s[5];
    int track;
    int mcp;
    char status[1];
    int course_num;
};
