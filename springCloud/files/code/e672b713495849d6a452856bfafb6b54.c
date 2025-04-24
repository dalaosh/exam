#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 100

// 定义栈结构
typedef struct Stack {
    int data[MAX_SIZE];
    int top;
} Stack;

// 初始化栈
void initStack(Stack* stack) {
    stack->top = -1;
}

// 判断栈是否为空
int isEmpty(Stack* stack) {
    return stack->top == -1;
}

// 入栈操作
void push(Stack* stack, int value) {
    if (stack->top == MAX_SIZE - 1) {
        printf("Stack overflow\n");
        return;
    }
    stack->data[++stack->top] = value;
}

// 出栈操作
int pop(Stack* stack) {
    if (isEmpty(stack)) {
        printf("Stack underflow\n");
        return -1;
    }
    return stack->data[stack->top--];
}

int main() {
    Stack stack;
    initStack(&stack);
    push(&stack, 1);
    push(&stack, 2);
    printf("Popped: %d\n", pop(&stack));
    printf("Popped: %d\n", pop(&stack));
    return 0;
}