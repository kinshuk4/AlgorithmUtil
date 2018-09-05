#include <iostream>
#include <vector>
#include <string>
using namespace std;


//Gestire dimensioni della destinazione senza string??
size_t filter (void *destination, const void * source, size_t source_size, size_t history_size)
{
    unsigned int i, j;
    bool found = false;
    char value;
    const char* src = static_cast<const char*>(source);
    char* dst = static_cast<char*>(destination);
    for (i = 0; i < source_size; ++i)
    {
        value = *((char *)source + i);
        for (j = 0; j < history_size && (dst.size () - j) > 0; ++j)
        {
            if ((value == (destionation - j)) || (!isalnum (value)))
            {
                found = true;
                break;
            }

        }

        if (found == false)
        {
            destination.push_back (value);
        }
    }

    return destionation.size ();
}

int main() {
    string source = "Hello, world!";
    size_t history_size = 2;
    size_t dim;
    void *destination;

    dim = filter (destination, source, source.size(), history_size);

    cout << "Result: " << to_string (*((char *) destination)) << "Dimension: " << to_string (dim) << endl;
    return 0;
}
