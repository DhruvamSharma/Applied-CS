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

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList;

    public AnagramDictionary(Context context, Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        wordList = new ArrayList<String>();
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            //Toast.makeText(context, wordList.get(0), Toast.LENGTH_LONG).show();
        }
    }

    boolean isGoodWord(String word, String base) {
        return true;
    }

    List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

        Iterator<String> iterator = wordList.iterator();
        while (iterator.hasNext()) {
            String wordListWord = null;
            wordListWord = iterator.next();
            if (targetWord.length() == wordListWord.length()&&wordListWord.equals("") ) {
                boolean answer = sortAndCheck(targetWord , wordListWord);
                if(answer) {
                    result.add(wordListWord);
                }
            }

        }


        return result;
    }

    private boolean sortAndCheck(String targetWord, String wordListWord) {
        boolean bool;

        Arrays.sort(targetWord.toCharArray());
        Arrays.sort(wordListWord.toCharArray());

        bool = targetWord.equals(wordListWord);

        return bool;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}