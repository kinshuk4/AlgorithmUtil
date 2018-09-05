//
// Created by Kinshuk Chandra on 10.10.18.
//


#include <iostream>
#include <string>
#include <vector>
#include <list>
#include <unordered_map>

using namespace std;
class LRUCache {
    // store keys of cache
    list<char> queue;

    // store references of key in cache
    unordered_map<char, list<char>::iterator> map;
    int cacheSize; //maximum capacity of cache

public:
    LRUCache(int);

    void put(char);

    bool isPresent(char);
};

LRUCache::LRUCache(int n)
{
    cacheSize = n;
}
/* Refers key x with in the LRU cache */
void LRUCache::put(char x) {
    // not present in cache
    if (map.find(x) == map.end()) {
        // cache is full
        if (queue.size() == cacheSize) {
            //delete least recently used element
            char last = queue.back();
            queue.pop_back();
            map.erase(last);
        }
    }

        // present in cache
    else
        queue.erase(map[x]);

    // update reference
    queue.push_front(x);
    map[x] = queue.begin();
}
bool LRUCache::isPresent(char x) {
    // not present in cache
    if (map.find(x) == map.end()) {
        return false;
    }
        // present in cache
    else
        return true;
}

size_t filter(const void *source, void *destination, size_t source_size, size_t history_size) {





    const char *source_char = static_cast<const char *>(source);
    char *destination_char = static_cast<char *>(destination);
    size_t destination_size = 0;
    LRUCache history_cache(history_size);
    for (size_t i = 0; i < source_size; ++i) {
        char c = source_char[i];
        if (isalnum(c)) {
            if (history_size == 0) {
                destination_char[destination_size++] = c;
            } else {
                if(!history_cache.isPresent(c)){
                    destination_char[destination_size++] = c;
                    history_cache.put(c);
                }
            }
        }
    }

    return destination_size;
}

int main() {
    std::string input = "Hello, world!";
    std::vector<char> output(input.size());

    auto characterCount = filter(input.data(), output.data(), input.size(), 2);

    std::cout << characterCount << std::endl;
    std::cout << std::string(output.data(), characterCount) << std::endl;

    return 0;
}