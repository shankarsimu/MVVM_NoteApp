package com.sns.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INoteRVAdapter {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //recyclerview assign
        rv_view.layoutManager = LinearLayoutManager(this)
        val adapter = NoteAdapter(this, this)
        rv_view.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {

                adapter.updateList(it)
            }


        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)


    }

    fun submitData(view: View) {
        val noteText=edText.text.toString()
        if (noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
        }
    }

}