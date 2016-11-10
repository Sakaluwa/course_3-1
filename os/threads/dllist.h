class DLLElement
{
public:
	DLLElement(void *itemPtr,int sortKey);//初始化链表元素（元素项，优先级）
	DLLElement *next;//指向下一个元素的指针，若为最后一个元素，则该指针为空
	DLLElement *prev;//指向上一个元素的指针，若为第一个元素，则该指针为空

	int key;//优先级
	void *item; //指针指向链表上的项
};

class DLList {
public:
	DLList();//初始化链表
	DLList(int type);//带错误类型的构造函数
	~DLList();//释放链表，解除分配给链表的内存
	
	void Prepend(void* item);//添加链表头元素，设置 key=min_key-1
	void Append(void* item);//添加链表尾元素，设置 key=max_key+1
	void* Remove(int* keyPtr);//从链表头移除元素，设置 *keyPtr 为移除元素项的key,并返回移除元素项，(当链表为空时，则返回NULL)
	
	bool IsEmpty();//返回true,如果链表为空
	//将特定项按优先级放入链表（从链表中取出）
	void SortedInsert(void* item, int sortKey);//按优先级将元素插入链表，若优先级相同，则后面插入的元素在后面
	void *SortedRemove(int sortKey);//移除第一个key==sortKey的元素，如果不存在这样的元素，则返回NULL,

private:
	DLLElement *first;//链表头元素，如果链表为空，则first=NULL
	DLLElement *last;//链表尾元素，如果链表为空，则last=NULL
	int err_type;//记录错误类型
	
};
