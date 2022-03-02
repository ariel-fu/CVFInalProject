#include <stdio.h>
/*
Name: Ariel Fu
NetID: 908 168 5910
*/
struct point
{
    int x;
    int y;
};

struct rect
{
    // lower left
    struct point pt1;
    // upper right
    struct point pt2;
};

// function prototypes
void Enlarge_Rectangle(struct rect *r, struct point p);
struct point makepoint(int x, int y);

void Enlarge_Rectangle(struct rect *r, struct point p)
{
    int pX = p.x;
    int pY = p.y;
    // check lower left x
    int lowerX = r->pt1.x;
    // if the point's x is less than the lower x, change the x of r
    if (lowerX > pX)
    {
        r->pt1.x = pX;
    }

    // check lower left y
    int lowerY = r->pt1.y;
    // if the point's y is lower than the lower y, change the y of r
    if (lowerY > pY)
    {
        r->pt1.y = pY;
    }

    // check upper right x
    int upperX = r->pt2.x;
    // if the point's x is greater than the upper x, change the x of r
    if (upperX < pX)
    {
        r->pt2.x = pX;
    }

    // check upper right y
    int upperY = r->pt2.y;
    // if the point's y is greater than the upper y, change the y of r
    if (upperY < pY)
    {
        r->pt2.y = pY;
    }
}

// helper method to create a point
struct point makepoint(int x, int y)
{
    struct point temp;
    temp.x = x;
    temp.y = y;
    return temp;
}

int main()
{
    struct rect r;
    struct point pt;
    struct point lowerR;
    struct point upperR;

    // increase upper y
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(2, 4);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    struct rect *pointer = &r;
    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");

    // increase upper x
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(4, 2);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");

    // increase both
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(4, 4);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");

    // increase upper x
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(0, 4);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");

    // increase upper x
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(4, 0);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");

    // lower half

    // increase upper x
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(-2, 0);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");

    // increase lower y
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(0, -2);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");

    // increase lower x and y
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(-2, -2);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");

    // increase lower x
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(-2, 2);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");

    // increase lower y
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(2, -2);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");

    // increase both
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(-2, 4);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");

    // increase upper x
    lowerR = makepoint(0, 0);
    upperR = makepoint(2, 2);
    pt = makepoint(4, -2);

    // // set r
    r.pt1 = lowerR;
    r.pt2 = upperR;
    printf("Original: pt1: (%d, %d) \t pt2: (%d, %d) \t new : (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y, pt.x, pt.y);
    printf("___________________________________________\n");

    // // enlarge
    Enlarge_Rectangle(pointer, pt);
    // // print
    printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1.x, r.pt1.y, r.pt2.x, r.pt2.y);
    printf("------------------------------------------------------\n");
    // enlarge upper - both
    // lowerR = makepoint(1, 1);
    // upperR = makepoint(2, 2);
    // pt = makepoint(3, 3);

    // // set r
    // r.pt1 = lowerR;
    // r.pt2 = upperR;
    // printf("Original: pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1, r.pt2);
    // printf("___________________________________________\n");

    // struct rect *pointer = &r;
    // // enlarge
    // Enlarge_Rectangle(pointer, pt);
    // // print
    // printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1, r.pt2);
    // printf("-------------------------\n");

    // // enlarge lower - both
    // lowerR = makepoint(1, 1);
    // upperR = makepoint(2, 2);
    // pt = makepoint(0, 0);

    // // set r
    // r.pt1 = lowerR;
    // r.pt2 = upperR;

    // // enlarge
    // Enlarge_Rectangle(pointer, pt);
    // // print
    // printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1, r.pt2);
    // printf("-------------------------\n");

    // // enlarge upper - x
    // lowerR = makepoint(1, 1);
    // upperR = makepoint(2, 2);
    // pt = makepoint(3, 2);

    // // set r
    // r.pt1 = lowerR;
    // r.pt2 = upperR;

    // // enlarge
    // Enlarge_Rectangle(pointer, pt);
    // // print
    // printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1, r.pt2);
    // printf("-------------------------\n");

    // // enlarge upper - y
    // lowerR = makepoint(1, 1);
    // upperR = makepoint(2, 2);
    // pt = makepoint(2, 3);

    // // set r
    // r.pt1 = lowerR;
    // r.pt2 = upperR;

    // // enlarge
    // Enlarge_Rectangle(pointer, pt);
    // // print
    // printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1, r.pt2);
    // printf("-------------------------\n");

    // // enlarge lower - x
    // lowerR = makepoint(1, 1);
    // upperR = makepoint(2, 2);
    // pt = makepoint(0, 1);

    // // set r
    // r.pt1 = lowerR;
    // r.pt2 = upperR;

    // // enlarge
    // Enlarge_Rectangle(pointer, pt);
    // // print
    // printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1, r.pt2);
    // printf("-------------------------\n");

    // // enlarge lower - y
    // lowerR = makepoint(1, 1);
    // upperR = makepoint(2, 2);
    // pt = makepoint(1, 0);

    // // set r
    // r.pt1 = lowerR;
    // r.pt2 = upperR;

    // // enlarge
    // Enlarge_Rectangle(pointer, pt);
    // // print
    // printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1, r.pt2);
    // printf("-------------------------\n");

    // // shrink
    // lowerR = makepoint(0, 0);
    // upperR = makepoint(1, 1);
    // pt = makepoint(2, 3);

    // // set r
    // r.pt1 = lowerR;
    // r.pt2 = upperR;

    // // enlarge
    // Enlarge_Rectangle(pointer, pt);
    // pt = makepoint(-1, 1);
    // Enlarge_Rectangle(pointer, pt);
    // // print
    // printf("pt1: (%d, %d) \t pt2: (%d, %d)\n", r.pt1, r.pt2);
    // printf("-------------------------\n");
    return 0;
}