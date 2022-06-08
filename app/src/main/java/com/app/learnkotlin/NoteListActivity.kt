package com.app.learnkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.app.learnkotlin.DataManager.notes
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        floatingActionButton.setOnClickListener { view ->
            val  intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        listView.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, notes)
        listView.setOnItemClickListener{parent,view,position,id ->
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(EXTRA_NOTE_POSITION,position)

            startActivity(intent)
        }
    }
}