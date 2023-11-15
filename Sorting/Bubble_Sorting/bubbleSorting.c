#include<stdio.h>
#include<stdbool.h>

void swapAt(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void bubbleSort(int array[], int length) {
    if (length < 2) {
        return;
    }

    for (int i = 4; i >= 0; i--) {
        bool swapped = false;
        for (int j = 0; j < i; j++) {
            if (array[j] > array[j + 1]) {
                swapAt(&array[j], &array[j + 1]);
                swapped = true;
            }
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

    bubbleSort(array, 5);

    printf("Bubble sorted: [ ");
    for (int i = 0; i < 4; i++) {
        printf("%d, ", array[i]);
    }
    printf("%d ]", array[4]);

    return 0;
}