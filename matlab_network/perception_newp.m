% perception_newp.m
% ����
clear,clc
close all
% ������֪��
net=newp([-20,20;-20,20],1);
% ��������ѵ������
P=[-9,1,-12,-4,0,5;
    15,-8,4,5,11,9];
%�������
T=[0,1,0,0,0,1]
% ѵ��
net=train(net,P,T);
% ����ѵ�����ݷ�����֤
Y=sim(net,P)
% �����Ȩֵ��ƫ��
iw=net.iw;
b=net.b;
w=[b{1},iw{1}];
disp('�����Ȩֵ��ƫ��Ϊ��');
disp(w);