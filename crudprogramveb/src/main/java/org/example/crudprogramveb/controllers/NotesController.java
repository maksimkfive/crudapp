package org.example.crudprogramveb.controllers;

import org.example.crudprogramveb.dao.WebNoteDAO;
import org.example.crudprogramveb.models.WebNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NotesController {
    private final WebNoteDAO webNoteDAO;

    @Autowired
    public NotesController(WebNoteDAO webNoteDAO) {
        this.webNoteDAO = webNoteDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("notes", webNoteDAO.index());
        return "notes/index"; // Возвращает шаблон для отображения списка заметок
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("note", webNoteDAO.show(id));
        return "notes/show"; // Возвращает шаблон для отображения конкретной заметки
    }

    @GetMapping("/new")
    public String newNote(Model model) {
        model.addAttribute("webNote", new WebNote());
        return "notes/new"; // Возвращает шаблон для создания новой заметки
    }

    @PostMapping()
    public String create(@ModelAttribute("webNote") WebNote webNote) {
        webNoteDAO.save(webNote);
        return "redirect:/notes"; // Перенаправляет на список заметок после создания
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("webNote", webNoteDAO.show(id));
        return "notes/edit"; // Возвращает шаблон для редактирования заметки
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("webNote") WebNote webNote,
                         @PathVariable("id") int id) {
        webNoteDAO.update(id,webNote);
        return "redirect:/notes"; // Перенаправляет на список заметок после обновления
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        webNoteDAO.delete(id);
        return "redirect:/notes"; // Перенаправляет на список заметок после удаления
    }
}