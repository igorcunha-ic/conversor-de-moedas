#include <iostream>
#include <vector>
#include "funcoes.h"
#include <curl/curl.h>
#include "json.hpp"
using namespace nlohmann;

size_t WriteCallback(void* contents, size_t size, size_t nmemb, std::string* output) {
    size_t totalSize = size * nmemb;
    output->append((char*)contents, totalSize);
    return totalSize;
}
plink obtlink(int num){
    plink p;
    std::vector<std::string> abrev = {"BRL", "DKK", "NOK", "SEK", "USD", "AUD", "CAD", "EUR", "CHF", "GBP", "JPY"};
    p.link = "http://localhost:8080/cotacao?abvmoeda=" + abrev[num];
    p.abv = abrev[num];
    return p;
}
moeda getval(int num){
    if(num == 0){
        moeda m{1 ,1};
        return m;
    }
    moeda m;
    CURL* curl;
    CURLcode res;
    std::string resposta;
    curl = curl_easy_init();
    if(curl){
        plink p = obtlink(num);
            curl_easy_setopt(curl, CURLOPT_URL, p.link.c_str());
            curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
            curl_easy_setopt(curl, CURLOPT_WRITEDATA, &resposta);
            curl_easy_setopt(curl,CURLOPT_TIMEOUT, 5L);
            res = curl_easy_perform(curl);
            if(res != CURLE_OK) {
                std::cout << "Erro: " << curl_easy_strerror(res) << std::endl;
                curl_easy_cleanup(curl);
                moeda m{};
                return m;
            } else {
                std::cout << "Resposta da API:\n" << resposta << std::endl;
            }
            curl_easy_cleanup(curl);
            try{
            json j = json::parse(resposta);
            if(resposta.empty()){
                std::cerr << "erro: resposta da API vazia" << std::endl;
            }
            m.c_compra = j["compra"].get<double>();
            m.c_venda = j["venda"].get<double>();
            m.moeda = p.abv;
            return m;
        }catch(const std::exception& e){
            std::cerr << "deu erro aqui:" << e.what() << std::endl;
            moeda m{};
            return m;
        }
    }
return m;
}
void exibir(){
    std::vector<std::pair<std::string, std::string>> tabela = {
    {"real", "BRL"},
    {"coroa dinamarquesa", "DKK"},
    {"coroa norueguesa", "NOK"},
    {"coroa sueca", "SEK"},
    {"dolar americano", "USD"},
    {"dolar australiano", "AUD"},
    {"dolar canadense", "CAD"},
    {"euro", "EUR"},
    {"franco suico", "CHF"},
    {"libra esterlina", "GBP"},
    {"iene", "JPY"}
    };
    std::cout << "digite:\n";
    for (int i = 0; i <= 10; i++) {
    std::cout << i << " para "
    << tabela[i].first << " ("<< tabela[i].second << ")\n";
    }
    std::cout << std::endl;
};
double calcular(double v1, double v2){
    if(v2 == 0){
        std::cout << "divisao por zero, o valor final da converao sera zero como forma de protecao ao sistema" << std::endl;
        return 0;
    }
    double resposta = v1 / v2;
    return resposta;
}