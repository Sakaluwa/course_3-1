#include "user_function.h"

//结构体：存储字符串，逆序度
typedef struct
{
	string dna;
	int count;
}DNA;

DNA dna[101];

//逆序度升序 快速排序
int cmp(const void *a, const void *b)
{
	DNA *aa = (DNA*)a;
	DNA *bb = (DNA*)b;
	return aa->count - bb->count;//升序
}

void dna_sort() {
	int n, m;
	cin >> n >> m;
	//遍历计算逆序度
	for (int i = 0; i < m; ++i) {
		cin >> dna[i].dna;
		dna[i].count = 0;
		for (int j = 0; j < n; ++j)
			for (int k = j + 1; k < n; ++k) {
				if (dna[i].dna[j]>dna[i].dna[k])
					dna[i].count++;

			}

	}
	//逆序度排序
	qsort(dna, m, sizeof(dna[0]), cmp);
	//输出结果
	for (int l = 0; l < m; ++l) {
		cout << dna[l].dna << endl;
	}
}