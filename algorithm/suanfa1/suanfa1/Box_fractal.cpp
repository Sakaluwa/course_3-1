#include"user_function.h"

/*�з������⣺��Ϊn�ĺз��εĹ�ģΪ3^(n-1)*3^(n-1)
*�ݹ����
*/
#define MAX 730		//<=7�ĺз��ι�ģΪ3^6*3^6=729*729
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
		//n-1�Ⱥз��εĹ�ģm
		int m = mypow(3, n - 2);
		//���Ϸ���n-1�Ⱥз���
		BoxFractal(n - 1, x, y);
		//���Ϸ���n-1�Ⱥз���
		BoxFractal(n - 1, x + 2 * m, y);
		//�м��n-1�Ⱥз���
		BoxFractal(n - 1, x + m, y + m);
		//���·���n-1�Ⱥз���
		BoxFractal(n - 1, x, y + 2 * m);
		//���·���n-1�Ⱥз���
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
		//�з��εĹ�ģ
		int size = mypow(3, n - 1);
		//��ʼ��
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				maps[i][j] = ' ';
			}
			maps[i][size] = '\0';//�ַ�����β��������
		}
		//����
		BoxFractal(n, 0, 0);
		//������ν��
		for (int i = 0;i < size;i++)
			printf("%s\n", maps[i]);
		cout << '-' << endl;
		n = input[count++];

	}
}