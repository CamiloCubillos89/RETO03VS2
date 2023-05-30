package com.reto03.grupog6.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto03.grupog6.Entities.Score;
import com.reto03.grupog6.Services.ScoreService;

@RestController
@RequestMapping("/api/Score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/all")
    public List<Score> getAll() {
        return scoreService.getAll();
    }

    @PostMapping("/save")
    public Score addScore(@RequestBody Score score) {
        return scoreService.addScore(score);
    }

    @PutMapping("/update")
    public Score updScore(@RequestBody Score score) {
        return scoreService.updScore(score);
    }

    @DeleteMapping("/{id}")
    public void delScore(@PathVariable Integer id) {
         scoreService.delScore(id);
    }

    @GetMapping("/{id}")
    public Score getScore(@PathVariable Integer id) {
        return scoreService.getScore(id);
    }

}
