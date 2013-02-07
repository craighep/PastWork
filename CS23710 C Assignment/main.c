/* 
 * File:   main.c
 * Author: Craig
 *
 * Created on 11 December 2012, 12:50
 *
 * main file for the project, allowing users to select options from the menu
 * and linking these to the operations/ functions.
 * */

#include <stdio.h>
#include <stdlib.h>
#include "riders.h"

/*
 * 
 */

int main() {
    int what_next, nodes_n, tracks_n, courses_n, entrants_n;
    //print title for the program
    printf("       __  ___   ____     _   __    ____  ______   ____     ____ \n");
    printf("      /  |/  /  / __ )   / | / /   /  _/ /_  __/  / __ (   / _  ( \n");
    printf("     / /|_/ /  / / / /  /  |/ /    / /    / /    / / / /  / /_/ / \n");
    printf("    / /  / /  / /_/ /  / /|  /   _/ /    / /    / /_/ /  / _, _/ \n");
    printf("   /_/  /_/   (____/  /_/ |_/   /___/   /_/     (____/  /_/ |_| \n\n\n");

    /*Set up arrays of structures for each of the groups of data, then pass these
     to the data functions which read in the information from the text files. 
     Populate the structs there.
     
     For the event structure, simply pass a pointer to the structure. */
    struct Event event_name;
    get_event(&event_name);

    struct Node nodes[30];
    nodes_n = get_nodes(nodes);

    struct Track tracks[30];
    tracks_n = get_tracks(tracks);

    struct Course courses[26];
    courses_n = get_courses(courses);

    struct Record entrants[30];
    entrants_n = get_entrants(entrants, courses, courses_n);

    //print the event information out
    printf("Welcome to system developed for:\n");
    printf("    %s\n", event_name.event);
    printf("    %s\n", event_name.date);
    printf("    %s\n\n", event_name.time);

    do {
        printf("       ******************************************************\n");
        printf("       *                                                    *\n");
        printf("       *             Please Select An Option:               *\n");
        printf("       *----------------------------------------------------*\n");
        printf("       * %2d.Locate Competitor                               *\n", LOCATE_COMPETITOR);
        printf("       * %2d.Count Competitors That Have Not Started         *\n", NOT_STARTED);
        printf("       * %2d.Count Competitors On Course                     *\n", ON_COURSE);
        printf("       * %2d.Count Competitors Finished                      *\n", HAVE_FINISHED);
        printf("       * %2d.Manually Supply Times                           *\n", MANUALLY_SUPPLY);
        printf("       * %2d.Supply Checkpoint Files                         *\n", CHECKPOINT);
        printf("       * %2d.Print Results                                   *\n", RESULTS_LIST);
        printf("       *----------------------------------------------------*\n");
        printf("       * %2d.Quit                                            *\n", QUIT);
        printf("       ******************************************************\n");
        scanf("%d", &what_next);
        //print out the options, using the header file options

        /* based on the user's choice, do what they want */

        switch (what_next) {
            case LOCATE_COMPETITOR: /*Check user input to check if
                                                 the entrant number actually 
                                                 exists*/
                printf("\nPlease Input Competitor Number: ");
                int comp;
                scanf("%d", &comp);
                while (comp > entrants_n || comp < 1) {
                    printf("\nInvalid Competitor Number. Try Again: ");
                    scanf("%d", &comp);
                } /* pass in the structure argument to the function, along
                         the selected entrant number*/
                locate_competitor(entrants, comp);
                printf("Type 'c' to continue..."); /* once complete, get the 
                                                       user to type 'c' to 
                                                       continue. then create
                                                       line space.*/
                while (getchar() != 'c') {
                }
                printf("\n\n\n\n\n\n\n\n");
                break;
            case NOT_STARTED:
                count_competitors_not_started(entrants, entrants_n);
                printf("Type 'c' to continue...");
                while (getchar() != 'c') {
                }
                printf("\n\n\n\n\n\n\n\n");
                break;
            case ON_COURSE:
                count_competitors_started(entrants, entrants_n);
                printf("Type 'c' to continue...");
                while (getchar() != 'c') {
                }
                printf("\n\n\n\n\n\n\n\n");
                break;
            case HAVE_FINISHED:
                count_competitors_finished(entrants, entrants_n);
                printf("Type 'c' to continue...");
                while (getchar() != 'c') {
                }
                printf("\n\n\n\n\n\n\n\n");
                break;
            case MANUALLY_SUPPLY:
                printf("\nPlease Input Competitor Number: ");
                scanf("%d", &comp);
                while (comp > entrants_n || comp < 1) {
                    printf("\nInvalid Competitor Number. Try Again: ");
                    scanf("%d", &comp);
                }
                if (entrants[comp].excluded == 0) {
                    supply_manually(entrants, comp, nodes, tracks, tracks_n);
                } else {
                    printf("Sorry, Competitor Excluded.\n");
                }
                printf("Type 'c' to continue...");
                while (getchar() != 'c') {
                }
                printf("\n\n\n\n\n\n\n\n");
                break;
            case CHECKPOINT:
                supply_checkpoints(entrants, nodes, nodes_n, tracks, tracks_n);
                printf("Type 'c' to continue...");
                while (getchar() != 'c') {
                }
                printf("\n\n\n\n\n\n\n\n");
                break;
            case RESULTS_LIST:
                print_results(entrants, entrants_n);
                printf("Type 'c' to continue...");
                while (getchar() != 'c') {
                }
                printf("\n\n\n\n\n\n\n\n");
                break;
            case QUIT:
                break;
            default: // if the user does not type a number from the list
                printf("Incorrect Input\n\n"); // inform them, and loop.
                break;
        }
    } while (what_next != QUIT);



    return (EXIT_SUCCESS);
}
