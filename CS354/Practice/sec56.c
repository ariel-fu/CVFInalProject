#include <stdio.h>

// concats t to the end of s
void strcat(char *s, char *t)
{

    for (int i = 0; *s; i++)
    {
        s++;
    }
    // concat t to s
    for (int i = 0; *t; i++)
    {
        *s = *t;
        // printf("s: %c \t t: %c", *s, *t);

        s++;
        t++;
    }

    *s = '\0';
}

int length(char *s)
{
    int length = 0;
    while (*s)
    {
        length++;
        s++;
    }
    return length;
}

int strend(char *s, char *t)
{
    int lengthT = length(t);
    int lengthS = length(s);
    if (lengthT > lengthS)
    {
        return 0;
    }
    else
    {
        // increment to where the string t would technically start
        for (int i = 0; i < (lengthS - lengthT); i++)
        {
            s++;
        }
    }
    // compare each char
    while (*s)
    {
        if (*s != *t)
        {
            return 0;
        }
        // increment oboth strings
        s++;
        t++;
    }
    if (*s != *t)
    {
        return 0;
    }
    // the strings are equal
    return 1;
}

void strncpy(char *s, char *t, int n)
{
    for (int i = 0; i < n; i++)
    {
        *t = *s;
        s++;
        t++;
    }
}

int main()
{
    char ariel[1024] = "ariel";
    char fu[] = " fu";
    char x[] = "x";
    char toolong[] = " fuxx";
    char tooshort[] = " f";
    strcat(ariel, fu);

    printf("Append: %s\n", ariel);

    int comp = strend(ariel, fu);
    printf("Comp fu: %d\n", comp);

    comp = strend(ariel, x);
    printf("Comp x: %d\n", comp);

    comp = strend(ariel, toolong);
    printf("Comp too long: %d\n", comp);

    comp = strend(ariel, tooshort);
    printf("Comp too short: %d\n", comp);

    char arielcopy[1024] = "abc12";
    strncpy(ariel, arielcopy, 3);
    printf("copy over 2 %s: \n", arielcopy);


    return 0;
}
