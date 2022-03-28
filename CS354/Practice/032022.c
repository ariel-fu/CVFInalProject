#include <stdio.h>
#include <stdlib.h>

void main()
{
    char g[] = "geeksforgeeks";
    char six = g[6];
    int intsix = (int)six;
    char eight = g[8];
    int inteight = (int)eight;
    printf("%s", g + intsix - inteight);

    printf(5 + "GeeksQuiz");
}