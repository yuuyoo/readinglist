package cn.technotes.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 功能：读书列表控制
 *
 * @author：DJL
 * @create：2020/2/29 17:08
 * @version：2020 Version：1.0
 */
@Controller
@RequestMapping("/")
@ConfigurationProperties(prefix = "amazon")
public class ReadingListController {

    private String associateId;

    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String readersBooks(Reader reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", associateId);
        }

        return "readingList";
    }

    /**
     * 根据读者获取读书列表
     * @param reader
     * @param model
     * @return
     */
//    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
//    public String readersBooks(@PathVariable("reader") String reader, Model model) {
//        List<Book> readingList = readingListRepository.findByReader(reader);
//        if (readingList != null) {
//            model.addAttribute("books", readingList);
//        }
//
//        return "readingList";
//    }


    /**
     * 添加读书列表
     *
     * @param reader
     * @param book
     * @return
     */
//    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
//    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
//        book.setReader(reader);
//        readingListRepository.save(book);
//        return "redirect:/{reader}";
//    }
    @RequestMapping(method = RequestMethod.POST)
    public String addToReadingList(Reader reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/";

    }
}
