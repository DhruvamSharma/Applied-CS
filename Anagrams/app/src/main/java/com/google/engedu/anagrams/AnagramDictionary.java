/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.annotation.SuppressLint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private static HashSet<String> wordSet = new HashSet<String>();
    private static ArrayList<String> wordList = new ArrayList<String>();
    private static HashMap<String, ArrayList<String>> lettersToWord = new HashMap<String, ArrayList<String>>();
    @SuppressLint("UseSparseArrays")
    private static HashMap<Integer, ArrayList<String>> sizeToWord = new HashMap<Integer, ArrayList<String>>();
    private static Integer wordLength = DEFAULT_WORD_LENGTH;


    AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordSet.add(word);
            wordList.add(word);
            if(sizeToWord.containsKey(word.length())) {
                sizeToWord.get(word.length()).add(word);
            } else {
                ArrayList<String> arrList = new ArrayList<String>();
                arrList.add(word);
                sizeToWord.put(word.length(), arrList);
            }
            String sorted;
            sorted = sortLetters(word);
            if(lettersToWord.containsKey(sorted)) {
                ArrayList<String> appList = lettersToWord.get(sorted);
                appList.add(word);
            } else {
                ArrayList<String> arrList = new ArrayList<String>();
                arrList.add(word);
                lettersToWord.put(sorted, arrList);
            }


        }

        //cleanUp();

    }

    private void cleanUp(){
        for (String s : wordSet){
            String sortedword = sortLetters(s);

            if (lettersToWord.containsKey(sortedword) && lettersToWord.get(sortedword).size() < MIN_NUM_ANAGRAMS){
                /*for (String str : lettersToWord.get(sortedword)) {
                    sizeToWord.get(str.length()).remove(str);
                }*/
                lettersToWord.remove(sortedword);
            }
        }

    }

    boolean isGoodWord(String word, String base) {
        if(!wordSet.contains(word)){
            System.out.println("Word is not in the List");
            return false;
        }

        // does word contain a substring base?
        if (word.contains(base)){
            System.out.println("Word contains the base word");
            return false;
        }

        return true;
    }

    private ArrayList<String> getAnagrams(String targetWord) {
        return lettersToWord.get(sortLetters(targetWord));
    }



    private String sortLetters(String word) {

        char[] word1 = word.toCharArray();
        Arrays.sort(word1);
        return new String(word1);
    }
    //MileStone II
    ArrayList<String> getAnagramsWithOneMoreLetter(String word) {

        ArrayList<String> result = new ArrayList<>();

        for (char c = 'a'; c <= 'z'; c++){
            String test = sortLetters(word + c);

            if (lettersToWord.containsKey(test)){
                ArrayList<String> anagrams = lettersToWord.get(test);

                for (String s : anagrams){
                    if (isGoodWord(s, word)) {
                        result.add(s);
                    }
                }
            }
            // else do nothing, no anagram was found
        }

        return result;
    }

    static void setDefaultWordLength( int anagramLength) {
        DEFAULT_WORD_LENGTH = anagramLength;
        wordLength = anagramLength;
    }

    String pickGoodStarterWord() {
        int randomNumber;
        String answer= null;
        Boolean found = false;
        while(!found) {

            randomNumber =Math.abs( random.nextInt(wordSet.size()-1));
            answer=wordList.get(randomNumber);

            ArrayList<String> temp = getAnagramsWithOneMoreLetter(answer);
            if (temp.size() >= MIN_NUM_ANAGRAMS && answer.length()==wordLength && wordLength<=MAX_WORD_LENGTH) {
                found=true;
                wordLength++;
                if (wordLength == MAX_WORD_LENGTH){
                    wordLength = DEFAULT_WORD_LENGTH;
                }
            }
        }



        return answer;

        /*boolean found = false;
        ArrayList<String> result = new ArrayList<String>();
        String s;
        do {
            int randomNum = random.nextInt((wordList.size() - 1));
            s = wordList.get(randomNum);
            result = getAnagramsWithOneMoreLetter(s);

            if( result.size() >= MIN_NUM_ANAGRAMS && s.length() == wordLength && s.length() <= MAX_WORD_LENGTH){
                found = true;
                wordLength++;
                if( wordLength == MAX_WORD_LENGTH) {
                    wordLength = DEFAULT_WORD_LENGTH;
                }
            }
        }while(!found);
        return s;*/
    }
}
