#include"copyright.h"//版权（著作权）头文件
#include"dllist.h"
#include"system.h"//系统头文件
//初始化一个链表元素（元素项，优先级）
DLLElement::DLLElement(void* itemPtr, int sortKey)
{
	item = itemPtr;
	key = sortKey;
	next = NULL;
	prev = NULL;//NULL在实际的底层代码中就是0
}

DLList::DLList()//初始化链表
{
	first = NULL;
	last = NULL;
	err_type=0;//错误类型为0表示未设置线程跳转
}
DLList::DLList(int type)
{
	first=NULL;
	last=NULL;
	err_type=type;
}
DLList::~DLList()//释放链表
{
	//移除链表中的所有元素
	while (Remove(NULL)!=NULL);//此时第一个NULL为一个int*
	
}
void DLList::Prepend(void* item)//添加元素到链表头(set key=min_key-1)
{
	DLLElement *element = new DLLElement(item, 0);
	if (IsEmpty())
	{
		first = element;
		last = element;
	}
	else
	{
		element->key = first->key - 1;
		element->next = first;
		element->prev = NULL;
		first->prev=element;
		first = element;
	}

}
//添加元素到链表尾（set key=max_key+1）
void DLList::Append(void* item)
{
	DLLElement *element = new DLLElement(item, 0);
	if (IsEmpty())
	{
		first = element;
		last = element;
	}
	else
	{
		element->key = last->key + 1;
		element->next = NULL;
		element->prev = last;
		last->next = element;
		last = element;
	}
}
//从链表头移除元素
void* DLList::Remove(int *keyPtr)
{
	DLLElement* element;//临时指向链表的第一个元素
	if (IsEmpty())
	{
		return NULL;
	}
	void* rem_item;//返回链表第一个元素的元素项
	element = first;
	*keyPtr = first->key;//链表第一个元素的元素优先级
	if(err_type == 1)
	{
		printf("移除元素时，当前线程切换到另一个线程！\n");
		currentThread->Yield();//当前运行线程强制切换到另一个就绪的线程运行
	}
	rem_item = element->item;
	if (first==last)
	{
		first = NULL;
		last = NULL;
	}
	else
	{
		if(err_type==1)
		{
			printf("移除元素时，当前线程切换到另一个线程！\n");
			currentThread->Yield();
		}
		first = element->next;
		first->prev = NULL;
	}
	delete element;//释放临时指针element
	return rem_item;

}

bool DLList::IsEmpty()//return true if list has no elements
{
	if ((first==NULL)&&last==NULL)
	{
		return true;
	}
	else if ((first!=NULL)&&(last!=NULL))
	{
		return false;
	}
	else
		printf("出错!  first 或者 last 不等于 NULL;\n");
	return false;
	
}
/*routines to put/get items on/off list in order(sorted by key)
*/
void DLList::SortedInsert(void *item, int sortKey)
{
	DLLElement* insertItem = new DLLElement(item, sortKey);//创建链表元素实例
	DLLElement* ptr = first;//遍历指针
	//链表为空
	if (IsEmpty())
	{
		first = insertItem;
		if(err_type == 2)
		{
			printf("排序插入时，线程切换，first!=last;\n");
			currentThread->Yield();
		}
		last = insertItem;
	}
	//链表不为空
	else
	{
		//遍历，找到大于插入元素key的链表元素
		for (;ptr!=NULL;ptr=ptr->next)
		{
			if (ptr->key > sortKey)
				break;
			
		}
		if(err_type==3)
		{
			printf("排序插入出错,位置丢失！\n");
			currentThread->Yield();
		}
		//插入元素放在表尾
		if (ptr==NULL)
		{
			insertItem->prev = last;
			last->next = insertItem;
			last = insertItem;
			last->next = NULL;

		}
		//插入元素放在表头
		else if (ptr==first)
			{
				insertItem->next = first;
				first->prev = insertItem;
				first = insertItem;
				first->prev = NULL;
			}
			//插入元素放在表中
			else
			{
				ptr->prev->next = insertItem;
				insertItem->prev = ptr->prev;
				if(err_type==4)
				{
					printf("排序插入时，当前线程切换到另一个线程！\n");
					currentThread->Yield();
				}
				insertItem->next = ptr;
				ptr->prev = insertItem;
			}	
	}
}
/*remove first item with key==sortKey
*return NULL if no such item exists
*/
void* DLList::SortedRemove(int sortKey)
{
	DLLElement* ptr = first;//从第一个遍历
	if (IsEmpty())
	{
		return NULL;
	}
	for (;ptr!=NULL;ptr=ptr->next)
		if (ptr->key > sortKey)
			break;
		if (ptr==NULL)
		{
			printf("移除元素出错! 没有这样一个优先级的元素!");
			return NULL;
		}
		else if (ptr==first)
		{
			first = first->next;
			first->prev = NULL;
		}
		else if (ptr==last)
		{
			last = last->prev;
			last->next = NULL;
		}
		else
		{
			ptr->prev->next = ptr->next;
			ptr->next->prev = ptr->prev;
		}	
	
	return ptr->item;
}
