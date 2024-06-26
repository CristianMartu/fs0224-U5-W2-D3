package cristianmartucci.U5_W2_D3.controllers;

import cristianmartucci.U5_W2_D3.entities.Blog;
import cristianmartucci.U5_W2_D3.entities.BlogPayload;
import cristianmartucci.U5_W2_D3.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    private Page<Blog> getAllBlog(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return this.blogService.getAllBlog(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Blog saveBlog(@RequestBody BlogPayload blog){
        return this.blogService.saveBlog(blog);
    }

    @GetMapping("/{blogId}")
    private Blog findById(@PathVariable UUID blogId){
        return this.blogService.findById(blogId);
    }

    @PutMapping("/{blogId}")
    private Blog updateBlog(@PathVariable UUID blogId, @RequestBody BlogPayload blog){
        return this.blogService.updateBlog(blogId, blog);
    }

    @DeleteMapping("/{blogId}")
    private void deleteBlog(@PathVariable UUID blogId){
        this.blogService.deleteBlog(blogId);
    }
}
