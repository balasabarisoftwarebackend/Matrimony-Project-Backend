package com.example.MatrimonyProject.utilits;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> content;       // the paginated data
    private int pageNumber;        // current page
    private int pageSize;          // size per page
    private long totalElements;    // total records in DB
    private int totalPages;        // total pages
    private boolean last;          // is this the last page?
}
