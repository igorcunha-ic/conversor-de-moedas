#include <iostream>
#include <string>
#include <vector>
#include "funcoes.h"
using namespace std;
int main(){
    moeda m;
    vector<moeda> dados;
    double cam, cam2, val;
    int opc, opc2;
    do{
        dados.clear();
        cout << "digite:\n1 para converter;\n2 para conferir a cotacao;\n3 para sair" << endl;
        cin >> opc;
        switch (opc){
        case 1:
            cout << "qual moeda voce gostaria de converter?\n" << endl;
            exibir();
            cin >> opc2;
            dados.push_back(getval(opc2));
            cout << "para qual moeda deve ser convertido?\n" << endl;
            exibir();
            cin >> opc2;
            dados.push_back(getval(opc2));
            cout << "conferir valor de compra ou venda?\ndigite:\n"
                 << "1 para compra"
                 << "2 para venda" << endl;
            cin >> opc2;
            if(opc2 == 1){
                cam = dados[0].c_compra;
                cam2 = dados[1].c_compra;
            }else if(opc2 == 2){
                cam = dados[0].c_venda;
                cam2 = dados[1].c_venda;
            }else{
                cout << "opcao invalida" << endl;
            }
            cout << "quanto voce quer converter?" << endl;
            cin >> val;
            cout << "conversao:" << dados[1].moeda << " " <<val * calcular(cam, cam2) << endl;

        break;
        case 2:
        cout <<"qual moeda voce gostaria de conferir a cotacao?" << endl;
        exibir();
        cin >> opc2;
        dados.push_back(getval(opc2));
        cout << dados[0].moeda << "\n"
             << "cotacao de compra:" << dados[0].c_compra << "\n"
             << "cotacao de venda:" << dados[0].c_venda << "\n" << endl;
        break;
        default: cout << "opcao invalida" << endl; break;
        }
    }while(opc != 3);
}