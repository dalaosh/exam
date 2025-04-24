#include <stdio.h>

void IntToString(int i, char* s) {
    long long n = i; // 使用long long避免绝对值溢出
    int sign = 0;
    if (n < 0) {
        sign = 1;
        n = -n;
    }
    char temp[32]; // 临时存储逆序的数字字符
    int index = 0;
    
    // 处理i为0的特殊情况
    if (n == 0) {
        temp[index++] = '0';
    } else {
        // 提取每一位数字，逆序存入temp
        while (n != 0) {
            temp[index++] = (n % 10) + '0';
            n /= 10;
        }
    }
    
    int s_index = 0;
    // 处理符号
    if (sign) {
        s[s_index++] = '-';
    }
    // 将逆序的数字字符反转，复制到s中
    for (int j = index - 1; j >= 0; j--) {
        s[s_index++] = temp[j];
    }
    // 添加字符串结束符
    s[s_index] = '\0';
}

// 示例用法
int main() {
    char buffer[32];
    IntToString(0, buffer);
    printf("%s\n", buffer);        // 输出 "0"
    
    IntToString(123, buffer);
    printf("%s\n", buffer);        // 输出 "123"
    
    IntToString(-456, buffer);
    printf("%s\n", buffer);        // 输出 "-456"
    
    IntToString(2147483647, buffer);
    printf("%s\n", buffer);        // 输出 "2147483647"
    
    IntToString(-2147483648, buffer); // 测试int最小值
    printf("%s\n", buffer);        // 输出 "-2147483648"
    
    return 0;
}