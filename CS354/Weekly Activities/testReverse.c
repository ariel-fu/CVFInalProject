#include <stdio.h>

void Reverse(char *str);

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
    {
        char str[] = "lkjwefkljwelkwejfl wefiowhglwhglkhgljk efkleke";
    }
    char str[240] = {'a', 'b', 'c', 'd', 'e', '\0'};
    // char *str;
    // str = odd;
    Reverse(str);
    printf("%s\n", str);

    char even[] = {'a', 'b', 'c', 'd', '\0'};

    Reverse(even);
    printf("%s\n", even);

    char ar[] = "arielfu";

    Reverse(ar);
    printf("%s\n", ar);

    char num[] = "123456789";

    Reverse(num);
    printf("%s\n", num);

    char alph[] = "abcdefghijklmnopqrstuvwxyz";

    Reverse(alph);
    printf("%s\n", alph);

    char special[] = "()|()";

    Reverse(special);
    printf("%s\n", special);

    char space[] = "-   |   ,";

    Reverse(space);
    printf("%s\n", space);
    return 0;
}
