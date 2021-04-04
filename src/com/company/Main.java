package com.company;

import java.io.*;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Reader reader = null;
        Writer writer;
        StringBuilder buffer = new StringBuilder(256);
        HashMap<String, Word> map = new HashMap<>();
        Character c;
        String temp;
        File output = new File("output");
        int word_num = 0;
        try
        {
            reader = new InputStreamReader(new FileInputStream("input"));
            while (reader.ready()){
                c = (char)reader.read();
                if (Character.isLetterOrDigit(c))
                    buffer.append(c);
                else if(!buffer.isEmpty()) {
                    temp = new String(buffer);
                    if (map.containsKey(temp))
                        map.get(temp).inc();
                    else {
                        map.put(temp, new Word(buffer));
                        word_num++;
                    }
                    buffer.delete(0,buffer.length());
                }
            }
            Word[] list = map.values().toArray(new Word[0]);
            Arrays.sort(list);
            System.out.println(output.createNewFile());
            writer = new OutputStreamWriter(new FileOutputStream(output));
            for(int i = 1; i <= list.length; ++i){
                Word curr = list[list.length-i];
                writer.write(curr.getWord()+','+curr.getCount()/word_num+',');
            }

        }
        catch (IOException e)
        {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally
        {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}