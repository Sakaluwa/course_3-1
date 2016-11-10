#pragma once //编译指示，杂注，保证头文件只被编译一次

/*头文件*/
#include<iostream>
#include<cstdio>
#include<cstring>
#include<stdlib.h>
#include <string>
#include <algorithm>
#include<Windows.h>
#include<time.h>
#include<math.h>
using namespace std;

/*函数声明*/

//DNA排序
void dna_sort();

//延迟函数
void delay(int time=3);

//随机数产生器
int random_number(int range=1000000);


//盒分形
void box_fractal();

//计数每个数码出现的次数
void counting_number();

