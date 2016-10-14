#include<stdlib.h>
#include"dllist.h"
#include<time.h>
void Insert(int t, int n, DLList* dllist)
{
	int i, ll;
	srand(time(0));
	for ( i = 0; i < n; i++)
	{
		ll = rand() % 101;
		dllist->SortedInsert(nullptr, ll);
		//printf("Thread %d:inserted key=%d\n",t,ll);
	}

}
void Remove(int t, int n, DLList* dllist)
{
	int i, keyll;
	for ( i = 0; i < n; i++)
	{
		dllist->Remove(&keyll);
	//printf("Thread %d:"removed key=%d\n",t,keyll);

	}
}