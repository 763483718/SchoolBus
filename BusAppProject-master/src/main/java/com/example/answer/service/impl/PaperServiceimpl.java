package com.example.answer.service.impl;

import com.example.answer.dto.PaperDTO;
import com.example.answer.mapper.PaperMapper;
import com.example.answer.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceimpl implements PaperService{
    @Autowired
    private PaperMapper paperMapper;

    @Override
    public List<PaperDTO>getPaperList(String account){
        List<PaperDTO> paper = paperMapper.getPaperList(account);
        return paper;
    }
    @Override
    public int createPaper(String title,String desc,long id){
        return paperMapper.createPaper(title,desc,id);
    }
    @Override
    public int deletePaper(long libraryID){
        return paperMapper.deletePaper(libraryID);
    }

}
