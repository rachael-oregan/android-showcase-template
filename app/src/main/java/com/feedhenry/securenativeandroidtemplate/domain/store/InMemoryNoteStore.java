package com.feedhenry.securenativeandroidtemplate.domain.store;

import com.feedhenry.securenativeandroidtemplate.domain.callbacks.Callback;
import com.feedhenry.securenativeandroidtemplate.domain.models.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An in-memory storage for the notes.
 */

public class InMemoryNoteStore implements NoteDataStore {

    private Map<String, Note> inMemoryStore = new HashMap<String, Note>();

    @Override
    public void createNote(Note note, Callback<Note> callback) {
        if (!inMemoryStore.containsKey(note.getId())) {
            inMemoryStore.put(note.getId(), note);
        }
        callback.onSuccess(note);
    }

    @Override
    public void updateNote(Note note, Callback<Note> callback) {
        inMemoryStore.put(note.getId(), note);
        callback.onSuccess(note);
    }

    @Override
    public void deleteNote(Note note, Callback<Note> callback) {
        inMemoryStore.remove(note.getId());
        callback.onSuccess(note);
    }

    @Override
    public void readNote(String noteId, Callback<Note> callback) {
        Note note = inMemoryStore.get(noteId);
        callback.onSuccess(note);
    }

    @Override
    public void listNotes(Callback<List<Note>> callback) {
        List<Note> notes =  new ArrayList<Note>();
        notes.addAll(inMemoryStore.values());
        callback.onSuccess(notes);
    }
}