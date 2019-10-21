package com.gbh.demo.strategy;

import org.springframework.stereotype.Component;

@Component
public class DocumentStrategyFactory {
    public DocumentStrategy createDocument(String strategy) {
        DocumentStrategy resultStrategy = null;

        if("HTML".equals(strategy)) {
            resultStrategy = new HTMLDocumentStrategy();
        }
        else if("PLAIN".equals(strategy)) {
            resultStrategy = new PlainTexDocumentStrategy();
        }
        return resultStrategy;
    }
}
