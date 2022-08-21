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

int main()
{
    char ariel[1024] = "ariel";
    char *s = ariel;
    char fu[] = " fu";
    char *t = fu;

    strcat(s, t);

    printf("Value: %s", s);

    return 0;
}
