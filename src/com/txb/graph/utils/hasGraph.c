/**
 * C++: �ڽӾ����ʾ��"����ͼ(List Undirected Graph)"
 *
 * @author skywang
 * @date 2014/04/19
 */

#include <iomanip>
#include <iostream>
#include <vector>
using namespace std;

#define MAX 100
class MatrixUDG {
	private:
        char mVexs[MAX];    // ���㼯��
        int mVexNum;             // ������
        int mEdgNum;             // ����
        int mMatrix[MAX][MAX];   // �ڽӾ���

    public:
        // ����ͼ(�Լ���������)
		MatrixUDG();
        // ����ͼ(�����ṩ�ľ���)
        MatrixUDG(char vexs[], int vlen, char edges[][2], int elen);
		~MatrixUDG();

        // ��ӡ�������ͼ
        void print();

	private:
        // ��ȡһ�������ַ�
        char readChar();
        // ����ch��mMatrix�����е�λ��
        int getPosition(char ch);
};

/* 
 * ����ͼ(�Լ���������)
 */
MatrixUDG::MatrixUDG()
{
    char c1, c2;
    int i, p1, p2;
    
    // ����"������"��"����"
    cout << "input vertex number: ";
    cin >> mVexNum;
    cout << "input edge number: ";
    cin >> mEdgNum;
    if ( mVexNum < 1 || mEdgNum < 1 || (mEdgNum > (mVexNum * (mVexNum-1))))
    {
        cout << "input error: invalid parameters!" << endl;
        return ;
    }
    
    // ��ʼ��"����"
    for (i = 0; i < mVexNum; i++)
    {
        cout << "vertex(" << i << "): ";
        mVexs[i] = readChar();
    }

    // ��ʼ��"��"
    for (i = 0; i < mEdgNum; i++)
    {
        // ��ȡ�ߵ���ʼ����ͽ�������
        cout << "edge(" << i << "): ";
        c1 = readChar();
        c2 = readChar();

        p1 = getPosition(c1);
        p2 = getPosition(c2);
        if (p1==-1 || p2==-1)
        {
            cout << "input error: invalid edge!" << endl;
            return ;
        }

        mMatrix[p1][p2] = 1;
        mMatrix[p2][p1] = 1;
    }
}

/*
 * ����ͼ(�����ṩ�ľ���)
 *
 * ����˵����
 *     vexs  -- ��������
 *     vlen  -- ��������ĳ���
 *     edges -- ������
 *     elen  -- ������ĳ���
 */
MatrixUDG::MatrixUDG(char vexs[], int vlen, char edges[][2], int elen)
{
    int i, p1, p2;
    
    // ��ʼ��"������"��"����"
    mVexNum = vlen;
    mEdgNum = elen;
    // ��ʼ��"����"
    for (i = 0; i < mVexNum; i++)
        mVexs[i] = vexs[i];

    // ��ʼ��"��"
    for (i = 0; i < mEdgNum; i++)
    {
        // ��ȡ�ߵ���ʼ����ͽ�������
        p1 = getPosition(edges[i][0]);
        p2 = getPosition(edges[i][1]);

        mMatrix[p1][p2] = 1;
        mMatrix[p2][p1] = 1;
    }
}

/* 
 * ��������
 */
MatrixUDG::~MatrixUDG() 
{
}

/*
 * ����ch��mMatrix�����е�λ��
 */
int MatrixUDG::getPosition(char ch)
{
    int i;
    for(i=0; i<mVexNum; i++)
        if(mVexs[i]==ch)
            return i;
    return -1;
}

/*
 * ��ȡһ�������ַ�
 */
char MatrixUDG::readChar()
{
    char ch;

    do {
        cin >> ch;
    } while(!((ch>='a'&&ch<='z') || (ch>='A'&&ch<='Z')));

    return ch;
}

/*
 * ��ӡ�������ͼ
 */
void MatrixUDG::print()
{
    int i,j;

    cout << "Martix Graph:" << endl;
    for (i = 0; i < mVexNum; i++)
    {
        for (j = 0; j < mVexNum; j++)
            cout << mMatrix[i][j] << " ";
        cout << endl;
    }
}

int main()
{
    char vexs[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    char edges[][2] = {
        {'A', 'C'}, 
        {'A', 'D'}, 
        {'A', 'F'}, 
        {'B', 'C'}, 
        {'C', 'D'}, 
        {'E', 'G'}, 
        {'F', 'G'}};
    int vlen = sizeof(vexs)/sizeof(vexs[0]);
    int elen = sizeof(edges)/sizeof(edges[0]);
    MatrixUDG* pG;

    // �Զ���"ͼ"(����������)
    //pG = new MatrixUDG();
    // �������е�"ͼ"
    pG = new MatrixUDG(vexs, vlen, edges, elen);

    pG->print();   // ��ӡͼ

    return 0;
}