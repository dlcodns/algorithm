#include<stdio.h>
#include<stdbool.h>

void swapAt(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void insertionSort(int array[], int length) {
    if (length < 2) {
        return;
    }

    for (int i = 1; i < length; i++) {
        for (int j = i; j >= 1; j--) {
            if (array[j] < array[j - 1]) {
                swapAt(&array[j], &array[j - 1]);
            }
            else {
                break;
            }
        }
    }
}

int main() {
    int array[5] = { 3, 4, 2, 5, 1 };

    printf("Original: [ ");
    for (int i = 0; i < 4; i++) {
        printf("%d, ", array[i]);
    }
    printf("%d ]\n", array[4]);

    insertionSort(array, 5);

    printf("Insertion sorted: [ ");
    for (int i = 0; i < 4; i++) {
        printf("%d, ", array[i]);
    }
    printf("%d ]", array[4]);

    return 0;
}