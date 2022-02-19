#include <stdio.h>

void Reverse(char *str);
int length(char *str);

int length(char *str)
{
    int length = 0;
    while (*str)
    {
        length++;
        str++;
    }
    return length;
}
void Reverse(char *str)
{

    // start with two pointers - one to the front and one to the end
    char *forP = str;
    char *endP = str;
    while (*str)
    {
        str++;
        endP++;
    }
    endP--;
    while (forP < endP)
    {
        char temp = *forP;
        *forP = *endP;
        *endP = temp;
        forP++;
        endP--;
    }
}

int main()
{
    // char c[] = "this";
    // char *str[] = c;
    // int eln = length(str);
    // printf("eln: %d", eln);

    char odd[] = {'a', 'b', 'c', 'd', 'e', '\0'};
    char *str;
    str = odd;
    Reverse(odd);
    printf("%s\n", odd);

    char even[] = "abcdefgh";
    str = even;
    Reverse(even);
    printf("%s\n", even);

    char ar[] = "arielfu";
    str = ar;
    Reverse(ar);
    printf("%s\n", ar);

    char num[] = "123456789";
    str = num;
    Reverse(num);
    printf("%s\n", num);

    char alph[] = "abcdefghijklmnopqrstuvwxyz";
    str = alph;
    Reverse(alph);
    printf("%s\n", alph);

    char special[] = "()|()";
    str = special;
    Reverse(special);
    printf("%s\n", special);

    char space[] = "-   |   ,";
    str = space;
    Reverse(space);
    printf("%s\n", space);
    return 0;
}
