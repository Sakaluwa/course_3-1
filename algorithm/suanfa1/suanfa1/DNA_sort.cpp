#include "user_function.h"

//�ṹ�壺�洢�ַ����������
typedef struct
{
	string dna;
	int count;
}DNA;

DNA dna[101];

//��������� ��������
int cmp(const void *a, const void *b)
{
	DNA *aa = (DNA*)a;
	DNA *bb = (DNA*)b;
	return aa->count - bb->count;//����
}

void dna_sort() {
	int n, m;
	cin >> n >> m;
	//�������������
	for (int i = 0; i < m; ++i) {
		cin >> dna[i].dna;
		dna[i].count = 0;
		for (int j = 0; j < n; ++j)
			for (int k = j + 1; k < n; ++k) {
				if (dna[i].dna[j]>dna[i].dna[k])
					dna[i].count++;

			}

	}
	//���������
	qsort(dna, m, sizeof(dna[0]), cmp);
	//������
	for (int l = 0; l < m; ++l) {
		cout << dna[l].dna << endl;
	}
}