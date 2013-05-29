package ua.com.mcgray.web;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.mcgray.dto.ToDoDocument;
import ua.com.mcgray.service.ToDoDocumentService;

/**
 * @author orezchykov
 * @since 23.05.13
 */
@Controller
public class SolrSearchController {

	@Resource
	private ToDoDocumentService toDoDocumentService;

	// @Secured("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	private Page<ToDoDocument> search(@RequestParam String q) {
		return toDoDocumentService.search(q, 0, 10);
	}

	// @Secured("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/searchPage", method = RequestMethod.GET, produces = "text/html")
	private String searchPage(@RequestParam String q, Model model) {
		model.addAttribute("documentList", toDoDocumentService.search(q, 0, 10).getContent());
		return "search-page";
	}
}
