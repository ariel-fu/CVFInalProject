#include <stdio.h>
#include <stdlib.h>

void Print_Matrix(int **M, int rows, int columns)
{

    for (int i = 0; i < rows; ++i)
    {
        printf("| ");
        for (int j = 0; j < columns; ++j)
            printf("%d\t", M[i][j]);
        printf(" |\n");
    }
    printf("\n");
    return;
}

void Free_Matrix(int **M, int r)
{
    // complete this function
    for (int i = 0; i < r; i++)
    {
        free(M[i]);
    }
    free(M);
    return;
}

int **Create_RxC_Index_Matrix(int r, int c)
{
    // complete this function
    // get r rows
    int **matrix = malloc(r * sizeof(int *));
    // get c cols
    for (int i = 0; i < r; i++)
    {
        matrix[i] = malloc(c * sizeof(int *));
    }

    // populate the values with M[i][j] = 10*(i+1) + (j+1);
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            matrix[i][j] = 10 * (i + 1) + (j + 1);
        }
    }

    return matrix; // replace null with the matrix you create
}

int main()
{
    printf("___________________________TEST 1 x 1_____________________________________\n\n");
    int **M11 = Create_RxC_Index_Matrix(1, 1);
    Print_Matrix(M11, 1, 1);
    Free_Matrix(M11, 1);
    // printf("\tFREED \n\n");
    // Print_Matrix(M11, 1, 1);
    printf("___________________________TEST 1 x 2_____________________________________\n\n");

    int **M12 = Create_RxC_Index_Matrix(1, 2);
    Print_Matrix(M12, 1, 2);
    Free_Matrix(M12, 1);
    // printf("\t FREED \n\n");
    // Print_Matrix(M12, 1, 2);
    printf("____________________________TEST 1 x 3____________________________________\n\n");
    int **M13 = Create_RxC_Index_Matrix(1, 3);
    Print_Matrix(M13, 1, 3);
    Free_Matrix(M13, 1);
    // printf("\t FREED \n\n");
    // Print_Matrix(M13, 1, 3);
    printf("____________________________TEST 1 x 4____________________________________\n\n");

    int **M14 = Create_RxC_Index_Matrix(1, 4);
    Print_Matrix(M14, 1, 4);
    Free_Matrix(M14, 1);
    // printf("\t FREED \n\n");
    // Print_Matrix(M14, 1, 4);
    printf("____________________________TEST 1 x 5____________________________________\n\n");

    int **M15 = Create_RxC_Index_Matrix(1, 5);
    Print_Matrix(M15, 1, 5);
    Free_Matrix(M15, 1);
    // printf("\t FREED \n\n");
    // Print_Matrix(M15, 1, 5);
    printf("____________________________TEST 2 x 5____________________________________\n\n");

    int **M25 = Create_RxC_Index_Matrix(2, 5);
    Print_Matrix(M25, 2, 5);
    Free_Matrix(M25, 2);
    // printf("\t FREED \n\n");
    // Print_Matrix(M25, 2, 5);
    printf("____________________________TEST 3 x 4____________________________________\n\n");

    int **M34 = Create_RxC_Index_Matrix(3, 4);
    Print_Matrix(M34, 3, 4);
    Free_Matrix(M34, 3);
    // printf("\t FREED \n\n");
    // Print_Matrix(M34, 3, 4);
    printf("____________________________TEST 4 x 4____________________________________\n\n");

    int **M44 = Create_RxC_Index_Matrix(4, 4);
    Print_Matrix(M44, 4, 4);
    Free_Matrix(M44, 4);
    // printf("\t FREED \n\n");
    // Print_Matrix(M44, 4, 4);
    printf("____________________________TEST 5 x 1____________________________________\n\n");

    int **M51 = Create_RxC_Index_Matrix(5, 1);
    Print_Matrix(M51, 5, 1);
    Free_Matrix(M51, 5);
    // Print_Matrix(M51, 5, 1);
    printf("____________________________TEST 5 x 2____________________________________\n\n");

    int **M52 = Create_RxC_Index_Matrix(5, 2);
    Print_Matrix(M52, 5, 2);
    Free_Matrix(M52, 2);
    // printf("\t FREED \n\n");
    // Print_Matrix(M44, 4, 4);
    printf("____________________________TEST 5 x 3____________________________________\n\n");

    int **M53 = Create_RxC_Index_Matrix(5, 3);
    Print_Matrix(M53, 5, 3);
    Free_Matrix(M53, 5);
    // printf("\t FREED \n\n");
    // Print_Matrix(M44, 4, 4);
    printf("____________________________TEST 5 x 4____________________________________\n\n");

    int **M54 = Create_RxC_Index_Matrix(5, 4);
    Print_Matrix(M54, 5, 4);
    Free_Matrix(M54, 5);
    // printf("\t FREED \n\n");
    // Print_Matrix(M44, 4, 4);
    printf("____________________________TEST 6 x 1____________________________________\n\n");

    int **M61 = Create_RxC_Index_Matrix(6, 1);
    Print_Matrix(M61, 6, 1);
    Free_Matrix(M61, 6);
    // printf("\t FREED \n\n");
    // Print_Matrix(M44, 4, 4);
    printf("____________________________TEST 6 x 2____________________________________\n\n");

    int **M62 = Create_RxC_Index_Matrix(6, 2);
    Print_Matrix(M62, 6, 2);
    Free_Matrix(M62, 6);
    // printf("\t FREED \n\n");
    // Print_Matrix(M44, 4, 4);
    printf("____________________________TEST 7 x 8____________________________________\n\n");

    int **M78 = Create_RxC_Index_Matrix(7, 8);
    Print_Matrix(M78, 7, 8);
    Free_Matrix(M78, 7);
    // Print_Matrix(M78, 7, 8);
    printf("____________________________TEST 9 x 9____________________________________\n\n");

    int **M99 = Create_RxC_Index_Matrix(9, 9);
    Print_Matrix(M99, 9, 9);
    Free_Matrix(M99, 9);
    // printf("\t FREED \n\n");
    // Print_Matrix(M44, 4, 4);

    return 0;
}
