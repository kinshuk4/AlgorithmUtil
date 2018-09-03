project(AlgorithmUtilsCpp)
cmake_minimum_required (VERSION 3.5)

add_definitions(-std=c++11)

#set(sources "src/compete/algorithms/greedy/*.cpp")
file(GLOB_RECURSE algo src/*.h src/*.cpp)
add_executable(AlgorithmUtilsCpp ${algo})