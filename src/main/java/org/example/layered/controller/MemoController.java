package org.example.layered.controller;

import org.example.layered.dto.MemoRequestDto;
import org.example.layered.dto.MemoResponseDto;
import org.example.layered.entity.Memo;
import org.example.layered.service.MemoSerivce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/memos")
public class MemoController {

    private final MemoSerivce memoSerivce;

    public MemoController(MemoSerivce memoSerivce) {
        this.memoSerivce = memoSerivce;
    }

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {



        return new ResponseEntity<>(memoSerivce.saveMemo(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MemoResponseDto> findAllMemos() {

        return memoSerivce.findAllMemos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {

        return new ResponseEntity<>(memoSerivce.findMemoById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<MemoResponseDto> updateMemo(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto) {

        return new ResponseEntity<>(memoSerivce.updateMemo(id,dto.getTitle(),dto.getContents()), HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ){
        return new ResponseEntity<>(memoSerivce.updateTitle(id, dto.getTitle(), dto.getContents()), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {



        memoSerivce.deleteMemo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
