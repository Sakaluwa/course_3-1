#include<stdlib.h>
#include"copyright.h"
#include"dllist.h"
#include"system.h"
#include<time.h>
//线程t: 链表dllist中插入n个链表元素
void Insert(int t, int n, DLList* dllist)
{
	int i, key;
	srand(time(0));//设置时间为种子
	for ( i = 0; i < n; i++)
	{
		key = rand() % 101;//产生0~100的随机数的优先级
		dllist->SortedInsert(NULL, key);
		printf("线程 %d：插入元素 key=%d;\n",t,key);
	}

}
//线程t: 链表ddlist中移除n个链表元素
void Remove(int t, int n, DLList* dllist)
{
	int i, key;
	for ( i = 0; i < n; i++)
	{
		dllist->Remove(&key);
	    printf("线程 %d：移除元素 key=%d;\n",t,key);//key 返回移除元素的key

	}
}
