#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Assumes little endian
void printBits(int value)
{
    char buffer[16];
    itoa(value, buffer, 2);
    printf("%s\n", buffer);
}

// when passing to a method, x and y will use the same address
void foo1(int x)
{
    printf("address of x (%d): 0x%x\n", x, &x);
}

void foo2(int y)
{
    printf("address of y (%d): 0x%x\n", y, &y);
}

// need a pointer to a pointer that points to the string to swap strings?
void switch_double_pointers_str(char **obj1, char **obj2)
{
    printf("original values: (%s, %s)\n", *obj1, *obj2);
    printf("original address: (0x%x, 0x%x)\n", &obj1, &obj2);
    char *temp = *obj1;
    *obj1 = *obj2;
    *obj2 = temp;
    printf("swapped values: (%s, %s)\n", *obj1, *obj2);
    printf("swapped address: (0x%x, 0x%x)\n", &obj1, &obj2);
}

// this only swaps the first char because the pointer points to the first char
void switch_single_pointers_str(char *obj1, char *obj2)
{
    printf("original values: (%s, %s)\n", obj1, obj2);
    printf("original address: (0x%x, 0x%x)\n", &obj1, &obj2);
    char temp = *obj1;
    *obj1 = *obj2;
    *obj2 = temp;
    printf("swapped values: (%s, %s)\n", obj1, obj2);
    printf("swapped address: (0x%x, 0x%x)\n", &obj1, &obj2);
}

// whether it is single or double pointers, the values are swapped
void switch_single_pointers_int(int *obj1, int *obj2)
{
    printf("original values: (%d, %d)\n", *obj1, *obj2);
    printf("original address: (0x%x, 0x%x)\n", &obj1, &obj2);
    int temp = *obj1;
    *obj1 = *obj2;
    *obj2 = temp;
    printf("swapped values: (%d, %d)\n", *obj1, *obj2);
    printf("swapped address: (0x%x, 0x%x)\n", &obj1, &obj2);
}

// does the wildcard only work with spaces? why?
void wildcard()
{
    printf("-     - (5 spaces) \n");
    printf("-%*s-\n", 5, " ");
    printf("-%*s-\n", 5, "*");
}
/* ------------- PAST QUESTIONS ------------- */

void count_addresses()
{
    int int_arr[] = {1, 2, 3, 4};
    int *start_index = int_arr;
    int *end_index = int_arr + 3;

    char *char_start = (char *)start_index;
    char *char_end = (char *)end_index;
    printf("addresses: 0x%x --> 0x%x\n", start_index, end_index);
    // count by 4 instead of 1 because of int?
    printf("int difference in memories: %d\n", (end_index - start_index));
    // count by 1 because of char?
    printf("char difference in memories: %d\n", (char_end - char_start));
}

void cast_int_ptr()
{
    // 00000001 00000001
    int a = 21845;
    int *a_ptr = &a;
    printf("original binary rep: \n");
    printBits(*a_ptr);

    char *char_ptr = (char *)a_ptr;
    // 00000001 00000001 -> 0000001 00000010?
    char_ptr[0] = 2;
    printf("change [0] = 2: \n");
    printBits(*a_ptr);

    // reset value
    a = 257;
    // 00000001 00000001 -> 0000011 00000001?
    char_ptr[1] = 3;
    printf("change [1] = 3: \n");
    // padding
    printf("00000");
    printBits(*a_ptr);
}

void amp_difference()
{
    int arr[] = {1, 2, 3, 4};

    // gets the address of arr and moves to 16 bytes/past the array?
    int *ptr1 = &arr + 1;
    // gets the address of arr and moves 4 bytes/to next array element?
    int *ptr2 = arr + 1;

    printf("arr: %p\n", arr);
    // prints out garbage value because address is not intialized?
    printf("arr1: %p, %d\n", ptr1, *ptr1);
    printf("arr2: %p, %d\n", ptr2, *ptr2);
}

void cast_increment()
{
    char *c_arr = "hello ariel";
    int *i_arr = (int *)c_arr;
    // increment by sizeof(type) instead of sizeof(pointer)?
    c_arr += 1;
    i_arr += 1;
    printf("c arr: %s\n", c_arr);
    // cast back to a char pointer to print
    printf("i arr: %s", (char *)i_arr);

    // are there types of illegal casting?
}

void struct_ptr_casting()
{
    // similar structs
    struct person
    {
        char *name;
        int age;
    };
    struct human
    {
        char *name;
        int age;
    };
    // create a human
    char name_h[] = "ariel the human";
    struct human ariel_h = {name_h, 18};
    // init a pointer to the human
    struct human *ariel_h_ptr = &ariel_h;
    // 1: ptr to the struct points to the 1st member of the struct?
    printf("ptr: %p | orig: %p %c\n", ariel_h_ptr, &(ariel_h.name));

    // cast the human to a pointer
    struct person *ariel_cast = (struct person *)ariel_h_ptr;

    // 2: cast to a person works?!?!?!
    printf("ariel the person: %s, %d\n", ariel_cast->name, ariel_cast->age);
}

void main()
{
    /* # of memory addresses are counted differently based on the pointer type? */
    // cast_int_ptr();

    /* convering int * to char * results in splitting the int into x arrays of length 8? */
    // count_addresses();

    /* difference between &ptr+1 and ptr+1? */
    // amp_difference();

    /* incrementing different types is different - 4 bytes for pointers in 32-bit processor and 8 bytes for pointers in 64-bit processor */
    // cast_increment();

    /* how does casting between structs work? how does the compiler know what is the name and what is the age?
    is it by coincidence because the struct members are in the same order and of the same type? */
    // struct_ptr_casting();

    // ----------------------------------------------//
    /** Past problems **/
    // ----------------------------------------------//
    // wildcard();

    // int int_obj1 = 15;
    // int int_obj2 = 20;
    // switch_single_pointers_int(&int_obj1, &int_obj2);

    // char *obj1 = "abc";
    // char *obj2 = "def";
    // switch_single_pointers_str(obj1, obj2);

    // char *obj1 = "abc";
    // char *obj2 = "def";
    // switch_double_pointers_str(&obj1, &obj2);
    // ----------------------------------------------//

    // int x = 1;
    // int y = 2;
    // foo1(x);
    // foo2(y);
    // ----------------------------------------------//
}