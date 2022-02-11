#include <stdio.h>
#include <string.h>
#include "student_functions.h"

void Read_CSV(char* filename, char csv[10][1024], int* num_movies) {
    FILE* fp;
    fp = fopen(filename, "r");
    fscanf(fp, "%d", num_movies);
    char read_rest_of_line[1024];
    fgets(read_rest_of_line, 1024, fp);
    for (int i = 0; i < *num_movies; i++)
        fgets(csv[i], 1024, fp);
    fclose(fp);
    return;
}

int main() {


    // int csvIndex = -1;
    // char csv[] = "Ariel Fu, Too Far, Way Too Far";
    // char temp[1024];
    // int next = getNextData(csv, csvIndex, temp);
    // printf("next: %d\n", next);
    // printf("temp: %s\n", temp);

    // next = getNextData(csv, next, temp);
    // printf("next: %d\n", next);
    // printf("temp: %s\n", temp);

    // next = getNextData(csv, next, temp);
    // printf("next: %d\n", next);
    // printf("temp: %s\n", temp);




    // // Clean_Whitespace
    // char hello[1024] = "hello";
    // char whitespace[1024];
    // // test 1: leading
    // strcpy(whitespace, "   hello");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);
    // int i;
    // for (i = 0; whitespace[i] != '\0'; i++) {
    //     if (hello[i] != whitespace[i]) {
    //         printf("whitespace: fail - test case 1\n");
    //     }
    // }
    // if (whitespace[i] == '\0') {
    //     printf("whitespace: pass - test case 1\n");
    // }


    // // test 2: trailing
    // strcpy(whitespace, "hello    ");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);
    // for (i = 0; whitespace[i] != '\0'; i++) {
    //     if (hello[i] != whitespace[i]) {
    //         printf("whitespace: fail - test case 2\n");
    //     }
    // }
    // if (whitespace[i] == '\0') {
    //     printf("whitespace: pass - test case 2\n");
    // }

    // // test 3: tabs
    // strcpy(whitespace, "\thello\t");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);

    // for (i = 0; whitespace[i] != '\0'; i++) {
    //     if (hello[i] != whitespace[i]) {
    //         printf("whitespace: fail - test case 3\n");
    //         break;
    //     }
    // }
    // if (whitespace[i] == '\0') {
    //     printf("whitespace: pass - test case 3\n");
    // }


    // // test 4: new lines
    // strcpy(whitespace, "\nhello\n");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);
    // printf("equal? %d\n", strcmp(whitespace, hello));

    // // mod test case to accomodate for internal spaces
    // strcpy(hello, "h e l l o");
    // // test 5: internal spaces
    // strcpy(whitespace, "h     e    l      l    o");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);

    // for (i = 0; whitespace[i] != '\0'; i++) {
    //     if (hello[i] != whitespace[i]) {
    //         printf("whitespace: fail - test case 4\n");
    //         break;
    //     }
    // }
    // if (whitespace[i] == '\0') {
    //     printf("whitespace: pass - test case 4\n");
    // }

    // // (@ARIEL) TODO: check piazza
    // // test 6: mixtures
    // strcpy(whitespace, " \t   \n  h    e     l    l    o   \t     \n     \t");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);


    // for (i = 0; whitespace[i] != '\0'; i++) {
    //     if (hello[i] != whitespace[i]) {
    //         printf("whitespace: fail - test case 5\n");
    //         break;
    //     }
    // }
    // if (whitespace[i] == '\0') {
    //     printf("whitespace: pass - test case 5\n");
    // }






    // int x = move("Title, 2002", 5);
    // printf("%d", x);
    // 2nd Iteration of Testing
    // split
    // char csv[1024] = "Title, 2022, 129, Ariel Fu, 5.8, 238K";
    // int num_movies = 1;
    // char titles[10][1024];
    // int years[10];
    // char directors[10][1024];
    // float ratings[10];
    // long long dollars[10];

    // char movieTitle[] = "Title";
    // int yearValue = 2022;
    // char directorLastName[] = "Fu";
    // float ratingValue = 5.8;
    // long long dollarValue = 238000;
    // int i;
    // test 1: simple
    // Split(csv, num_movies, titles, years, directors, ratings, dollars);
    // // movie
    // for (i = 0; titles[0][i] != '\0'; i++) {
    //     if (movieTitle[i] != titles[0][i]) {
    //         printf("director: fail\n");
    //         break;
    //     }
    // }
    // if (directors[0][i] == '\0') {
    //     printf("director case: pass\n");
    // }
    // // year
    // if (years[0] != yearValue) {
    //     printf("year: %d\n", years[0]);
    //     printf("year case: fail\n");
    // }
    // else {
    //     printf("year case: pass\n");
    // }
    // // director
    // for (i = 0; directors[0][i] != '\0'; i++) {
    //     if (directorLastName[i] != directors[0][i]) {
    //         printf("director: fail\n");
    //         break;
    //     }
    // }
    // if (directors[0][i] == '\0') {
    //     printf("director case: pass\n");
    // }
    // // ratings
    // if (ratings[0] != ratingValue) {
    //     printf("rating case: fail\n");
    // }
    // else {
    //     printf("rating case: pass \n");
    // }
    // // dollars
    // if (dollars[0] != dollarValue) {
    //     printf("dollar: %llu \n ", dollars[0]);
    //     printf("dollar case: fail\n");
    // }
    // else {
    //     printf("dollar case: pass\n");
    // }


    // test 2: lots of white space
    // strcpy(csv, "     Title   ,    2022    ,     129, Ariel      Fu, 5.8, 238K");
    // // num_movies = 1;
    // // titles[10][1024];
    // // years[10];
    // // directors[10][1024];
    // // ratings[10];
    // // dollars[10];

    // // test 1: simple
    // Split(csv, num_movies, titles, years, directors, ratings, dollars);
    // // movie
    // for (i = 0; titles[0][i] != '\0'; i++) {
    //     if (movieTitle[i] != titles[0][i]) {
    //         printf("movie: fail\n");
    //         break;
    //     }
    // }
    // if (directors[0][i] == '\0') {
    //     printf("movie case: pass\n");
    // }
    // // year
    // if (years[0] != yearValue) {
    //     printf("year case: fail\n");
    // }
    // else {
    //     printf("year case: pass\n");
    // }
    // // director
    // for (i = 0; directors[0][i] != '\0'; i++) {
    //     if (directorLastName[i] != directors[0][i]) {
    //         printf("director: fail\n");
    //         break;
    //     }
    // }
    // if (directors[0][i] == '\0') {
    //     printf("director case: pass\n");
    // }
    // // ratings
    // if (ratings[0] != ratingValue) {
    //     printf("rating case: fail\n");
    // }
    // else {
    //     printf("rating case: pass \n");
    // }
    // // dollars
    // if (dollars[0] != dollarValue) {
    //     printf("dollar case: fail\n");
    // }
    // else {
    //     printf("dollar case: pass");
    // }

    // test 2.b: no spaces
    // strcpy(csv, "Title,2022,129,Ariel Fu,5.8,238K");
    // Split(csv, num_movies, titles, years, directors, ratings, dollars);
    // // movie
    // for (i = 0; titles[0][i] != '\0'; i++) {
    //     if (movieTitle[i] != titles[0][i]) {
    //         printf("movie: fail\n");
    //         break;
    //     }
    // }
    // if (directors[0][i] == '\0') {
    //     printf("movie case: pass\n");
    // }
    // // year
    // if (years[0] != yearValue) {
    //     printf("year case: fail\n");
    // }
    // else {
    //     printf("year case: pass\n");
    // }
    // // director
    // for (i = 0; directors[0][i] != '\0'; i++) {
    //     if (directorLastName[i] != directors[0][i]) {
    //         printf("director: fail\n");
    //         break;
    //     }
    // }
    // if (directors[0][i] == '\0') {
    //     printf("director case: pass\n");
    // }
    // // ratings
    // if (ratings[0] != ratingValue) {
    //     printf("rating case: fail\n");
    // }
    // else {
    //     printf("rating case: pass \n");
    // }
    // // dollars
    // if (dollars[0] != dollarValue) {
    //     printf("dollar case: fail\n");
    // }
    // else {
    //     printf("dollar case: pass");
    // }

    // test 3: all caps
    // strcpy(csv, "TITLE, 2022, 129, ARIEL FU, 5.8, 238K");
    // Split(csv, num_movies, titles, years, directors, ratings, dollars);
    // // movie
    // for (i = 0; titles[0][i] != '\0'; i++) {
    //     if (movieTitle[i] != titles[0][i]) {
    //         printf("movie: fail\n");
    //         break;
    //     }
    // }
    // if (directors[0][i] == '\0') {
    //     printf("movie case: pass\n");
    // }
    // // year
    // if (years[0] != yearValue) {
    //     printf("year case: fail\n");
    // }
    // else {
    //     printf("year case: pass\n");
    // }
    // // director
    // for (i = 0; directors[0][i] != '\0'; i++) {
    //     if (directorLastName[i] != directors[0][i]) {
    //         printf("director: fail\n");
    //         break;
    //     }
    // }
    // if (directors[0][i] == '\0') {
    //     printf("director case: pass\n");
    // }
    // // ratings
    // if (ratings[0] != ratingValue) {
    //     printf("rating case: fail\n");
    // }
    // else {
    //     printf("rating case: pass \n");
    // }
    // // dollars
    // if (dollars[0] != dollarValue) {
    //     printf("dollar case: fail\n");
    // }
    // else {
    //     printf("dollar case: pass");
    // }

    // test 4: all lowercase
    // strcpy(csv, "Title, 2022, 129, Ariel Fu, 5.8, 238K");
    // Split(csv, num_movies, titles, years, directors, ratings, dollars);
    // // movie
    // for (i = 0; titles[0][i] != '\0'; i++) {
    //     if (movieTitle[i] != titles[0][i]) {
    //         printf("movie: fail\n");
    //         break;
    //     }
    // }
    // if (directors[0][i] == '\0') {
    //     printf("movie case: pass\n");
    // }
    // // year
    // if (years[0] != yearValue) {
    //     printf("year case: fail\n");
    // }
    // else {
    //     printf("year case: pass\n");
    // }
    // // director
    // for (i = 0; directors[0][i] != '\0'; i++) {
    //     if (directorLastName[i] != directors[0][i]) {
    //         printf("director: fail\n");
    //         break;
    //     }
    // }
    // if (directors[0][i] == '\0') {
    //     printf("director case: pass\n");
    // }
    // // ratings
    // if (ratings[0] != ratingValue) {
    //     printf("rating case: fail\n");
    // }
    // else {
    //     printf("rating case: pass \n");
    // }
    // // dollars
    // if (dollars[0] != dollarValue) {
    //     printf("dollar case: fail\n");
    // }
    // else {
    //     printf("dollar case: pass");
    // }






    // // Clean_Whitespace
    // char hello[1024] = "hello";
    // char whitespace[1024];
    // // test 1: leading
    // strcpy(whitespace, "   hello");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);
    // int i;
    // for (i = 0; whitespace[i] != '\0'; i++) {
    //     if (hello[i] != whitespace[i]) {
    //         printf("whitespace: fail - test case 1\n");
    //     }
    // }
    // if (whitespace[i] == '\0') {
    //     printf("whitespace: pass - test case 1\n");
    // }


    // // test 2: trailing
    // strcpy(whitespace, "hello    ");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);
    // for (i = 0; whitespace[i] != '\0'; i++) {
    //     if (hello[i] != whitespace[i]) {
    //         printf("whitespace: fail - test case 2\n");
    //     }
    // }
    // if (whitespace[i] == '\0') {
    //     printf("whitespace: pass - test case 2\n");
    // }

    // // test 3: tabs
    // strcpy(whitespace, "\thello\t");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);

    // for (i = 0; whitespace[i] != '\0'; i++) {
    //     if (hello[i] != whitespace[i]) {
    //         printf("whitespace: fail - test case 3\n");
    //         break;
    //     }
    // }
    // if (whitespace[i] == '\0') {
    //     printf("whitespace: pass - test case 3\n");
    // }


    // // test 4: new lines
    // strcpy(whitespace, "\nhello\n");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);
    // printf("equal? %d\n", strcmp(whitespace, hello));

    // // mod test case to accomodate for internal spaces
    // strcpy(hello, "h e l l o");
    // // test 5: internal spaces
    // strcpy(whitespace, "h     e    l      l    o");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);

    // for (i = 0; whitespace[i] != '\0'; i++) {
    //     if (hello[i] != whitespace[i]) {
    //         printf("whitespace: fail - test case 4\n");
    //         break;
    //     }
    // }
    // if (whitespace[i] == '\0') {
    //     printf("whitespace: pass - test case 4\n");
    // }

    // // (@ARIEL) TODO: check piazza
    // // test 6: mixtures
    // strcpy(whitespace, " \t   \n  h    e     l    l    o   \t     \n     \t");
    // printf("before: %s\n", whitespace);
    // Clean_Whitespace(whitespace);
    // printf("after: %s\n", whitespace);


    // for (i = 0; whitespace[i] != '\0'; i++) {
    //     if (hello[i] != whitespace[i]) {
    //         printf("whitespace: fail - test case 5\n");
    //         break;
    //     }
    // }
    // if (whitespace[i] == '\0') {
    //     printf("whitespace: pass - test case 5\n");
    // }

    // printf("--------------- Fix Case --------------------\n");
    // // Test fix case:
    // char helloWorld[1024] = "Hello World";
    // char fixcase[1024];
    // // completely lowercase
    // strcpy(fixcase, "hello world");
    // printf("before: %s\n", fixcase);
    // Fix_Case(fixcase);
    // printf("after: %s\n", fixcase);

    // for (i = 0; fixcase[i] != '\0'; i++) {
    //     if (helloWorld[i] != fixcase[i]) {
    //         printf("fix case: fail - test case 1\n");
    //         break;
    //     }
    // }
    // if (fixcase[i] == '\0') {
    //     printf("fix case: pass - test case 1\n");
    // }

    // // completely uppercase
    // strcpy(fixcase, "HELLO WORLD");
    // printf("before: %s\n", fixcase);
    // Fix_Case(fixcase);
    // printf("after: %s\n", fixcase);

    // for (i = 0; fixcase[i] != '\0'; i++) {
    //     if (helloWorld[i] != fixcase[i]) {
    //         printf("fix case: fail - test case 2\n");
    //         break;
    //     }
    // }
    // if (fixcase[i] == '\0') {
    //     printf("fix case: pass - test case 2\n");
    // }

    // // upper = lower, lower = upper
    // strcpy(fixcase, "hELLO wORLD");
    // printf("before: %s\n", fixcase);
    // Fix_Case(fixcase);
    // printf("after: %s\n", fixcase);

    // for (i = 0; fixcase[i] != '\0'; i++) {
    //     if (helloWorld[i] != fixcase[i]) {
    //         printf("fix case: fail - test case 3\n");
    //         break;
    //     }
    // }
    // if (fixcase[i] == '\0') {
    //     printf("fix case: pass - test case 3\n");
    // }

    // // no errors
    // strcpy(fixcase, "Hello World");
    // printf("before: %s\n", fixcase);
    // Fix_Case(fixcase);
    // printf("after: %s\n", fixcase);

    // for (i = 0; fixcase[i] != '\0'; i++) {
    //     if (helloWorld[i] != fixcase[i]) {
    //         printf("fix case: fail - test case 4\n");
    //         break;
    //     }
    // }
    // if (fixcase[i] == '\0') {
    //     printf("fix case: pass - test case 4\n");
    // }

    // printf("--------------- String to Year --------------------\n");

    // // Test String To Year
    // int smallest = 1800;
    // char year[1024];
    // strcpy(year, "   1800    ");
    // printf("before: %s\n", year);
    // int yearInt = String_To_Year(year);
    // printf("after: %d\n", yearInt);
    // printf("equal? %d\n", (yearInt == smallest));

    // int max = 9999;
    // strcpy(year, "   9999 ");
    // printf("before: %s\n", year);
    // yearInt = String_To_Year(year);
    // printf("after: %d\n", yearInt);

    // printf("equal? %d\n", (yearInt == max));

    // printf("--------------- Director Last Name --------------------\n");

    // // Test director_last_name
    // char lastname[1024] = "Fu";
    // char director[1024];

    // // test 1 name
    // strcpy(director, "Fu");
    // printf("before: %s\n", director);
    // Director_Last_Name(director);
    // printf("after: %s\n", director);

    // for (i = 0; director[i] != '\0'; i++) {
    //     if (lastname[i] != director[i]) {
    //         printf("test case: fail - test case 1\n");
    //         break;
    //     }
    // }
    // if (director[i] == '\0') {
    //     printf("test case: pass - test case 1\n");
    // }

    // // test 2 names
    // strcpy(director, "Ariel Fu");
    // printf("before: %s\n", director);
    // Director_Last_Name(director);
    // printf("after: %s\n", director);

    // for (i = 0; director[i] != '\0'; i++) {
    //     if (lastname[i] != director[i]) {
    //         printf("test case: fail - test case 2\n");
    //         break;
    //     }
    // }
    // if (director[i] == '\0') {
    //     printf("test case: pass - test case 2\n");
    // }
    // // test 3 names
    // strcpy(director, "Ariel Alex Fu");
    // printf("before: %s\n", director);
    // Director_Last_Name(director);
    // printf("after: %s\n", director);

    // for (i = 0; director[i] != '\0'; i++) {
    //     if (lastname[i] != director[i]) {
    //         printf("test case: fail - test case 3\n");
    //         break;
    //     }
    // }
    // if (director[i] == '\0') {
    //     printf("test case: pass - test case 3\n");
    // }

    // // test bad cases: all upper
    // strcpy(director, "ARIEL FU");
    // printf("before: %s\n", director);
    // Director_Last_Name(director);
    // printf("after: %s\n", director);

    // for (i = 0; director[i] != '\0'; i++) {
    //     if (lastname[i] != director[i]) {
    //         printf("test case: fail - test case 4\n");
    //         break;
    //     }
    // }
    // if (director[i] == '\0') {
    //     printf("test case: pass - test case 4\n");
    // }

    // // test bad cases: all lower
    // strcpy(director, "ariel fu");
    // printf("before: %s\n", director);
    // Director_Last_Name(director);
    // printf("after: %s\n", director);

    // for (i = 0; director[i] != '\0'; i++) {
    //     if (lastname[i] != director[i]) {
    //         printf("test case: fail - test case 5\n");
    //         break;
    //     }
    // }
    // if (director[i] == '\0') {
    //     printf("test case: pass - test case 5\n");
    // }
    // // test bade cases: lower = upper, upper = lower
    // strcpy(director, "aRIEL fU");
    // printf("before: %s\n", director);
    // Director_Last_Name(director);
    // printf("after: %s\n", director);

    // for (i = 0; director[i] != '\0'; i++) {
    //     if (lastname[i] != director[i]) {
    //         printf("test case: fail - test case 6\n");
    //         break;
    //     }
    // }
    // if (director[i] == '\0') {
    //     printf("test case: pass - test case 6\n");
    // }

    // // test with a number
    // strcpy(director, "Ariel 235 Fu");
    // printf("before: %s\n", director);
    // Director_Last_Name(director);
    // printf("after: %s\n", director);

    // for (i = 0; director[i] != '\0'; i++) {
    //     if (lastname[i] != director[i]) {
    //         printf("director case: fail - test case 7\n");
    //         break;
    //     }
    // }
    // if (director[i] == '\0') {
    //     printf("director case: pass - test case 7\n");
    // }

    // // STRING TO RATING

    // printf("--------------- String to Rating --------------------\n");

    // // Test String To Year
    // // test case: small
    // float small = 0.0;
    // char rating[1024];
    // strcpy(rating, "     0.0      ");
    // printf("before: %s\n", rating);
    // float ratingInt = String_To_Rating(rating);
    // printf("after: %f\n", ratingInt);
    // printf("equal? %d\n", (ratingInt == small));

    // // test case: medium
    // float medium = 5.5;
    // strcpy(rating, "     5.5      ");
    // printf("before: %s\n", rating);
    // ratingInt = String_To_Rating(rating);
    // printf("after: %f\n", ratingInt);
    // printf("equal? %d\n", (ratingInt == medium));

    // float largest = 10.0;
    // strcpy(rating, "   10.0");
    // printf("before: %s\n", rating);
    // ratingInt = String_To_Rating(rating);
    // printf("after: %f\n", largest);

    // printf("equal? %d\n", (largest == ratingInt));

    // // string to dollars
    // printf("--------------- String to Dollars --------------------\n");
    // // test 1: k
    // long long dollar = 1000;
    // char dollarStr[1024];
    // strcpy(dollarStr, "1k");
    // printf("before: %s\n", dollarStr);
    // long long dollarInt = String_To_Dollars(dollarStr);
    // printf("after: %f\n", dollarInt);
    // printf("equal? %d\n", (dollar == dollarInt));

    // // test 2: K
    // dollar = 1000;
    // dollarStr[1024];
    // strcpy(dollarStr, "1K");
    // printf("before: %s\n", dollarStr);
    // dollarInt = String_To_Dollars(dollarStr);
    // printf("after: %f\n", dollarInt);
    // printf("equal? %d\n", (dollar == dollarInt));

    // // test 3: m
    // dollar = 1000000;
    // strcpy(dollarStr, "1m");
    // printf("before: %s\n", dollarStr);
    // dollarInt = String_To_Dollars(dollarStr);
    // printf("after: %f\n", dollarInt);
    // printf("equal? %d\n", (dollar == dollarInt));

    // // test 4: M
    // dollar = 1000000;
    // strcpy(dollarStr, "1M");
    // printf("before: %s\n", dollarStr);
    // dollarInt = String_To_Dollars(dollarStr);
    // printf("after: %f\n", dollarInt);
    // printf("equal? %d\n", (dollar == dollarInt));

    // // test 5: no prefix
    // dollar = 123456;
    // strcpy(dollarStr, "123456");
    // printf("before: %s\n", dollarStr);
    // dollarInt = String_To_Dollars(dollarStr);
    // printf("after: %f\n", dollarInt);
    // printf("equal? %d\n", (dollar == dollarInt));



    // Initial testing
    // char str[1024];
    // int val;

    // char movies[10][1024];
    // char directors[10][1024];
    // int year[1024];
    // int years[10];

    // float rating[1024];
    // float ratings[10];

    // long dollar[1024];
    // long dollars[10];

    // //// TEST get movie data ////
    // strcpy(str, "   Insurgent    amonst other mvoies, 2   44012, 12328, Ariel    Fu Is the Best Director Fu, 5.3, 93M");

    // Clean_Whitespace(str);
    // Fix_Case(str);

    // int runner = getMovieData(str, movies, 0, 0);
    // printf("\n%s\n", movies);
    // // move past the comma and space
    // runner += 2;

    // // get the year
    // runner = getNumberData(str, year, 0, runner);
    // years[0] = String_To_Year(year);
    // runner += 2;

    // // move past the run time
    // while (str[runner] != ',') {
    //     runner++;
    // }
    // // move past the comma and space
    // runner += 2;
    // // get the director
    // runner = getDirectorData(str, directors, 0, runner);
    // printf("\n%s\n", directors);
    // runner += 2;

    // // get the rating
    // runner = getNumberData(str, rating, 0, runner);
    // printf("\n%s\n", rating);
    // ratings[0] = String_To_Rating(rating);
    // runner += 2;

    // // get the dollar
    // runner = getNumberData(str, dollar, 0, runner);
    // printf("\n%s\n", dollar);
    // dollars[0] = String_To_Dollars(dollar);
    // runner += 2;


    // printf("movies: %s\n", movies[0]);
    // printf("year: %d\n", years[0]);
    // printf("director: %s\n", directors);
    // printf("rating: %f\n", ratings[0]);
    // printf("dollar: %d\n", dollars[0]);

    // printf("second test:\n");
    // strcpy(str, "   Idew    aDfwef bwEmvoies, 2144012, 12328, Not Fu Ariel, 105.3, 393M");

    // Clean_Whitespace(str);
    // Fix_Case(str);

    // runner = getMovieData(str, movies, 0, 0);
    // printf("\n%s\n", movies);
    // // move past the comma and space
    // runner += 2;

    // // get the year
    // runner = getNumberData(str, year, 0, runner);
    // years[0] = String_To_Year(year);
    // runner += 2;

    // // move past the run time
    // while (str[runner] != ',') {
    //     runner++;
    // }
    // // move past the comma and space
    // runner += 2;
    // // get the director
    // runner = getDirectorData(str, directors, 0, runner);
    // printf("\n%s\n", directors);
    // runner += 2;

    // // get the rating
    // runner = getNumberData(str, rating, 0, runner);
    // printf("\n%s\n", rating);
    // ratings[0] = String_To_Rating(rating);
    // runner += 2;

    // // get the dollar
    // runner = getNumberData(str, dollar, 0, runner);
    // printf("\n%s\n", dollar);
    // dollars[0] = String_To_Dollars(dollar);
    // runner += 2;


    // printf("movies: %s\n", movies[0]);
    // printf("year: %d\n", years[0]);
    // printf("director: %s\n", directors);
    // printf("rating: %f\n", ratings[0]);
    // printf("dollar: %d\n", dollars[0]);

    // strcpy(str, "Great Movie, 2012, 128, Ariel Fu, 5.3, 93M");
    // int runner = getMovieData(str, movies, 0, 0);
    // printf("\n%s\n", movies);
    // // move past the comma and space
    // runner += 2;

    // // get the year
    // runner = getNumberData(str, year, 0, runner);
    // years[0] = String_To_Year(year);
    // runner += 2;

    // // move past the run time
    // while (str[runner] != ',') {
    //     runner++;
    // }
    // // move past the comma and space
    // runner += 2;
    // // get the director
    // runner = getMovieData(str, directors, 0, runner);
    // printf("\n%s\n", directors);
    // runner += 2;

    // // get the rating
    // runner = getNumberData(str, rating, 0, runner);
    // printf("\n%s\n", rating);
    // ratings[0] = String_To_Rating(rating);
    // runner += 2;

    // // get the dollar
    // runner = getNumberData(str, dollar, 0, runner);
    // printf("\n%s\n", dollar);
    // dollars[0] = String_To_Dollars(dollar);
    // runner += 2;


    // printf("movies: %s\n", movies[0]);
    // printf("year: %d\n", years[0]);
    // printf("director: %s\n", directors);
    // printf("rating: %f\n", ratings[0]);
    // printf("dollar: %d\n", dollars[0]);









    // char bigNum[10] = "10";
    // val = String_To_Year(bigNum);
    // printf("%d\n\n", val);


    // char test[2][4] = { {' ', 'd', 'd', '\0'}, {' ', ' ', 'd', '\0'} };


    // Clean_Whitespace(test[0]);
    // printf("%s\n", test[0]);

    // printf("%s\n", test[1]);

    // Clean_Whitespace(test[1]);
    // printf("%s", test[1]);












    //// TEST convert string to dollars ////
    //// TEST convert string to rating ////
    // upper case M
    // val = 1200000;
    // strcpy(str, "1.2M");
    // if (val == String_To_Dollars(str)) {
    //     printf("true\n");
    // }
    // else {
    //     printf("flase\n");
    // }
    // // lower case m
    // val = 1200000;
    // strcpy(str, "1.2m");
    // if (val == String_To_Dollars(str)) {
    //     printf("true\n");
    // }
    // else {
    //     printf("flase\n");
    // }

    // // upper case k
    // val = 1200;
    // strcpy(str, "1.2K");
    // if (val == String_To_Dollars(str)) {
    //     printf("true\n");
    // }
    // else {
    //     printf("flase\n");
    // }

    // // lower k
    // val = 1200;
    // strcpy(str, "1.2k");
    // if (val == String_To_Dollars(str)) {
    //     printf("true\n");
    // }
    // else {
    //     printf("flase\n");
    // }



    //// TEST convert string to rating ////
    // val = 1.2;
    // strcpy(str, "1.2");
    // printf("%f", String_To_Rating(str));
    // if (val == String_To_Rating(str)) {
    //     printf("true");
    // }
    // else {
    //     printf("false");
    // }







    //// TEST get last name method ////
    // strcpy(str, "Ariel Fu");
    // Director_Last_Name(str);
    // printf("%d\n", strcmp("Fu", str));

    // strcpy(str, "Ariel Ariel Fu");
    // Director_Last_Name(str);
    // printf("%d\n", strcmp("Fu", str));

    // strcpy(str, "Fu");
    // Director_Last_Name(str);
    // printf("%d\n", strcmp("Fu", str));






    // char test[] = "size 5";
    // // strcpy(str, "size4");
    // printf("%d\n", (int)sizeof test);
    // printf("%c", test[sizeof test - 2]);


    //// TEST string to year ////
    // strcpy(str, "         2012");
    // val = String_To_Year(str);
    // if (val == 2012) {
    //     printf("true\n");
    // }
    // else {
    //     printf("false\n");
    // }

    // strcpy(str, "2012");
    // val = String_To_Year(str);
    // if (val == 2012) {
    //     printf("true\n");
    // }
    // else {
    //     printf("false\n");
    // }




    //// TEST CONVERT CHAR TO NUM HELPER ////
    // printf("simple: \n");
    // strcpy(str, "12125");
    // val = toNum(str);
    // if (val == 12125) {
    //     printf("true \n");
    // }
    // else {
    //     printf("false");
    // }



    //// TEST fix case ////
    // // upper only
    // printf("upper case one char: \n");
    // strcpy(str, "simple case");
    // Fix_Case(str);
    // printf("%d\n", strcmp("Simple Case", str));

    // // upper case middle
    // printf("upper case middle char: \n");
    // strcpy(str, "Simple case");
    // Fix_Case(str);
    // printf("%d\n", strcmp("Simple Case", str));

    // // lower case 
    // printf("lower case one char: \n");
    // strcpy(str, "sImPle cAse");
    // Fix_Case(str);
    // printf("%d\n", strcmp("Simple Case", str));

    // // opposite
    // printf("opposite case: \n");
    // strcpy(str, "sIMPLE cASE");
    // Fix_Case(str);
    // printf("%d\n", strcmp("Simple Case", str));

    // // lower only
    // printf("no upper, no lower: \n");
    // strcpy(str, "SIMPLE CASE");
    // Fix_Case(str);
    // printf("%d\n", strcmp("Simple Case", str));

    // // no upper, no lower
    // printf("no upper, no lower: \n");
    // strcpy(str, "123456");
    // Fix_Case(str);
    // printf("%d\n", strcmp("123456", str));




    //// TEST WHITESPACE ////
    // // int pos = 2;
    // // shift(str, 1);
    // // printf(str);

    // strcpy(str, " this");
    // printf(str);
    // shift(str, 0);
    // printf(str);

    // // simple
    // printf("simple case: \n");
    // strcpy(str, "simple   case");
    // Clean_Whitespace(str);
    // printf("%d\n", strcmp("simple case", str));

    // // leading tab
    // printf("leading tab case: \n");
    // strcpy(str, "\tsimple case");
    // Clean_Whitespace(str);
    // printf("%d\n", strcmp("simple case", str));

    // // trailing tab
    // printf("trailing tab case: \n");
    // strcpy(str, "simple case\t");
    // Clean_Whitespace(str);
    // printf("%d\n", strcmp("simple case", str));

    // // leading newline
    // printf("leading new line case: \n");
    // strcpy(str, "\nsimple case");
    // Clean_Whitespace(str);
    // printf("%d\n", strcmp("simple case", str));

    // // trailing newline
    // printf("trailing new line case: \n");
    // strcpy(str, "simple case\n");
    // Clean_Whitespace(str);
    // printf("%d\n", strcmp("simple case", str));

    // // leading whitespaces
    // printf("leading whitespace case: \n");
    // strcpy(str, "    simple case");
    // Clean_Whitespace(str);
    // printf("%d\n", strcmp("simple case", str));

    // // trailing whitespaces
    // printf("trailing whitespace case: \n");
    // strcpy(str, "simple case   ");
    // Clean_Whitespace(str);
    // printf("%d\n", strcmp("simple case", str));
    // printf("%s\n", str);

    // // complicated case
    // printf("comp case: \n");
    // strcpy(str, "\n   simple      case   \t");
    // Clean_Whitespace(str);
    // printf("%d\n", strcmp("simple case", str));
    // printf("%s", str);

    // char character = 'a';
    // printf("%c\n", uppercase(character));
    // character = 'A';
    // printf("%c\n", lowercase(character));

    // character = '.';
    // printf("%c\n", lowercase(character));
    // printf("%c\n", uppercase(character));
    // shift(str, pos, length);
    // printf(str);
    // str = " is this  working ?";



    char csv[10][1024]; // data structure for the entire movie csv file
    int num_movies;
    char titles[10][1024];
    int years[10];
    char directors[10][1024];
    float ratings[10];
    long long dollars[10];

    Read_CSV("movies1", csv, &num_movies);

    Split(csv, num_movies, titles, years, directors, ratings, dollars);
    Print_Table(num_movies, titles, years, directors, ratings, dollars);





    return 0;
}
