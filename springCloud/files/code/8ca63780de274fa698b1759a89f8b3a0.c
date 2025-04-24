#include <stdio.h>
void IntToString(int i, char* s) {
    long long n = i;
    int sign = 0;
    if (n < 0) {//判断正负
        sign = 1;
        n = -n;
    }
    char temp[32];
    int index = 0;
    if (n == 0) {
        temp[index++] = '0';
    } else {//将数字转为字符并进行存储，逆序
        while (n != 0) {
            temp[index++] = (n % 10) + '0';
            n /= 10;
        }
    }
    int s_index = 0;
    if (sign) {//如果是负数，补全
        s[s_index++] = '-';
    }//进行翻转
    for (int j = index - 1; j >= 0; j--) {
        s[s_index++] = temp[j];
    }
    s[s_index] = '\0';
}
int main() {
    char buffer[32];
    IntToString(0, buffer);
    printf("%s\n", buffer);
    IntToString(123, buffer);
    printf("%s\n", buffer);
    IntToString(-456, buffer);
    printf("%s\n", buffer);
    return 0;
}