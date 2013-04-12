package com.forum.services;

import com.forum.domain.Advice;
import com.forum.repository.AdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceService {
    private AdviceRepository adviceRepository;

    @Autowired
    public AdviceService(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    public Advice save(Advice advice) {
        return adviceRepository.save(advice);
    }

    public List<Advice> getAdvices(int questionId) {
        return adviceRepository.getAdvices(questionId);
    }

    public List getQuestionIdAnsweredBy(String userName) {
        return adviceRepository.getQuestionIdAnsweredBy(userName);
    }
}
