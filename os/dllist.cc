#include"copyright.h"
#include"dllist.h"
#include"system.h"

DLLElement::DLLElement(void* itemPtr, int sortKey)//initialize a list element
{
	item = itemPtr;
	key = sortKey;
	next = NULL;
	prev = NULL;//正常使用过程中NULL与NULL等价
}

DLList::DLList()//
{
	first = NULL;
	last = NULL;

}
DLList::~DLList()//de-allocate the list
{
	//remove all the elements of list
	while (IsEmpty()==false)
	{
		Remove(NULL);
	}
	
}
void DLList::Prepend(void* item)//add to head of list(set key=min_key-1)
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
		first = element;
	}

}

void DLList::Append(void* item) //add to tail of list(set key=max_key+1)
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

void* DLList::Remove(int *keyPtr)//remove from head of list
{
	DLLElement* element;
	if (IsEmpty())
	{
		return NULL;
	}
	void* rem_item;
	//移除第一个元素
	element = first;
	//移除元素的key
	*keyPtr = first->key;
	//移除元素的项
	rem_item = element->item;
	if (first==last)
	{
		first = NULL;
		last = NULL;
	}
	else
	{
		first = element->next;
		first->prev = NULL;
	}
	delete element;
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
			printf("Remove error! No such a key!")
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