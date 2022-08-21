#include <stdio.h>
#include <string.h>

typedef struct STRING{
    char *string;
} STRING;

STRING reverse(STRING temp){

    char str[500];
    strncpy(str, temp.string, 20);
    char *frontPtr = str;
    char *endPtr = str;
    while(*endPtr){
        endPtr++;
    }
    endPtr--;

    while(frontPtr < endPtr){
        char temp = *frontPtr;
        *frontPtr = *endPtr;
        *endPtr = temp;
        frontPtr++;
        endPtr--;
    }
    STRING strReversed;
    strReversed.string = str;
    // strncpy(strReversed.string, str, 500);
    return strReversed;
}



void main(){

    char z[3];
    strncpy(z, "abc", 3);
    printf("z: %s\n", z);
    char *y;
    y = z;
    *y = 'k';
    printf("y: %s\n", y);
    z[0] = 'f';
    printf("z: %s\n", z);

    STRING x;
    x.string = "abcdef";
    // strncpy(x.string, "arielfu", 500);
    STRING temp = reverse(x);
    printf("rev: %s\n", temp.string);
    temp = reverse(temp);
    printf("rev2: %s\n", temp.string);
}