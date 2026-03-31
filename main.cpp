#include <iostream>
#include <string>
#include <curl/curl.h>
using namespace std;
 size_t WriteCallback(void* contents, size_t size, size_t nmemb, std::string* output) {
    size_t totalSize = size * nmemb;
    output->append((char*)contents, totalSize);
    return totalSize;
}
int main(){
    CURL* curl;
    CURLcode res;
    string resposta;
    curl = curl_easy_init();
    if(curl){
        curl_easy_setopt(curl, CURLOPT_URL, "http://localhost:8080/cotacao");
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &resposta);
        res = curl_easy_perform(curl);
        if(res != CURLE_OK) {
            std::cout << "Erro: " << curl_easy_strerror(res) << std::endl;
        } else {
            std::cout << "Resposta da API:\n" << resposta << std::endl;
        }
        curl_easy_cleanup(curl);
    }
    return 0;
}