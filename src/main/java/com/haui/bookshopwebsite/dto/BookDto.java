package com.haui.bookshopwebsite.dto;

import com.haui.bookshopwebsite.entity.Book;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Book}
 */
@Data
@Value
public class BookDto implements Serializable {
    String title;
    Double totalRevenue;

}