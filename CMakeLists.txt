cmake_minimum_required(VERSION 3.10)
project (Conversor)
set(CMAKE_CXX_STANDARD 17)
set(SOURCES
cliente/main.cpp
cliente/funcoes.cpp
)
add_executable(conversor_de_moedas ${SOURCES})
include_directories(C:/msys64/ucrt64/include)
link_directories(C:/msys64/ucrt64/lib)
target_link_libraries(conversor_de_moedas PRIVATE curl)