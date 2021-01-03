package com.sns.noteapp

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

 class NoteAdapter(private val context:Context, private val listener: INoteRVAdapter) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
      val allNotes=ArrayList<Note>()

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.text)
        val deletebutton=itemView.findViewById<ImageView>(R.id.imgButton)

    }

    override

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater .from(context).inflate(R.layout.items,parent,false))
        viewHolder.deletebutton.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

     override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=allNotes[position]
        holder.textView.text=currentNote.name
    }
     fun updateList(newList: List<Note>){
         allNotes.clear()
         allNotes.addAll(newList)

         notifyDataSetChanged()
     }

    override fun getItemCount(): Int {
    return allNotes.size
    }
}

interface INoteRVAdapter{
    fun onItemClicked(note: Note)
}