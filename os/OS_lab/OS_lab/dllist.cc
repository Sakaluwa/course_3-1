
#include"dllist.h"

DLLElement::DLLElement(void* itemPtr, int sortKey)//initialize a list element
{
	item = itemPtr;
	key = sortKey;
	next = nullptr;
	prev = nullptr;//正常使用过程中nullptr与NULL等价
}

DLList::DLList()//
{
	first = nullptr;
	last = nullptr;

}
DLList::~DLList()//de-allocate the list
{
	//remove all the elements of list
	while (IsEmpty()==false)
	{
		Remove(nullptr);
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
		element->prev = nullptr;
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
		element->next = nullptr;
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
		return nullptr;
	}
	void* rem_item;
	element = first;
	*keyPtr = first->key;
	rem_item = element->item;
	if (first==last)
	{
		first = nullptr;
		last = nullptr;
	}
	else
	{
		first = element->next;
		first->prev = nullptr;
	}
	delete element;
	return rem_item;

}

bool DLList::IsEmpty()//return true if list has no elements
{
	if ((first==nullptr)&&last==nullptr)
	{
		return true;
	}
	else if ((first!=nullptr)&&(last!=nullptr))
	{
		return false;
	}
	
}

void DLList::SortedInsert(void *item, int sortKey)//routines to put/get items on/off list in order(sorted by key)
{
	DLLElement* insertItem = new DLLElement(item, sortKey);
	DLLElement* ptr = first;
	if (IsEmpty())
	{
		first = insertItem;
		last = insertItem;
	}
	else
	{
		for (;ptr!=nullptr;ptr=ptr->next)
		{
			if (ptr->key > sortKey)
				break;
			
		}
		if (ptr==nullptr)
		{
			insertItem->prev = last;
			last->next = insertItem;
			last = insertItem;
			last->next = nullptr;

		}
		else
		{
			if (ptr==first)
			{
				insertItem->next = first;
				first->prev = insertItem;
				first = insertItem;
				first->prev = nullptr;
			}
			else
			{
				ptr->prev->next = insertItem;
				insertItem->prev = ptr->prev;
				insertItem->next = ptr;
				ptr->prev = insertItem;

			}
		}
	}
}

void* DLList::SortedRemove(int sortKey)
{
	DLLElement* ptr = first;
	if (IsEmpty())
	{
		return nullptr;
	}
	for (;ptr!=nullptr;ptr=ptr->next)
	{
		if (ptr->key > sortKey)
			break;
		if (ptr==nullptr)
		{
			//printf("Remove error! No such a key!")
			return nullptr;
		}
		else if (ptr==first)
		{
			first = first->next;
			first->prev = nullptr;
		}
		else if (ptr==last)
		{
			last = last->prev;
			last->next = nullptr;
		}
		else
		{
			ptr->prev->next = ptr->next;
			ptr->next->prev = ptr->prev;
		}	
	}
	return ptr->item;
}