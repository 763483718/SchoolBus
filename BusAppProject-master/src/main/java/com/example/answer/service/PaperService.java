package com.example.answer.service;

import com.example.answer.dto.PaperDTO;

import java.util.List;

public interface PaperService {
    // 获取当前管理的试卷信息
    public List<PaperDTO> getPaperList(String account);

    // 创建试卷信息
    public int createPaper(String title,String desc,long id);

    // 删除试卷信息
    public int deletePaper(long libraryID);

}
