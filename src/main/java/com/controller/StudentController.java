package com.controller;

import com.domain.Book;
import com.domain.BookLate;
import com.domain.Student;
import com.domain.StudentBook;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.IBookService;
import com.service.IStuBookService;
import com.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author 刘磊
 * @version 1.0
 */
@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private IStuBookService stuBookService;

    /**
     * 查询所有学生
     *
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                @RequestParam(name = "size", defaultValue = "4", required = false) int size) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (size == 0) {
            size = 4;
        }
        List<Student> studentList = studentService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(studentList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("/student/student-list");
        return mv;
    }

    /**
     * 添加学生
     *
     * @param student
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public ModelAndView save(Student student) throws Exception {
        ModelAndView mv = new ModelAndView();
        studentService.save(student);
        String name = student.getName();
        mv.setViewName("redirect:/students/findByName?name=" + URLEncoder.encode(name, "UTF-8"));
        return mv;
    }

    /**
     * 删除学生
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(name = "id") Integer id, @RequestParam(name = "page") Integer page,
                               @RequestParam(name = "size") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        studentService.delete(id);
        mv.setViewName("redirect:/students/findAll?page=" + page + "&size=" + size);
        return mv;
    }

    /**
     * 跳转到更新页面
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) throws Exception {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "/student/student-update";
    }

    /**
     * 更新页面
     *
     * @param student
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public String update(Student student) throws Exception {
        studentService.update(student);
        return "redirect:/students/findAll";
    }

    /**
     * 学生个人信息查看
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/showInfo")
    public String showInfo(Integer id, Model model) throws Exception {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "/student/show-info";
    }

    /**
     * 学生页面借书，查看所有
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAllBook")
    public ModelAndView findAllBook(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page, @RequestParam(name = "size", defaultValue = "4", required = false) Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Book> books = bookService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(books);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("/student/select-book");
        return mv;
    }

    /**
     * 根据类别查询
     *
     * @param type
     * @return
     */
    @RequestMapping("/findByType")
    public ModelAndView findByType(@RequestParam(name = "type") String type) {
        ModelAndView mv = new ModelAndView();
        List<Book> books = bookService.findByType(type);
        PageInfo pageInfo = new PageInfo(books);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("/student/select-book");
        return mv;
    }

    /**
     * 根据图书名字查找图书
     *
     * @param bookName
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/findByBName")
    public String findByBName(String bookName, Model model) throws Exception {
        List<Book> book = bookService.findByName(bookName);
        String error = "";
        if (book.size() == 0) {
            error = "未找到!";
        }
        model.addAttribute("error", error);
        model.addAttribute("books", book);
        return "/student/select-book";
    }

    /**
     * 借书操作 并重定向到当前借阅界面
     *
     * @param studentId
     * @param bookId
     * @return
     * @throws Exception
     */
    @RequestMapping("/borrowBook")
    public ModelAndView borrowBook(@RequestParam(name = "sid") Integer studentId, @RequestParam(name = "bid") Integer bookId
    ) throws Exception {
        List<StudentBook> lateBook = stuBookService.lateBook(studentId);//查找当前有无超期图书
        String text = "";
        ModelAndView mv = new ModelAndView();
        if (lateBook.size() != 0) {
            text = "你当前有未归还的图书，请先进行归还！";
            mv.addObject("text", text);
            mv.setViewName("redirect:/students/findLate?id=" + studentId);
        } else {
            Student student = studentService.findById(studentId);
            Book book = bookService.findById(bookId);
            StudentBook studentBook = new StudentBook();
            Date deadTime = new Date();
            Date borrowTime = new Date();
            deadTime.setTime(borrowTime.getTime() + 30L * 24 * 60 * 60 * 1000);//截止时间为一个月
            studentBook.setBookId(book.getBid());
            studentBook.setStuId(student.getSid());
            studentBook.setBorrowTime(borrowTime);
            studentBook.setDeadTime(deadTime);
            stuBookService.save(studentBook);
            mv.setViewName("redirect:/students/nowBorrow?id=" + studentId);
        }
        return mv;
    }

    /**
     * 查看当前借阅
     *
     * @param studentId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/nowBorrow")
    public String nowBorrow(@RequestParam(name = "id") Integer studentId, Model model, @RequestParam(name = "text", required = false) String text) throws Exception {
        String message = "";
        List<StudentBook> studentBookList = stuBookService.findBookByStuId(studentId);
        model.addAttribute("studentBookList", studentBookList);
        if (studentBookList.size() == 0) {
            message = "你还没有借任何书";
        }
        model.addAttribute("message", message);
        model.addAttribute("text", text);
        return "/student/now-borrow";
    }

    /**
     * 还书操作
     *
     * @param studentId
     * @param bookId
     * @param stuBookId
     * @return
     * @throws Exception
     */
    @RequestMapping("/returnBook")
    public ModelAndView returnBook(@RequestParam(name = "sid") Integer studentId, @RequestParam(name = "bid", required = true) Integer bookId
            , @RequestParam(name = "SBId") Integer stuBookId) throws Exception {
        ModelAndView mv = new ModelAndView();
        StudentBook studentBook = new StudentBook();
        studentBook.setId(stuBookId);
        studentBook.setStuId(studentId);
        studentBook.setBookId(bookId);
        Date returnTime = new Date();//还书时间
        studentBook.setReturnTime(returnTime);
        stuBookService.update(studentBook);
        System.out.println("执行了....");
        System.out.println(studentId);
        mv.setViewName("redirect:/students/nowBorrow?id=" + studentId);
        return mv;
    }

    /**
     * 更新密码
     *
     * @param id
     * @param pwd
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatePassword")
    public String updatePassword(@RequestParam(name = "id") Integer id, @RequestParam(name = "pwd2") String pwd)
            throws Exception {
        Student student = new Student();
        student.setSid(id);
        student.setPassword(pwd);
        studentService.updatePassword(student);
        return "main";
    }

    /**
     * 借阅历史
     *
     * @param studentId
     * @param model
     * @return
     */
    @RequestMapping("/history")
    public String history(@RequestParam(name = "id") Integer studentId, Model model) throws Exception {
        List<StudentBook> studentBooks = stuBookService.findHistoryByStuId(studentId);
        String text = null;
        if (studentBooks.size() == 0) {
            text = "未找到你有任何借阅历史！";
        }
        model.addAttribute("text", text);
        model.addAttribute("studentBooks", studentBooks);
        return "/student/history";
    }

    /**
     * 续借
     *
     * @param stuBookId
     * @return
     * @throws Exception
     */
    @RequestMapping("/xuJie")
    public ModelAndView xuJie(@RequestParam(name = "id") Integer stuBookId) throws Exception {
        ModelAndView mv = new ModelAndView();
        String text = "";
        StudentBook studentBook = stuBookService.findById(stuBookId);//查找记录
        Date deadTime = studentBook.getDeadTime();//获取截止时间
        deadTime.setTime(deadTime.getTime() + 30L * 24 * 60 * 60 * 1000);//延长一个月
        Date borrowTime = studentBook.getBorrowTime();//获取借阅时间
        borrowTime.setTime(borrowTime.getTime() + 4 * 30L * 24 * 60 * 60 * 1000);
        if (deadTime.getTime() == borrowTime.getTime()) {
            System.out.println("你已续借两次，不能再进行续借");
            text = "你已续借两次，不能再进行续借";
        } else {
            studentBook.setDeadTime(deadTime);
            stuBookService.updateXuJie(studentBook);
        }
        Integer studentId = studentBook.getStuId();
        mv.setViewName("redirect:/students/nowBorrow?id=" + studentId + "&text=" + URLEncoder.encode(text, "UTF-8"));
        return mv;
    }

    /**
     * 通过学生名字查询
     *
     * @param name
     * @return
     * @throws Exception
     */
    @PostMapping("/findByName")
    public ModelAndView findByName(@RequestParam(name = "name") String name) throws Exception {
        ModelAndView mv = new ModelAndView();
        String message;
        List<Student> student = studentService.findByName(name);
        if (student.size() == 0) {
            message = "未找到啊！";
            mv.addObject("message", message);
        }
        PageHelper.startPage(1, 4);
        PageInfo pageInfo = new PageInfo(student);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("/student/student-list");
        return mv;
    }

    /**
     * 查看超期图书
     *
     * @param studentId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findLate")
    public ModelAndView findLate(@RequestParam(name = "id") Integer studentId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Date date = new Date();
        String message = "";
        List<StudentBook> studentBooks1 = stuBookService.lateBook(studentId);
        List<BookLate>bookLates = new ArrayList<>();
        /*List<StudentBook> studentBooks=stuBookService.findBookByStuId(studentId);//获取当前借阅图书
        List<StudentBook> studentBooks1=new ArrayList<>();
        for(StudentBook studentBook:studentBooks){
                    if(studentBook.getDeadTime().getTime()-date.getTime()<0){
                        studentBooks1.add(studentBook);}
        }*/
        if (studentBooks1.size() == 0) {
            message = "你当前没有超期图书!";
        }
        System.out.println("你好!!!" + message);
        for (StudentBook studentBook:studentBooks1){
            BookLate bookLate = new BookLate();
            bookLate.setBorrowTime(studentBook.getBorrowTime());
            bookLate.setDeadTime(studentBook.getDeadTime());
            String bookName = bookService.findById(studentBook.getBookId()).getName();
            bookLate.setBookName(bookName);
            String stuName = studentService.findById(studentBook.getStuId()).getName();
            bookLate.setName(stuName);
            bookLates.add(bookLate);
        }
//        mv.addObject("studentBook", studentBooks1);
        mv.addObject("bookLate",bookLates);
        mv.addObject("message", message);


        mv.setViewName("/student/late");
        return mv;
    }
}
