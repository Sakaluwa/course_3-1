#include"user_function.h"

int ansa[10], ansb[10];//

void Count(int x, int ans[], int times)
{
	if (x <= 0)
		return;
	int r = x % 10;
	int p = x / 10;
	for (int i = 1; i <= r; i++)
	{
		ans[i] += times;//0~9出现的次数
	}
	while (p)
	{
		ans[p % 10] += (r + 1)*times;
		p /= 10;
	}
	for (int i = 0; i <= 9; i++)
	{
		ans[i] += times*(x / 10);
	}
	times *= 10;
	Count(x / 10 - 1, ans, times);
}
void counting_number()
{
	int a, b;
	int aa[100], bb[100], count = 0;
	while (scanf_s("%d%d", &a, &b), a || b)
	{
		aa[count] = a;
		bb[count] = b;
		count++;
	}
	aa[count] = 0;
	bb[count] = 0;


	count = 0;
	while (aa[count] || bb[count])//当a,b同时为0时跳出循环
	{
		a = aa[count];
		b = bb[count];
		memset(ansa, 0, sizeof(ansa));//清零，初始化
		memset(ansb, 0, sizeof(ansb));//清零，初始化
		if (a > b)
			swap(a, b);//交换a,b值，统一化处理
		a--;
		Count(a, ansa, 1);
		Count(b, ansb, 1);
		for (int i = 0; i <= 9; i++)
		{
			printf_s("%d", ansb[i] - ansa[i]);
			if (i != 9)printf_s(" ");
		}
		printf_s("\n");
		++count;

	}
}