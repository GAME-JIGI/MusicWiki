package com.gamejigi.wiki.web.wiki;

import com.gamejigi.wiki.domain.document.Document;
import com.gamejigi.wiki.repository.document.DocumentRepository;
import com.gamejigi.wiki.service.document.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wiki/")
@RequiredArgsConstructor
public class DocumentController {

    public final DocumentService documentService;

    @GetMapping("{documentId}")
    public String documentPage(
            @PathVariable long documentId,
            Model model
    ) {
        Document document = documentService.getDocumentById(documentId);
        model.addAttribute(document);

        return "wiki/document";
    }

}
