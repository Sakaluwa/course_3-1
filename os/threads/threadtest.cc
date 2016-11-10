// threadtest.cc 
//	Simple test case for the threads assignment.
//
//	Create two threads, and have them context switch
//	back and forth between themselves by calling Thread::Yield, 
//	to illustratethe inner workings of the thread system.
//
// Copyright (c) 1992-1993 The Regents of the University of California.
// All rights reserved.  See copyright.h for copyright notice and limitation 
// of liability and disclaimer of warranty provisions.
#include<iostream>
using namespace std;
#include "dllist.h"
#include "copyright.h"
#include "system.h"
extern void Insert(int t,int n,DLList* dllist),Remove(int t,int n,DLList* dllist);
// testnum is set in main.cc
int testnum = 1;//测试号
//---------------saka's variables------------------
int n;//结点
int threadnum=1;//线程号
int err_type=0;//错误类型
DLList* dllist;
//--------saka's----------------------
//将双向链表的功能嵌入，设置测试号为2
void DLListThread(int t)
{
	Insert(t,n,dllist);//线程t 中插入链表元素，元素个数为 n
	Remove(t,n,dllist);//线程t 中移除链表元素,移除个数为n
}
void SelfTest()
{
 	Thread *t[130];
	for(int i=0;i<130;i++)
	{
		t[i]=new Thread("create thread");
		//要清除第i个线程，则令i=i-1
		//此操作清除第i个线程后，程序会接着连续创建线程，所以不能清除线程；
		if(i==(122-1)){
		     delete t[i];
	             i++;
		}
	}	

}
//----------------------------------------------------------------------
// SimpleThread
// 	Loop 5 times, yielding the CPU to another ready thread 
//	each iteration.
//
//	"which" is simply a number identifying the thread, for debugging
//	purposes.
//----------------------------------------------------------------------

void
SimpleThread(int which)
{
    int num;
    
    for (num = 0; num < 5; num++) {
	printf("*** thread %d looped %d times\n", which, num);
        currentThread->Yield();
    }
}

//----------------------------------------------------------------------
// ThreadTest1
// 	Set up a ping-pong between two threads, by forking a thread 
//	to call SimpleThread, and then calling SimpleThread ourselves.
//----------------------------------------------------------------------

void
ThreadTest1()
{
    DEBUG('t', "Entering ThreadTest1");

    Thread *t = new Thread("forked thread");

    t->Fork(SimpleThread, 1);
    SimpleThread(0);
}

//----------------------------------------------------------------------
//------------------saka's----------------
void
ThreadTest2()
{
	DEBUG('t',"Entering ThreadTest2");//输出调试信息
	dllist=new DLList(err_type);//新建双向链表实例
	for(int i=1;i<threadnum;i++)
	{
		Thread* t=new Thread("forker thread");//创建新线程，初始化
		t->Fork(DLListThread,i);//线程初始化后将该线程设置成可运行的，（运行函数，函数参数）
	}
	DLListThread(threadnum);//执行编号为 threadnum 的线程
}
//-------------------------------------------------
// ThreadTest
// 	Invoke a test routine.
//----------------------------------------------------------------------

void
ThreadTest()
{
//不同的测试号，执行不同的测试函数
    switch (testnum) {
    case 1:
	ThreadTest1();
	break;
//-----------saka's---------
    case 2:
	ThreadTest2();
	break;
    case 3:
	SelfTest();
	break;
//---------------------------------
    default:
	printf("No test specified.\n");
	break;
    }
}

