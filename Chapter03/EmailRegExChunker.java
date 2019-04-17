package com.packt.javanlp.cookbook.chapter4;

import com.aliasi.chunk.RegExChunker;

public class EmailRegExChunker extends RegExChunker {
    private final static String EMAIL_REGULAR_EXPRESSION = 
    		"[A-Za-z0-9](([_\\.\\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\\.\\-]?[a-zA-Z0-9]+)*)\\.([A-Za-z]{2,})";

        private final static String CHUNK_TYPE = "EMAIL";
        private final static double CHUNK_SCORE = 1.0;
        
        public EmailRegExChunker() {
            super(EMAIL_REGULAR_EXPRESSION,CHUNK_TYPE,CHUNK_SCORE);
        }
}
