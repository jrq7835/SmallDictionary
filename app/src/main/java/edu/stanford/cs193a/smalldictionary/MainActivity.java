package edu.stanford.cs193a.smalldictionary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Map<String, String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dictionary = new HashMap<>();

        Scanner scan = new Scanner(getResources().openRawResource(R.raw.small_dict));

        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split("->");
            dictionary.put(parts[0], parts[1]);
        }

        ListView list = (ListView) findViewById(R.id.myList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );

        list.setAdapter(adapter);

        list.setOnItemClickListener((parent, view, position, id) -> {
            String word = parent.getItemAtPosition(position).toString();
            String defn = dictionary.get(word);

            Toast.makeText(getApplicationContext(), defn, Toast.LENGTH_SHORT).show();
        });
    }
}
