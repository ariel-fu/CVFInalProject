#include <stdio.h>
/*
Ariel is making structs
Ariel got waitlisted from UIUC today
Ariel is very sad
Ariel has strange music taste
Ariel needs a new spotify account to remove the curr search history
Ariel needs to focus on programming
*/
typedef struct POINT
{
    int x;
    int y;
} point;

typedef struct RECTANGLE
{
    point upperRight;
    point lowerLeft;
} rect;

point makePoint(int x, int y)
{
    point temp;
    temp.x = x;
    temp.y = y;
    return temp;
}

int main()
{
}