#include <stdio.h>
#include <stdlib.h>
#include <math.h>

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
int str_end(char *s, char *t)
{
    int s_length = length(s);

    int t_length = length(t);

    if (t_length > s_length)
    {
        return 0;
    }
    // increment s until the spot where t would start
    // abcde
    // de
    for (int i = 0; i < s_length - t_length; i++)
    {
        s++;
    }

    while (*s)
    {
        if (*s != *t)
        {
            return 0;
        }
        s++;
        t++;
    }
    // still something in t
    if (*t)
    {
        return 0;
    }

    return 1;
}

int hex_to_int(char *s)
{
    int s_length = length(s);
    int power = s_length - 1;
    int int_equiv = 0;
    while (*s)
    {
        char curr = *s;
        int currVal = 0;
        if (*s >= '0' && *s <= '9')
        {
            currVal = *s - '0';
        }
        else if (*s >= 'a' && *s <= 'f')
        {
            currVal = *s - 'a' + 10;
        }
        else
        {
            currVal = *s - 'A' + 10;
        }

        currVal *= pow(16, power);

        int_equiv += currVal;
        s++;
        power--;
    }

    return int_equiv;
}

// void squeeze(char *s1, char *s2)
// {
//     int num_removed = 0;
//     while (*s1)
//     {
//         // check if there is a matching char in s2
//         char delete = *s1;
//         char *s2_runner = s2;
//         while (*s2_runner)
//         {
//             // found a matching char?
//             if (*s1 == *s2_runner)
//             {
//                 // remove the curr char
//                 *s1 = *(s1 + 1);
//                 num_removed++;
//                 break;
//             }
//             s2_runner++;
//         }
//         s1++;
//     }
//     s1 -= num_removed;
//     *s1 = '\0';
// }

int any(char *s1, char *s2)
{
    int index = 0;
    while (*s1)
    {
        char *s2_runner = s2;
        while (*s2_runner)
        {
            if (*s1 == *s2_runner)
            {
                return index;
            }
            s2_runner++;
        }
        index++;
        s1++;
    }
    return -1;
}

void main()
{
    // char s[] = "abcde";
    // char t[] = "de";
    // printf("%d = 1\n", str_end(s, t));

    // char snot[] = "abcde";
    // char tnot[] = "bc";
    // printf("%d = 0\n", str_end(snot, tnot));

    // char sext[] = "abcde";
    // char text[] = "def";
    // printf("%d = 0\n", str_end(sext, text));

    // char s[] = "E0";
    // int int_equiv = hex_to_int(s);
    // printf("%d = 224", int_equiv);

    // char s1[] = "efgo";
    // char s2[] = "abcd";
    // printf("before squeezed: %s\n", s1);
    // squeeze(s1, s2);
    // printf("after squeezed: %s\n", s1);

    char s1[] = "xyzb";
    char s2[] = "abcdefgh";
    int index = any(s1, s2);
    printf("%d = 0\n", index);
}
