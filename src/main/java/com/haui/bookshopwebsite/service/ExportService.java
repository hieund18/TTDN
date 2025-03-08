package com.haui.bookshopwebsite.service;

import com.haui.bookshopwebsite.dto.BookDto;
import com.haui.bookshopwebsite.dto.CategoryDto;
import com.haui.bookshopwebsite.dto.OrderDTO;
import com.haui.bookshopwebsite.entity.User;

import java.util.List;

public interface ExportService {

    String exportOrderReport(User user, List<OrderDTO> orderDTOList, String keyword);

    String exportCategoryReport(User user, List<CategoryDto> categoryDTOList, String keyword);

    String exportBookReport(User user, List<BookDto> bookDtoList, String keyword);

}
