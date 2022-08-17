package com.app.learnkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerAdapter(private val context:Context,private val notes: List<NoteInfo>): RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView : View = layoutInflater.inflate(R.layout.note_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes.get(position)
        holder.textCourse?.text = note.text
        holder.textView2?.text = note.course?.courseTitle
        holder.notePosition = position
    }

    override fun getItemCount() =  notes.size

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        val textView2 = itemView?.findViewById<TextView?>(R.id.textView2)
        val textCourse = itemView?.findViewById<TextView?>(R.id.textView)
        var notePosition = 0

        init{
            itemView?.setOnClickListener{
                val intent = Intent(context,MainActivity::class.java)
                intent.putExtra(EXTRA_NOTE_POSITION,notePosition)

                context.startActivity(intent)
            }
        }
    }

}