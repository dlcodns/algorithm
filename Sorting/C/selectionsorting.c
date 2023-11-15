#include<stdio.h>
#include<stdbool.h>

void swapAt(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void selectionSort(int array[], int length) {
    if (length < 2) {
        return;
    }

    for (int i = 0; i < length - 1; i++) {
        int lowest = i;
        bool swapped = false;
        for (int j = i + 1; j < length; j++) {
            if (array[lowest] > array[j]) {
                lowest = j;
                swapped = true;
            }
        }
        if (lowest != i) {
            swapAt(&array[lowest], &array[i]);
        }
        if (!swapped) return;
    }

}

int main() {
    int array[5] = { 3, 4, 2, 5, 1 };

    printf("Original: [ ");
    for (int i = 0; i < 4; i++) {
        printf("%d, ", array[i]);
    }
    printf("%d ]\n", array[4]);

    selectionSort(array, 5);

    printf("Selection sorted: [ ");
    for (int i = 0; i < 4; i++) {
        printf("%d, ", array[i]);
    }
    printf("%d ]", array[4]);

    return 0;
}