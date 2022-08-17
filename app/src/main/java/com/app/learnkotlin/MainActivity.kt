package com.app.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.app.learnkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var notePosition = POSITION_NOT_SET
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ArrayAdapter<CourseInfo>(this,android.R.layout.simple_spinner_item,DataManager.courses.values.toList())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        notePosition = savedInstanceState?.getInt(EXTRA_NOTE_POSITION, POSITION_NOT_SET) ?: intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)

        if(notePosition != POSITION_NOT_SET)
            displayNote()
        else{
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_NOTE_POSITION,notePosition)
    }
    private fun displayNote() {
         val note = DataManager.notes[notePosition]

        binding.titleText.setText(note.title)
        binding.editTextTextPersonName2.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        binding.spinner.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {

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

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    /**
     * Save note in db
     */
    private fun saveNote() {
       val note = DataManager.notes[notePosition]
        note.title = binding.titleText.text.toString()
        note.text = binding.editTextTextPersonName2.text.toString()
        note.course = binding.spinner.selectedItem as CourseInfo

    }

    private fun moveText() {
        notePosition++

        displayNote()
        invalidateOptionsMenu()
    }
}