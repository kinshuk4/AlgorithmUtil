package com.vaani.algo.compete.company.wallethub.java;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.vaani.algo.compete.company.wallethub.java.TopPhrases.Solution1MapReduce.PhraseFrequency;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by kchandra on 23/10/16.
 */
//I am not using spark etc to get the result as data size is less than 1TB.
public class TopPhrases {
    public static final String DELIM = "|";
    public static final String EOL = System.getProperty("line.separator");
    private static final long MAXIMUM_MEMORY_AVAILABLE = Runtime.getRuntime().maxMemory();
    private static final int TOP_PHRASE_COUNT = 100000;
    public static final String LARGE_FILE_PATH = "SOME LARGE FILE";
    
    
    public static void main(String[] args) throws IOException {
    	TopPhrases tp = new TopPhrases();
    	
    	Solution1MapReduce mr = new Solution1MapReduce();
    	
    	List<String> phraseList = mr.getTopPhrases(LARGE_FILE_PATH, TOP_PHRASE_COUNT);
    	
    	Solution2UsingPersistentMap pm = new Solution2UsingPersistentMap();
    	List<String> phraseList2 = pm.getTopPhrases(LARGE_FILE_PATH, TOP_PHRASE_COUNT);
    	
    	
	}
    static class Solution1MapReduce {

        /**
         * Net complexity is O(n) + O(top), where n is total number of tokens in file
         * Memory consumption is O(n) limited by Xmx/2 + const (won't grow after Xmx/2 consumed)
         *
         * @param file File from which the @code top most frequent word will be mined.
         * @param top  How many top strings to include
         * @return TopMostOccuringPhraseList [top] strings in the given file
         */
        public  List<String> getTopPhrases(String filePath, int top) throws IOException {
            if (top < 1)
                return new ArrayList<String>();
            File file = new File(filePath);
            long start = System.currentTimeMillis();
            Collection<File> chunks = split(file);
            System.out.println("File split into "+ chunks.size()+ " chunks, time it took:"+(System.currentTimeMillis() - start));

            // After split operation, in worst case, we may have 1 file containing only 1 repeating string - but it won't lead to OEM
            //  because its same string (only count will be increased, not String objects in memory)

            long start2 = System.currentTimeMillis();
            List<PhraseFrequency> phraseFrequencyList = countInChunks(chunks, top);
            System.out.println("Count from chunks took took in ms:"+ (System.currentTimeMillis() - start2));
            System.out.println("Total time for "+ top+ " top phrases in ms: " + (System.currentTimeMillis() - start));
            //java 8, get list of properties from list of objects
            List<String> phrases = phraseFrequencyList.stream().map(PhraseFrequency::getPhrase).collect(Collectors.toList());
            return phrases;
        }

       
        /**
         * Split large file into number of smaller chunk of files based upon hashCodes
         * We achieve 2 goals:
         * 1) Split file completely fits in memory (most of the times)
         * 2) Same strings are in one file
         * 
         * If, in worst case, we have again 1 (or more) large file that does not fit in memory -
         * it consists of at most several different string
         * which will not eat memory as they are used as map keys
         * O(n)
         * Space compexity : O(n)
         * @param file File which has to be split to fit in memory
         * @return Collection of files split from large files
         * @throws IOException
         */
        private Collection<File> split(File file) throws IOException {
            // Lets use half the memory, such that when we may have to merge in future
        	// the merge entity is less than memory available
            long memoryPerFile = MAXIMUM_MEMORY_AVAILABLE >> 1;

            System.out.println("Total memory available: " + (MAXIMUM_MEMORY_AVAILABLE /(1024*1024)) + " Mb");
            System.out.println("Memory we may use: " + (memoryPerFile /(1024*1024)) + " Mb");
            System.out.println("File size: " + (file.length() /(1024*1024)) + " Mb");

            int num = (int) (file.length() / memoryPerFile);
            //Big File completely fits into memory, so number of chunks will be 0
            final int filesNumber = num == 0 ? 1 : num;

            System.out.println("Total number of splitted files: " + filesNumber);

            final Collection<File> results = new HashSet<>(filesNumber);
            final Map<Integer, FileWriter> fileMap = new HashMap<>(filesNumber);

            //Now we know how many files to split, lets create those many files.
            final long start = System.currentTimeMillis();

            
            try (Scanner scanner = new Scanner(file)) {
                int i = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Arrays.asList(line.split('\\' + DELIM))
                            .forEach(phrase -> {                                        
                                        File fileChunk = writeSplittedFile(phrase, fileMap, filesNumber);
                                        if (fileChunk != null) {
                                            results.add(fileChunk);
                                        }
                                    }
                            );

                    if (++i % TOP_PHRASE_COUNT == 0) {
                        System.out.println("Processed " + i + " lines in sec:" + (System.currentTimeMillis() - start) / 1000);
                    }

                }
            } finally {
            	//close all the files
                fileMap.entrySet().forEach(e -> {
                    try {
                        e.getValue().flush();
                        e.getValue().close();
                    } catch (IOException ex) {
                        System.err.println("Unable to flush/close file, possible data loss: " + e.getValue());
                    }
                });

            }

            return results;
        }

        // O(n)
        private List<PhraseFrequency> countInChunks(Collection<File> chunks, int top) throws IOException {
        	//min heap
            Queue<PhraseFrequency> minHeap = new PriorityQueue<>(top);
            //Go through all the file chunks
            for (File chunk : chunks) {
                int size = (int) (chunk.length() >> 32);
                if (size == 0)
                    size = 16;

                Map<String, Integer> frequencyMap = new HashMap<>(size);
                Files.lines(chunk.toPath()).forEach(line -> {
                    int count = frequencyMap.getOrDefault(line, 0);
                    frequencyMap.put(line, ++count);
                });
                //We will be doing sifting operation in the minHeap
                // O(n) + O(n * log(topNumber)) = O(n)
                frequencyMap.forEach((k, v) -> {
                    minHeap.add(new PhraseFrequency(k, v));
                    if (minHeap.size() > top)
                        minHeap.remove();
                });
                //remove the file
                chunk.delete();
            }

            List<PhraseFrequency> result = new ArrayList<>(minHeap.size());
            // O(top)
            while (!minHeap.isEmpty())
                result.add(minHeap.remove());

            // O(top)
            Collections.reverse(result);

            return result;
        }

        private File writeSplittedFile(String phrase, Map<Integer, FileWriter> fileMap, int filesNumber) {
            File file = null;
            int number = Math.abs(phrase.hashCode() % filesNumber); 
            try {
                FileWriter fw = fileMap.get(number);
                if (fw == null) {
                    file = File.createTempFile("chunk", "count");
                    file.deleteOnExit();
                    fw = new FileWriter(file);
                    fileMap.put(number, fw);
                }

                fw.write(phrase + EOL);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return file;
        }

        public class PhraseFrequency implements Comparable<PhraseFrequency> {
            public final String phrase;
            public final long frequency;

            public PhraseFrequency(String phrase, long count) {
                this.phrase = phrase;
                this.frequency = count;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                PhraseFrequency that = (PhraseFrequency) o;

                if (frequency != that.frequency) return false;
                return phrase != null ? phrase.equals(that.phrase) : that.phrase == null;

            }

            @Override
            public int hashCode() {
                int result = phrase != null ? phrase.hashCode() : 0;
                result = 31 * result + (int) (frequency ^ (frequency >>> 32));
                return result;
            }

            @Override
            public int compareTo(PhraseFrequency o) {
                if (o == null) return 1;

                return this.frequency > o.frequency ? 1 : this.frequency < o.frequency ? -1 : 0;
            }

            @Override
            public String toString() {
                return "PC {" + phrase + " => " + frequency + '}';
            }

			public String getPhrase() {
				return phrase;
			}

			public long getCount() {
				return frequency;
			}
            
        }

    }

    // This solution uses persistent map to store the occurences of all the words
    /*
    Here is the dependency for the same:
            <dependency>
            <groupId>org.mapdb</groupId>
            <artifactId>mapdb</artifactId>
            <version>1.0.9</version>
        </dependency>

     */
    static class Solution2UsingPersistentMap {
        private Map<String, Integer> frequencyMap;
        private DB db;
        
        
        public Solution2UsingPersistentMap(){
        	//initialized the persistent hashmap
        	createMapDB();
        	
        }
        public void createMapDB() {
            //location where we have to create the persistent cache
            db = DBMaker.newFileDB(new File("/tmp/cache"))
                    .closeOnJvmShutdown()
                    .make();

            // Create a Map now:
            this.frequencyMap = db.getHashMap("topWordsMap");
        }

        
        /**
         * This will require O(1) memory for read a file line by line.
         * After reading the file, we can put all the phrases in the 
         *
         * @param file File from which the @code top most frequent word will be mined.
         * @param top  How many top strings to include
         * @return TopMostOccuringPhraseList [top] strings in the given file
         */
        public List<String> getTopPhrases(String filePath, int top) throws FileNotFoundException{
            BufferedReader br = new BufferedReader( new FileReader(filePath));

            String strLine;
            int lineNumFile = 0;

            //Read File Line By Line
            try {
                while ((strLine = br.readLine()) != null)   {
                    lineNumFile++;
                    // Print the content on the console
                    strLine = strLine.trim();

                    if(strLine==null || "".equals(strLine)){
                        continue;
                    }
                    String[] phrases = strLine.split(DELIM);

                    for(String phrase:phrases){
                        if(frequencyMap.containsKey(phrase)){
                            frequencyMap.put(phrase,1);
                        }else{
                            frequencyMap.put(phrase,frequencyMap.get(phrase)+1);
                        }
                    }

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Close the input stream
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //Now we have frequency map ready, with some part of it on disk and some in memory
            //Now get all the values via creating array of linked list - 
            //Index in this array is frequency count of the elements, and 
            //Linked list contains all the words with the same frequency
            ArrayList<LinkedList<String>> arrOfList = new ArrayList<LinkedList<String>>();
            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet())
            {
                if(arrOfList.size()>entry.getValue()){
                    LinkedList<String> getValue = arrOfList.get(entry.getValue());
                    if(getValue==null){
                        getValue = new LinkedList<>();
                    }
                    getValue.add(entry.getKey());
                }else {
                    arrOfList.add(entry.getValue(), new LinkedList<>(Arrays.asList(entry.getKey())));
                }
            }

            int k=0;
            //Now go through the array, and as soon as we have TOP_PHRASE_COUNT words, quit
            List<String> result = new ArrayList<>();
            for(int i=arrOfList.size();i>0;i++){
                List<String> entryKeyList = arrOfList.get(i);
                if(entryKeyList == null || entryKeyList.size()==0){
                    continue;
                }else{
                    for(String entryKey : entryKeyList){
                        System.out.println(entryKey);
                        result.add(entryKey);
                        k++;
                        if(k % TOP_PHRASE_COUNT == 0){
                            break;
                        }
                    }
                }

            }
            return result;
        }


    }
}
