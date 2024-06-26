package cristianmartucci.U5_W2_D3.services;

import cristianmartucci.U5_W2_D3.entities.Author;
import cristianmartucci.U5_W2_D3.entities.Blog;
import cristianmartucci.U5_W2_D3.entities.BlogPaylod;
import cristianmartucci.U5_W2_D3.exceptions.NotFoundException;
import cristianmartucci.U5_W2_D3.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlogService {
    @Autowired
    public AuthorService authorService;

    @Autowired
    public BlogRepository blogRepository;

    public Page<Blog> getAllBlog(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 100) pageSize = 100;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return blogRepository.findAll(pageable);
    }

    public Blog saveBlog(BlogPaylod updateBlog){
        Author author = authorService.findById(updateBlog.getAuthorId());

        Blog blog = new Blog();
        blog.setCategoria(updateBlog.getCategoria());
        blog.setTitolo(updateBlog.getTitolo());
        blog.setCover(updateBlog.getCover());
        blog.setContenuto(updateBlog.getContenuto());
        blog.setTempoDiLettura(updateBlog.getTempoDiLettura());

        blog.setAuthor(author);

        return this.blogRepository.save(blog);
    }

    public Blog findById(UUID id){
        return this.blogRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Blog updateBlog(UUID id, Blog updateBlog){
        Blog blog = this.findById(id);
        blog.setCategoria(updateBlog.getCategoria());
        blog.setTitolo(updateBlog.getTitolo());
        blog.setCover(updateBlog.getCover());
        blog.setContenuto(updateBlog.getContenuto());
        blog.setTempoDiLettura(updateBlog.getTempoDiLettura());
        return this.blogRepository.save(blog);
    }

    public void deleteBlog(UUID id){
        this.blogRepository.delete(this.findById(id));
    }

}
