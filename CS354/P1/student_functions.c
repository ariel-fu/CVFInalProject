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

    for (i = 0; str[i] != '\0'; i++)
    {
        // get the current character
        currChar = str[i];

        // check if the curr char is a tab or a new line
        if (currChar == tab || currChar == newline)
        {
            // shift everything down
            shift(str, i);
        }
        else if (currChar == space)
        {
            if (str[i + 1] == space || str[i + 1] == '\0')
            {
                // check if the next character is a space
                // while the next character is a space, shift down
                while (str[i] != '\0' && str[i + 1] == space)
                {
                    shift(str, i + 1);
                }
            }
            // trailing white space:
            else if (str[i + 1] == '\0' || str[i + 1] == tab || str[i + 1] == newline)
            {
                str[i] = '\0';
            }
        }
    }
    // get the length of the string
    int currLength = getLength(str);
    // get the trailing white spaces
    while (str[currLength - 1] == space) {
        shift(str, currLength - 1);
    }

    // get the number of leading white spaces:
    i = 0;
    while (str[0] == space)
    {
        shift(str, 0);
    }



    return;
}

/* This function takes a string and makes the first
 * letter of every word upper case and the rest of the
 * letters lower case
 */
void Fix_Case(char str[])
{
    // do your work here
    int i = 0;
    char prevChar;
    char space = ' ';


    while (str[i] != '\0') {
        // if the prev char is equal to a space or this is the first char:
        if (prevChar == space || i == 0)
            // set the next char to an uppercase if possible
            str[i] = uppercase(str[i]);
        // else:
        else
            // set the current char to a lowercase if possible
            str[i] = lowercase(str[i]);
        // increment the counter
        i++;
        // get the prev char
        prevChar = str[i - 1];
    }
    return;
}

/* this function takes a string and returns the
 * integer equivalent
 */
int String_To_Year(char str[])
{
    // do your work here
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
        //DEBUG
        // printf("%s", str);
        decimalEquiv = atol(str);
    }


    return decimalEquiv;
}

int getMovieData(char csv[1024], char data[10][1024], int csvIndex, int runner) {
    int index = 0;
    char comma = ',';
    // DEBUG
    // printf("%s", csv);
    char currChar = csv[runner];
    while (currChar != comma && currChar != '\0') {
        data[csvIndex][index] = csv[runner];
        index++;
        runner++;
        currChar = csv[runner];
    }
    // terminate the string
    data[csvIndex][index] = '\0';
    // DEBUG
    // printf("%s\n", data[csvIndex]);
    return runner;
}

int getDirectorData(char csv[1024], char directors[10][1024], int csvIndex, int runner) {
    int index = 0;
    char comma = ',';
    char directorName[1024];
    char currChar = csv[runner];

    // get director whole name
    while (currChar != comma && currChar != '\0') {
        directorName[index] = csv[runner];
        index++;
        runner++;
        currChar = csv[runner];
    }
    // terminate the string
    directorName[index] = '\0';

    // get the last name
    Director_Last_Name(directorName);
    // get the length
    int nameLength = getLength(directorName);

    // put in csv
    for (index = 0; index < nameLength; index++) {
        directors[csvIndex][index] = directorName[index];
    }
    // terminate the string
    directors[csvIndex][index] = '\0';
    // DEBUG
    // printf("director: %s\n", directors[csvIndex]);

    return runner;
}

int getNumberData(char csv[1024], char temp[1024], int csvIndex, int runner) {
    int index = 0;
    char comma = ',';
    char currChar = csv[runner];
    while (currChar != comma && currChar != '\0') {
        temp[index] = csv[runner];
        index++;
        runner++;
        currChar = csv[runner];
    }
    temp[index] = '\0';
    return runner;
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
    char year[1024], rating[1024], dollar[1024];

    for (csvIndex = 0; csvIndex < num_movies; csvIndex++) {
        // get the row of movie data & remove the white spaces
        Clean_Whitespace(csv[csvIndex]);

        // set the uppercase
        Fix_Case(csv[csvIndex]);


        // get the title and add to the corresponding array
        int runner = getMovieData(csv[csvIndex], titles, csvIndex, 0);
        // move past the space and the comma
        runner += 2;

        // get the year
        runner = getNumberData(csv[csvIndex], year, csvIndex, runner);
        int numYear = String_To_Year(year);
        years[csvIndex] = numYear;
        // DEBUG
        // printf("year: %d\n", years[csvIndex]);
        // move past the space and the comma
        runner += 2;

        // move past the runtime
        while (csv[csvIndex][runner] != ',') {
            runner++;
        }
        // move past the space and the comma
        runner += 2;
        // get the director
        runner = getDirectorData(csv[csvIndex], directors, csvIndex, runner);
        runner += 2;

        // get the rating
        runner = getNumberData(csv[csvIndex], rating, csvIndex, runner);
        float numRating = String_To_Rating(rating);
        ratings[csvIndex] = numRating;

        // DEBUG
        // printf("ratings: %.1f\n", ratings[csvIndex]);

        // move past the space and the comma
        runner += 2;

        // get the dollar amount
        runner = getNumberData(csv[csvIndex], dollar, csvIndex, runner);
        // debug
        // printf("%s", dollar);
        long long int numDollar = String_To_Dollars(dollar);
        dollars[csvIndex] = numDollar;
        // DEBUG
        // printf("dollars: %llu\n", dollars[csvIndex]);

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
    printf("Id %-*sYear  %-*sRating    Revenue\n", longestMovie, title, longestDirector, director);

    // for every movie, print out the value
    for (int i = 0; i < num_movies; i++) {
        year = years[i];
        rating = ratings[i];
        revenue = dollars[i];
        // DEBUG
        // printf("curr movie: %s\n", titles[i]);
        // printf("curr director: %s\n", directors[i]);
        // printf("curr year: %d \n ", year);
        // printf("curr rating: %f \n", rating);
        // printf("curr revenue: %llu\n", revenue);
        // printf("_---------------------\n");
        //id (3, left)
        // title (longest+2 (or 7), left)
        // year (6, left)
        // director (longest+2 (or 10), left)
        // rating (6, 1 precision, right)
        // dollars (11, right)

        // TODO
        /**
         * @brief split up the printf statement
         *  individually print out the id, the movie, the year, the director, rating, and the revenue
         */
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


        // DEBUG: write to a file

        id++;
    }

    return;
}

/// DELETE

// void main()
// {
//     char str[1024];
//     int val;

//     char movies[10][1024];
//     char directors[10][1024];
//     int year[1024];
//     int years[10];

//     float rating[1024];
//     float ratings[10];

//     long dollar[1024];
//     long dollars[10];

//     //// TEST get movie data ////
//     strcpy(str, "   Insurgent    amonst other mvoies, 2   44012, 12328, Ariel    Fu Is the Best Director Fu, 5.3, 93M");

//     Clean_Whitespace(str);
//     Fix_Case(str);

//     int runner = getMovieData(str, movies, 0, 0);
//     printf("\n%s\n", movies);
//     // move past the comma and space
//     runner += 2;

//     // get the year
//     runner = getNumberData(str, year, 0, runner);
//     years[0] = String_To_Year(year);
//     runner += 2;

//     // move past the run time
//     while (str[runner] != ',') {
//         runner++;
//     }
//     // move past the comma and space
//     runner += 2;
//     // get the director
//     runner = getDirectorData(str, directors, 0, runner);
//     printf("\n%s\n", directors);
//     runner += 2;

//     // get the rating
//     runner = getNumberData(str, rating, 0, runner);
//     printf("\n%s\n", rating);
//     ratings[0] = String_To_Rating(rating);
//     runner += 2;

//     // get the dollar
//     runner = getNumberData(str, dollar, 0, runner);
//     printf("\n%s\n", dollar);
//     dollars[0] = String_To_Dollars(dollar);
//     runner += 2;


//     printf("movies: %s\n", movies[0]);
//     printf("year: %d\n", years[0]);
//     printf("director: %s\n", directors);
//     printf("rating: %f\n", ratings[0]);
//     printf("dollar: %d\n", dollars[0]);

//     printf("second test:\n");
//     strcpy(str, "   Idew    aDfwef bwEmvoies, 2144012, 12328, Not Fu Ariel, 105.3, 393M");

//     Clean_Whitespace(str);
//     Fix_Case(str);

//     runner = getMovieData(str, movies, 0, 0);
//     printf("\n%s\n", movies);
//     // move past the comma and space
//     runner += 2;

//     // get the year
//     runner = getNumberData(str, year, 0, runner);
//     years[0] = String_To_Year(year);
//     runner += 2;

//     // move past the run time
//     while (str[runner] != ',') {
//         runner++;
//     }
//     // move past the comma and space
//     runner += 2;
//     // get the director
//     runner = getDirectorData(str, directors, 0, runner);
//     printf("\n%s\n", directors);
//     runner += 2;

//     // get the rating
//     runner = getNumberData(str, rating, 0, runner);
//     printf("\n%s\n", rating);
//     ratings[0] = String_To_Rating(rating);
//     runner += 2;

//     // get the dollar
//     runner = getNumberData(str, dollar, 0, runner);
//     printf("\n%s\n", dollar);
//     dollars[0] = String_To_Dollars(dollar);
//     runner += 2;


//     printf("movies: %s\n", movies[0]);
//     printf("year: %d\n", years[0]);
//     printf("director: %s\n", directors);
//     printf("rating: %f\n", ratings[0]);
//     printf("dollar: %d\n", dollars[0]);

//     // strcpy(str, "Great Movie, 2012, 128, Ariel Fu, 5.3, 93M");
//     // int runner = getMovieData(str, movies, 0, 0);
//     // printf("\n%s\n", movies);
//     // // move past the comma and space
//     // runner += 2;

//     // // get the year
//     // runner = getNumberData(str, year, 0, runner);
//     // years[0] = String_To_Year(year);
//     // runner += 2;

//     // // move past the run time
//     // while (str[runner] != ',') {
//     //     runner++;
//     // }
//     // // move past the comma and space
//     // runner += 2;
//     // // get the director
//     // runner = getMovieData(str, directors, 0, runner);
//     // printf("\n%s\n", directors);
//     // runner += 2;

//     // // get the rating
//     // runner = getNumberData(str, rating, 0, runner);
//     // printf("\n%s\n", rating);
//     // ratings[0] = String_To_Rating(rating);
//     // runner += 2;

//     // // get the dollar
//     // runner = getNumberData(str, dollar, 0, runner);
//     // printf("\n%s\n", dollar);
//     // dollars[0] = String_To_Dollars(dollar);
//     // runner += 2;


//     // printf("movies: %s\n", movies[0]);
//     // printf("year: %d\n", years[0]);
//     // printf("director: %s\n", directors);
//     // printf("rating: %f\n", ratings[0]);
//     // printf("dollar: %d\n", dollars[0]);









//     // char bigNum[10] = "10";
//     // val = String_To_Year(bigNum);
//     // printf("%d\n\n", val);


//     // char test[2][4] = { {' ', 'd', 'd', '\0'}, {' ', ' ', 'd', '\0'} };


//     // Clean_Whitespace(test[0]);
//     // printf("%s\n", test[0]);

//     // printf("%s\n", test[1]);

//     // Clean_Whitespace(test[1]);
//     // printf("%s", test[1]);












//     //// TEST convert string to dollars ////
//     //// TEST convert string to rating ////
//     // upper case M
//     // val = 1200000;
//     // strcpy(str, "1.2M");
//     // if (val == String_To_Dollars(str)) {
//     //     printf("true\n");
//     // }
//     // else {
//     //     printf("flase\n");
//     // }
//     // // lower case m
//     // val = 1200000;
//     // strcpy(str, "1.2m");
//     // if (val == String_To_Dollars(str)) {
//     //     printf("true\n");
//     // }
//     // else {
//     //     printf("flase\n");
//     // }

//     // // upper case k
//     // val = 1200;
//     // strcpy(str, "1.2K");
//     // if (val == String_To_Dollars(str)) {
//     //     printf("true\n");
//     // }
//     // else {
//     //     printf("flase\n");
//     // }

//     // // lower k
//     // val = 1200;
//     // strcpy(str, "1.2k");
//     // if (val == String_To_Dollars(str)) {
//     //     printf("true\n");
//     // }
//     // else {
//     //     printf("flase\n");
//     // }



//     //// TEST convert string to rating ////
//     // val = 1.2;
//     // strcpy(str, "1.2");
//     // printf("%f", String_To_Rating(str));
//     // if (val == String_To_Rating(str)) {
//     //     printf("true");
//     // }
//     // else {
//     //     printf("false");
//     // }







//     //// TEST get last name method ////
//     // strcpy(str, "Ariel Fu");
//     // Director_Last_Name(str);
//     // printf("%d\n", strcmp("Fu", str));

//     // strcpy(str, "Ariel Ariel Fu");
//     // Director_Last_Name(str);
//     // printf("%d\n", strcmp("Fu", str));

//     // strcpy(str, "Fu");
//     // Director_Last_Name(str);
//     // printf("%d\n", strcmp("Fu", str));






//     // char test[] = "size 5";
//     // // strcpy(str, "size4");
//     // printf("%d\n", (int)sizeof test);
//     // printf("%c", test[sizeof test - 2]);


//     //// TEST string to year ////
//     // strcpy(str, "         2012");
//     // val = String_To_Year(str);
//     // if (val == 2012) {
//     //     printf("true\n");
//     // }
//     // else {
//     //     printf("false\n");
//     // }

//     // strcpy(str, "2012");
//     // val = String_To_Year(str);
//     // if (val == 2012) {
//     //     printf("true\n");
//     // }
//     // else {
//     //     printf("false\n");
//     // }




//     //// TEST CONVERT CHAR TO NUM HELPER ////
//     // printf("simple: \n");
//     // strcpy(str, "12125");
//     // val = toNum(str);
//     // if (val == 12125) {
//     //     printf("true \n");
//     // }
//     // else {
//     //     printf("false");
//     // }



//     //// TEST fix case ////
//     // // upper only
//     // printf("upper case one char: \n");
//     // strcpy(str, "simple case");
//     // Fix_Case(str);
//     // printf("%d\n", strcmp("Simple Case", str));

//     // // upper case middle
//     // printf("upper case middle char: \n");
//     // strcpy(str, "Simple case");
//     // Fix_Case(str);
//     // printf("%d\n", strcmp("Simple Case", str));

//     // // lower case 
//     // printf("lower case one char: \n");
//     // strcpy(str, "sImPle cAse");
//     // Fix_Case(str);
//     // printf("%d\n", strcmp("Simple Case", str));

//     // // opposite
//     // printf("opposite case: \n");
//     // strcpy(str, "sIMPLE cASE");
//     // Fix_Case(str);
//     // printf("%d\n", strcmp("Simple Case", str));

//     // // lower only
//     // printf("no upper, no lower: \n");
//     // strcpy(str, "SIMPLE CASE");
//     // Fix_Case(str);
//     // printf("%d\n", strcmp("Simple Case", str));

//     // // no upper, no lower
//     // printf("no upper, no lower: \n");
//     // strcpy(str, "123456");
//     // Fix_Case(str);
//     // printf("%d\n", strcmp("123456", str));




//     //// TEST WHITESPACE ////
//     // // int pos = 2;
//     // // shift(str, 1);
//     // // printf(str);

//     // strcpy(str, " this");
//     // printf(str);
//     // shift(str, 0);
//     // printf(str);

//     // // simple
//     // printf("simple case: \n");
//     // strcpy(str, "simple   case");
//     // Clean_Whitespace(str);
//     // printf("%d\n", strcmp("simple case", str));

//     // // leading tab
//     // printf("leading tab case: \n");
//     // strcpy(str, "\tsimple case");
//     // Clean_Whitespace(str);
//     // printf("%d\n", strcmp("simple case", str));

//     // // trailing tab
//     // printf("trailing tab case: \n");
//     // strcpy(str, "simple case\t");
//     // Clean_Whitespace(str);
//     // printf("%d\n", strcmp("simple case", str));

//     // // leading newline
//     // printf("leading new line case: \n");
//     // strcpy(str, "\nsimple case");
//     // Clean_Whitespace(str);
//     // printf("%d\n", strcmp("simple case", str));

//     // // trailing newline
//     // printf("trailing new line case: \n");
//     // strcpy(str, "simple case\n");
//     // Clean_Whitespace(str);
//     // printf("%d\n", strcmp("simple case", str));

//     // // leading whitespaces
//     // printf("leading whitespace case: \n");
//     // strcpy(str, "    simple case");
//     // Clean_Whitespace(str);
//     // printf("%d\n", strcmp("simple case", str));

//     // // trailing whitespaces
//     // printf("trailing whitespace case: \n");
//     // strcpy(str, "simple case   ");
//     // Clean_Whitespace(str);
//     // printf("%d\n", strcmp("simple case", str));
//     // printf("%s\n", str);

//     // // complicated case
//     // printf("comp case: \n");
//     // strcpy(str, "\n   simple      case   \t");
//     // Clean_Whitespace(str);
//     // printf("%d\n", strcmp("simple case", str));
//     // printf("%s", str);

//     // char character = 'a';
//     // printf("%c\n", uppercase(character));
//     // character = 'A';
//     // printf("%c\n", lowercase(character));

//     // character = '.';
//     // printf("%c\n", lowercase(character));
//     // printf("%c\n", uppercase(character));
//     // shift(str, pos, length);
//     // printf(str);
//     // str = " is this  working ?";
// }
