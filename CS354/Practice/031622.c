#include <stdio.h>
#include <stdlib.h>

void lower(char *s)
{
    while (*s)
    {
        (*s >= 'A' && *s <= 'Z') ? (*s = *s - 'A' + 'a') : (*s = *s);
        s++;
    }
}

void main()
{
    char s[] = "abcd";
    lower(s);
    printf("%s\n", s);
}