package org.example.crudprogramveb.dao;

import org.example.crudprogramveb.models.WebNote;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class WebNoteDAO {
    private static int NOTES_COUNT;
    private List<WebNote> notes;

    public WebNoteDAO() {
        notes = new ArrayList<WebNote>();

        // Добавление нескольких тестовых заметок в список при инициализации DAO
        notes.add(new WebNote(++NOTES_COUNT, "test1"));
        notes.add(new WebNote(++NOTES_COUNT, "test2"));
        notes.add(new WebNote(++NOTES_COUNT, "test3"));
        notes.add(new WebNote(++NOTES_COUNT, "test4"));
        notes.add(new WebNote(++NOTES_COUNT, "test5"));
    }

    // Возвращает список всех заметок
    public List<WebNote> index() {
        return notes;
    }

    // Возвращает заметку по указанному идентификатору
    public WebNote show(int id) {
        return notes.stream().filter(n -> n.getId() == id).findAny().orElse(null);
    }

    // Сохраняет новую заметку
    public void save(WebNote webNote) {
        webNote.setId(++NOTES_COUNT);
        notes.add(webNote);
    }

    // Обновляет заметку с указанным идентификатором
    public void update(int id, WebNote updatedNote) {
        WebNote toUpdateNote = show(id);
        toUpdateNote.setNote(updatedNote.getNote());
    }

    // Удаляет заметку с указанным идентификатором
    public void delete(int id) {
        notes.removeIf(n -> n.getId() == id);
    }
}