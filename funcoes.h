#include <iostream>
struct moeda {
    double c_compra;
    double c_venda;
    std::string moeda;
};
struct plink{
    std::string link;
    std::string abv;
};
plink obtlink(int num);
size_t WriteCallback(void* contents, size_t size, size_t nmemb, std::string* output);
void exibir();
moeda getval(int num);
double calcular(double v1, double v2);