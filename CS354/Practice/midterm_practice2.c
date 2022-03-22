#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct
{

    char *name;
    int id;
} STUDENT;

void q8(STUDENT s)
{
    // Assume
    // printf("%p\n", &s)
    // prints 0x300
    printf("student address: %p, id address: %p, name address: %p\n", &s, &s.id, &s.name);
}

int main()
{
    STUDENT student;
    student.name = "Michael";
    q8(student);

    return 0;
}