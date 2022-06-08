package com.app.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var notePosition = POSITION_NOT_SET
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter<CourseInfo>(this,android.R.layout.simple_spinner_item,DataManager.courses.values.toList())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        notePosition = intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)

        if(notePosition != POSITION_NOT_SET)
            displayNote()

    }

    private fun displayNote() {
         val note = DataManager.notes[notePosition]

        titleText.setText(note.title)
        editTextTextPersonName2.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spinner.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menuSettings -> true

            R.id.next -> {
                moveText()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        if(notePosition >= DataManager.notes.lastIndex)
        {
            val menuItem = menu?.findItem(R.id.next)

            if(menuItem != null){
                menuItem.icon = getDrawable(R.drawable.ic_baseline_arrow_back_ios_24)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun moveText() {
        notePosition++

        displayNote()
        invalidateOptionsMenu()
    }
}