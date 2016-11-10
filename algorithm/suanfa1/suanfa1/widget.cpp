#include"user_function.h"

//延时函数：延时time秒
void delay(int time)
{
	Sleep(time * 1000);
}

//产生range以内的随机数
int random_number(int range )
{
	srand(time(0));//设置种子
	return rand() % range;
}