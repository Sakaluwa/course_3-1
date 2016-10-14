#include<stdlib.h>
#include"copyright.h"
#include"dllist.h"
#include"system.h"
#include<time.h>
//线程t: ddlist中插入n个链表元素
void Insert(int t, int n, DLList* dllist)
{
	int i, ll;
	srand(time(0));//设置时间为种子
	for ( i = 0; i < n; i++)
	{
		ll = rand() % 101;//产生0~100的随机数
		dllist->SortedInsert(NULL, ll);
		printf("Thread %d:inserted key=%d\n",t,ll);
	}

}
//线程t: ddlist中移除n个链表元素
void Remove(int t, int n, DLList* dllist)
{
	int i, keyll;
	for ( i = 0; i < n; i++)
	{
		dllist->Remove(&keyll);
	    printf("Thread %d:removed key=%d\n",t,keyll);//keyll 返回移除元素的key

	}
}