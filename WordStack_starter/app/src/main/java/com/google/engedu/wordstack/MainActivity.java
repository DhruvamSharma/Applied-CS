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

package com.google.engedu.wordstack;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private int WORD_LENGTH = 3;
    public static final int LIGHT_BLUE = Color.rgb(176, 200, 255);
    public static final int LIGHT_GREEN = Color.rgb(200, 255, 200);
    private ArrayList<String> words = new ArrayList<>();
    private HashSet <String> wordSet = new HashSet<>();
    private StackedLayout stackedLayout;
    private String word1, word2;
    private Context context;
    static LinearLayout word1LinearLayout = null;
    static LinearLayout word2LinearLayout = null;
    private static Stack<LetterTile> placedTiles = null;
    private StringBuilder answer1 = new StringBuilder();
    private StringBuilder answer2 = new StringBuilder();
    private boolean hasStarted = false;
    private HashMap<Integer,ArrayList<String>> wordMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = in.readLine()) != null) {
                String word = line.trim();
                /**
                 **  YOUR CODE GOES HERE
                 **/
                int  wordLength = word.length();
                wordSet.add(word);
                if (wordLength == WORD_LENGTH) {
                    words.add(word);
                }
                if (wordMap.containsKey(wordLength)) {
                    wordMap.get(wordLength).add(word);

                }else {
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add(word);
                    wordMap.put(wordLength,arrayList);
                }


            }

        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        LinearLayout verticalLayout = (LinearLayout) findViewById(R.id.vertical_layout);
        stackedLayout = new StackedLayout(this);
        assert verticalLayout != null;

        verticalLayout.addView(stackedLayout, 3);
        //stackedLayout.setOnDragListener(new DragListener());
        placedTiles = new Stack<>();
        word1LinearLayout = (LinearLayout) findViewById(R.id.word1);
        //word1LinearLayout.setOnTouchListener(new TouchListener());
        word1LinearLayout.setOnDragListener(new DragListener());
        word2LinearLayout = (LinearLayout) findViewById(R.id.word2);
        //word2LinearLayout.setOnTouchListener(new TouchListener());
        word2LinearLayout.setOnDragListener(new DragListener());
        context = this;
    }

    private class TouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN && !stackedLayout.empty()) {
                LetterTile tile = (LetterTile) stackedLayout.peek();
                tile.moveToViewGroup((ViewGroup) v);
                placedTiles.push(tile);
                if (stackedLayout.empty()) {
                    TextView messageBox = (TextView) findViewById(R.id.message_box);
                    messageBox.setText(word1 + " " + word2);
                }
                /**
                 **
                 **  YOUR CODE GOES HERE
                 **
                 **/
                return true;
            }
            return false;
        }
    }

    private class DragListener implements View.OnDragListener {

        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    v.setBackgroundColor(LIGHT_BLUE);
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(LIGHT_GREEN);
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(LIGHT_BLUE);
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(Color.WHITE);
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign Tile to the target Layout
                    LetterTile tile = (LetterTile) event.getLocalState();


                    tile.moveToViewGroup((ViewGroup) v);
                    placedTiles.push(tile);
                        Log.v("ERROR", "............");
                    if (stackedLayout.empty()) {
                        for (int i = 0; i < word1LinearLayout.getChildCount(); i++) {
                            answer1.append(word1LinearLayout.getChildAt(i).toString());
                            answer2.append(word2LinearLayout.getChildAt(i).toString());
                        }
                        if (wordSet.contains(answer1.toString()) && wordSet.contains(answer2.toString())) {
                            TextView messageBox = (TextView) findViewById(R.id.message_box);
                            messageBox.setText(word1 + " " + word2);
                            answer1.setLength(0);
                            answer2.setLength(0);

                        }
                        //Toast.makeText(context,answer1,Toast.LENGTH_SHORT).show();

                    }

                    /**
                     **
                     **  YOUR CODE GOES HERE
                     **
                     **/
                    return true;
            }
            return false;
        }
    }

    public boolean onStartGame(View view) {

        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/

            if (WORD_LENGTH >7) {
                WORD_LENGTH = 3;
            }

            stackedLayout.clear();
            word2LinearLayout.removeAllViews();
            word1LinearLayout.removeAllViews();


            ArrayList<String> arrayList = wordMap.get(WORD_LENGTH);
            word1 = arrayList.get((int) (Math.random() * (arrayList.size())));
            word2 = arrayList.get((int) (Math.random() * (arrayList.size())));
            StringBuilder result = new StringBuilder();
            // Logic of scrambling word goes here
            for (int i = 0; i < word1.length(); i++) {
                result.append(String.valueOf(word1.charAt(i))).append(String.valueOf(word2.charAt(i)));
            }
            // printing to screen
            TextView messageBox = (TextView) findViewById(R.id.message_box);
            assert messageBox != null;
            messageBox.setText(result);
            //adding to stackedLayout
            String reverse = String.valueOf(result.reverse());
            for (int i = 0; i < result.length(); i++) {
                stackedLayout.push(new LetterTile(context, reverse.charAt(i)));
            }
            WORD_LENGTH++;
        return true;
    }

    public boolean onUndo(View view) {
        /**
         **
         **  YOUR CODE GOES HERE
         **  Also check for filled stack (placedTiles)
         **/

        LetterTile tile =null;
        try {
            tile = placedTiles.pop();

            tile.moveToViewGroup(stackedLayout);
        }catch (EmptyStackException ignored) {}



        return true;
    }



}
