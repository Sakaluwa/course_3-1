#include"user_function.h"

//��ʱ��������ʱtime��
void delay(int time)
{
	Sleep(time * 1000);
}

//����range���ڵ������
int random_number(int range )
{
	srand(time(0));//��������
	return rand() % range;
}