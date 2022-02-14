/* Name: Ariel Fu
 * UW Net ID: 908 168 5910
*/
#include <stdio.h>
#include <stdlib.h>
#include "student_functions.h"

// function prototypes for helper methods
int getLength(char str[]);
void shift(char str[], int pos);
char uppercase(char c);
char lowercase(char c);
int getNextData(char csv[], int csvIndex, char temp[]);

// get the length of the char array
int getLength(char str[]) {
    int i, count = 0;
    for (i = 0; str[i] != '\0'; i++)
        count++;
    return count;
}

// shifts every value to the left once
void shift(char str[], int pos)
{
    for (int i = pos; str[i] != '\0'; i++)
        str[i] = str[i + 1];
}

// uppercases a character
char uppercase(char c)
{
    // ensure that the char c can be uppercased
    if (c >= 97 && c <= 122)
        return c - ('a' - 'A');
    // otherwise, return itself
    return c;
}
// lowercases a character
char lowercase(char c)
{
    // ensure that the char can be uppercased
    if (c >= 65 && c <= 90)
        return c + ('a' - 'A');
    // otherwise, return itself
    return c;
}


// get the next piece of data from the string
int getNextData(char csv[], int csvIndex, char temp[]) {
    char comma = ',';
    int i;
    int length = 0;
    // populate the temp arr with values from csv
    for (i = (csvIndex + 1); csv[i] != '\0'; i++) {
        char curr = csv[i];
        // exit teh loop if reached a ',' bc that is the end
        if (curr == comma) {
            break;
        }
        else {
            // populate the temp array with teh curr char from csv
            temp[length] = curr;
            length++;
        }
    }
    // add the null terminator at the end
    temp[length] = '\0';
    // return the index that ',' is at
    return i;
}

/* This function takes a string as input and removes
 * leading and trailing whitespace including spaces
 * tabs and newlines. It also removes multiple internal
 * spaces in a row. Arrays are passed by reference.
 */
void Clean_Whitespace(char str[])
{
    // do your work here
    // set up the values
    int i;
    char currChar;
    char space = ' ';
    char tab = '\t';
    char newline = '\n';

    // remove tabs and newlines
    for (i = 0; str[i] != '\0';) {
        currChar = str[i];
        // if curr char is a tab or new line, remove it
        if (currChar == tab || currChar == newline) {
            shift(str, i);
        }
        else {
            i++;
        }
    }


    // remove duplicate spaces
    for (i = 0; str[i + 1] != '\0'; i++) {
        currChar = str[i];

        // remove spaces in a row
        if (currChar == space) {
            // while the next char is still a space, remove it
            char nextChar = str[i + 1];
            while (str[i] != '\0' && nextChar == space)
            {
                shift(str, i + 1);
                nextChar = str[i + 1];
            }
        }
    }

    // remove leading white spaces
    i = 0;
    while (str[0] == space)
    {
        shift(str, 0);
    }

    // remove trailing white spaces
    // get the length of the string
    int currLength = getLength(str);
    i = 1;
    while (str[currLength - i] == space) {
        shift(str, currLength - i);
        i++;
    }

    return;
}

/* This function takes a string and makes the first
 * letter of every word upper case and the rest of the
 * letters lower case
 */
void Fix_Case(char str[])
{
   char current;
    int i = 1;
    char prevChar = str[0];
    if (prevChar == '\0') {
        return;
    }
    
    //take care of 1st char
    if ((str[0] >= 'A' && str[0] <= 'Z') || (str[0] >= 'a' && str[0] <= 'z')) {
        str[0] = uppercase(str[0]);
    }

    //loop thru the rest of the string
    do {
        current = str[i];
        //check if current is an alphabet
        if ((current >= 'A' && current <= 'Z') || (current >= 'a' && current <= 'z')) {
            //is current the first letter?
            //not if the prevChar is an alphabet
            if ((prevChar >= 'A' && prevChar <= 'Z') || (prevChar >= 'a' && prevChar <= 'z')) {
                str[i] = lowercase(str[i]);
            }
            else {
                str[i] = uppercase(str[i]);
            }
        }
        prevChar = current;
        i++;
    } while (current != '\0');

    return;
}

/* this function takes a string and returns the
 * integer equivalent
 */
int String_To_Year(char str[])
{
    // do your work here
    // remove the whitespaces   
    Clean_Whitespace(str);
    return atoi(str);
}

/* this function takes the name of a
 * director as a string and removes all but
 * the last name.  Example:
 * "Bucky Badger" -> "Badger"
 */
void Director_Last_Name(char str[])
{
    // do your work here
    // uppercase
    Fix_Case(str);
    // get the length of the string
    int str_length = getLength(str);
    // index that runs back from the last char
    int runner = str_length - 1;
    // length of the last name, and runs from the first char
    int start_run = 0;
    char space = ' ';

    // get length of the string

    // find the end of the "last" name
    while (runner >= 0 && str[runner] != space) {
        runner--;
    }

    runner++;
    // run from the front and replace each char with a last name char
    for (; runner < str_length; runner++) {
        str[start_run] = str[runner];
        start_run++;
    }
    // add the terminator character
    str[start_run] = '\0';
    return;
}

/* this function takes the a string and returns
 * the floating point equivalent
 */
float String_To_Rating(char str[])
{
    // do your work here
    // clean the string first
    Clean_Whitespace(str);
    return atof(str);
}

/* this function takes a string representing
 * the revenue of a movie and returns the decimal
 * equivalent. The suffix M or m represents millions,
 * K or k represents thousands.
* example: "123M" -> 123000000
*/
long long String_To_Dollars(char str[])
{
    // do your work here
    long decimalEquiv;
    // clean the string first
    Clean_Whitespace(str);
    int strLength = getLength(str);

    // get the suffix
    char suffix = str[strLength - 1];

    // multiply by 1000 if the suffix is a K
    if (uppercase(suffix) == 'K') {
        // remove the suffix
        str[strLength - 1] = '\0';

        // conver the curr string to dollars
        decimalEquiv = atol(str);

        decimalEquiv *= 1000;
    }
    else if (uppercase(suffix) == 'M') {
        // remove the suffix
        str[strLength - 1] = '\0';

        // conver the curr string to dollars
        decimalEquiv = atol(str);

        // multiply by 1000000 if the suffix is a M
        decimalEquiv *= 1000000;
    }
    else {
        decimalEquiv = atol(str);
    }


    return decimalEquiv;
}


/* This function takes the array of strings representing
 * the csv movie data and divides it into the individual
 * components for each movie.
 * Use the above helper functions.
 */
void Split(char csv[10][1024], int num_movies, char titles[10][1024], int years[10], char directors[10][1024], float ratings[10], long long dollars[10])
{
    // do your work here
    // format: movie title, year, run time, director, rating, dollars

    // ideal format: movie, year, director, rating, dollar

    // init vars
    int csvIndex;
    char movie[1024], year[1024], director[1024], rating[1024], dollar[1024];
    // get every row of csv data and format them
    for (csvIndex = 0; csvIndex < num_movies; csvIndex++) {
        int runner = -1;
        // get the movie data
        runner = getNextData(csv[csvIndex], runner, movie);
        // delete white spaces
        Clean_Whitespace(movie);
        // capitalize it
        Fix_Case(movie);
        // add to the movie array
        int length = 0;
        for (int i = 0; movie[i] != '\0'; i++) {
            titles[csvIndex][i] = movie[i];
            length++;
        }
        titles[csvIndex][length] = '\0';

        // get the year data
        runner = getNextData(csv[csvIndex], runner, year);

        // delete the white spaces
        Clean_Whitespace(year);
        // parse it to year format
        int yearVal = String_To_Year(year);

        // add to the year array
        years[csvIndex] = yearVal;

        // move until the next comma to bypass rating data
        runner = getNextData(csv[csvIndex], runner, movie);

        // get the director data
        runner = getNextData(csv[csvIndex], runner, director);
        // delete the white spaces
        Clean_Whitespace(director);
        // capitalize it
        Fix_Case(director);
        // remove the other names
        Director_Last_Name(director);
        // add the last name to the director array
        length = 0;
        for (int i = 0; director[i] != '\0'; i++) {
            directors[csvIndex][i] = director[i];
            length++;
        }
        directors[csvIndex][length] = '\0';

        // get the rating data
        runner = getNextData(csv[csvIndex], runner, rating);
        // delete the white spaces
        Clean_Whitespace(rating);
        // parse it to rating format
        float ratingVal = String_To_Rating(rating);
        // add to the rating array
        ratings[csvIndex] = ratingVal;

        // get the dollar data
        runner = getNextData(csv[csvIndex], runner, dollar);
        // delete the white spaces
        Clean_Whitespace(dollar);
        // parse it to dollar format
        long long dollarVal = String_To_Dollars(dollar);
        // add to the dollar array
        dollars[csvIndex] = dollarVal;

    }

    return;
}

/* This function prints a well formatted table of
 * the movie data
 * Row 1: Header - use name and field width as below
 * Column 1: Id, field width = 3, left justified
 * Column 2: Title, field width = length of longest movie + 2 or 7 which ever is larger, left justified, first letter of each word upper case, remaining letters lower case, one space between words
 * Column 3: Year, field with = 6, left justified
 * Column 4: Director, field width = length of longest director last name + 2 or 10 (which ever is longer), left justified, only last name, first letter upper case, remaining letters lower case
 * column 5: Rating, field width = 5, precision 1 decimal place (e.g. 8.9, or 10.0), right justified
 * column 6: Revenue, field width = 11, right justified
 */
void Print_Table(int num_movies, char titles[10][1024], int years[10], char directors[10][1024], float ratings[10], long long dollars[10])
{
    // do your work here
    int longestMovie = 5, longestDirector = 8;
    // get the longest movie name and longest director name
    for (int i = 0; i < num_movies; i++) {
        int movieLength = getLength(titles[i]);
        if (movieLength > longestMovie) {
            longestMovie = movieLength;
        }

        int nameLength = getLength(directors[i]);
        if (nameLength > longestDirector) {
            longestDirector = nameLength;
        }
    }
    // add 2 to the longestmovie value
    longestMovie += 2;
    longestDirector += 2;
    // set up the initial values
    int id = 1;
    char title[1024] = "Title";
    int year;
    float rating;
    long long int revenue;
    char director[1024] = "Director";

    // print out the header
    printf("Id %-*sYear  %-*sRating    Revenue\n", longestMovie, title, longestDirector, director);

    // for every movie, print out the value
    for (int i = 0; i < num_movies; i++) {
        // get the year, rating, and revenue for a single movie

        year = years[i];
        rating = ratings[i];
        revenue = dollars[i];
        // print the id
        printf("%-3d", id);

        // print the movie
        printf("%-*s", longestMovie, titles[i]);

        // print the year
        printf("%-6d", year);

        // print the director
        printf("%-*s", longestDirector, directors[i]);

        // print the rating
        printf("%6.1f", rating);


        // print the revenue
        printf("%11lld\n", revenue);


        id++;
    }

    return;
}