package ua.com.mcgray.repository.solr;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;
import ua.com.mcgray.dto.ToDoDocument;

/**
 * @author orezchykov
 * @since 23.05.13
 */
public interface ToDoDocumentRepository extends SolrCrudRepository<ToDoDocument, String> {

	List<ToDoDocument> findByContent(String searchString);

	Page<ToDoDocument> findByContent(String searchString, Pageable pageable);

}
