#include"user_function.h"

/*盒分形问题：度为n的盒分形的规模为3^(n-1)*3^(n-1)
*递归求解
*/
#define MAX 730		//<=7的盒分形规模为3^6*3^6=729*729
char maps[MAX][MAX];

int mypow(int n, int m)
{
	if (m == 0)
		return 1;
	else if (m == 1)
	{
		return n;
	}
	else
	{
		for (int i = 1; i < m; i++)
		{
			n *= 3;
		}
	}
	return n;


}
void BoxFractal(int n, int x, int y)
{
	if (n == 1)
	{
		maps[x][y] = 'X';
	}
	else
	{
		//n-1度盒分形的规模m
		int m = mypow(3, n - 2);
		//左上方的n-1度盒分形
		BoxFractal(n - 1, x, y);
		//右上方的n-1度盒分形
		BoxFractal(n - 1, x + 2 * m, y);
		//中间的n-1度盒分形
		BoxFractal(n - 1, x + m, y + m);
		//左下方的n-1度盒分形
		BoxFractal(n - 1, x, y + 2 * m);
		//右下方的n-1度盒分形
		BoxFractal(n - 1, x + 2 * m, y + 2 * m);

	}

}
void box_fractal() {
	int n;
	int input[100], count = 0;
	while (true)
	{
		cin >> input[count++];
		if (input[count - 1] == -1)
			break;
	}
	count = 0;
	n = input[count++];
	while (n != -1)
	{
		//盒分形的规模
		int size = mypow(3, n - 1);
		//初始化
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				maps[i][j] = ' ';
			}
			maps[i][size] = '\0';//字符数组尾部结束符
		}
		//分形
		BoxFractal(n, 0, 0);
		//输出分形结果
		for (int i = 0;i < size;i++)
			printf("%s\n", maps[i]);
		cout << '-' << endl;
		n = input[count++];

	}
}