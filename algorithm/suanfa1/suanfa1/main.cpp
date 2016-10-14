#include "user_function.h"
#include<time.h>

int main() {
	int i;
	srand(time(0));//设置种子
	/*输出10个随机数*/
	for ( i = 0; i < 100; i++)
	{
		cout << (rand()%101) << endl;
	}
	delay(1000);
	return 0;
}