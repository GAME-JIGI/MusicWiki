package com.gamejigi.wiki.service.document;

import com.gamejigi.wiki.domain.document.Document;
public interface DocumentService {
    Document getByTitle(String title);
}
