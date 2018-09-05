//
// Created by Kinshuk Chandra on 10.10.18.
//


#include <iostream>
#include <string>
#include <vector>

size_t filter(const void* source, void* destination, size_t source_size, size_t history_size)
{
    struct Pair
    {
        char key = ' ';
        size_t visitedCount;

        Pair(char k, size_t v){
            key = k;
            visitedCount = v;
        }
    };

    static Pair alphaNumericMap[] = {
            {'0', 0}, {'1', 0}, {'2', 0}, {'3', 0}, {'4', 0}, {'5', 0}, {'6', 0},
            {'7', 0}, {'8', 0}, {'9', 0},

            {'A', 0}, {'B', 0}, {'C', 0}, {'D', 0}, {'E', 0}, {'F', 0}, {'G', 0},
            {'H', 0}, {'I', 0}, {'J', 0}, {'K', 0}, {'L', 0}, {'M', 0}, {'N', 0},
            {'O', 0}, {'P', 0}, {'Q', 0}, {'R', 0}, {'S', 0}, {'T', 0}, {'U', 0},
            {'V', 0}, {'W', 0}, {'X', 0}, {'Y', 0}, {'Z', 0},

            {'a', 0}, {'b', 0}, {'c', 0}, {'d', 0}, {'e', 0}, {'f', 0}, {'g', 0},
            {'h', 0}, {'i', 0}, {'j', 0}, {'k', 0}, {'l', 0}, {'m', 0}, {'n', 0},
            {'o', 0}, {'p', 0}, {'q', 0}, {'r', 0}, {'s', 0}, {'t', 0}, {'u', 0},
            {'v', 0}, {'w', 0}, {'x', 0}, {'y', 0}, {'z', 0},
    };

    const char* src = static_cast<const char*>(source);
    char* dst = static_cast<char*>(destination);
    size_t dstSize = 0;
    size_t dstIndex = 0;

    for (size_t srcIndex = 0; srcIndex < source_size; ++srcIndex)
    {
        if (isalnum(src[srcIndex]))
        {
            if (history_size == 0)
            {
                dst[dstIndex++] = src[srcIndex];
                ++dstSize;
            }
            else
            {
                const struct Pair key = {src[srcIndex], 0};

                struct Pair* const mapItem = static_cast<Pair*>(bsearch(&key, alphaNumericMap,
                                                                        sizeof(alphaNumericMap) / sizeof(alphaNumericMap[0]), sizeof(alphaNumericMap[0]),
                                                                        [](const void* const lhs, const void* const rhs)
                                                                        {
                                                                            const struct Pair* const a = static_cast<const struct Pair* const>(lhs);
                                                                            const struct Pair* const b = static_cast<const struct Pair* const>(rhs);

                                                                            if (a->key < b->key)
                                                                                return -1;
                                                                            else if (a->key > b->key)
                                                                                return 1;
                                                                            else
                                                                                return 0;
                                                                        }));

                if (mapItem)
                {
                    ++mapItem->visitedCount;

                    if (mapItem->visitedCount < history_size)
                    {
                        dst[dstIndex++] = src[srcIndex];
                        ++dstSize;
                    }
                }
            }
        }
    }

    return dstSize;
}

int main()
{
    std::string input = "Hello, world!";
    std::vector<char> output(input.size());

    auto characterCount = filter(input.data(), output.data(), input.size(), 2);

    std::cout << characterCount << std::endl;
    std::cout << std::string(output.data(), characterCount) << std::endl;

    return 0;
}