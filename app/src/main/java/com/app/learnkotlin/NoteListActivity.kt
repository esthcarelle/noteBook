package com.app.learnkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.learnkotlin.DataManager.notes
import com.app.learnkotlin.databinding.ActivityNoteListBinding

class NoteListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNoteListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.floatingActionButton.setOnClickListener {
            val  intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
//        listView.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, notes)
//        listView.setOnItemClickListener{ _, _, position, _ ->
//            val intent = Intent(this,MainActivity::class.java)
//            intent.putExtra(EXTRA_NOTE_POSITION,position)
//
//            startActivity(intent)
//        }
        binding.listView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteRecyclerAdapter(this,notes)
        binding.listView.adapter = adapter


    }

    override fun onResume() {
        super.onResume()
        binding.listView.adapter?.notifyDataSetChanged()
    }
}