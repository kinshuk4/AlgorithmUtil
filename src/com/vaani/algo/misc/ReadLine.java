package com.vaani.algo.misc;

/**
 * Give you  a function char* read4096(), return a string which has <= 4096 characters
 * <p>
 * If the string less than 4096 characters which means reached the end of file ”
 * <p>
 * Use API read4096(), write a function char* readline()
 * <p>
 * Requirement:
 * #1 readline() returns when reading ‘\n’ or ”;
 * #2 readline() may be called multiple times on a file, the return value  should be correct.
 * #3 readline() may return char array longer than 4096 chars.
 */
public class ReadLine {
    String buff = null;
    int p = 0;
    char[] buffer = new char[4096];
    int bufferSize = 0;
    int bufferPos = 0;

    /**
     * Reference: http://weidw.wordpress.com/2012/05/03/use-read4096-implement-readlin/
     */
    public String readLine() {
        StringBuilder result = new StringBuilder();
        boolean EOF = false;
        while (true) {
            if (!EOF) {
                if (buff == null || p == buff.length()) {
                    buff = read4096();
                    p = 0;
                    if (buff.length() < 4096) EOF = true;
                } else {
                    int i;
                    for (i = p; i < buff.length(); i++) {
                        if (buff.charAt(i) == '\0' || buff.charAt(i) == '\n') break;
                    }
                    int oldSize = result.length();
                    result.append(buff.substring(p, i + 1));
                    p = i + 1;
                    if (i != buff.length()) break;
                }

            }
        }
        return result.toString();
    }

    public void readLine2(char[] buf) {
        int pos = 0;
        boolean eof = false;
        while (!eof) {
            int size = bufferSize > 0 ? bufferSize : read4096(buffer);
            if (bufferSize == 0 && size < 4096) eof = true;
            int i;
            for (i = bufferPos; i < bufferPos + size; i++) {
                if (buffer[i] == '\0' || buffer[i] == '\n') break;
            }
            if (i == bufferPos + size) {
                System.arraycopy(buffer, bufferPos, buf, pos, i - bufferPos);
                pos += i - bufferPos;
                bufferPos = 0;
                bufferSize = 0;
            } else {
                System.arraycopy(buffer, bufferPos, buf, pos, i - bufferPos + 1);
                pos += i - bufferPos + 1;
                bufferPos = (i + 1) % 4096;
                bufferSize = size - (i - bufferPos + 1);
                return;
            }
        }
    }

    public String read4096() {
        //given implement
        return "";
    }


    public int read4096(char[] buf) {
        //given implement
        return 1;
    }
}
