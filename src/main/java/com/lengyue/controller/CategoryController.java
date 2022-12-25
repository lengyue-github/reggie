package com.lengyue.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lengyue.commons.Result;
import com.lengyue.entity.Category;
import com.lengyue.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 类别控制器
 *
 * @author 陌年
 * @date 2022/12/20
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result<String> save(@RequestBody Category category) {
        categoryService.save(category);
        log.info("分类添加成功！");
        return Result.success("分类添加成功！");
    }

    @GetMapping("/page")
    public Result<Page> queryCategoryByPage(@Param("page") int page, @Param("pageSize") int pageSize) {
        Page<Category> pages = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Category::getSort);
        Page<Category> categoryPage = categoryService.page(pages, wrapper);
        return Result.success(categoryPage);
    }

    @DeleteMapping
    public Result<String> deleteCategoryById(@Param("id") Long id) {
        boolean flag = categoryService.removeById(id);
        if (flag) {
            return Result.success("删除成功！");
        }
        return Result.error("删除失败！");
    }

    @PutMapping
    public Result<String> modifyCategory(@RequestBody Category category) {
        boolean flag = categoryService.updateById(category);
        if (flag) {
            return Result.success("更新成功！");
        }
        return Result.error("更新失败！");
    }
}
