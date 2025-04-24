#include <stdio.h>
#include <stdlib.h>

// 定义单链表节点结构
typedef struct Node {
    int data;
    struct Node* next;
} Node;

// 在指定位置插入新节点
Node* insertNode(Node* head, int position, int value) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = value;

    if (position == 0) {
        newNode->next = head;
        return newNode;
    }

    Node* current = head;
    for (int i = 0; i < position - 1 && current != NULL; i++) {
        current = current->next;
    }

    if (current == NULL) {
        free(newNode);
        return head;
    }

    newNode->next = current->next;
    current->next = newNode;
    return head;
}

// 打印链表
void printList(Node* head) {
    Node* current = head;
    while (current != NULL) {
        printf("%d ", current->data);
        current = current->next;
    }
    printf("\n");
}

int main() {
    Node* head = NULL;
    head = insertNode(head, 0, 1);
    head = insertNode(head, 1, 2);
    head = insertNode(head, 1, 3);
    printList(head);
    return 0;
}