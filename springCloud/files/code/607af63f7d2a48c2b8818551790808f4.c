#include <stdio.h>
#define MAX_SIZE 100

typedef struct {
    int data[MAX_SIZE];
    int length;
} SeqList;

// 逆置顺序表元素
void reverseList(SeqList *list) {
    int i, j, temp;
    for (i = 0, j = list->length - 1; i < j; i++, j--) {
        temp = list->data[i];
        list->data[i] = list->data[j];
        list->data[j] = temp;
    }
}

// 打印顺序表
void printList(SeqList list) {
    for (int i = 0; i < list.length; i++) {
        printf("%d ", list.data[i]);
    }
    printf("\n");
}

int main() {
    SeqList list;
    list.length = 5;
    for (int i = 0; i < list.length; i++) {
        list.data[i] = i + 1;
    }
    printf("逆置前: ");
    printList(list);
    reverseList(&list);
    printf("逆置后: ");
    printList(list);
    return 0;
}